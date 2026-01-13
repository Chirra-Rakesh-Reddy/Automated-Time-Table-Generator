package com.timetable.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.OutputStream;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timetable.dao.TimetableDAO;
import com.timetable.model.Timetable;
import com.timetable.model.TimetableEntry;

@WebServlet("/timetables/*")
public class TimetablesController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TimetablesController.class);
	    private TimetableDAO timetableDAO = new TimetableDAO();
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list";
	        
	        try {
	            switch (action) {
	                case "/list":
	                    listTimetables(request, response);
	                    break;
	                case "/view":
	                    viewTimetable(request, response);
	                    break;
	                case "/export":
	                    exportTimetable(request, response);
	                    break;
	                default:
	                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	            }
	        } catch (Exception e) {
	            logger.error("Error in TimetablesController: " + e.getMessage(), e);
	            request.setAttribute("error", "Error processing request: " + e.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
	    }
    
    private void listTimetables(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        List<Timetable> timetables = timetableDAO.getAllGeneratedTimetables();
        request.setAttribute("timetables", timetables);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
    
    private void viewTimetable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        try {
            String batchIdParam = request.getParameter("batchId");
            if (batchIdParam == null || batchIdParam.isEmpty()) {
                throw new ServletException("Batch ID parameter is missing");
            }
            
            // Handle case where parameter might contain additional query string
            if (batchIdParam.contains("?")) {
                batchIdParam = batchIdParam.substring(0, batchIdParam.indexOf("?"));
            }
            
            int batchId = Integer.parseInt(batchIdParam);
            logger.info("Fetching timetable for batch ID: " + batchId);
            
            List<TimetableEntry> entries = timetableDAO.getTimetableDetails(batchId);
            
            // Get any warning/error messages from redirects
            String warning = request.getParameter("warning");
            String error = request.getParameter("error");
            
            if (warning != null) {
                request.setAttribute("warning", URLDecoder.decode(warning, "UTF-8"));
            }
            if (error != null) {
                request.setAttribute("error", URLDecoder.decode(error, "UTF-8"));
            }
            
            if (entries == null || entries.isEmpty()) {
                request.setAttribute("warning", "No timetable entries found for batch ID: " + batchId);
            } else {
                String[] timeSlots = {"09:00:00", "10:00:00", "11:00:00", "01:00:00", "02:00:00"};
                String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
                
                request.setAttribute("timeSlots", timeSlots);
                request.setAttribute("daysOfWeek", daysOfWeek);
                request.setAttribute("entries", entries);
            }
            
            request.getRequestDispatcher("/details.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid Batch ID format", e);
        }
    }
    
    private void exportTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException {
        
        String batchIdParam = request.getParameter("batchId");
        String type = request.getParameter("type");
        
        logger.info("Export request - batchId: {}, type: {}", batchIdParam, type);
        
        if (batchIdParam == null || batchIdParam.isEmpty() || type == null || type.isEmpty()) {
            logger.error("Missing parameters in export request");
            sendErrorRedirect(request, response, "Missing+required+parameters");
            return;
        }
        
        try {
            int batchId = Integer.parseInt(batchIdParam);
            
            if (batchId <= 0) {
                logger.error("Invalid batch ID: {}", batchId);
                sendErrorRedirect(request, response, "Invalid+batch+ID");
                return;
            }
            
            logger.info("Fetching timetable for batch ID: {}", batchId);
            List<TimetableEntry> entries = timetableDAO.getTimetableDetails(batchId);
            
            if (entries == null || entries.isEmpty()) {
                logger.warn("No timetable entries found for batch ID: {}", batchId);
                sendErrorRedirect(request, response, "No+timetable+found+for+batch+ID+" + batchId);
                return;
            }
            
            String batchName = entries.get(0).getBatch().getBatchName();
            
            switch (type.toLowerCase()) {
                case "pdf":
                    exportAsPdf(entries, batchName, response);
                    break;
                case "excel":
                    exportAsExcel(entries, batchName, response);
                    break;
                default:
                    logger.error("Invalid export type: {}", type);
                    sendErrorRedirect(request, response, "Invalid+export+type");
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid batch ID format: {}", batchIdParam);
            sendErrorRedirect(request, response, "Invalid+batch+ID+format");
        }
    }

    private void sendErrorRedirect(HttpServletRequest request, HttpServletResponse response, String message) 
            throws IOException {
        String referer = request.getHeader("referer");
        if (referer != null) {
            // Clean the referer URL by removing existing parameters
            String cleanReferer = referer.split("\\?")[0];
            response.sendRedirect(cleanReferer + "?batchId=" + 
                request.getParameter("batchId") + "&error=" + message);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                URLDecoder.decode(message, "UTF-8"));
        }
    }
    // Helper method to get parameters case-insensitively
    

    private void exportAsPdf(List<TimetableEntry> entries, String batchName, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=timetable_" + batchName + ".pdf");
        
        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document(PageSize.A4.rotate()); // Landscape orientation
            PdfWriter.getInstance(document, out);
            document.open();
            
            // Title
            Paragraph title = new Paragraph("Timetable for " + batchName,
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);
            
            // Create PDF table
            PdfPTable table = new PdfPTable(7); // Time + 6 days
            table.setWidthPercentage(100);
            
            // Table headers
            String[] headers = {"Time/Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(220, 220, 220));
                table.addCell(cell);
            }
            
            // Time slots
            String[] timeSlots = {"09:00:00", "10:00:00", "11:00:00", "01:00:00", "02:00:00"};
            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            
            for (String time : timeSlots) {
                // Time column
                String timeDisplay = formatTimeSlot(time);
                table.addCell(new Phrase(timeDisplay));
                
                // Day columns
                for (String day : daysOfWeek) {
                    StringBuilder cellContent = new StringBuilder();
                    for (TimetableEntry entry : entries) {
                        if (entry.getSlot().getDayOfWeek().equals(day) && 
                            entry.getSlot().getStartTime().equals(time)) {
                            cellContent.append(entry.getSubject().getSubjectName()).append("\n")
                                     .append("Code: ").append(entry.getSubject().getSubjectCode()).append("\n")
                                     .append("Teacher: ").append(entry.getTeacher().getTeacherName()).append("\n")
                                     .append("Room: ").append(entry.getClassroom().getBuilding())
                                     .append(" ").append(entry.getClassroom().getRoomNumber())
                                     .append("\n");
                        }
                    }
                    PdfPCell cell = new PdfPCell(new Phrase(
                        cellContent.length() > 0 ? cellContent.toString() : "Free",
                        FontFactory.getFont(FontFactory.HELVETICA, 10)
                    ));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setMinimumHeight(40);
                    table.addCell(cell);
                }
            }
            
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }

    private void exportAsExcel(List<TimetableEntry> entries, String batchName, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=timetable_" + batchName + ".xlsx");
        
        try (Workbook workbook = new XSSFWorkbook();
             OutputStream out = response.getOutputStream()) {
            
            Sheet sheet = workbook.createSheet("Timetable");
            sheet.setDisplayGridlines(true);
            
            // Set column widths
            sheet.setColumnWidth(0, 4000); // Time column
            for (int i = 1; i < 7; i++) {
                sheet.setColumnWidth(i, 8000); // Day columns
            }
            
            // Title row
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Timetable for " + batchName);
            
            // Merge title cells
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            
            // Header row
            Row headerRow = sheet.createRow(1);
            String[] headers = {"Time/Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            CellStyle headerStyle = createHeaderStyle(workbook);
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Time slots and days
            String[] timeSlots = {"09:00:00", "10:00:00", "11:00:00", "01:00:00", "02:00:00"};
            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            
            int rowNum = 2;
            CellStyle cellStyle = createCellStyle(workbook);
            
            for (String time : timeSlots) {
                Row row = sheet.createRow(rowNum++);
                
                // Time column
                row.createCell(0).setCellValue(formatTimeSlot(time));
                
                // Day columns
                for (int i = 0; i < daysOfWeek.length; i++) {
                    Cell cell = row.createCell(i + 1);
                    StringBuilder cellContent = new StringBuilder();
                    
                    for (TimetableEntry entry : entries) {
                        if (entry.getSlot().getDayOfWeek().equals(daysOfWeek[i]) && 
                            entry.getSlot().getStartTime().equals(time)) {
                            cellContent.append(entry.getSubject().getSubjectName()).append("\n")
                                      .append("Code: ").append(entry.getSubject().getSubjectCode()).append("\n")
                                      .append("Teacher: ").append(entry.getTeacher().getTeacherName()).append("\n")
                                      .append("Room: ").append(entry.getClassroom().getBuilding())
                                      .append(" ").append(entry.getClassroom().getRoomNumber())
                                      .append("\n");
                        }
                    }
                    
                    cell.setCellValue(cellContent.length() > 0 ? cellContent.toString() : "Free");
                    cell.setCellStyle(cellStyle);
                }
            }
            
            workbook.write(out);
        }
    }

    private String formatTimeSlot(String time) {
        switch (time) {
            case "09:00:00": return "09:00 - 10:00";
            case "10:00:00": return "10:00 - 11:00";
            case "11:00:00": return "11:00 - 12:00";
            case "01:00:00": return "01:00 - 02:00";
            case "02:00:00": return "02:00 - 03:00";
            default: return time;
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    
}