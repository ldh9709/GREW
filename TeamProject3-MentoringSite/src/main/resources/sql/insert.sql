/* 멘티 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'aaa','aaa','aaa@naver.com','김진영','ROLE_MENTEE',12000,1,sysdate,0);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'bbb','bbb','bbb@naver.com','김찬영','ROLE_MENTEE',500,1,sysdate,0);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'ccc','ccc','ccc@naver.com','나문정','ROLE_MENTEE',5000,1,sysdate,0);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'ddd','ddd','ddd@naver.com','문준형','ROLE_MENTEE',700,1,sysdate,0);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'eee','eee','eee@naver.com','박은미','ROLE_MENTOR',9000,1,sysdate,0);

/* 멘토 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'fff','fff','fff@naver.com','송대현','ROLE_MENTOR',5500,1,sysdate,0);
INSERT INTO MentorProfile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 송대현 멘토입니다.', '경력자입니다', 5, 10, 'profile1.jpg', 100, 50, 1, 1, member_no_SEQ.CURRVAL);


INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'ggg','ggg','ggg@naver.com','양한수','ROLE_MENTOR',6300,1,sysdate,0);
INSERT INTO MentorProfile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 양한수 멘토입니다.', '경력자입니다', 5, 10, 'profile1.jpg', 100, 50, 1, 1, member_no_SEQ.CURRVAL);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'hhh','hhh','hhh@naver.com','이도현','ROLE_MENTOR',4000,1,sysdate,0);
INSERT INTO MentorProfile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', '경력자입니다', 5, 10, 'profile1.jpg', 100, 50, 1, 1, member_no_SEQ.CURRVAL);

/* 신고 등록 */
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 1, 1,'욕함요', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'USER' , 3, 3,'광고쟁이', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 1, 4,'욕쟁이', sysdate, 1, sysdate , 3);

commit;