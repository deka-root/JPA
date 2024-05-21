create database CollegeDB;

CREATE TABLE IF NOT EXISTS tbl_courses
(
    courses_code    INT AUTO_INCREMENT COMMENT '강의코드',
    courses_name    VARCHAR(30) NOT NULL COMMENT '강의명',
    CONSTRAINT pk_courses_code PRIMARY KEY (courses_code)
) ENGINE=INNODB COMMENT '강의';


CREATE TABLE IF NOT EXISTS tbl_student
(
    student_code    INT AUTO_INCREMENT COMMENT '학생코드',
    student_name    VARCHAR(30) NOT NULL COMMENT '학생이름',
    student_age    INT NOT NULL COMMENT '학생나이',
    courses_code    INT NOT NULL COMMENT '강의코드',
    enroll_status    CHAR(1) NOT NULL COMMENT '수강상태',
    CONSTRAINT pk_student_code PRIMARY KEY (student_code),
    CONSTRAINT fk_courses_code FOREIGN KEY (courses_code) REFERENCES tbl_courses (courses_code)
) ENGINE=INNODB COMMENT '학생';

-- 수강 강의 데이터 삽입
INSERT INTO tbl_courses VALUES (null, 'JAVA');
INSERT INTO tbl_courses VALUES (null, 'C');
INSERT INTO tbl_courses VALUES (null, 'C++');
INSERT INTO tbl_courses VALUES (null, 'C#');
INSERT INTO tbl_courses VALUES (null, 'Android Studio');
INSERT INTO tbl_courses VALUES (null, 'Arduino');
INSERT INTO tbl_courses VALUES (null, 'Spring');
INSERT INTO tbl_courses VALUES (null, 'R');
INSERT INTO tbl_courses VALUES (null, '데이터베이스 이론');
INSERT INTO tbl_courses VALUES (null, 'MySQL');
INSERT INTO tbl_courses VALUES (null, 'Python');
INSERT INTO tbl_courses VALUES (null, 'Java Script');
INSERT INTO tbl_courses VALUES (null, 'PHP');
INSERT INTO tbl_courses VALUES (null, 'React');
INSERT INTO tbl_courses VALUES (null, 'OracleDB');
INSERT INTO tbl_courses VALUES (null, 'MariaDB');
INSERT INTO tbl_courses VALUES (null, 'Linux');



