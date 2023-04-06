package com.connect.campus.services;

import com.connect.campus.dao.AttendanceRepository;
import com.connect.campus.dao.BatchRepository;
import com.connect.campus.dao.NotificationRepository;
import com.connect.campus.dao.StudentRepository;
import com.connect.campus.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServices {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    BatchRepository batchRepository;
    public StudentEntity studentLogin(int id, String password) {
        StudentEntity student=studentRepository.findByStudentIdAndPassword(id, password);
        System.out.println(student);
        return student;
    }

    public void passwordChange(int studentId, String newPassword) {
        StudentEntity student=studentRepository.findByStudentId(studentId);
        if(student!=null){
            student.setPassword(newPassword);
            studentRepository.save(student);
        }

    }


    public List<NotificationEntity> getNotification() {
        List<NotificationEntity> notifications= new ArrayList<>();
        notifications.addAll(notificationRepository.findAll());
        return notifications;
    }

    public List<NotificationEntity> searchNotification(String title) {
        List<NotificationEntity> notifications= new ArrayList<>();
        notifications=notificationRepository.findByNotificationTitle(title);
        return notifications;
    }

    public List<ScheduleEntity> classSchedule(int studentId) {
        String batchId= studentRepository.findBatchIdByStudentId(studentId);
        BatchEntity batch=batchRepository.findByBatchId(batchId);
        return  batch.getSchedules();
    }

    public List<AttendanceEntity> viewSubjectAttendance(int studentId, int subjectId) {
        List<AttendanceEntity> attendanceList= attendanceRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        return attendanceList;
    }

    public List<StudentProgressEntity> viewStudentMarks(int studentId) {
        StudentEntity student= studentRepository.findByStudentId(studentId);
        return student.getStudentProgress();
    }
}
