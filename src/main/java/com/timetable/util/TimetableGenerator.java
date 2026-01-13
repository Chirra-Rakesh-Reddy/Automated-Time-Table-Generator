package com.timetable.util;

import com.timetable.dao.*;
import com.timetable.model.*;
import java.util.*;

public class TimetableGenerator {
    private TeacherDAO teacherDAO;
    private SubjectDAO subjectDAO;
    private BatchDAO batchDAO;
    private ClassroomDAO classroomDAO;
    private TimetableSlotDAO slotDAO;
    private TimetableDAO timetableDAO;
    
    public TimetableGenerator() {
        teacherDAO = new TeacherDAO();
        subjectDAO = new SubjectDAO();
        batchDAO = new BatchDAO();
        classroomDAO = new ClassroomDAO();
        slotDAO = new TimetableSlotDAO();
        timetableDAO = new TimetableDAO();
    }
    
    public boolean generateTimetable(int batchId) {
        try {
            // Clear existing timetable for this batch
            timetableDAO.deleteTimetableForBatch(batchId);
            
            Batch batch = batchDAO.getBatchById(batchId);
            if (batch == null) {
                return false;
            }
            
            List<Subject> subjects = subjectDAO.getSubjectsByDepartment(batch.getDeptId());
            List<Teacher> teachers = teacherDAO.getTeachersByDepartment(batch.getDeptId());
            List<Classroom> classrooms = classroomDAO.getAllClassrooms();
            List<TimetableSlot> slots = slotDAO.getAllSlots();
            
            if (subjects.isEmpty() || teachers.isEmpty() || classrooms.isEmpty() || slots.isEmpty()) {
                return false;
            }
            
            // Create a list of all required lectures
            List<Lecture> lectures = new ArrayList<>();
            for (Subject subject : subjects) {
                int hoursPerWeek = subject.getHoursPerWeek();
                for (int i = 0; i < hoursPerWeek; i++) {
                    lectures.add(new Lecture(subject));
                }
            }
            
            // Assign teachers to subjects they can teach
            Map<Subject, Teacher> subjectTeacherMap = new HashMap<>();
            for (Subject subject : subjects) {
                List<Teacher> subjectTeachers = new ArrayList<>();
                for (Teacher teacher : teachers) {
                    if (teacherDAO.getSubjectsByTeacher(teacher.getTeacherId()).stream()
                        .anyMatch(s -> s.getSubjectId() == subject.getSubjectId())) {
                        subjectTeachers.add(teacher);
                        System.out.println(subject.toString());
                    }
                }
                
                if (!subjectTeachers.isEmpty()) {
                    // Assign a random teacher for this subject
                    Collections.shuffle(subjectTeachers);
                    subjectTeacherMap.put(subject, subjectTeachers.get(0));
                }
            }
            
            // Shuffle resources to randomize assignments
            Collections.shuffle(lectures);
            Collections.shuffle(slots);
            
            System.out.println(subjectTeacherMap.toString());
            // Assign lectures to slots
            for (Lecture lecture : lectures) {
                Subject subject = lecture.getSubject();
                Teacher teacher = subjectTeacherMap.get(subject);
                System.out.println(lecture.toString());

                
                if (teacher == null) {
                    continue; // Skip if no teacher available for this subject
                }
                
                boolean assigned = false;
                for (TimetableSlot slot : slots) {
                    if (isSlotAvailable(slot.getSlotId(), batchId, teacher.getTeacherId(), classrooms)) {
                        Classroom room = findAvailableClassroom(slot.getSlotId(), classrooms);
                        System.out.println(room.toString());
                        if (room != null) {
                            TimetableEntry entry = new TimetableEntry();
                            entry.setSlotId(slot.getSlotId());
                            entry.setBatchId(batchId);
                            entry.setTeacherId(teacher.getTeacherId());
                            entry.setSubjectId(subject.getSubjectId());
                            entry.setRoomId(room.getRoomId());
                           
                            if (timetableDAO.addTimetableEntry(entry)) {
                                assigned = true;
                                break;
                            }
                        }
                    }
                }
                
                if (!assigned) {
                    System.out.println("Failed to assign lecture: " + subject.getSubjectName());
                }
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean isSlotAvailable(int slotId, int batchId, int teacherId, List<Classroom> classrooms) {
        // Check if batch is free in this slot
        if (timetableDAO.isBatchBusy(slotId, batchId)) {
            return false;
        }
        
        // Check if teacher is free in this slot
        if (timetableDAO.isTeacherBusy(slotId, teacherId)) {
            return false;
        }
        
        // Check if any classroom is available in this slot
        for (Classroom room : classrooms) {
            if (!timetableDAO.isClassroomBusy(slotId, room.getRoomId())) {
                return true;
            }
        }
        
        return false;
    }
    
    private Classroom findAvailableClassroom(int slotId, List<Classroom> classrooms) {
        for (Classroom room : classrooms) {
            if (!timetableDAO.isClassroomBusy(slotId, room.getRoomId())) {
                return room;
            }
        }
        return null;
    }
    
    private static class Lecture {
        private Subject subject;
        
        public Lecture(Subject subject) {
            this.subject = subject;
        }
        
        public Subject getSubject() {
            return subject;
        }

		@Override
		public String toString() {
			return "Lecture [subject=" + subject + "]";
		}
        
        
    }
}