/* 카테고리 등록 */
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'직무 상담',1, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'인사/총무/노무',2, 1);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'영업/영업관리',2, 1);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'IT개발/데이터',2, 1);

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'학습/교육',1, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'중학생',2,5);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'고등학생',2,5);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'대학입시상담',2,5);

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'예술/창작',1,null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'음악',2,9);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'글쓰기',2,9);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'미술',2,9);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'사진/영상 제작',2,9);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'연기/연극',2,9);

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'창업/비즈니스',1,null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'스타트업 아이디어',2,15);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'마케팅 전략',2,15);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'법률 특허 상담',2,15);


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
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'ggg','ggg','ggg@naver.com','양한수','ROLE_MENTOR',6300,1,sysdate,0);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_role,member_points,member_status,member_join_date,member_report_count)
VALUES(member_no_SEQ.nextval,'hhh','hhh','hhh@naver.com','이도현','ROLE_MENTOR',4000,1,sysdate,0);


/* 팔로우 등록 */
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,1,6);
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,2,6);
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,3,6);
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,4,7);
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,5,7);
INSERT INTO follow(follow_no,follower_member,followed_member)
VALUES(follow_no_SEQ.nextval,1,8);


/* 신고 등록 */
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 1, 1,'욕함요', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'USER' , 3, 3,'광고쟁이', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 1, 4,'욕쟁이', sysdate, 1, sysdate , 3);


commit;
