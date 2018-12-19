USE testingDB;

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('tuna18', 'tuna', 0, 'Tuna', 'Ates', 'tuna@gmail.com', '1997-11-06' );

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('jesus18', 'jesus', 0, 'Jesus', 'Rada', 'jesus@gmail.com', '1995-09-25' );

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('dylan18', 'dylan', 0, 'Dylan', 'Bartending', 'dylan@gmail.com', '1995-09-27' );

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('JarlT', 'jarl', 0, 'Jarl', 'Teacher', 'jarl@gmail.com', '1900-01-01' );

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('AndreaT', 'andrea', 0, 'Andrea', 'Teacher', 'andrea@gmail.com', '1900-01-01' );

INSERT INTO user(username, password, role, name, surname, email, birthdate) 
VALUES('DanyT', 'dany', 0, 'Dany', 'Teacher', 'dany@gmail.com', '1900-01-01' );

SELECT * FROM user;

INSERT INTO student VALUES(1);
INSERT INTO student VALUES(2);
INSERT INTO student VALUES(3);

INSERT INTO teacher(ID) VALUES(4);
INSERT INTO teacher(ID) VALUES(5);
INSERT INTO teacher(ID) VALUES(6);

INSERT INTO course(courseName, yearDone) VALUES('Databases', 2018);
INSERT INTO course(courseName, yearDone) VALUES('Security', 2018);
INSERT INTO course(courseName, yearDone) VALUES('Testing', 2018);
INSERT INTO course(courseName, yearDone) VALUES('AI', 2018);

INSERT INTO studentCourse VALUES(1, 1);
INSERT INTO studentCourse VALUES(1, 2);
INSERT INTO studentCourse VALUES(1, 3);

INSERT INTO studentCourse VALUES(2, 1);
INSERT INTO studentCourse VALUES(2, 2);
INSERT INTO studentCourse VALUES(2, 3);

INSERT INTO studentCourse VALUES(3, 1);
INSERT INTO studentCourse VALUES(3, 4);
INSERT INTO studentCourse VALUES(3, 3);

INSERT INTO teacherCourse VALUES(4, 3);
INSERT INTO teacherCourse VALUES(5, 1);
INSERT INTO teacherCourse VALUES(6, 2);

CALL getCoursesStudent(1);
CALL getCoursesStudent(3);

CALL getCoursesTaught(4);

INSERT INTO activity(activityName, activityType, dateDone, courseID, weight)
				VALUES ('DBProject', 1, '2018-12-25', 1, 100);

INSERT INTO activity(activityName, activityType, dateDone, courseID, weight)
				VALUES ('SecurityProject', 1, '2018-12-25', 2, 100);

INSERT INTO activity(activityName, activityType, dateDone, courseID, weight)
				VALUES ('TestingProject', 1, '2018-12-25', 3, 100);

INSERT INTO activity(activityName, activityType, dateDone, courseID, weight)
				VALUES ('AIProject', 1, '2018-12-25', 4, 100);
                
CALL getActivitiesCourse(1);

CALL updateGrade( 1, 1, 8);
CALL updateGrade( 1, 2, 14);
CALL updateGrade( 1, 3, 14);

CALL getActivitiesStudentCourse (1, 1);



CALL getStudentsCourse(2);


