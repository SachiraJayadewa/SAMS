package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.model.Attendance;
import java.util.List;
import java.util.Map;

public interface AttendanceService {
    void markAttendance(Attendance attendance);
    List<Attendance> getAttendanceBySchedule(int scheduleId);
    List<Attendance> getAttendanceByStudent(int studentId);
    void deleteAttendance(int attendanceId);
    Map<String, Integer> getAttendanceSummaryBySchedule(int scheduleId);  // new method
}



