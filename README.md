SAMS submission report

GitHub repository link
https://github.com/SachiraJayadewa/SAMS

MySQL Script
-- Create Database
CREATE DATABASE IF NOT EXISTS sams;
USE sams;

-- Table: user
CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('Admin', 'Lecturer', 'Student') NOT NULL
);

-- Table: student
CREATE TABLE IF NOT EXISTS student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    reg_number VARCHAR(50) NOT NULL UNIQUE,
    course VARCHAR(100),
    contact VARCHAR(20)
);

-- Table: lecturer
CREATE TABLE IF NOT EXISTS lecturer (
    lecturer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    contact VARCHAR(50),
    email VARCHAR(100) UNIQUE
);

-- Table: course
CREATE TABLE IF NOT EXISTS course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

-- Table: class_schedule
CREATE TABLE IF NOT EXISTS class_schedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    subject VARCHAR(100),
    lecturer_id INT,
    scheduled_date DATE,
    scheduled_time TIME,
    FOREIGN KEY (course_id) REFERENCES course(course_id),
    FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id)
);

-- Table: attendance
CREATE TABLE IF NOT EXISTS attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    schedule_id INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    marked_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (schedule_id) REFERENCES class_schedule(schedule_id)
);

-- Sample Data

-- Users
INSERT INTO user (username, password, role) VALUES
('admin', 'admin123', 'Admin'),
('jane.doe', 'password123', 'Lecturer'),
('nimasha', 'nimasha123', 'Student');

-- Lecturers
INSERT INTO lecturer (name, department, contact, email) VALUES
('Dr. John Smith', 'Computer Science', '0771234567', 'john.smith@university.com'),
('Prof. Jane Doe', 'Information Systems', '0712345678', 'jane.doe@university.com'),
('Dr. Alan Grant', 'Software Engineering', '0759876543', 'alan.grant@university.com'),
('Ms. Sarah Connor', 'Computer Science', '0781237890', 's.connor@university.com'),
('Mr. Bruce Wayne', 'Cybersecurity', '0704567890', 'bruce.wayne@wayneenterprises.com');

-- Courses
INSERT INTO course (course_name, description) VALUES
('Computer Programming', 'Introduction to programming using Java.'),
('Database Systems', 'Study of relational databases and SQL.'),
('Web Development', 'Frontend and backend web technologies.'),
('Software Engineering', 'Principles of software design and architecture.'),
('Cybersecurity', 'Basics of securing digital systems and networks.');

-- Students
INSERT INTO student (name, reg_number, course, contact) VALUES
('Nimasha Perera', 'REG002', 'Software Engineering', '0719876543'),
('amal', '123456', 'english', '12345678');

-- Class Schedule
INSERT INTO class_schedule (course_id, subject, lecturer_id, scheduled_date, scheduled_time) VALUES
(1, 'Variables and Data Types', 1, '2025-07-25', '09:00:00'),
(2, 'SQL Joins and Queries', 2, '2025-07-26', '10:30:00'),
(3, 'HTML & CSS Basics', 3, '2025-07-27', '14:00:00'),
(4, 'Software Life Cycle', 4, '2025-07-28', '11:00:00'),
(4, 'AI', 5, '2025-07-29', '13:30:00');

-- Attendance
INSERT INTO attendance (student_id, schedule_id, status, marked_date) VALUES
(2, 1, 'Present', '2025-07-25'),
(6, 1, 'Absent', '2025-07-25'),
(2, 3, 'Present', '2025-07-27'),
(6, 5, 'Present', '2025-07-29');



### 🔐 Login Credentials

| Role     | Username     | Password     |
|----------|--------------|--------------|
| Admin    | admin        | admin123     |
| Lecturer | jane.doe     | password123  |
| Student  | nimasha      | nimasha123   |
