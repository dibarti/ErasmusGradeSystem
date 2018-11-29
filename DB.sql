-- DATABASE CREATION

DROP DATABASE testingDB IF EXISTS;
CREATE DATABASE testingDB;

USE testingDB;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	iduser INT UNSIGNED NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL UNIQUE,
	password VARCHAR(45) NOT NULL,
	role INT(11) NOT NULL,

	name VARCHAR(45),
	surname VARCHAR(45),
	email VARCHAR(60) UNIQUE,
	birthdate DATE,

	PRIMARY KEY(iduser)
)

DROP TABLE IF EXISTS student;
CREATE TABLE student(
ID IN UNSIGNED NOT NULL,

PRIMARY KEY(ID),
FOREIGN KEY ID REFERENCES user(iduser)
);

DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher(

ID INT UNSIGNED NOT NULL,
active BOOLEAN DEFAULT 1,

PRIMARY KEY(ID),
FOREIGN KEY ID REFERENCES user(iduser)
);

DROP TABLE IF EXISTS course;
CREATE TABLE course(
ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
courseName VARCHAR(255),
yearDone INT,
closed BOOLEAN DEFAULT 0,

PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS teacherCourse;
CREATE TABLE teacherCourse(
teacherID INT UNSIGNED NOT NULL,
courseID INT UNSIGNED NOT NULL,

PRIMARY KEY(teacherID, courseID),
FOREIGN KEY (teacherID) REFERENCES teacher(ID),
FOREIGN KEY (courseID) REFERENCES course(ID)
);

DROP TABLE IF EXISTS studentCourse;
CREATE TABLE studentCourse(
studentID INT UNSIGNED NOT NULL,
courseID INT UNSIGNED NOT NULL,

PRIMARY KEY(studentID, courseID),
FOREIGN KEY (studentID) REFERENCES student(ID),
FOREIGN KEY (courseID) REFERENCES course(ID)
);

DROP TABLE IF EXISTS activity;
CREATE TABLE activity(
ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
activityName VARCHAR(255),
activityType SMALLINT, -- 0 assignment, 1 project,  2 exam
dateDone DATE,
courseID VARCHAR(255),
weight INT,

PRIMARY KEY (ID),
FOREIGN KEY (courseID) REFERENCES course(ID)
);

CREATE TABLE activityStudent(
grade SMALLINT,
studentID INT UNSIGNED NOT NULL,
activityID INT UNSIGNED NOT NULL,

PRIMARY KEY(studentID, assignmentID),
FOREIGN KEY (studentID) REFERENCES student(ID),
FOREIGN KEY (activityID) REFERENCES activity(ID)
);

-- STORED PROCEDURES

-- SELECTS

-- Load courses current teacher ----------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS testingDB.getCoursesTaught $$
CREATE PROCEDURE testingDB.getCoursesTaught( IN userID_in INT UNSIGNED)
BEGIN
		SELECT yearDone, courseID, courseName FROM course, teacherCourse
    WHERE teacherID = userID_in AND closed = 0;
END $$
DELIMITER ;

-- Load students current course ----------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS testingDB.getStudentsCourse $$
CREATE PROCEDURE testingDB.getStudentsCourse( IN courseID_in INT UNSIGNED)
BEGIN
		SELECT studentID, studentName, studentSurname FROM studentCourse, student
    WHERE courseID = courseID_in AND student.ID = studentID;
END $$
DELIMITER ;

-- Load activities current student at current course -

DELIMITER $$

DROP PROCEDURE IF EXISTS testingDB.getActivitiesStudentCourse $$
CREATE PROCEDURE testingDB.getActivitiesStudentCourse (IN userID_in INT UNSIGNED, IN courseID_in INT UNSIGNED)
BEGIN
	SELECT activityID, activityName, activityType, grade, weight FROM activity, activityStudent
	WHERE studentID = userID_in AND courseID = courseID_in
	GROUP BY activityType
	ORDER BY dateDone;
END $$

DELIMITER ;

-- Load activities current course --------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS testingDB.getActivitiesCourse $$
CREATE PROCEDURE testingDB.getActivitiesCourse( IN courseID_in INT UNSIGNED)
BEGIN
		SELECT activityID, activityName, activityType FROM activity WHERE ID = usuari_ID_in
    ORDER BY activityType;
END $$
DELIMITER ;

-- Load courses current student ----------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS testingDB.getCoursesStudent $$
CREATE PROCEDURE testingDB.getCoursesStudent( IN userID_in INT UNSIGNED)
BEGIN
		SELECT courseID, courseName, yearDone FROM course, studentCourse WHERE studentID = userID_in;
END $$
DELIMITER ;

-- INSERTS

-- Admin ----------

-- New teacher

-- New student

-- New course

-- Teacher --------

-- New activity

-- Update grade

DELIMITER $$
DROP PROCEDURE IF EXISTS testingDB.updateGrade $$
CREATE PROCEDURE testingDB.updateGrade( IN activityID_in INT UNSIGNED, IN userID_in INT UNSIGNED, IN grade_in SMALLINT)
BEGIN
		REPLACE INTO activityStudent VALUES(grade_in, userID_in, activityID_in);
END $$
DELIMITER ;



