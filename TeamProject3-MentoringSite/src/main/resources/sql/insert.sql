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
VALUES(category_no_SEQ.nextval,'스타트업',2,15);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'마케팅 전략',2,15);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'법률 특허 상담',2,15);

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (1)',3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (2)',3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (3)',3, null);

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '건강/운동', 1, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '피트니스', 2, 22);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '요가/필라테스', 2, 22);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '식단/영양 상담', 2, 22);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '관심사를 선택해주세요', 3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '관심사를 선택해주세요', 3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '관심사를 선택해주세요', 3, null);


/***** 테스트용 아이디 등록(멘티) *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'asdf1234', '{bcrypt}$2a$10$Lhr9iLqvAzcQhu4Bd.VXmOHSu0LepHnKlrx43i5EbsEgkJBsNaVke', 'asdf1234@gmail.com', '김진영', 500, 1, '25/01/01', 0, 'ROLE_MENTEE', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 4);

/***** 테스트용 아이디 등록(멘티) *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'qwer1234', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'zszz5434@gmail.com', '이도현', 500, 1, '25/01/01', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 2, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 2, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 2, 25);

/***** 테스트용 아이디 등록(멘토) *****/
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', 5, 10, '/images/mentor-profile/15.jpeg', 100, 50, 3, 2, 2, '반갑습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 1);

/***** 테스트용 아이디 등록(관리자) *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'admin1234', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'admin1234@gmail.com', '관리자', 500, 1, '25/01/01', 0, 'ROLE_ADMIN', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 3, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 3, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 3, 25);


/* 멘티 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'bbb','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','bbb@naver.com','김찬영',500,1,'25/01/01',0,'ROLE_MENTEE','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 4, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 4, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 4, 4);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ccc','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','ccc@naver.com','나문정',5000,1,'25/01/01',0,'ROLE_MENTEE','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 5, 6);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 5, 7);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 5, 8);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ddd','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','ddd@naver.com','문준형',700,1,'25/01/01',0,'ROLE_MENTEE','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 10);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 11);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 12);
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'eee','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','eee@naver.com','박은미',9000,1,'25/01/02',0,'ROLE_MENTEE','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 7, 13);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 7, 14);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 7, 16);

/* 멘토 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'fff','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','fff@naver.com','송대현',5500,1,'25/01/02',0,'ROLE_MENTOR','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 8, 6);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 8, 7);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 8, 8);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 송대현 멘토입니다.', 5.0, 10, '/images/mentor-profile/1.jpeg', 100, 50, 3, 4, member_no_SEQ.currval, '자바 백엔드 마스터입니다');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, 'xx개발', '백엔드 개발자 담당', '2020.01.01', '2024.01.01', 2);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ggg','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','ggg@naver.com','양한수',6300,1,'25/01/02',0,'ROLE_MENTOR','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 9, 10);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 9, 11);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 9, 12);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 양한수 멘토입니다.', 5.0, 10, '/images/mentor-profile/2.png', 100, 50, 3, 10, member_no_SEQ.currval, '반갑습니다 성심성의껏 멘토해드리겠습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 3);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'hhh','{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K','hhh@naver.com','이지현',4000,1,'25/01/02',0,'ROLE_MENTOR','Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 10, 10);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 10, 11);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 10, 12);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이지현 멘토입니다.', 5.0, 10, '/images/mentor-profile/3.jpeg', 100, 50, 3, 12, member_no_SEQ.currval, '믿고맡겨 주세요!');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 4);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor01', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor01@naver.com', '김민수', 3000, 1, '25/01/03', 0, 'ROLE_MENTOR', 'Email');.
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 11, 16);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 11, 17);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 11, 18);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김민수 멘토입니다.', 5.0, 25, '/images/mentor-profile/4.jpg', 150, 75, 3, 2, member_no_SEQ.currval, '시 부터 소설까지 어떠한 글쓰기든 도와드리겠습니다~');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, 'xx출판사', '소설부문 담당자', '2023.04.01', '2024.01.01', 5);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor02', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor02@naver.com', '이서준', 4000, 1, '25/01/03', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 12, 16);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 12, 17);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 12, 18);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이서준 멘토입니다.', 4.8, 20, '/images/mentor-profile/5.jpeg', 120, 60, 3, 3, member_no_SEQ.currval, '너도나도유캔두');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '갤러리', '전시 및 마케팅 담당', '2020.01.01', '2024.01.01', 6);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor03', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor03@naver.com', '박지우', 3500, 1, '25/01/03', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 13, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 13, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 13, 25);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박지우 멘토입니다.', 4.5, 15, '/images/mentor-profile/6.jpg', 110, 50, 3, 4, member_no_SEQ.currval, '고등 담임의 경험으로 컨설팅 해드리겠습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, 'xx고등학교', '고2 수학 담임', '2020.01.01', '2023.12.30', 7);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, 'xx고등학교', '고2 수학 담임', '2023.01.01', '2024.01.01', 7);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor04', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor04@naver.com', '정하늘', 3200, 1, '25/01/03', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 14, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 14, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 14, 25);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정하늘 멘토입니다.', 4.7, 18, '/images/mentor-profile/7.jpeg', 130, 65, 3, 8, member_no_SEQ.currval, '입시상담 자신 있습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '대치동 학원', '입시 컨설팅 담당자', '2024.01.01', null, 8);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor05', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor05@naver.com', '최예진', 5000, 1, '25/01/04', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 15, 12);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 15, 13);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 15, 14);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최예진 멘토입니다.', 4.6, 22, '/images/mentor-profile/8.jpeg', 140, 70, 3, 16, member_no_SEQ.currval, '스타트업 아이디어 고민해드립니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '아이티윌', '스타트업 상담 및 컨설팅', '2024.01.01', null, 9);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor06', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor06@naver.com', '홍서연', 4800, 1, '25/01/04', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 16, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 16, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 16, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 홍서연 멘토입니다.', 4.9, 30, '/images/mentor-profile/9.png', 160, 80, 3, 8, member_no_SEQ.currval, 'HELLO');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, 'xx아카테미', '일렉기타 강습', '2024.05.01', null, 10);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor07', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor07@naver.com', '김도윤', 4500, 1, '25/01/04', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 17, 10);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 17, 11);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 17, 12);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, 
'- 특성화고 직업상담사의 업무
- 직업상담사의 직무 역량
- 대학원 졸업장 없이 심리학 전공을 살릴 수 있었던 방법
- 계약직 기회를 제대로 활용하는 방법
- 대학원 진학의 득과 실 등을 드릴 수 있습니다 :)', 5.0, 40, '/images/mentor-profile/10.jpg', 170, 90, 3, 12, member_no_SEQ.currval, '입시컨설팅 늦지 않았습니다!');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '영업시스템 PM팀_ 서비스기획', '2024.01.01', null, 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발팀_ 서비스 기획', '2020.01.01', '2024.05.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발TF팀_서비스 기획', '2019.01.01', '2020.01.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '리하우스 영업관리팀_ 인테리어 설계', '2016.01.01', '2019.01.01', 11);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor08', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor08@naver.com', '김도옌', 4500, 1, '25/01/05', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 18, 16);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 18, 17);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 18, 18);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도옌 멘토입니다.', 5.0, 40, '/images/mentor-profile/11.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '그림을 두려워하지 마세요');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 12);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor09', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor09@naver.com', '김도예', 4500, 1, '25/01/05', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 19, 13);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 19, 14);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 19, 17);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도예 멘토입니다.', 5.0, 40, '/images/mentor-profile/12.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '사진 잘 찍는 법 알려드릴게요');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '17년 경력', '2024.01.01', null, 13);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor10', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor10@naver.com', '김도요', 4500, 1, '25/01/05', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 20, 16);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 20, 17);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 20, 18);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도요 멘토입니다.',5.0, 40, '/images/mentor-profile/13.png', 170, 90, 3, 18, member_no_SEQ.currval, '열심히 멘토하겠습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 14);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor11', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor11@naver.com', '김윤하', 4500, 1, '25/01/05', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 21, 16);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 21, 17);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 21, 18);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김윤하 멘토입니다.',5.0, 40, '/images/mentor-profile/14.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '자세한 상담 도와드리겠습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 15);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor12', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor12@naver.com', '김태윤', 3500, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 22, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 22, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 22, 25);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김태윤 멘토입니다. 중학생들을 위한 학습 및 진로 상담 전문가입니다.', 4.8, 25, '/images/mentor-profile/16.jpg', 90, 40, 3, 6, member_no_SEQ.currval, '중학생 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '스마트러닝 교육센터', '중학생 학습 프로그램 개발자', '2015-03-01', '2023-12-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor12')));



INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor14', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor14@naver.com', '박수민', 5000, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 23, 23);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 23, 24);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 23, 25);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박수민 멘토입니다. 음악 이론과 실기에 대해 전문적으로 가르칩니다.', 5.0, 50, '/images/mentor-profile/18.jpg', 150, 70, 3, 10, member_no_SEQ.currval, '음악 멘토링 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '국립음악원', '음악 이론 강사', '2012-05-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor14')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor15', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor15@naver.com', '정해인', 4800, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 24, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 24, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 24, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정해인 멘토입니다. 연기와 연극에 대한 전문 지식을 제공합니다.', 4.9, 35, '/images/mentor-profile/19.jpg', 100, 50, 3, 14, member_no_SEQ.currval, '연기 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '서울연극협회', '연극 연출 및 배우 지도', '2010-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor15')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor16', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor16@naver.com', '이현아', 4500, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 25, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 25, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 25, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이현아 멘토입니다. 건강한 식단과 영양 상담을 전문적으로 도와드립니다.', 4.8, 28, '/images/mentor-profile/20.jpg', 110, 55, 3, 25, member_no_SEQ.currval, '영양 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '영양학 연구소', '임상 영양사', '2015-07-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor16')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor17', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor17@naver.com', '손민호', 4200, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 26, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 26, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 26, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 손민호 멘토입니다. 글쓰기와 창작에 대한 멘토링을 제공합니다.', 4.6, 20, '/images/mentor-profile/21.jpg', 130, 65, 3, 11, member_no_SEQ.currval, '글쓰기 멘토링 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '창작 글쓰기 워크숍', '글쓰기 강사', '2013-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor17')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor18', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor18@naver.com', '이강민', 4700, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 27, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 27, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 27, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이강민 멘토입니다. 사진과 영상 제작에 대한 전문적인 상담을 제공합니다.', 5.0, 40, '/images/mentor-profile/22.jpg', 170, 80, 3, 13, member_no_SEQ.currval, '사진/영상 제작 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '크리에이티브 스튜디오', '영상 제작자', '2011-04-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor18')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor19', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor19@naver.com', '최유진', 5500, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 28, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 28, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 28, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최유진 멘토입니다. 마케팅 전략과 성공 사례를 전문적으로 상담합니다.', 5.0, 50, '/images/mentor-profile/23.jpg', 200, 100, 3, 17, member_no_SEQ.currval, '마케팅 전략 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '마케팅 솔루션 기업', '마케팅 컨설턴트', '2009-03-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor19')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor20', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor20@naver.com', '이정훈', 4600, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 29, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 29, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 29, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이정훈 멘토입니다. 피트니스와 운동 프로그램 설계 전문가입니다.', 4.9, 35, '/images/mentor-profile/24.jpg', 140, 70, 3, 23, member_no_SEQ.currval, '피트니스 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '헬스 퍼스널 트레이닝 센터', '피트니스 트레이너', '2012-06-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor20')));











INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor50', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor50@naver.com', '이재훈', 5200, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 30, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 30, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 30, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이재훈 멘토입니다. 금융 및 투자 전문가로 활동하고 있습니다.', 4.8, 50, '/images/mentor-profile/1a.jpg', 120, 45, 3, 3, member_no_SEQ.currval, '금융 투자 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '투자 컨설팅 회사', '금융 컨설턴트', '2016-01-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor50')));


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor51', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor51@naver.com', '김유나', 4800, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 31, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 31, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 31, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김유나 멘토입니다. UI/UX 디자인 전문가로 활동 중입니다.', 4.7, 35, '/images/mentor-profile/2a.jpg', 140, 55, 3, 4, member_no_SEQ.currval, 'UI/UX 디자인 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '디자인 에이전시', 'UI/UX 디자이너', '2015-03-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor51')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor52', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor52@naver.com', '박정민', 5000, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 32, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 32, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 32, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박정민 멘토입니다. 법률 및 상담 전문가입니다.', 4.9, 42, '/images/mentor-profile/3a.jpg', 160, 70, 3, 9, member_no_SEQ.currval, '법률 상담 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '법률 사무소', '법률 컨설턴트', '2014-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor52')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor53', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor53@naver.com', '최서연', 5100, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 33, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 33, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 33, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최서연 멘토입니다. 디지털 마케팅 전문가로 활동 중입니다.', 4.8, 38, '/images/mentor-profile/4a.jpg', 130, 65, 3, 9, member_no_SEQ.currval, '디지털 마케팅 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '마케팅 에이전시', '디지털 마케터', '2017-04-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor53')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor54', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor54@naver.com', '이민재', 4700, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 34, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 34, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 34, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이민재 멘토입니다. 콘텐츠 크리에이션 전문가입니다.', 4.8, 29, '/images/mentor-profile/5a.jpg', 140, 58, 3, 13, member_no_SEQ.currval, '콘텐츠 크리에이션 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '미디어 회사', '콘텐츠 제작자', '2016-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor54')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor55', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor55@naver.com', '박하은', 4800, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 35, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 35, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 35, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박하은 멘토입니다. 교육 및 학습 코칭 전문가입니다.', 4.9, 40, '/images/mentor-profile/6a.jpg', 150, 60, 3, 7, member_no_SEQ.currval, '학습 코칭 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '교육 컨설팅 센터', '학습 코치', '2015-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor55')));


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor57', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor57@naver.com', '정유진', 5100, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 36, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 36, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 36, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정유진 멘토입니다. 금융 컨설팅 전문가입니다.', 4.7, 35, '/images/mentor-profile/8a.jpg', 110, 47, 3, 17, member_no_SEQ.currval, '금융 컨설팅 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '금융 서비스 회사', '재무 컨설턴트', '2018-01-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor57')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor58', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor58@naver.com', '이서윤', 4700, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 37, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 37, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 37, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이서윤 멘토입니다. 필라테스 강사입니다.', 4.8, 60, '/images/mentor-profile/9a.jpg', 180, 85, 3, 24, member_no_SEQ.currval, '필라테스 경력 20년');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '필라테스', '강사', '2014-07-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor58')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor59', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor59@naver.com', '최민지', 4900, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 38, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 38, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 38, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최민지 멘토입니다. 디자인 및 일러스트 전문가입니다.', 4.7, 45, '/images/mentor-profile/10a.jpg', 160, 68, 3, 9, member_no_SEQ.currval, '디자인 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '디자인 스튜디오', '일러스트레이터', '2015-06-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor59')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor60', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor60@naver.com', '김도현', 5100, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 39, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 39, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 39, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도현 멘토입니다. 헬스 및 피트니스 전문가입니다.', 4.8, 50, '/images/mentor-profile/11a.jpg', 190, 90,3, 22, member_no_SEQ.currval, '피트니스 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '헬스 트레이닝 센터', '퍼스널 트레이너', '2013-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor60')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor61', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor61@naver.com', '정민호', 5000, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 40, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 40, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 40, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정민호 멘토입니다. IT 보안 및 네트워크 전문가입니다.', 4.9, 40, '/images/mentor-profile/12a.jpg', 140, 55, 3, 4, member_no_SEQ.currval, 'IT 보안 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, 'IT 보안 회사', '네트워크 엔지니어', '2012-11-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor61')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor62', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor62@naver.com', '한지원', 5200, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 41, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 41, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 41, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 한지원 멘토입니다. 창업 및 비즈니스 컨설팅 전문가입니다.', 4.8, 55, '/images/mentor-profile/13a.jpg', 170, 75, 3, 15, member_no_SEQ.currval, '창업 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '스타트업 인큐베이터', '창업 컨설턴트', '2014-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor62')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor63', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor63@naver.com', '이수민', 4700, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 42, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 42, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 42, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이수민 멘토입니다. 데이터 분석 및 통계 전문가입니다.', 4.7, 32, '/images/mentor-profile/14a.jpg', 120, 45, 3, 5, member_no_SEQ.currval, '데이터 분석 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '분석 컨설팅 회사', '데이터 분석가', '2015-08-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor63')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor65', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor65@naver.com', '유민재', 4600, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 43, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 43, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 43, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 유민재 멘토입니다. 창업 컨설팅 전문가입니다.', 4.8, 50, '/images/mentor-profile/16a.jpg', 130, 65, 3, 15, member_no_SEQ.currval, '창업 컨설팅 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '창업 지원 센터', '창업 컨설턴트', '2012-10-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor65')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor66', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'mentor66@naver.com', '한유진', 4700, 1, '25/01/07', 0, 'ROLE_MENTOR', 'Email');
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 44, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 44, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 44, 4);
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 한유진 멘토입니다. 영어 교육 전문가입니다.', 4.9, 44, '/images/mentor-profile/17a.jpg', 150, 80, 3, 6, member_no_SEQ.currval, '영어 교육 전문가');
INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '영어 교육 센터', '영어 교사', '2014-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor66')));





/*멘토 보드 등록*/

/* 인사/총무/노무 분야 category 2번 member 번호 2번 ,11번*/
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '핵심만 콕! 바로쓰는 총무 실무', '''핵심만 뽑아 쓰는 총무 업무의 모든 것!''  NCS 학습모듈 집필위원이 알려주는 학습포인트와 현업 꿀팁!',  '/images/mentor-board/인사총무01.jpg', TO_DATE('2024-12-13 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 185, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '총무 업무 자동화로 시간 절약하기', '반복적인 총무 업무를 효율적으로 처리할 수 있는 자동화 도구와 팁에 대해 소개합니다.', '/images/mentor-board/인사총무02.jpg', TO_DATE('2024-08-10 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 85, 1, 2);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '노무 관리에서의 주요 법적 이슈와 해결 방안', '최근 발생한 주요 노무 관련 분쟁 사례와 이를 예방하고 해결하는 방법을 알려드립니다.', '/images/mentor-board/인사총무03.jpg', TO_DATE('2024-08-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 2);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '신입사원 온보딩 프로세스 최적화하기', '신입사원이 빠르게 조직에 적응하고 성과를 낼 수 있도록 하는 온보딩 프로세스를 소개합니다.', '/images/mentor-board/인사총무04.jpg', TO_DATE('2024-08-20 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 97, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '임직원 복리후생 제도 설계의 핵심 포인트', '직원 만족도를 높이고 회사 경쟁력을 강화할 수 있는 복리후생 제도 설계 방법에 대해 논의합니다.', '/images/mentor-board/인사총무05.jpg', TO_DATE('2024-08-25 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 110, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 인사 평가 시스템 구축 방법', '인사 평가의 객관성을 높이고 직원들의 동기를 유발할 수 있는 평가 시스템 구축 방법에 대해 공유합니다.', '/images/mentor-board/인사총무06.jpg', TO_DATE('2024-08-05 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 2);


/* 영업/영업관리 분야 category 3번 member 번호 12번*/
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '영업 관리의 기본 - 성과를 높이기 위한 팁!', '"영업 관리"는 광범위한 업무처럼 보이지만, 영업팀의 일반적인 업무를 말하는 것은 아닙니다. 영업 관리는 다시 말해 하나의 큰 선박을 안내하는 역할입니다. 한마디로 영업관리는 전략적으로 지원하여 영업 프로세스에서 마찰을 줄임으로써 영업팀이 보다 효율적으로 업무를 수행할 수 있도록 하는 것입니다.',  '/images/mentor-board/영업관리01.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 12);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 영업 목표 설정 방법', '영업 목표를 설정할 때 중요한 것은 팀의 역량과 시장 상황을 기반으로 한 현실적인 계획입니다. 이 글에서는 SMART 목표 설정 원칙을 활용하여 달성 가능한 영업 목표를 만드는 방법을 다룹니다.', '/images/mentor-board/영업관리02.jpg', TO_DATE('2024-10-12 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 12);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '영업 데이터를 활용한 고객 분석 전략', '효과적인 고객 분석은 데이터 기반의 영업 전략을 수립하는 데 필수적입니다. 이번 글에서는 CRM 데이터와 판매 데이터를 활용해 고객 행동을 분석하고 예측하는 방법을 소개합니다.', '/images/mentor-board/영업관리03.jpg', TO_DATE('2024-11-13 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 12);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '영업 조직의 효율성을 높이는 커뮤니케이션 기술', '영업 팀의 성공은 원활한 내부 커뮤니케이션에 달려 있습니다. 이 글에서는 영업 팀 내 협업을 강화하고 문제를 신속히 해결하기 위한 커뮤니케이션 전략을 다룹니다.', '/images/mentor-board/영업관리04.jpg', TO_DATE('2024-12-14 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 165, 1, 12);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '영업 프로세스에서의 리더십 역할', '성공적인 영업 관리는 리더의 역할에 달려 있습니다. 이 글에서는 영업 리더가 팀의 성과를 극대화하기 위해 어떤 방식으로 리더십을 발휘해야 하는지에 대해 논의합니다.', '/images/mentor-board/영업관리05.jpg', TO_DATE('2024-12-15 14:20:00', 'YYYY-MM-DD HH24:MI:SS'), 135, 1, 12);


/* IT개발/데이터 category 4번 member 번호 8번, 13번 */
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '개발 직군, 다른 직무부터 시작해도 될까요?', '멘티님, 산다는 게 참 어려운 결정의 연속인 것 같습니다. 당연하게도 제가 뭔가 해답을 제시해 드리기는 어렵겠지만 제 경우의 경험을 말씀드려보자면, 저는 고등학교는 문과를 졸업했어요',  '/images/mentor-board/it개발데이터01.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 8);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '백엔드 개발자가 알아야 할 서버 구조 기초', '효율적인 백엔드 개발을 위한 서버 아키텍처 설계와 확장성 있는 구조에 대해 알아봅니다.', '/images/mentor-board/it개발데이터02.jpg', TO_DATE('2024-12-25 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 8);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 데이터베이스 설계 원칙', '데이터 중복을 최소화하고 데이터 무결성을 보장할 수 있는 데이터베이스 설계 방법을 알아봅니다.', '/images/mentor-board/it개발데이터03.jpg', TO_DATE('2024-12-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 8);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '비전공자를 위한 개발자 커리어 전환 가이드', '비전공자로 시작하는 개발자의 경로와 필수적으로 익혀야 할 기술들을 소개합니다.', '/images/mentor-board/it개발데이터04.jpg', TO_DATE('2024-12-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 13);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '프론트엔드 개발 트렌드와 기술 스택', '최신 프론트엔드 개발 트렌드와 프로젝트에 맞는 기술 스택 선택 방법을 공유합니다.', '/images/mentor-board/it개발데이터05.jpg', TO_DATE('2024-12-18 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 135, 1, 13);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '데이터 분석가가 되기 위한 첫 걸음', '데이터 분석 기초와 통계 및 머신러닝의 중요성을 사례를 통해 알아봅니다.', '/images/mentor-board/it개발데이터06.jpg', TO_DATE('2024-12-22 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 13);


/* 중학생 학습/교육 category 6번 member 번호 22번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '중학생을 위한 학습 노하우', '중학생들이 효율적으로 공부할 수 있는 방법을 알려드립니다. 스터디 플랜과 학습 습관을 형성하는 팁을 제공합니다.', '/images/mentor-board/중학생01.jpg', TO_DATE('2024-12-09 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 172, 1, 22);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '중학생을 위한 효과적인 시간 관리 비법', '시간 관리를 통해 학습 효율을 높이는 방법과 스케줄링 팁을 알려드립니다.', '/images/mentor-board/중학생02.jpg', TO_DATE('2024-10-04 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 213, 1, 22);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '공부의 재미를 느끼는 방법', '중학생들이 공부의 즐거움을 느낄 수 있도록 동기부여와 목표 설정 방법을 알려드립니다.', '/images/mentor-board/중학생03.jpg', TO_DATE('2024-08-24 15:42:00', 'YYYY-MM-DD HH24:MI:SS'), 258, 1, 22);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효율적인 방과 후 시간 활용법', '방과 후 시간을 효과적으로 활용하여 학업과 휴식을 균형 있게 유지하는 방법을 소개합니다.', '/images/mentor-board/중학생04.jpg', TO_DATE('2024-05-07 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 123, 1, 22);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '중학교 시험 대비 꿀팁', '중간고사와 기말고사를 효율적으로 준비할 수 있는 학습 계획 및 실전 팁을 제공합니다.', '/images/mentor-board/중학생05.jpg', TO_DATE('2024-04-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 111, 1, 22);


/* 고등학생 학습/교육 category 7번 member 번호 35번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '고등학생 학습법과 대학입시 준비', '고등학생들이 학업 성취를 극대화하고 대학 입시에 성공하기 위한 실질적인 가이드를 제공합니다.', '/images/mentor-board/고등학생01.jpg', TO_DATE('2024-12-03 22:12:00', 'YYYY-MM-DD HH24:MI:SS'), 320, 1, 35);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효율적인 수능 대비 전략', '수능 준비 과정을 체계적으로 계획하고 학습 효율을 높이는 방법에 대해 다룹니다.', '/images/mentor-board/고등학생02.jpg', TO_DATE('2024-08-13 18:20:00', 'YYYY-MM-DD HH24:MI:SS'), 280, 1, 35);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '고등학생을 위한 시간 관리 기술', '학업과 생활의 균형을 맞추기 위한 시간 관리 기술과 실천 팁을 제공합니다.', '/images/mentor-board/고등학생03.jpg', TO_DATE('2024-10-07 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 100, 1, 35);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '고등학교 주요 과목 학습법', '국어, 영어, 수학, 과학 등 주요 과목별 효과적인 학습 전략과 문제 해결 팁을 소개합니다.', '/images/mentor-board/고등학생04.jpg', TO_DATE('2024-10-07 15:50:00', 'YYYY-MM-DD HH24:MI:SS'), 132, 1, 35);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효과적인 진로 탐색 방법', '고등학생들이 자신의 진로를 탐색하고 목표를 설정할 수 있도록 돕는 실질적인 방법과 사례를 제공합니다.', '/images/mentor-board/고등학생05.jpg', TO_DATE('2024-09-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 35);


/* 대학입시상담 category 8번 member 번호 14,16번 */
-- member_no 14번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '합격률을 높이는 자기소개서 작성법', '자기소개서에서 강조해야 할 핵심 포인트와 작성법을 공유합니다.', '/images/mentor-board/대학입시상담04.jpg', TO_DATE('2024-10-15 14:45:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 14);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '대학 입시에서 실패를 성공으로 바꾸는 방법', '실패를 극복하고 다시 도전할 수 있는 방법과 사례를 나눕니다.', '/images/mentor-board/대학입시상담05.jpg', TO_DATE('2024-10-18 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 135, 1, 14);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '대학별 전형 정보 활용하기', '대학별 전형 정보를 체계적으로 분석해 입시에 활용하는 방법을 알려드립니다.', '/images/mentor-board/대학입시상담06.jpg', TO_DATE('2024-10-20 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 160, 1, 14);

-- member_no 16번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '대학별 추천 전공 찾기', '적성과 흥미에 맞는 대학과 전공을 선택하는 데 필요한 정보를 제공합니다.', '/images/mentor-board/대학입시상담07.jpg', TO_DATE('2024-10-22 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 16);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '입시생을 위한 스트레스 관리법', '시험 준비 과정에서 발생하는 스트레스를 해소하고 집중력을 높이는 팁을 소개합니다.', '/images/mentor-board/대학입시상담08.jpg', TO_DATE('2024-10-25 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 16);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '수험생 학습 플랜 작성하기', '개인의 학습 스타일에 맞는 수험 계획을 작성하고 실행하는 방법을 공유합니다.', '/images/mentor-board/대학입시상담09.jpg', TO_DATE('2024-10-28 11:45:00', 'YYYY-MM-DD HH24:MI:SS'), 155, 1, 16);


/* 음악 category 10번 member 번호 9번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '음악 이론과 실기 완벽 가이드', '음악을 배우고자 하는 학생들을 위한 이론과 실기 학습법을 소개합니다. 기초부터 고급 단계까지 다룹니다.', '/images/mentor-board/음악01.jpg',TO_DATE('2024-10-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 230, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 악기 선택 가이드', '처음 음악을 시작하는 분들을 위한 악기 선택 팁과 추천 정보를 제공합니다.', '/images/mentor-board/음악02.jpg', TO_DATE('2024-04-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 130, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '작곡을 시작하는 사람들을 위한 팁', '초보자들이 작곡을 시작할 때 알아야 할 기초 지식과 창작 방법을 소개합니다.', '/images/mentor-board/음악03.jpg', TO_DATE('2024-10-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '음악 이론 쉽게 배우기', '복잡해 보이는 음악 이론을 쉽게 이해하고 활용하는 방법을 단계별로 설명합니다.', '/images/mentor-board/음악04.jpg', TO_DATE('2024-10-20 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '음악 경연대회 준비 팁', '음악 경연대회에서 좋은 결과를 얻기 위한 준비 과정과 실전 노하우를 소개합니다.', '/images/mentor-board/음악05.jpg', TO_DATE('2024-10-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 155, 1, 9);


/* 글쓰기 category 11번 member 번호 26번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '창의적인 글쓰기 비법', '창의적인 글쓰기를 위한 아이디어 발굴 방법과 실질적인 글쓰기 노하우를 제공합니다.', '/images/mentor-board/글쓰기01.jpg', TO_DATE('2024-11-17 10:20:00', 'YYYY-MM-DD HH24:MI:SS'), 155, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효과적인 스토리텔링 기술', '독자의 관심을 끌고 감동을 줄 수 있는 스토리텔링 기술과 사례를 공유합니다.', '/images/mentor-board/글쓰기02.jpg', TO_DATE('2024-11-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '글쓰기 블록 극복하기', '글쓰기 과정에서 마주치는 창작의 벽을 극복하기 위한 유용한 팁과 방법을 제공합니다.', '/images/mentor-board/글쓰기03.jpg', TO_DATE('2024-11-23 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '설득력 있는 글쓰기의 비밀', '독자를 설득할 수 있는 글쓰기 방법과 사례를 통해 설득력을 높이는 노하우를 배웁니다.', '/images/mentor-board/글쓰기04.jpg', TO_DATE('2024-11-25 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '다양한 장르의 글쓰기 도전하기', '에세이, 소설, 시 등 다양한 장르에서 글을 쓰는 방법과 재미를 소개합니다.', '/images/mentor-board/글쓰기05.jpg', TO_DATE('2024-11-30 16:15:00', 'YYYY-MM-DD HH24:MI:SS'), 160, 1, 26);


/* 미술 category 12번 member 번호 10,17번 */
-- member_no 10번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '이미지 리터러시와 시각적 해석_미술을 통한 이미지 소통의 본질', '이미지 리터러시와 시각적 해석은 미술을 통한 이미지 소통의 본질 이미지 리터러시와 시각적 해석은 현대 사회에서 더욱 중요한 역할을 맡고 있습니다. 미술은 이러한 관점에서 특히 이미지를 통한 소통의 핵심을 형성하고 있습니다. ',  '/images/mentor-board/미술01.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '창의력을 키우는 드로잉 연습법', '초보자와 전문가 모두를 위한 창의력을 키우는 드로잉 연습 방법과 실전 팁을 제공합니다.', '/images/mentor-board/미술02.jpg', TO_DATE('2024-12-15 10:45:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 10);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '미술사와 현대 미술의 이해', '미술사의 주요 흐름과 현대 미술의 트렌드를 쉽게 이해할 수 있도록 안내합니다.', '/images/mentor-board/미술03.jpg', TO_DATE('2024-12-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 135, 1, 10);

-- member_no 17번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '예술적 영감을 얻는 방법', '창작 과정에서 예술적 영감을 얻기 위한 다양한 방법과 실전 사례를 공유합니다.', '/images/mentor-board/미술04.jpg', TO_DATE('2025-01-07 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 17);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '색채학의 기초와 활용', '효과적인 색채 사용을 위해 알아야 할 기초 이론과 실전 활용법을 설명합니다.', '/images/mentor-board/미술05.jpg', TO_DATE('2024-12-25 16:15:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 17);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '조형 예술에서의 균형과 조화', '조형 예술 작품을 제작할 때 필수적으로 고려해야 할 균형과 조화의 개념을 다룹니다.', '/images/mentor-board/미술06.jpg', TO_DATE('2024-12-30 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 17);
	

/* 사진/영상제작 category 13번 member 27번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '사진과 영상 제작의 모든 것', '사진 촬영과 영상 제작의 기초부터 고급 기술까지 실질적인 조언과 팁을 제공합니다.', '/images/mentor-board/사진영상제작01.jpg', TO_DATE('2024-11-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '스마트폰으로 멋진 사진 촬영하기', '스마트폰 카메라를 활용해 전문가 못지않은 사진을 찍는 팁과 기법을 공유합니다.', '/images/mentor-board/사진영상제작02.jpg', TO_DATE('2024-11-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 85, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '영상 제작을 위한 스토리보드 작성법', '영상 제작의 기본 단계인 스토리보드 작성법과 이를 활용한 효율적인 제작 과정을 다룹니다.', '/images/mentor-board/사진영상제작03.jpg', TO_DATE('2024-10-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 110, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '촬영 후 보정 작업의 중요성', '사진과 영상 촬영 후 보정 작업의 중요성과 이를 위한 기본 툴 사용법을 설명합니다.', '/images/mentor-board/사진영상제작04.jpg', TO_DATE('2024-09-18 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 95, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '자연광을 활용한 촬영 기법', '자연광을 활용해 감성적인 사진과 영상을 제작하는 기술과 사례를 소개합니다.', '/images/mentor-board/사진영상제작05.jpg', TO_DATE('2024-12-20 18:15:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 27);


/* 연기/연극 category 14번 member 24번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '연기와 연극의 세계', '연극과 연기 수업을 통해 자신을 표현하는 방법을 배워보세요. 전문적인 팁과 실질적인 조언을 제공합니다.', '/images/mentor-board/연기연극01.jpg', TO_DATE('2024-07-18 14:17:00', 'YYYY-MM-DD HH24:MI:SS'), 300, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 연기 기본기 연습법', '연기의 기본기를 다지고 자신감을 키우기 위한 연습 방법을 단계별로 설명합니다.', '/images/mentor-board/연기연극02.jpg', TO_DATE('2024-11-20 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 210, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '무대에서의 자신감 향상 비법', '무대 공포증을 극복하고 관객 앞에서 자신 있게 연기할 수 있는 노하우를 공유합니다.', '/images/mentor-board/연기연극03.jpg', TO_DATE('2024-09-22 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 275, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '연극 대본 분석과 캐릭터 이해하기', '연극 대본을 분석하고 캐릭터를 깊이 이해하여 더 나은 연기를 하는 방법을 소개합니다.', '/images/mentor-board/연기연극04.jpg', TO_DATE('2024-12-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 190, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '감정 표현의 다양성 연습법', '다양한 감정을 자연스럽게 표현할 수 있도록 도와주는 연습 방법과 팁을 제공합니다.', '/images/mentor-board/연기연극06.jpg', TO_DATE('2024-12-28 18:15:00', 'YYYY-MM-DD HH24:MI:SS'), 225, 1, 24);


/* 스타트업 category 16번 member 15번 */
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '스타트업 A to Z', '스타트업은 고수익과 고성장에 이미지가 강하지만, 고위험에 대한 얘기들이 많이 없습니다. 스타트업은 야생이고, 여기서 살아남기 위한 생존전략이 필요합니다.',  '/images/mentor-board/스타트업01.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 400, 1, 15);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '스타트업의 초기 자금 조달 전략', '스타트업의 초기 자금을 효율적으로 조달하는 방법과 성공 사례를 소개합니다.', '/images/mentor-board/스타트업02.jpg', TO_DATE('2024-02-15 10:45:00', 'YYYY-MM-DD HH24:MI:SS'), 320, 1, 15);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '팀 구성의 중요성과 성공적인 스타트업 팀 만들기', '스타트업에서 성공적인 팀 구성 방법과 효과적인 팀 운영 전략을 다룹니다.', '/images/mentor-board/스타트업03.jpg', TO_DATE('2024-05-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 275, 1, 15);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '스타트업을 위한 고객 개발 방법론', '스타트업이 시장에서 성공하기 위해 고객의 요구를 이해하고 해결책을 제시하는 방법론을 설명합니다.', '/images/mentor-board/스타트업04.jpg', TO_DATE('2024-08-10 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 350, 1, 15);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '스타트업 성장의 핵심 - 데이터 기반 의사결정', '데이터를 기반으로 비즈니스 의사결정을 내리고 성장 전략을 수립하는 방법을 공유합니다.', '/images/mentor-board/스타트업05.jpg', TO_DATE('2024-10-18 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 400, 1, 15);


/* 마케팅전략 category 17번 member 28번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '효과적인 마케팅 전략', '마케팅 전략의 기본과 실제 성공 사례를 통해 효과적인 전략을 설계하는 방법을 제공합니다.', '/images/mentor-board/마케팅전략01.jpg', TO_DATE('2024-10-14 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 100, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '디지털 마케팅의 핵심 요소', '디지털 마케팅의 성공을 위한 핵심 요소와 실전 적용 방법을 다룹니다.', '/images/mentor-board/마케팅전략02.jpg', TO_DATE('2024-03-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '브랜드 인지도를 높이는 방법', '브랜드 인지도를 효과적으로 높이기 위한 전략과 성공적인 사례를 소개합니다.', '/images/mentor-board/마케팅전략03.jpg', TO_DATE('2024-05-20 14:45:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '소셜 미디어를 활용한 마케팅 전략', '소셜 미디어 플랫폼을 활용해 효과적으로 고객과 소통하고 마케팅 성과를 극대화하는 방법을 설명합니다.', '/images/mentor-board/마케팅전략04.jpg', TO_DATE('2024-08-15 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '타겟 고객 분석과 맞춤형 전략', '타겟 고객을 분석하고 그에 맞는 맞춤형 마케팅 전략을 세우는 방법을 공유합니다.', '/images/mentor-board/마케팅전략05.jpg', TO_DATE('2024-11-01 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 225, 1, 28);


/* 법률특허상담 category 18번 member 18,19,20,21번 */
-- member_no 18번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '특허 출원의 기본 절차', '특허 출원의 기본 단계와 주의할 점을 단계별로 설명합니다.', '/images/mentor-board/법률특허상담01.jpg', TO_DATE('2024-03-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 18);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '지식재산권 보호 전략', '기업과 개인이 지식재산권을 보호하기 위해 필요한 전략과 사례를 공유합니다.', '/images/mentor-board/법률특허상담02.jpg', TO_DATE('2024-06-15 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 18);

-- member_no 19번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '특허 분쟁 사례와 해결 방안', '최근 특허 분쟁 사례를 통해 법적 대응 전략을 알아봅니다.', '/images/mentor-board/법률특허상담03.jpg', TO_DATE('2024-07-12 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 19);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '상표권 등록과 활용 방법', '상표권 등록 절차와 이를 활용하여 브랜드를 보호하는 방법을 안내합니다.', '/images/mentor-board/법률특허상담04.jpg', TO_DATE('2024-08-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 19);

-- member_no 20번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '계약서 작성의 필수 요소', '법적 분쟁을 방지하기 위한 계약서 작성 시 필수 고려 사항을 다룹니다.', '/images/mentor-board/법률특허상담05.jpg', TO_DATE('2024-09-01 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 20);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '지식재산권 분쟁 예방 방법', '지식재산권 분쟁을 미리 예방하기 위한 법적 조치와 사례를 소개합니다.', '/images/mentor-board/법률특허상담06.jpg', TO_DATE('2024-09-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 190, 1, 20);

-- member_no 21번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '특허 검색과 분석 기법', '특허를 검색하고 분석하는 방법과 이를 활용한 기술 개발 전략을 안내합니다.', '/images/mentor-board/법률특허상담07.jpg', TO_DATE('2024-10-15 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 220, 1, 21);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '기업을 위한 법적 리스크 관리', '기업이 법적 리스크를 사전에 관리하고 분쟁을 방지하는 전략을 소개합니다.', '/images/mentor-board/법률특허상담08.jpg', TO_DATE('2024-11-10 15:45:00', 'YYYY-MM-DD HH24:MI:SS'), 180, 1, 21);


/* 피트니스 category 23번 member 29번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '운동 성과를 극대화하는 피트니스 팁', '효과적인 피트니스 계획 수립 및 운동 루틴 조정을 통해 성과를 높이는 방법을 소개합니다.', '/images/mentor-board/피트니스01.jpg', TO_DATE('2024-03-19 15:45:00', 'YYYY-MM-DD HH24:MI:SS'), 379, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '근육 성장에 최적화된 운동 방법', '근육 성장을 위해 필수적인 운동 루틴과 영양 섭취 요령을 다룹니다.', '/images/mentor-board/피트니스02.jpg', TO_DATE('2024-04-10 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '다이어트를 위한 효과적인 유산소 운동', '체중 감량을 위한 유산소 운동의 선택과 지속 가능성을 높이는 팁을 제공합니다.', '/images/mentor-board/피트니스03.jpg', TO_DATE('2024-05-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 310, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '피트니스 초보자를 위한 가이드', '초보자들이 운동을 시작할 때 유의해야 할 사항과 단계별 운동 계획을 소개합니다.', '/images/mentor-board/피트니스04.jpg', TO_DATE('2024-06-20 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 285, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '체형 교정을 위한 스트레칭과 운동법', '체형 교정에 효과적인 스트레칭과 운동법을 설명하며 바른 자세 유지 방법을 소개합니다.', '/images/mentor-board/피트니스05.jpg', TO_DATE('2024-08-05 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 295, 1, 29);


/* 요가/필라테스 category 24번 member 37번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '요가와 필라테스로 건강한 몸 만들기', '요가와 필라테스의 기초와 자세 교정을 통해 건강과 유연성을 개선하는 방법을 공유합니다.', '/images/mentor-board/요가필라테스01.jpg', TO_DATE('2024-08-06 19:37:00', 'YYYY-MM-DD HH24:MI:SS'), 337, 1, 37);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 요가 입문 가이드', '요가를 처음 시작하는 사람들을 위한 기초 자세와 호흡법을 소개합니다.', '/images/mentor-board/요가필라테스02.jpg', TO_DATE('2024-08-10 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 220, 1, 37);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '필라테스로 코어 근육 강화하기', '필라테스를 통해 코어 근육을 강화하고 자세를 개선하는 효과적인 운동법을 다룹니다.', '/images/mentor-board/요가필라테스03.jpg', TO_DATE('2024-09-01 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 280, 1, 37);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '요가와 명상을 통한 스트레스 해소법', '요가와 명상의 조합으로 스트레스를 해소하고 마음의 평화를 찾는 방법을 공유합니다.', '/images/mentor-board/요가필라테스04.jpg', TO_DATE('2024-09-15 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 310, 1, 37);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '필라테스를 활용한 체형 교정', '필라테스를 통해 체형 교정과 유연성을 높이는 효과적인 자세와 운동법을 소개합니다.', '/images/mentor-board/요가필라테스05.jpg', TO_DATE('2024-10-05 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 250, 1, 37);


/* 식단/영양 상담 category 25번 member 25번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '건강한 식단과 영양 상담', '건강한 몸을 유지하기 위한 식단 설계와 영양학적 조언을 제공합니다. 목표 달성을 돕는 맞춤형 가이드입니다.', '/images/mentor-board/식단영양상담01.jpg', TO_DATE('2024-10-23 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 185, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '체중 감량을 위한 건강한 식단 설계', '체중 감량 목표를 달성하기 위해 필요한 식단 구성과 영양 균형을 소개합니다.', '/images/mentor-board/식단영양상담02.jpg', TO_DATE('2024-11-01 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 210, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '운동과 함께하는 고단백 식단', '운동 후 회복과 근육 성장을 지원하는 고단백 식단 계획을 다룹니다.', '/images/mentor-board/식단영양상담03.jpg', TO_DATE('2024-11-10 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 230, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '식단 관리와 건강 유지의 비밀', '식단 관리가 건강에 미치는 영향을 분석하고 이를 실천하는 방법을 소개합니다.', '/images/mentor-board/식단영양상담04.jpg', TO_DATE('2024-11-20 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 190, 1, 26);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '특수 목표를 위한 맞춤형 식단', '다양한 건강 목표(체중 증가, 체중 유지, 질병 예방 등)에 맞는 맞춤형 식단 설계 방법을 다룹니다.', '/images/mentor-board/식단영양상담05.jpg', TO_DATE('2024-12-05 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 26);



/* 팔로우 등록 */
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,1,20);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,8);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,9);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,10);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,11);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,12);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,13);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,14);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,15);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,16);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,17);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,3,2);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,4,2);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,5,7);

/* 신고 등록 */
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 1, 1,'욕함요', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'MEMBER' , 1, 3,'광고쟁이', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 1, 4,'욕쟁이', sysdate, 1, sysdate , 3);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 2, 1,'나쁜사람', sysdate, 1, sysdate , 4);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'MEMBER' , 5, 3,'스팸', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 2, 4,'별로임', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 3, 4,'욕쟁이', sysdate, 1, sysdate , 3);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 4, 1,'나쁜사람', sysdate, 1, sysdate , 4);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'MEMBER' , 9, 3,'스팸', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 7, 4,'별로임', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 8, 4,'별로임', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 6, 4,'욕쟁이', sysdate, 1, sysdate , 3);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 6, 1,'나쁜사람', sysdate, 1, sysdate , 4);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'MEMBER' , 16, 3,'스팸', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 11, 4,'별로임', sysdate, 1, sysdate , 1);

/* 질문 등록 */
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '집합건물 총무의 관리비 사기 이슈', '안녕하세요. 성남시 분당구 야탑동 일반 상가입니다. 2층에 실평수 15평의 점유중인 임차인입니다. 현재 2년 가까이 임차중입니다만 3층에 1개호실 점유자가 본 건물을 5년 가까인 총무역할을 하고 있습니다. 사업은 에스컴 같은 사설 보안cctv경호를 하고 잇는데, 제가 입주 당시 난데없이 총무라고 소개하면서, 자신의 보안카메라 또는 에어컨 실외기를 달라는 권유와 강요를 3차례 이상해오며, 사무실 문을 자기 집 들어오듯이 열고 들어오는 행위등 소위 말해. 주거침입 범죄을 해왓습니다. 관리비 고지서를 문자 또는 문틈에 끼워두면 되는데. 그걸 빌미로 사무실 부재중이고. 문이열렷을때. 안에 들어와 책상에 두고 가는 짓을 하엿습니다.

우선 본론으로 이 총무가 관리비를 자기 계좌로 받으며. 건물수리등응 자가로 수리하며. 기타지출증빙 자료 일체 없으며, 지출내역 보고등. 일체하지 않습니다. 이미 공동주택관리법 위반으로 경찰에 사기로 고소하엿지만. 집합건물애 해당하여 각하되엇습니다

명백한건. 총무가. 임차인을 상대로 사기 및 횡령한 금액이 매월 최소50만원에서 최대 백만원은 충분히 입증할수 잇습니다.

이 교활한 잡범을 반드시 엄벌하고 싶습니다',sysdate,1,411,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '노가다 총무 재직증명서 발급 거절', '4대보험 들어가있는 회사이구요
입사하자마자 재직증명서 신청 했더니
몇일 일하고 신청 하라고 거절하더라구요?
그래서 몇일 일하고 신청 했더니 이젠 카톡 읽고 씹고
답장도 안하네요?
은행. 업무로 신청하는대 은행명이랑 사유 묻고있고

현장에서 근무 하는 총무인데요 전화번호랑 회사명은 알고있고 퇴사후에 신고하려고 하는데요이분때매 제가 일 하루 쉬고 은행가야되게 생겨서요.

이 총무 신고 하려면 고용노동부 가면 되나요??
카카오톡 보내도 읽고 씹고 그러는 내용은 있네요',sysdate,1,851,2,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '인사총무관리사 자격증', '안녕하세요
인사팀쪽 일을 하다가 인사총무관리사 자격증을 알아보고있는데요
민간자격증이라 해도 취득하면 나름 업무적으론 도움이 될 것 같아서 취득하려고 하는데요
어디서 자격증 취득해야할까요 ?',sysdate,1,864,2,4);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '장원급제의 정확한 뜻은 무엇인가요?!', '장원급제 뜻에 대해 아래 내용이 궁금해요.

• 장원급제의 정확한 뜻은 무엇인가요?
• 역사적으로 유명한 장원급제자는 누가 있나요?
• 장원급제를 한 사람이 조선시대에 맡았던 직책은 무엇인가요??',sysdate,1,351,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문대학교 총무활동 근로장려금
', '제가 대학교에서 총무 활동을 하고 있어 제 계좌로 큰돈이 왔다갔다 합니다.
이게 근로 장려금이 지급되는 액수와 큰 관련이 있을까요?
만약 관련 또한 있다면 해결방법도 알려주세요ㅠㅠ',sysdate,1,984,2,4);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문총무와 재무의 차이점', '회사 직무에서 총무와 재무가 있는데
두 개의 차이점이 뭔가요',sysdate,1,144,2,5);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문종중 총무임의 로 임야매매', '종중 총무가 임으로 임야 매매를 했어요
개인뿌로가 한테요(자격 없읆)
5억에 샀다기에 등본으떼어보니 3억이에요
맨처음 다운 계역서 라드니 3일후 나무값이 2억아라고 매매서류를 만들어 왔어요
매매 무효화 하고  나무값 2억을 되돌려 바을수 있을까요
임원 및 이사들 모르게 거래를 햇어요  ',sysdate,1,1114,2,5);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '총무 현금영수증(소득공제용, 지출증빙용)', '이번 5월에 입사했고 총무 처음해봐서,,현금영수증을 제 번호로 끊어버렸어요..이러면 안 된다는 걸 지금 알아버렸습니다. 그래서 용도 변경을 하려면 홈택스에 들어가면 가능하다해서 소득공제용 현금영수증 끊은 내역을 보니 안 떴더라고요?? 이런 경우에는 아직 반영이 안 된건지, 아니면 소득공제용으로 끊긴게 아닌건지 궁금합니다 만약 소득공제용으로 끊겼을 경우에 다시 갔던 식당에서 지출증빙용으로 다시 현금영수증 달라고 하면 안되는 부분인지 궁금합니다 ㅠㅠㅠㅠㅠ 
+제가 4월에 첫 월급을 받고 5월에 일을 하며 현금영수증을 제 번호로 현금영수증을 끊었는데요 그래서 부모님 연말정산 세금 감면에 쓰여서 내역에 안뜨는 경우인건지도 궁금합니다. ',sysdate,1,844,2,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '위촉직 총무 실업급여 수급자격문의', '보험사에 위촉직(특고직/설계사코드사용)총무로 고용보험 가입 후 2년간 근무하고 계약 만기로 퇴사 예정입니다.
위촉직이어도 해당 계약서와 고용보험 상실시 계약만기사유라는게 확인되면 실업급여 수급가능한가요? 수급이 가능하다면 센터 방문시 필요서류 함께 문의드립니다.(해촉증명서 등)
고용보험',sysdate,1,584,2,5);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '인사총무관리사 자격증 공부하려고 하는데요.', '안녕하세요! 인사과를 목표로 하는 취준생입니다.
이력서 쓰다 보니 관련한 스펙이 부족한 것 같아서
인사총무관리사 자격증 준비를 해볼까 하는데요.

혹시 인사총무관리사 자격증도 인강으로 딸 수 있는 건가요?

기간은 보통 어느 정도 걸릴까요?',sysdate,1,444,2,7);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '영업신고 및 사업자등록 관련 부가세 문의', '오픈 준비중에 있습니다.
영업신고증은 12월 20일쯤 신청해서 받을 생각이고
간이사업자는 1월에 사업자를 내는게 좋다고해서
사업자등록증 신청은 25년 1월 2일에 할 예정인데

영업신고증 날짜와 상관없이 사업자등록(개업)이 1월이면
25년 1월 부가세 신고는 안해도 되는걸까요?

그리고 만약 사업자등록을 올해 12월에 같이해버리고
신청시 개업연월일을 25년 1월달로 작성하면
25년 1월에 부가세 신고를 안해도되나요? 아니면
신청이 24년 12월이니까 25년 1월 부가세 신고를 해야하나요?',sysdate,1,984,3,5);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '영업방해및 협박죄 성립가능한가요?', '안녕하세요. 질문하겠습니다.
저는 숙박업을 하고있습니다. 저희 전 직원이 약 4년을 일을 하면서 저희 회사돈을 횡령을 했습니다,
그래서 관할 경찰서에 신고를 했지만, 그 직원의 주소가 불명이라 잡지 못하고 있습니다.
그런데 문제는 여기서 인데요
그 직원이 일을 하고 횡령한 사실을 알고 월급을 받는 동시에 도망을 갔습니다.,
9일간 돈을 안준 상태입니다. 그 직원은 돈을 주지 않았다는것으로 노동청에  신고를 했었구요
그래서 2번 조서를 받으러 출석을 안했습니다. 그래서 노동청에서 수사가 종료가 된상태입니다
근데 그 직원이 저희 홈페이지에 글을 남기고 있습니다. 나쁜말들로 악용하고 있습니다.
그뿐만이 아니고 저희숙박업소에 방이 남아있는곳에 임의적으로 예약을 하고있습니다.
다른 손님들 예약을 못하게 방해를 하고있습니다.(증거는 차고 넘칩니다)
이 부분이 영업방해죄 성립이 되나요?
사이버 수사대로 신고를 할수있는부분일까요? 그 사람이 주소로도 못찾고 연락처도 수시로 바꿔서
잡지를 못하고 있는데. 어떻게 해야될까요?
또한 저희 회사 통장에 돈을 1원씩 넣으면서 (언제까지 돈을 넣어라)(안그러면 신고한다등)
협박을 하고 있습니다.
저는 답변 또한 그 직원에게 해주지도 못하는 상황입니다.
이또한 협박죄도 가능한가요?',sysdate,1,284,3,13);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '영업방해죄 성립 요건과 처벌 수위는 어떻게 되나요?', '최근 가게 앞에서 큰 소리로 욕설을 하며 소란을 피우는 사람 때문에 영업에 큰 피해를 입었습니다. 이 경우 영업방해죄로 고소할 수 있나요? 만약 가능하다면 영업방해죄 성립 요건과 처벌 수위는 어떻게 되는지 알고 싶습니다. 또한 증거 수집 방법과 절차도 함께 알려주시면 감사하겠습니다.',sysdate,1,84,3,14);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '미용업 영업신고 시 필요한 사항은 무엇인가요?', '미용실 영업신고할 때 필요한 서류는 무엇이 있나요?',sysdate,1,147,3,21);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문병원영업정지 해결 원해요', '남편이 운영하는 병원이 광고법위반으로 병원영업정지 당할 위기에 놓여있습니다. 
업체 통해서 광고를 해왔고 문제 없다고 들었는데 당황스럽습니다...
도와주실 변호사님 찾습니다..',sysdate,1,84,3,41);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, 'IT 개발 대금 미지급 문제와 사기죄 고소 가능성', '안녕하세요, IT 개발사를 운영하고 있습니다.
저희가 올해 2월에 홈페이지 개발 건을 수주 받은 것이 있는데,
12월이 다 되도록 잔금을 이체하지 않고 있습니다.
계약은 총 500만원으로, 잔금은 250만원이 남은 상태입니다.

잔금을 지급하지 않는 이유는 한결같이 최종 결과물이 마음에 들지 않는다는 것입니다.
잔금 지급 요청도 벌써 5번 넘게 했으며,
수정 요청 해달라고 할 때마다 수정해서 디자인 전면 갈아 엎기만 3 차례가 넘습니다.
별도의 유지보수 계약 없이 계속 인력 소요가 들어가니 벌써 적자인 프로젝트입니다.

홈페이지에 오타가 많고, (어디에 오타있는지 설명도 안함)
이미지가 마음에 들지 않으며 (추가로 주지도 않음)
레이아웃 변경을 수없이 요구합니다.
그걸 다 해야 잔금을 준다는 식인데, 벌써 몇번의 수정을 거쳤는데도 주지 않습니다.
너무 지쳐서 이제 고소하고 싶은데, 승소 가능성이 있나요?
큰 돈은 아니지만 2월부터 지금까지 너무 힘이 듭니다.',sysdate,1,847,4,14);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, 'IT 웹 개발 위탁학교 알려주세요', '위탁학교 고민중인에 it웹 개발 위탁학교 알려주세요',sysdate,1,824,4,16);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발 IT국비지원 온라인 강의 문의입니다', '안녕하세요

현재 내일배움카드를 신청하고 강의를 신청하려 하고 있습니다

제가 7년정도전에 이민을 와서 혹시 웹개발 IT국비지원 온라인 강의 아시는 것 있으시면 알려주시면 감사하겠습니다

감사합니다',sysdate,1,1284,4,5);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '중학생 백반증, 한의원 치료 효과 있을까요?', '강남 압구정역 10대 중반/여 백반증

저는 중학생 딸아이를 둔 엄마입니다. 몇 달 전부터 아이 팔과 다리에 하얀 반점이 생겨 병원에 갔더니 백반증 진단을 받았습니다.



여러 치료 방법을 알아보다가 한의원 치료가 백반증에 효과적이라는 이야기를 들었습니다.



아이가 아직 어리고, 민감한 피부를 가지고 있어서 어떤 치료가 가장 안전하고 효과적일지 고민이 됩니다.



한의원에서는 아이들의 백반증을 어떤 방식으로 치료하고, 치료가 진행되면서 어떤 점에 유의해야 하는지 알고 싶습니다.



부모로서 아이의 백반증이 다시 생기지 않도록 관리하는 방법도 궁금합니다.',sysdate,1,384,6,22);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '중학생보약도 효과가 좋을까요?', '청담 10대 중반/여 공진단

키성장에도 도움이 되고 아이 공부하는데 도움이 된다면 중학생이어도 보약은 필요할 것 같아요 근데 비쌀까요?',sysdate,1,774,6,41);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '중학생도 코수술 할 수 있나요?', '중학생인데 부모님 허락받고 코수술 하신 분 있으신가요..?
병원에서 아예 안 받아주나요?',sysdate,1,984,6,15);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '농구선수 중학생 때 키', '남자 농구선수들 중학생 때는 키가 어느정도 되어야 할까요?',sysdate,1,1025,6,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '중학생 학폭 소송', '중학생 학폭했을때 소송같은거 해서 합의금 물어줘야 하면
부모가 그냥 배째라는식으로 안줄수도있나요?',sysdate,1,887,6,7);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '고등학생성추행 어떻게해야할까요', '고등학생성추행 피해자의 부모입니다. 같은반 학생에게 딸이 성추행을 당했습니다. 고등학생성추행은 처벌이 어려우려나요? 그냥 덮고 묻어주고 잊어야하는게 맞는지 어디까지 가야할지 모르겠습니다.',sysdate,1,151,7,36);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '전화 추합이 마지막 날 제일 많이 빠지나요?', '전화 추합이 마지막 날 제일 많이 돌고 빠질까요ㅠㅠ??? 정말 피가 마르네요…',sysdate,1,1118,7,8);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '2024 SBS 가요대전 순서별 예측 시간', '안녕하세여! 저는 스트레이키즈 분들의 무대만 보고 싶은데 정확한 시간을 몰라서 글을 올립니다.. 가요대전을 보는 게 이번이 처음이라서.. 대략 몇시에 시작할 지도 잘 몰라서ㅜ 스키즈 븐들 무대가 3부에 될 거 같은데.. 언제 시작할 까요?? 큐피드 사진 빨갛게 밑줄 친 데가 스키즈분들 순서 입니당..',sysdate,1,97,7,34);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '고등학생 크리스마스 서울 데이트장소 추천', '오늘부로 100일된 커플입니다
서울에서 데이트 할 예정인데
뭐하고 놀아야할지 감이 잘안잡혀 질문 올립니다
크리스마스에 여자친구랑 재밌게 놀만한 장소나
분위기 좋은곳 추천해주시면 정말 감사하겠습니다',sysdate,1,222,7,37);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '대학입시 연기', '현재 05년생이고 대학입시를 준비중입니다. 내년에 삼수를 하게되면 어떻게 연기신청을 해야되나요? 수능접수증을 준비하면 되나요? ',sysdate,1,6,8,31);

insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '학폭대학입시 영향있을까요?', '학폭대학입시 문제로 걱정입니다...

제가 학교폭력을 하게 되어 학폭위가 열리는데
만일 이게 생기부에 남으면 영구적으로 남는 걸까요?
학폭대학입시 지장이 갈 정도일까요?',sysdate,1,105,8,36);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '04년생 대학입시 사유로 입영연기', '04년생인데 대학입시로 입영 연기가 된다고 하는데 대학입시서류..? 를 어디서 떼오나요..?',sysdate,1,417,8,26);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '일본 대학 입시', '지금 현재 고삼이고 수시2차 결과랑 추합 기다리고 있습니다 제가 학과를 일어과로 다 넣었는데 만약에 다 떨어지면 일본 대학을 갈 계획인데 일본대학 입시에 대해서 알려주실 수 있나요..? 내신성적이 좋지는 않지만 넣은 대학은 명전 배화 한양여대입니다 일본대학 전문대학 또는 사년제 대학 입시에 대해서 알려주실 수 있나요..? 준비과정이나 어떤 걸 해야하는지 지금 현재 jlpt를 준비하고 있습니다 (일본대학에 어떤 전형이 있는지도 알려주세요ㅜㅜ) 부탁드립니다….',sysdate,1,888,8,24);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '음악 저작권 어떻게 되나요?', '블로그에 음악을 올리려고 하는데 저작권 문제가 걱정됩니다. 음악 저작권법 위반 시 처벌 수위와 함께 합법적으로 음악을 사용하는 방법에 대해서도 알려주시면 감사하겠습니다. 상세한 답변 부탁드립니다.',sysdate,1,777,10,16);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '전문대 실용음악과', '실용음악과있는 전문대학교가 어디인가요?',sysdate,1,333,10,21);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '음악교육과...', '음악교육과 진학 원하는 예비 고1학생인데요
1.음악교육과는 생기부를 안 보나요? 서원대는 안 본다고 하던데 제 세대부터 교육체제가 바뀌어서 잘 모르겠어요
2.음악교육과 있는 대학교에 내신컷이 어느정도 되나요?
3.실기 준비는 언제 부터 해야할까요? 입시 학원을 다니자니 아무래도 음악교육과니깐 공부를 우선으로 해야할거같아서요
4.만약 음악교육과에 진학 하고 나서 임영고시를 치면 음악선생님이 되는건데 임용고시는 많이 어렵나요….??',sysdate,1,1441,10,23);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '한국교원대 음악교육과 편입', '타 대학 음악교육과에서 한국교원대 음악교육과로 편입할 수 있나요 ?? 한국교원대 홈페이지 편입요강에는 없던데 된다면 어디서 전형 확인할 수 있을까요 ..?',sysdate,1,3133,10,27);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '소방공무원 실용글쓰기', '실용글쓰기 3%가 630점 맞나요? 710점이라 나와있는것도 있어서요 2025년 기준으로 알려주세요!',sysdate,1,733,11,31);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '통일 글쓰기 대회 같은 거 있나요?', '제목이 곧 내용!
현재 진행중인 대회도 좋고, 매해 마다 하는 대회여도 좋아요! 좀 알려주세요ㅠㅠ

(소설로 써도 되는 대회로!!)',sysdate,1,433,11,32);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '글쓰기 시작하려면', '글쓰기를 시작하고 싶은데 어떤 방법으로 시작을 하면 좋을지요?',sysdate,1,333,11,37);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '중2 글쓰기 수행평가 처음 끝', '중2 글쓰기 수행평가 처음이랑 끝을 어떤 설명방법을 사용해서 써야할 지 모르겠어요',sysdate,1,733,11,31);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '초등 미술 공모전', '곧잇으면 하는 미술 공모전이 잇는지 궁금합니다 !!
올해에 공모전 몇번 한 뒤로 재미있어서 계속 시도 하는 중인데
2025년 미술 공모전이라던가 아니면 지금 하고잇는 미술 공모전 좀 알려주세요 !! 주제는 상관 없어요 시간이 넉넉하고 초등학생 잼민이가 참여할 공모전이묜 됩니다 !',sysdate,1,133,12,11);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '광주미술학원 어디로 보내야할까요..?', '저희 아이가 미술에 관심이 많다고 미술쪽으로 보내달라고 얼마 전 이야기를 하더라구요. 평소에 그림 그린 것들도 보면 확실히 소질이 있어보이는 것 같아서 미술학원을 보내보고자 하는데 광주미술학원 어디가 잘 가르치나요? 되도록이면 입시 전문으로 하는 학원으로 보내고 싶습니다. 광주미술학원 정보 좀 주세요 ㅠ',sysdate,1,133,12,21);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '예고 미술선생님이 되는법', '전 중2인데 진로를 예고 미술선생님이 되고 싶습니다

1. 미술 선생님이 될려면 소묘나 이런 쪽 말고 애니매이션이런 만화 쪽으로 나와도 괜찮나요?

2. 미술선생님이 되려면 공부는 얼마정도 해야하나요?

3. 예고를 나오게 되면 미술선생님이 되는게 빨라지나요?

4. 미술선생님이 되기 까지 무엇을 해야하나요?

5. 지금 미술성적이 고등학교 진학에 도움이 되나요?',sysdate,1,533,12,22);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '백석예술대학교 미술학부', '안녕하세요 이번에 백석예대 미술학부에 대해 궁금한 게 있어서요
백석예대 미술학부 안에 전공이 네 개로 나눠지는 거 같은데
제가 수시 접수할 때는 전공 선택하는 거 없이 그냥 미술학부로만 있었거든요?
그럼 시스템이 미술학부에 들어간 후에 제가 전공을 선택하는 건가요?',sysdate,1,433,12,25);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '미술 학원을 운영하고 있는 평범한 사람입니다,ㅎ', '지식인에서 답변 해 주시는 거 보고
저도 궁금한 게 생각이 나서 질문 드려 봅니다.

현재 사업자 등록은
서비스교육업 사업자가 하나 있는 상태입니다.
제가 하고자 하는 것은 중국 사이트에서
미술재료를 구입 해+ 미술커리큘럼을 (본인 개인적으로 만듦)
금액 측정을 해서 판매를 보고 싶습니다.

3. 사업자 등록은 어떤 것으로 내야 하는지 궁금 합니다.
sns 홍보 후 네이버 결제창을 연결하여 판매 할 계획입니다.
5. 부가적으로 필요한 것들이 있을까요?ㅜㅜ

6. 중국에서 물건을 사입 해 올 때, 법규 안에 가능한 금액 대라던지 또는 얼마 이상이 되면 관세 또는 세금 신고를 해야 되는 건지 그런 건 어떻게 하는 건지도 궁금합니다^^',sysdate,1,1233,12,29);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '신분증 재발급 사진', '제가 신분증을 2번째 재발급 받아야 하는 상황인데
처음에 a사진 발급 받고 -> b사진으로 새로 재발급 받은 상태였는데 다시 a사진으로 재발급 가능하나요??ㅠ
b사진으로 발급 받은지 6개월이 지나버려서요
',sysdate,1,233,13,33);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '반려동물 사진 공모전', '반려동뭉 사진 공모전 참여해보고 싶은데
지금 하고 있는 공모전 없을까요? 지역은 광주에 살아요 ㅠ
고등학생이에여 주변에 공모전 수상해서 상금 받고 하더라구요
대학교 같은데에서 진행중인 공모전도 상관없어요 ㅠ',sysdate,1,933,13,13);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문민증 사진 수험표 사진으로', '민증 사진 너무 마음에 안 들어서 그런데 수험표에 넣었던 사진으로 민증 사진 해도 되나여',sysdate,1,833,13,23);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '대학로 연극 예매 할인 되는거 있나요?', '오랜만에 남자친구랑 대학로 연극보러 가려는데
대학로 연극 예매 할인 되는거 있나요?
재밌는거로 골라서 보고 싶은데
대학로 연극 예매 할인 받는 방법이나
정보 좀 알려주세용~',sysdate,1,833,14,23);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '연극 라면', '고3인데요.다음주에 아빠랑 연극 라면보러 갈건데 부모님이랑 같이보기에 괜찮나요?',sysdate,1,833,14,13);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '스타트업법률자문 받아야할까요?', '친언니는 대기업 출신이고 저는 일반 공무원을 하다가 둘다 그만둔 상태에요
인공지능 관련한 일을 같이 해보고 싶어서 같이 시작하려고 하는데 
스타트업에 경우 스타트업법률자문 꼭 받아가면서 일 시작하는게 좋을까요?
언니가 대기업 있을때는 변호사 도움 받아가면서 일 진행했다길래요 ㅎ
신생기업이라도 이런 부분 도움을 받으면서 시작하는게 좋을까 싶어서요. 
답변주시면 감사하겠습니다.',sysdate,1,833,16,28);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '스타트업특허를 준비중입니다', '최근 스타트업을 창설하게 되었는데
스타트업특허의 뜻과 특허시 이점을 알려주시면 감사하겠습니다.',sysdate,1,833,16,38);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '네이버 블로그 마케팅 효과있나요?', '음식점을 오픈했습니다 요즘에도 네이버 블로그 마케팅 효과가 있을까요? 인스타그램이 대세일거 같긴한데 여전히 블로그 리뷰와 방문자 리뷰가 경쟁사들이 많은 거 같고 요즘엔 어떤가요 블로그마케팅에서 제가 아는 건 체험단인데 이거 말고 또 좋은 네이버 마케팅 방법이 있을가요?.',sysdate,1,833,17,18);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '특허법률사무소 어떻게 고르는 건가요?', '마케팅은 어떤 직업이고 어떤일을 하나요?',sysdate,1,333,17,19);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '마케팅이 하는일', '특허법률사무소를 알아보고 있습니다. 특허 출원을 준비중인데 특허법률사무소가 정말 많더라고요.. 어떤걸 보고 골라야 하는지 막막하기도 하고 특허법률사무소는 어떻게 현명하게 고를 수 있는지 알려주세요!',sysdate,1,333,18,9);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '해외특허법률사무소 알아보고있습니다.', '회사 대표님꼐서 해외특허를 진행해보시자고 합니다.
회사에서 특허쪽으로 잘 알고있지못해
해외특허법률사무소 같은 곳에 의뢰를 맡겨야할 것 같은데요
어떻게 해외특허법률사무소를 알아보면 좋을까요?
금액은 어느정도 나올까요?',sysdate,1,133,18,10);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '피트니스 복싱 초기', '닌텐도 피트니스 복싱 미쿠 한 3일정도 2,30분씩 했는데 벌써 상체에 알배겼는데 괜찮은가요??? 고3이어서 운동을 전혀 아예 안하고 막 완전 난생 처음 운동 시작해서 근육이 좀 놀란걸까요? 팔ㅇ이 안올라가여ㅓㅜㅜㅋㅋㅋㅋ 관절빠지는거 아니겟죠?? 괜찮은걸까여ㅏ?? 이렇게 게임에서 시키는대로 무식하게 주먹질 해도 괜찮은걸까요? 스트레칭 해줘야하나여',sysdate,1,233,23,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '피트니스 광고 마케팅 어디가 잘하나요?', '피트니스 광고 마케팅 어디가 잘하나요?
잘하는곳중 하나로 선택해서 진행하려고 하는데 어디가 정말 좋은지 모르겠어서요
이용하시는곳 있으시면 공유 부탁드려요 ',sysdate,1,833,23,7);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '요가필라테스의 차이점은 어떤게 있을까요?', '요가와 필라테스는 모두 몸과 마음의 건강을 증진시키는 운동이지만 둘 사이에는 몇 가지 차이점이 있다고 들었습니다. 요가와 필라테스의 차이점은 무엇인가요? 또 각각의 운동이 가지는 장단점과 효과도 알려주시면 감사하겠습니다. 아울러 요가와 필라테스 중 어떤 운동이 저에게 더 적합할지 추천해 주시면 많은 도움이 될 것 같습니다. ',sysdate,1,123,24,9);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '도수치료와 필라테스?', '일자목 두통이 심각하게 심해서 필라테스를 꾸준히 해오다가 (필테를 하면 두통은 안와요) 치루수술 후 운동을 두달간 못하여 두통에 시달리는데요
도수치료와 필라테스를 같이 해도 되나요? 오전에 필테를 가고 오후에 도수치료를 받구요',sysdate,1,128,24,15);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '콜레스테롤 식단관리 어떻게 하죠?', '성남 50대 후반/여 콜레스테롤

콜레스테롤 식단관리 어떻게 할지 고민입니다.

제가 콜레스테롤 수치가 높아요.

어떤음식이 콜레스테롤을 낮추는데 도움이 될까요',sysdate,1,128,25,10);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '당뇨에좋은식단 추천해주세요(김해 당뇨)', '김해 40대 초반/남 당뇨

공복혈당이 전단계 수치로 나와서 이제 관리하려고 하는데요

일단 먹는 걸 조절해야 할 것 같은데 어떤 식단이 좋을지 추천해주세요',sysdate,1,128,25,19);




/* 답변 등록 */
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '

1. 상가 3층 1호실 점유자가 총무 역할을 하고 있는데, 총무 개인 계좌로 관리비 및 건물수리비를 받아서 지출 내역을 보고 하지 않았기 때문에,

2. 총무를 업무상 횡령죄 및 업무상 배임죄로 형사고소 하여, 처벌할 수 있습니다.

3. 상가 관리 분쟁 사건을 수행한 경험 있는 19년차 경력의 대방건설 법무실장 출신 형사법 및 민사법 전문 변호사와 함께, 형사 사건을 진행하여야, 총무를 형사처벌 시키고, 그동안 피해 입은 손해를 배상 받을 수 있습니다.


',sysdate, 2, 1, 2, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '근기법상 사용증명서는 30일 이상 재직하여야 발급이 가능하므로 말씀하신 기간 내 신청하였다면 회사는 발급 의무는 없습니다. 따라서 노동청에 신고할 사항이 있는 것으로 보이지는 않습니다.

추가 상담을 원하시면 상담(유료)을 신청하여주세요.

(댓글을 통한 추가상담은 하지 않으니 양해 부탁드립니다.)',sysdate, 2, 1, 11, 2);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요 :)

​

인사총무관리사란 인사 부서의 운영을 계획 및 조직, 지시하며 관리하는 역할을 맡으며, 각종 훈련과 채용활동을 조정하고 노사관계 정책 및 절차를 만들어 시행하며 단체교섭을 협상하는 전문가로서, 

임직원 업무지원 및 복지, 대/내외적 행사, 인적자원을 효율적으로 관리하는 담당자입니다. 

세부적으로는 총무부서의 전반적인 이해, 자산관리, 일반행정, 회사설립업무, 의전 및 행사진행, 복리후생, 시설물 관리 등 일의 범위와 양이 상당히 많으며, 인사총무는 업종(태)에 따라 핵심업무가 달라집니다.

​

한국심리교육협회에서 인사총무관리사 자격증 어렵지 않게 취득하실 수 있으시며,

자격기본법에 의거하여 국가기관인 한국직업능력연구원에 정식등록되어 이력사항 및 활용하실 수 있으시며,

온라인수강과 온라인시험으로 이루어져 있어 큰 부담없이 수강 및 시험응시가 가능하답니다.

​

교안/예상문안도 다운로드 받아보실 수 있으시며,

자격증 과정의 커리큘럼도 자세하게 잘 짜여져 있어 참고해주셔도 좋으실 것 같아요 :)

​

한국심리교육협회에서 현재 무료수강 이벤트가 진행중에 있으니 확인하시고 신청 진행 하시면 될 것 같습니다 :).',sysdate, 1, 2, 11, 3);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '​직업은 말그대로 평생 해야할 업이기에 잘 선택하셔야 해요

좀 더 수월한 자격증을 기반으로 준비하시는 분들도 많은거 같습니다

​

무엇도 나의 노력을 막을수 없다고 생각하는게 첫번째 인거 같습니다

자격증 종류도 보면 예전과는 다르게 정말 다양하게 많아진거 같습니다

​

늘 긍정적인 마인드와 노력이 함께 있다면 좋을결과가 늘 함께할걸로 생각이 되어지네요

새롭게 일을 시작하거나 공부를 시작하실때에 끈기는 정말 중요한부분인거 같더라구요',sysdate, 1, 1, 2, 3);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '[질문]

​대학교 총무활동 근로장려금

​

제가 대학교에서 총무 활동을 하고 있어 제 계좌로 큰돈이 왔다갔다 합니다.

이게 근로 장려금이 지급되는 액수와 큰 관련이 있을까요?

만약 관련 또한 있다면 해결방법도 알려주세요ㅠㅠ

​

[답변]

안녕하세요. 근로장려금 제도에 대해 문의해주셨네요.

저도 근로장려금 제도에 대해 관심이 많아서 정보를 찾아보고 있습니다.

제가 알고 있는 정보를 바탕으로 답변을 드리겠습니다.

​

대학교 총무 활동으로 인해 계좌에 큰 금액이 입출금되는 상황이라면, 근로장려금 지급 심사에 영향을 미칠 수 있습니다.

​

왜 영향을 미칠까요?

소득 판단 기준: 근로장려금은 저소득층을 위한 지원 제도이므로, 소득 기준을 엄격하게 적용합니다. 총무 활동으로 인해 계좌에 큰 금액이 오고 가면, 이를 소득으로 간주하여 근로장려금 지급 대상에서 제외될 수 있습니다.

자금 출처 소명: 총무 활동으로 인한 입출금 내역은 단순히 통과금일 수도 있지만, 근로장려금 심사 과정에서는 이 자금의 출처와 용도를 명확하게 소명해야 할 수 있습니다.

​

해결 방법

자금의 성격 명확히 밝히기:

통장 내역 정리: 총무 활동으로 인한 입출금 내역을 정리하여, 단순히 통과금임을 증명할 수 있는 자료를 준비합니다. (예: 회계 장부, 영수증 등)

자금의 용도: 총무 활동 자금의 사용 내역을 상세하게 기록하여, 개인적인 소득으로 사용되지 않았음을 증명합니다.

세무사 상담: 복잡한 소득 신고와 관련된 문제는 세무사에게 상담하여 전문적인 도움을 받는 것이 좋습니다.

국세청 문의: 근로장려금 심사 기준이나 필요한 서류 등에 대해 국세청에 직접 문의하여 자세한 안내를 받을 수 있습니다.

​

이상으로 근로장려금 제도에 대한 답변을 마치겠습니다.',sysdate, 2, 1, 2, 5);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, ' 종중 총무가 임으로 임야 매매를 했어요

개인뿌로가 한테요(자격 없읆)

5억에 샀다기에 등본으떼어보니 3억이에요

맨처음 다운 계역서 라드니 3일후 나무값이 2억아라고 매매서류를 만들어 왔어요

매매 무효화 하고  나무값 2억을 되돌려 바을수 있을까요

임원 및 이사들 모르게 거래를 햇어요  

==> 종관에 매매 절차가 규정되는데 이러한 경우 일반적으로 종관 회의에서 결정을 한 후 매도하는 순으로 진행됩니다. 총무가 이러한 절차를 생략한 것으로 보이고, 매매계약 무효처분을 할 수 있으나 이러한 경우 총무는 사기죄, 사문서 위조 및 행사죄 등으로 형사처벌대상입니다.',sysdate, 1, 1, 2, 7);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '혹시 현금영수증 발급받은 번호를 현금영수증에 등록하셨을까요?

​

만약 안했다면 번호등록 후 다음날 조회될거니 그때 용도 변경하시면됩니다.

​

그래도 안될 경우

다시 발급하면 허위매출이 올라가니까 그 방법은 아닌거 같고.

​

업체사장님께 위임받으셔서 서류들고 세무서에서 정정요청하는 방법도 있습니다.',sysdate, 2, 1, 2, 8);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '이번 5월에 입사했고 총무 처음해봐서,,현금영수증을 제 번호로 끊어버렸어요..이러면 안 된다는 걸 지금 알아버렸습니다. 그래서 용도 변경을 하려면 홈택스에 들어가면 가능하다해서 소득공제용 현금영수증 끊은 내역을 보니 안 떴더라고요?? 이런 경우에는 아직 반영이 안 된건지, 아니면 소득공제용으로 끊긴게 아닌건지 궁금합니다 만약 소득공제용으로 끊겼을 경우에 다시 갔던 식당에서 지출증빙용으로 다시 현금영수증 달라고 하면 안되는 부분인지 궁금합니다 ㅠㅠㅠㅠㅠ 

+제가 4월에 첫 월급을 받고 5월에 일을 하며 현금영수증을 제 번호로 현금영수증을 끊었는데요 그래서 부모님 연말정산 세금 감면에 쓰여서 내역에 안뜨는 경우인건지도 궁금합니다. 

​

​

질문주신 말씀에 답변드려봅니다

​

용도 변경 가능 여부 확인 후 재발급 요청 가능.',sysdate, 1, 1, 11, 8);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요. 한국공인노무사회-네이버 지식iN 상담 공인노무사 김정식 입니다.

​

총무는 근로자로 처리하는 것이 옳을 듯한데, 설계사코드로 가입되었다면 서류상 특고로 적용될 듯합니다. 회사에서 이직확인서를 제출하면, 본인은 신분증만 가져가면 됩니다. ',sysdate, 2, 1, 2, 9);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요! 저는 국제자격검정원에서 취득했는데

강사진, 강의 퀄리티 모두 훌륭해서 추천하고 싶네요 :)

​

인사과 목표 취준생이시라고 하니 인사총무관리사 

자격증 준비하시는 거 정말 현명한 결정인 것 같아요.

​

수업은 5주 과정이고, 시험은 100점 만점에 60점 이상이면 합격인데요.

​

통합 강의라 합격 시 1급이랑 2급 인사총무관리사 자격증을 동시에 받으실 수 있어요.

​

정식으로 등록된 자격기관에서 발급되는 것이라서 

이력서에 스펙으로 인정되도록 정식 기재가 가능해

취업하실 때 도움이 되는데요.

​

교육 재능 기부 차원으로 수강료, 교재비, 응시료를 무료로 

해 주고 있어서 부담 없이 공부하실 수 있는 기관입니다.

​

엄선한 채용공고가 매일 업데이트 되는 

취업정보센터도 지원하는 곳이니 고려해 보시길 바래요 :)',sysdate, 1, 1, 11, 10);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '사업자등록증상 개업연월일이 개업일이 됩니다. 따라서 사업자등록신청은 12월에 하더라도 개업연월일이 25년1월이라면 24년도분 부가세 신고(25년1월25일 신고)는 하지 않아도 됩니다. 다만, 12월 중에 오픈하여 실제적인 매출이 있다면 신고를 하는 것이 원칙입니다. 영업신고증 날짜는 부가세 신고와 직접적인 연관은 없습니다.

​

​

도움이 되셨기를 바랍니다.

​',sysdate, 2, 1, 30, 12);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '영업방해 및 협박죄 성립 가능성 여부 관련 질문에 대해 답변드리겠습니다.

​

현재 상황을 종합해 볼 때, 전 직원의 행위가 영업방해죄 및 협박죄에 해당될 가능성이 매우 높습니다.

​

1. 영업방해죄 성립 가능성

홈페이지에 악의적인 글 게시: 사업체의 명예를 훼손하고 고객의 이용을 저해하여 영업 활동을 방해하는 행위입니다.

임의적인 예약: 정당한 사유 없이 타인의 예약을 방해하여 영업 활동에 지장을 초래하는 행위입니다.

​

증거가 충분하다면 영업방해죄가 성립될 가능성이 매우 높습니다.

​

2. 협박죄 성립 가능성

돈을 1원씩 넣으면서 협박: 상대방에게 불안감이나 공포심을 유발하여 부당한 이득을 취하려는 행위입니다.

신고하겠다는 협박: 허위 사실 유포나 위협적인 언행으로 상대방을 압박하는 행위입니다.

​

이러한 행위는 명백한 협박죄에 해당됩니다.

​

3. 대응 방안

1) 증거 확보:

홈페이지에 게시된 글, 예약 내역, 통장 입금 내역 등 모든 증거를 확보해야 합니다.

협박 내용이 담긴 메시지, 통화 녹음 등도 중요한 증거가 될 수 있습니다.

2) 경찰에 신고:

관할 경찰서 사이버수사대에 신고하여 영업방해죄 및 협박죄로 고소해야 합니다.

확보한 증거를 제출하고, 피해 상황을 상세하게 설명해야 합니다.

3) 법률 전문가의 도움:

변호사와 상담하여 법적인 절차를 진행하고, 향후 발생할 수 있는 문제에 대비해야 합니다.

변호사는 증거 분석, 고소장 작성, 법원 절차 진행 등을 도와줄 수 있습니다.

​

4. 추가 조언

답변을 하지 마세요: 가해자에게 답변을 하면 오히려 더 큰 피해를 볼 수 있습니다.

SNS 등을 통한 확산 방지: 가해자의 글이 더 퍼지지 않도록 조치를 취해야 합니다.

민사 소송: 형사 고소와 함께 민사 소송을 통해 손해배상을 청구할 수도 있습니다.

​

주의사항:

자신의 감정을 조절하고 신중하게 대처해야 합니다.

법적인 절차에 따라 침착하게 대응해야 합니다.

전문가의 도움을 받아 문제를 해결하는 것이 좋습니다.

​

현재 상황이 매우 힘들겠지만, 너무 걱정하지 마시고 법적인 절차를 통해 문제를 해결하시기 바랍니다.',sysdate, 1, 1, 30, 13);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '미용실 영업신고 하기 전 영업장 장소에 대한 확인이 필요합니다.

미용실의 경우 근린생활 1종, 2종시설에만 가능하며 영업신고 가능여부는 관할 구청 위생과에서 확인이 가능하오니

부동산 계약 전 확인하시기 바랍니다.



영업신고 시 필요한 서류는 ①위생교육수료증 ②미용사면허증 ③신분증 지참하시어 구청 위생과로 방문하시면 됩니다.

별도의 수수료는 없지만 면적에 따라 부과하는 면허세는 있을 수 있으니 참고하시기 바랍니다.',sysdate, 2, 1, 12, 14);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '의료광고는 의료법에 의하여 엄격하게 다루어지고 있기 때문에, 잘못 광고를 진행한다면 병원영업정지 등의 행정적 처분이 내려질 수 있습니다.

​

대행업체를 통하여 광고를 진행했다 하더라도 최후 검토 과정을 거치는 과정에서의 책임이 발생하기 때문에 해당 업체에게 모든 책임을 물기는 힘든 부분이 있습니다.

​

어떤 부분에서 의료광고법을 위반하였는지 확인하시고 계약 및 광고 발행까지의 경위를 파악할 수 있도록 계약서와 업체와의 대화 내용을 검토하셔야 합니다.

​

그 이후에 영업정지 처분에 대한 불복 및 감경을 원한다는 의사를 표현하며 행정심판 등의 절차를 거치는 것이 좋지요.

​',sysdate, 2, 1, 30, 15);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요. 서울지방변호사회 소속으로 지식iN 법률상담을 진행하고 있는 김국일 변호사입니다.

​

생각보다 의료광고법이 매우 엄격하고 까다로운 데다가 의사라고 해서 이런 부분을 모두 파악하고 있는 것이 아니기 때문에 이러한 문제가 종종 발생하곤 합니다. 

​

의료광고법위반에 해당될 수 있는 행위로는 아래와 같습니다. 

​

1. 의료인이 아닌 사람이 의료에 대한 광고를 게시하거나, 홍보 활동을 하는 것 

​

2. 환자로 하여금 리뷰이벤트 등을 활용한 치료 후기 광고 

​

3. 타 병원의 치료를 비교하거나, 비방하는 홍보 

​

4. 수술이나 시술을 받은 후 부작용을 명시하지 않는 것 

​

5. 가격 할인, 교통 편의 제공, 알선 등 유인하는 행위 

​

의료광고법위반으로 병원영업정지를 받게 된다면 이에 더하여 형사처벌까지 받게 될 수 있으며, 거짓이 없는 내용이라고 하더라도 객관적인 사실이 아니라면 처벌대상이 될 수 있기 때문에 이점 유의하셔야 합니다. 

. 

또한 병원영업정지와 같은 행정처분이 이루어졌다면, 처분이 내려지기 전 신속하게 행정소송을 진행할 수 있어야 합니다. ',sysdate, 1, 1, 12, 15);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '1. 냉정하게 말씀드리자면 사기죄로 고소야 할 수 있지만, 형사고소하더라도 처벌이 어렵습니다.

2. 부득이 민사소송을 제기해야 하나 소가를 고려할 때 변호사 조력을 받을 경우 실익이 없습니다.

3. 그러므로 소송 전에 법리적 검토가 완료된 법무법인 명의의 내용증명을 발송하여 신속히 잔금을 지급할 것을 요구하는 한편, 요구에 불응할 경우 상대방을 사기로 고소할 것이고(실제 처벌 여부는 별론으로 하고 경고는 필요합니다), 민사적으로도 소송을 제기할 것이며 민형사 소송이 진행되면 상대방은 형사적으로는 처벌되고 민사적으로도 패소하여 원고(질문자님)의 소송비용까지 부담해야 한다는 점을 지적함으로써 소송 전에 문제해결을 도모할 수 있겠습니다.

내용증명 발송을 통해 민형사 분쟁을 신속하고 합리적인 비용으로 해결하는데 있어 단언컨대 압도적인 전문성을 보유하고 있습니다.
',sysdate, 2, 1, 31, 16);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, 'IT 웹 개발 위탁학교로는 인천중앙직업전문학교가

고용노동부가 선정한 4차산업 선도인력 양성기관으로

위탁학교들 중 유일하게 4차산업 특성화 교육기관이라

가장 유명한걸로 알고 있으니 참고해!

​',sysdate, 2, 1, 31, 17);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, 'IT앱 웹 개발쪽은 인천중앙직업전문학교가 유명한걸로암',sysdate, 1, 1, 40, 17);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '전국 고교위탁 최다 과정 승인!!

전국 고교위탁 최다 과정 승인!!

​

안녕하세요. 고교위탁 NO.1

[인천중앙직업전문학교] 입니다.

​

IT웹.앱 개발 위탁교육을 희망하는 학생이라면

고용노동부 선정 4차산업선도인력양성기관으로써

4차산업 특성화 교육기관의 특화된 커리큘럼을 기반으로

체계적인 교육을 통해 전문인재를 양성하고 있는

[인천중앙직업전문학교]의 정보보안&사물인터넷 / 게임프로그래밍&게임기획

게임그래픽디자인&게임원화 / 설계디자인&3D프린터

다양한 IT계열의 전공과정이 있습니다.',sysdate, 1, 1, 8, 17);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요! 고교위탁 9년 연속 우수 훈련기관 인천직업능력교육원 입니다.

​

​

위탁에 관련하여 질문 주셨는데요.

​

​

​

​

Q. 위탁학교 고민중인에 it웹 개발 위탁학교 알려주세요

​

​

​

- 안녕하세요. 인천직업능력교육원은  IT전문 위탁교육기관으로써 9년 연속 우수훈련기관에 선정되었으며 이는 직업능력심사평가원 사이트에서 직접 선별된 기관들을 확인도 하실 수 있습니다. 

아래 25년도 개설예정 과정들과 저희 학교에 대한 간략한 설명이 있으니 읽어봐주시고 편하게 문의해주시면 감사하겠습니다.',sysdate, 1, 1, 13, 17);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요~!

IT국비지원교육기관 교육상담센터입니다.

우선 국비지원을 활용하여 취업준비를 하시려는 계획이시라면 온라인교육으로는 어렵습니다.

실무실습부터 프로젝트진행 및 취업준비와 취업연계등 온라인으로는 진행이 어렵기때문에 오프라인교육으로 IT국비지원교육을 진행하셔야 합니다.

IT국비지원교육기관은 전국에 지점들이 있기때문에 가까우신 지점에서 수강 가능하구요~ 교육에 앞서 1:1무료컨설팅을 통해 자세한 교육계획 상담 도와드리고있습니다.

아래 IT국비지원교육기관 '하이미디어아카데미' 정보공유 블로그 남겨드릴게요~

실시간 상담도 진행중이오니 아래 네이버 정보공유 카페글을 참고해주세요!!',sysdate, 1, 1, 40, 18);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요 취업왕입니다.

​

KG아이티뱅크는 코딩취업 / 자격증 / 진로 / 전공대비 / 자기개발 / 진학 / IT특기병 등 코딩 전문 교육 및 상담을 진행하고 있습니다.

​

KG아이티뱅크는 정보보안, 네트워크, 시스템, 클라우드컴퓨팅, 프로그래밍, 데이터베이스 등 IT 고급인력양성을 목적으로 2002년 설립된 IT전문교육기관입니다.',sysdate, 2, 1, 13, 18);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, 'IT 웹개발은 국비지원 온라인 교육보다는 집체교육으로 받으시는 것을 추천해드립니다.

집체교육을 받으시면서 요즘은 무료 동영상강의가 많아서 추가적으로 유투버 또는

무료 온라인 강의를 병행 하시기 바랍니다.

현재 내국인 또는 결혼 이민자의 경우 국비 무료교육이 가능하며 자세한

신청자격 및 훈련과정은 www.hrd-net 을 통해 검색 및 신청이 가능합니다.',sysdate, 1, 1, 31, 18);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '온라인 교육은 자격증 취득과정이라고 계좌제 형태가 많습니다!

​

온라인 강의는 자부담이 들 확률이 큽니다!

​

일단 백엔드 쪽으로 취업을 준비하신다면 IT 취업연계 국비수업으로 공부를 하시는게 가장 좋아요!

​

6개월 과정동안 집체교육 수업을 통해 백엔드 실무역량을 완벽히 익히고 프로젝트 진행하여 포트폴리오에 녹여서

​

취업하는 것이 가장 현실적으로 취업확률이 높습니다!

​

궁금한 거 있으시면 물어보세요!!',sysdate, 1, 1, 8, 18);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요, 닥톡-네이버 지식iN 상담한의사 김세윤입니다.



중학생 딸의 백반증 진단 소식을 들으시고 많이 걱정되셨을 것 같습니다. 특히 아이가 어리고 피부가 민감하니 치료 방법에 대해 더욱 신중하게 고민하시는 점 충분히 이해됩니다.



백반증은 피부에 나타나는 증상이지만, 근본 원인은 면역기능 이상에서 시작되는 질환입니다. 멜라닌세포가 파괴되면서 다양한 크기와 형태의 하얀 반점이 생기는데, 통증이나 가려움 같은 증상이 없어 초기에 큰 문제로 인식되지 않다가 점차 부모님과 아이 모두에게 심리적 부담을 줄 수 있습니다.',sysdate, 1, 1, 44, 19);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요, 닥톡-네이버 지식iN 상담한의사 장영용입니다.



중학생보약 관련해서 질문을 주신 것 같습니다,

중학생보약이 효과가 좋을지 고민이신 모양입니다

공진단으로 드신다면 효과를 볼 수 있습니다

제가 관련 정보를 드릴 테니 참고해 주세요



많은 수험생영양제로 알려져 있으며 한약으로는 으뜸이 되는 것이 공진단이라고 할 수 있습니다 공진단의 특성상 아이부터 노인까지 누구나 먹을 수 있다는 점입니다 가장 많이 찾는 것이 기력회복용 피로회복용이며 수술 후 보약이나 떨어진 기운을 좋게해주는 효과가 있는 것이 공진단이라고 할 수 있습니다 특히 뇌기능을 회복해주는 효과까지 있어서 청소년이나 수험생에게 효과가 좋다고 할 수 있습니다



정기를 돋우고 기와 혈을 원활하게 하여 질병 예방과 건강한 삶을 유지하는 모든 약 중에 최고의약인 공진단은 그 효능만큼 믿을 수 있고 검증된 곳에서 조제하여 드시는 것이 무엇보다 중요합니다.



도움이 되셨는지요

감사합니다,',sysdate, 2, 1, 22, 20);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '성형을 위한거면 안받아주죠. 미용시술면으론 아직 이른 나이',sysdate, 1, 1, 44, 21);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '

코는 뼈와 연골같은 골격 구조가 있고 성장기에 같이 커지기 때문에,

성형 목적의 시술이나 수술은 성년기(만19세) 이후에 합니다.

대부분의 병원에서 중학생을 대상으로 코 성형수술을 하진 않습니다.

​',sysdate, 2, 1, 22, 21);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '크면 클수록 좋겠지만 농구를 잘한다는 전제하에 가장 작은 단신 가드를 가정. 

​

볼 핸들링 좋고 빠르고 잘 뛰는 중학생 포인트 가드를 기준으로 

1학년 때 적어도 170초반에는 진입해야 이후 성장 가능성을 볼 수 있을 겁니다. 

​

그 외의 포지션 플레이어라면 볼 핸들링과 시야 능력치는 조금씩 부족해도 각 포지션에 맞는 스킬과 포지션 넘버 순서 대로 5cm씩은 더 큰 신장을 가진다고 보면 될 것 같습니다. ',sysdate, 1, 1, 44, 22);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '그럴 수 있습니다 

​

민사소송 제기할 때 부모 상대로 하세요

​',sysdate, 1, 1, 22, 23);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '가해자 부모를 상대로하여 민사소송을 제기하셔야 합니다.',sysdate, 2, 1, 44, 23);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '
고등학생 간의 성추행 사건이라 하더라도, 14세 이상이라면 형사처벌의 대상이 되므로 가해 학생도 법적 책임을 질 수 있습니다.

​

​

고등학생 성추행의 경우, 피해자의 고통과 회복이 중요하므로, 사건을 덮고 지나가기보다는 피해자의 권리를 지키기 위해 적절한 조치를 취하는 것이 필요하게 될텐데요,

​

​

만약 가해자 학생의 처벌을 원한다면, 학교나 교육청에 피해 사실을 알릴 수 있는 방법이 있지만, 혹여나 학교에 소문이 퍼질까 두렵다면 경찰에 신고해 도움을 청하시는 것도 하나의 방법이 되겠죠.',sysdate, 2, 1, 35, 24);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '○ 현역병 입영일자 연기 사유 중 ‘대학진학 예정자’는 21세가 되는 해의 5월 말일까지 연기가능하며(증빙서류 없음), 이에 더하여 연기를 희망하는 사람은 다음 연도 대학수학능력시험 접수증 등 증빙서류를 제출하는 경우 22세가 되는 해의 5월 말까지 연기가 가능합니다.

 

​

* 병역법 상 나이계산 : 당해연도-출생연도

 

​

○ 입영일자 연기신청은 입영일 5일 전까지 인터넷/모바일/팩스/방문 중 편하신 방법을 택하여 연기 신청 하실 수 있습니다.

- 인터넷 신청 : 병무청 누리집 – 병무민원 – 현역/상근 – 현역/상근 민원신청 – 입영일자 연기원 신청

- 모바일 신청 : 병무청 앱 – 민원서비스 – 현역상근 – 입영일자 연기원 신청

 ',sysdate, 2, 1, 14, 28);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '학교폭력위원회의 징계가 결정되면, 그 기록이 학교생활기록부에 남을 수 있게 되는데요, 다만, 생기부에 남는 기간은 징계의 수위에 따라 달라집니다.

​

​

보편적으로 출석 정지 이상의 징계가 내려지면 생기부에 기록되며, 졸업 후 최대 2년까지 유지되며, 학교폭력 기록이 생기부에 남으면 대학 입시에 지장을 줄 수밖에 없게 되겠죠.',sysdate, 1, 1, 16, 29);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '04년생은 수능접수증이 필요없습니다.

그냥 연기신청서에 대학진학사유로 작성하여 접수하면 됩니다.

아래 내용을 읽어보시고, 바로 연기신청하세요.',sysdate, 2, 1, 16, 26);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '일본 국공사립대학 외국인 전형은 대학과 전공학부에 따라 EJU 시험 반영과목/ 영어성적 반영여부/

본고사과목이 다를 수 있으므로 먼저 전공을 정하고 각 대학 입시요강을 확인해 입시에 꼭 필요한 과목만을

준비하면 시간대비 효과적으로 시험 준비를 할 수 있습니다.(종로 와세다 진학상담 강추!!)

종로 와세다는 일본대학입시전문학원으로.. 시험 준비부터 대학 선택 원서 접수및 입시 전반적인 부분을 지도 관리합니다.',sysdate, 1, 1, 14, 24);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '
​
저작권을 침해하면 저작권법에 따라 민사 및 형사적 책임을 질 수 있습니다.

민사적 처벌의 경우, 저작권자가 실제 손해액과 함께 그 침해 행위로 인한 이익을 포함한 배상을 요구할 수 있습니다.

형사적 처벌의 경우, 1년 이하의 징역형이나 최대 1천만원 이하의 벌금, 이외에도 침해 행위에 따라 더 높은 처벌을 받을 수도 있습니다.

​

음악을 합법적으로 사용하려면, 저작권자의 허락을 받거나, 저작권이 만료되었거나 없는 음악을 사용하는 등의 방법을 통해 저작권을 존중하는 방식으로 접근할 것을 권장 드립니다.',sysdate, 2, 1, 23, 32);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '서울예술대학교

동아방송예술대학교

백석예술대학교

명지전문대학교

여주대학교 등등 있고

​

실용음악으로 유명한 대학은 

한양대 에리카

호원대학교

서울예술대학교',sysdate, 1, 1, 9, 33);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '1. 음악교육과는 생기부를 보지 않아요.

2. 내신컷은 대학교마다 다르니 확인 필요해요.

3. 실기 준비는 고1부터 시작하는 것이 좋아요.

4. 임용고시는 준비하기에 따라 달라요.',sysdate, 1, 1, 9, 34);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요 질문자님 :) 저는 한국교원대학교 재학생입니다.

​

편입에 관한 정보는 *한국교원대학교 입학안내홈페이지-편입학* 에서 확인하실 수 있는데요, 아쉽게도 최근 3개년 동안 음악교육과는 편입학이 시행되지 않고 있습니다. ㅠㅠ

​

매년 12월에 편입학 TO 가 모집요강에 공지되니 올해도 잘 살펴보시길 추천드립니다. 답변이 도움이 되었을까요? 추가로 궁금하신 점은 댓글 주시면 친절히 답변드리겠습니다. ',sysdate, 1, 1, 9, 35);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '
3% 가산점을 받으시려면 630점 이상을 취득하셔야 합니다. ',sysdate, 2, 1, 26, 36);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '대회.공모전 콘테스트코리아입니다.

​

통일과 관련된 글쓰기 대회는

대회.공모전 - 문학.문예 부문(www.contestkorea.com) 에서

통합검색 창 옆에 '통일'로 검색하면 도움이 됩니다.

​

2025 청소년 문학 및 백일장 모음(https://tinyurl.com/23alncnc)에서도

참고해 보세요.',sysdate, 2, 1, 26, 37);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '한국실용글쓰기 cbt 시험 소방가산점에 대해 알려드리겠습니다. 한국실용글쓰기 cbt 시험은 정기시험이 아닌 상시시험으로 진행됩니다. 

 

cbt시험은 일반적으로 자격증 가산점으로 인정되지 않습니다. 그러나 소방과 관련된 일부 직업에는 소방 자격증이 요구될 수 있으며, 이는 채용 과정에서 가산점으로 고려될 수 있습니다. 

 

따라서 소방 관련 자격증을 보유하면 채용에 도움이 될 수 있을 수 있습니다. 그러나 cbt시험 자체는 자격증 가산점으로 효력이 있는 것은 아니니 참고하시기 바랍니다.',sysdate, 2, 1, 26, 38);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '1. 주제 선정과 목적 설정

먼저, 어떤 주제에 대해 글을 쓰고 싶은지, 그리고 왜 그 글을 쓰는지 목적을 분명히 정해야 합니다. 목적이 분명할수록 글의 방향이 잡히고 독자에게 전달할 메시지를 명확히 할 수 있습니다.

​

​

2. 구조 구상하기

서론, 본론, 결론의 구조를 미리 계획해두는 것이 좋습니다. 서론에서는 주제와 문제를 제시하고, 본론에서 구체적인 내용을 다루며, 결론에서는 핵심 내용을 정리하거나 의견을 제시합니다. 이를 통해 논리적이고 체계적인 글을 쓸 수 있습니다.

​',sysdate, 1, 1, 26, 39);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '대한민국 대표 공모전미디어 입니다.

​

연말이라 시기적으로 가장 공모전 행사가 적은 때입니다. 

접수 2주전부터 공지되니 수시로 방문해보세요. 

​

씽굿에는 진행중인 다양한 대회정보,대외활동,공모전 정보를 확인할 수 있습니다.

현재 진행중인 공모전은 모두 정리되어 확인이 가능합니다. 

참여 가능한 대외활동, 공모전, 대회들은 언제나 진행중에 있습니다.

​',sysdate, 1, 1, 10, 40);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '네 광주미술학원 중에 입시 전문으로 가르치는 학원으로는 창의미술원을 추천해드릴게요!',sysdate, 2, 1, 17, 41);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '광주미술학원 고민하고 계신가보네요.

그렇다면 여러 미술학원 중 북구C.&.C미술학원 추천드려요

​

2024년 여러 실기대회에서 학생들이 수 많은 수상을 받을만큼 체계적으로 잘 알려주고 있습니다',sysdate, 1, 1, 10, 41);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '예고미술쌤은 회화전공해야해여 ㅋㅋ(디자인쌤 하실거면 디자인도ㄱㅊ)

사범대 말고요 ㅇㅇ.. 

​

애니고 쌤할거면 애니해야갰지만

 일반 애니과없는 예고는 애니전공하면 선생님 못합니다.

예고에서 소묘가르치는건 회화전공쌤들이하고요

나머지 전공가르치는 쌤 하고싶으시면  님 전공대로 하는겁니다.

(애니랑 회회파트(디자인도)는 천지차이)

​

일단 미술쌤 (예고기준)되시려면 대학 잘가야해요 (당연한소리지만) 그냥 인서울이아니라 진짜 상위권학교들

(ex서울대, 홍대, 이대, 경희대)

대학원나온분들도 많고요.

​

즉, 공부 잘해야합니다. (근데 이것도 사실 전형이나 전공마자 조금씩 다 다르긴하지만, 공부는 잘할수록좋음.)

​',sysdate, 2, 1, 17, 42);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '백석예술대학교 디자인미술학부 전공선택에 대해 궁금하셨군요!

​

본교 디자인미술학부는 1개의 학부 내에 4개의 세부전공 분야로 특화되어 분야에 대해 다채롭게 배우고 선택할 수 있습니다. 1학년 1학기는 학부생으로 입학하여 여러 전공수업을 수강하여 자신에게 맞는 전공을 찾아가며,

1학년 2학기부터 세부전공을 선택하여 전공과 관련하여 더욱 심화된 수업을 수강할 수 있습니다. 

​',sysdate, 1, 1, 17, 43);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '수입하고자 하시는 "미술재료"가 구체적으로 무엇인지 알아야 답변이 가능합니다

(물품에 따라 관세율 및 통관절차가 상이함)

​

사업자등록의 업태/종목과 수입가능 여부는 직접적인 관련이 없으며

사업자가 사업을 목적으로 수입하는 모든 물품은 관세사를 통해 일반통관절차를 거쳐야 하며

관세 및 부가가치세를 납부해야 합니다',sysdate, 1, 1, 26, 44);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '1. 주맨등록증 사진은 6개월이내 촬영된 것을 요구하고 있습니다.

​

2. 주민등록증 신규발급 및 재발급신청을 하면서 제출한 사진은 국가전산망에 등록되어 있습니다.

​

3. 기존 주민등록증의 발급일자가 6개월이내라면 기존 주민등록증에 사용한 사진을 사용할 수 있으나, 6개월이 지났다면 새로운 사진을 준비하는 것이 바람직합니다,

​

4. 질문의 a와 b사진 모두 국가전산망에 등록되어 있습니다. b사진이 6개월을 지났다고 하였으므로 그 보다 더 오래된 a사진을 제출하면 전산망에서 6개월이 지난 기존 사진임을 확인할 수 있고 그러면 접수가 거부될 것입니다,',sysdate, 2, 1, 27, 45);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '대회.공모전 콘테스트코리아입니다.

​

반려동물 사진 공모전은 마감된 공모전만 보입니다.

1년에 15회 정도 열리는 것 같습니다.',sysdate, 2, 1, 34, 46);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '반려동물 사진 공모전을 진행하는 곳은 국내에서 쓰담쓰담 밖에 없습니다.

​

쓰담쓰담 커뮤니티에서 1등에 도전해보세요. 동물사진',sysdate, 1, 1, 27, 46);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '수능 원서 사진과 주민등록증 사진은 모두 여권용(3.5x4.5cm) 사이즈를 사용하기에 사용은 가능합니다.

​

다만 사진 촬영 시에 신분증 규정에 맞도록 촬영했으면 가능하나, 그냥 크기만 맞추고 기준을 지키지 않았으면 불가할 수 있으니 아래 이미지 참고해 보시고, 딱히 문제 없으면 재발급 신청하면 됩니다(수수료 5,000원).',sysdate, 2, 1, 27, 47);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '다양한 장르의 대학로 연극 정보를 찾고 계신다면

관련 앱에서도 손쉽게 대학로 연극 예매 할인 정보들을 참고해볼 수 있습니다

이런 방법으로 활용하여 알아보시는걸 권유드려요

대학로 연극 예매 할인 받는 방법도 자세하게 나와 있어

어렵지 않게 대학로 연극 예매 할인 받고 이용해보실 수 있을 거예요

​',sysdate, 2, 1, 24, 48);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '성인이 봐도 괜찮은 연극이면 부모님이랑 같이 봐도 괜찮습니다.',sysdate, 1, 1, 24, 49);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '스타트업을 창업할 때 중요한 것은 초기 단계에서 사업 구조나 계약서 작성, 인사, 지식재산권 문제 등 다양한 법적 이슈를 예방하는 것이겠지요.

​

특히나 인공지능과 관련된 사업은 지식재산권 및 기술적인 특성상 법적 규제가 많고, 계약서나 협약서에서 발생할 수 있는 분쟁을 예방하기 위해 신경을 써야 합니다.

​

자금이나 인력 등의 제약이 있기 때문에 법률 비용을 아끼고자 할 수 있지만, 법적 문제로 인한 비용 부담은 오히려 더 커질 수 있습니다.',sysdate, 2, 1, 15, 50);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '한국의 스타트업이 높은 성장률을 보이지 못하는 통계가 있어서, 최근에는 많은 사람들이 스타트업 특허에 관심을 갖고 있습니다. 스타트업 특허를 취득하면 아이디어에 대한 지식재산권을 확보하여 사업을 더욱 안정적으로 성장시킬 수 있습니다.

​

우선 출원 가능성을 검토한 후 등록 절차를 밟아야 스타트업 특허를 안전하게 한 번에 등록할 수 있다고 생각합니다. 출원 가능성 판단이나 서류 작성은 전문적인 지식이 필요하기 때문에 전문가의 도움을 받는 것이 좋습니다.',sysdate, 2, 1, 15, 51);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '네이버 블로그 마케팅은 크게 3가지가 있습니다. 첫째, 블로그체험단 입니다 가장 보편적이고 중요한 마케팅으로서 개인의 리뷰이므로 정보성이고 그래서 신뢰도가 높고 자연스럽다는 장점이 있습니다 단점은 무료로 체험서비스를 제공해야 합니다 다만, 광고비가 저렴해서 이것은 상쇄 할 수 있는 단점입니다.',sysdate, 2, 1, 28, 52);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '바이럴마케팅을 독학하실거면 커뮤니티부터 참고하시면

​

공부하는데 도움이 될거예요

​

보통 온라인마케팅에서 활발한 커뮤니티는 아이보스,

​

셀클럽 등이 있습니다

​

한반 가셔 보시면 도움될 내용 많으니 참고하시면 좋을듯해요

​',sysdate, 1, 1, 36, 52);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '대중에세 홍보하는 모든 방법의 일을 말합니다',sysdate, 1, 1, 28, 53);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '마케팅은 제품이나 서비스의 홍보 및 판매 증진을 위해 광고 마케팅 전략 시장 조사 등을 수행하는 직업입니다',sysdate, 2, 1, 36, 53);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '특허법률사무소 알아보고 계신다면 첫 번째로 고려해야할 사항은 관련 사례가 많은지 입니다. 특허 출원을 원하신다면 각종 서류 작성 및 확실한 법률자문을 제공할 수 있어야 하기에, 지식재산권 전문변호사를 상세히 찾아보고 상담을 받아보는 것이 좋습니다.',sysdate, 2, 1, 21, 54);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '특허법률사무소를 선택할 때 고려해야 할 사항은 다음과 같습니다.

​

1.전문성: 특허 출원은 전문적인 지식과 경험이 필요한 분야입니다. 해당 사무소의 전문성을 확인하기 위해서는 변리사의 경력과 실적을 확인하는 것이 좋습니다.

​

2.비용: 수임 비용이 합리적인지, 추가 비용이 발생하는지 등을 확인해야 합니다.

​',sysdate, 1, 1, 18, 54);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '아파서 병원갈때..병의 종류에 따라..병원 종류가 정해지듯...특허로 바슷할듯 합니다..

예를 들어 이 아플때 치과...눈아플땐 안과 가듯..

기계분야 특허는 그 분야를 잘 이해할수 있고..경력도 풍부한 변리사를 찾아가는 것이 좋습니다.',sysdate, 1, 1, 20, 54);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '

비용은 출원하시려는 국가에 따라 다르기 때문에

상담을 통해 자세히 안내받아보실 수 있습니다.

출원 방법에는 PCT출원과 개별국 출원이 있습니다.

PCT출원은 여러 국가에 출원하기에 용이하기 때문에

상황에 맞게 진행하시고 상담을 받아보시기 바랍니다.',sysdate, 1, 1, 19, 55);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '아무래도 날씨가 추워지는만큼 가볍게 움직여 몸을 내고 운동을 시작하시는게 부상방지에 도움이될듯 합니다. 난생처음 운동하셨다면 지금 느끼시는 근육통(알배김)현상이 맞습니다.

닌텐도에서 제안하는 수준의 운동이라면 지금 강도로 꾸준하게 하셔도 문제 없을듯 싶습니다. 

알배김은 운동하면서 근육을 연결하는 섬유가 찢어지면서 생기는 통증으로 3~4일정도 휴식과 적절한 음식섭취를 겸해주시면 금방회복됩니다.',sysdate, 2, 1, 29, 56);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '저 같은 경우 광고할 때

정말 전화도 엄청오고

이것저것 다 해봤는데요

​

가장 괜찮았고 SNS랑 지식인으로

노출이 이렇게 많이 될 줄은 몰랐네요',sysdate, 2, 1, 29, 57);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '요가와 필라테스는 모두 신체와 마음의 균형을 맞추는 운동이지만, 그 목적과 방법에서 차이가 있습니다. 아래에 각각의 차이점과 장단점을 정리해드리겠습니다.',sysdate, 2, 1, 37, 58);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '같이 해보세요

통증이 심하면 도수치료만 해보세요',sysdate, 2, 1, 37, 59);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '콜레스테롤 수치를 낮추기 위해서는

다음과 같은 음식을 포함한 식단을 고려하는 것이 좋습니다.



식이섬유가 풍부한 음식: 귀리, 보리, 콩류, 과일,

채소는 식이섬유가 많아 콜레스테롤 수치를 낮추는 데 도움을 줍니다.



건강한 지방: 올리브유, 아보카도, 견과류와 같은

불포화 지방은 나쁜 콜레스테롤(LDL)을 줄이는 데 도움이 됩니다.

',sysdate, 2, 1, 25, 60);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '당뇨 식단을 구성할 때 중요한 것은 제시간에 한식 위주

건강한 식단을 삼시세끼 잘 챙겨 먹는 것입니다.',sysdate, 2, 1, 25, 61);


/* 채팅방 요정 등록 */
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 1, 2);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 4, 2);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 5, 2);

insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 2, 8);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 2, 9);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 2, 10);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 2, 11);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 2, 12);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7500, sysdate, null, 2, 13);


/* 채팅방 활성화 등록 */
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 이도현님의 채팅방', 7600, 1, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 이도현님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '이도현님에게 채팅방을 신청했습니다.', 7500, 4, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '문준형님에게서 채팅방 신청이 도착했습니다.', 7500, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 5, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '송대현님에게 채팅방을 신청했습니다.', 7500, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '이도현님에게서 채팅방 신청이 도착했습니다.', 7500, 8, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '양한수님에게 채팅방을 신청했습니다.', 7500, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '이도현님에게서 채팅방 신청이 도착했습니다.', 7500, 9, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '이도현님과 이도현님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '이도현님과 이도현님의 채팅방', 7600, 10, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김민수님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김민수님의 채팅방', 7600, 11, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (8, '이도현님과 이서준님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (8, '이도현님과 이서준님의 채팅방', 7600, 12, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 박지우님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 박지우님의 채팅방', 7600, 13, chat_room_status_no_SEQ.nextval);



/* 채팅방 내용 등록 */
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '안녕하세요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 2, '반가워요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 2, '코딩에 관심이 많으세요?');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '네');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '최근에 배우다가 어려운 부분이 있어서 조언을 구하고자 신청했어요!');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 2, '그렇군요 잘되었네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 2, '제가 이 분야 탑이에요  ㅎㅎ');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '아 진짜요? 든든하네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 2, '좋아요 그럼 먼저 뭐 때문에 신청하셨나요?');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, 'java에서 객체지향이 어려워서 질문하려고요');
/* 채팅방 나갔는지 여부 확인 */

/* 리뷰 등록 */
insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '정말 만족스러운 경험이었어요!', 1, '멘토님 덕분에 많은 도움이 되었어요. 처음에는 긴장했지만, 멘토님이 친절하게 설명해 주셔서 이해가 잘 됐어요. 정말 감사해요!', 5, sysdate, 1);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '좀 아쉬운 부분이 있었어요', 1, '멘토님은 좋은 분이시지만, 제가 기대한 내용과는 조금 달랐어요. 그래도 유익한 시간은 되었어요.', 3, sysdate, 1);
insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '좀 아쉬운 부분이 많았어요', 1, '멘토님은 좋은 분이시지만, 제가 기대한 내용과는 조금 달랐어요. 그래도 유익한 시간은 되었어요.', 3, sysdate, 1);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '완벽한 멘토링!', 1, '정말 최고였어요! 멘토님의 조언 덕분에 고민이 해결됐고, 실질적인 도움을 많이 받았습니다. 추천드려요!', 5, sysdate, 1);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '멘토님이 너무 친절했어요', 1, '멘토님이 너무 친절하고 잘 설명해 주셔서 편하게 질문할 수 있었습니다. 덕분에 많은 도움이 되었어요. 감사합니다!', 4, sysdate, 2);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '조금 아쉬웠어요', 1, '멘토님의 경험은 풍부하셨지만, 제 상황에 맞는 구체적인 예시를 더 들어주셨으면 좋았을 것 같아요.', 2, sysdate, 2);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '좋은 경험이었어요', 1, '멘토님의 조언이 정말 도움이 되었어요. 다만, 약간의 시간이 부족했던 점은 아쉬웠습니다.', 4, sysdate, 2);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '다시 만나고 싶은 멘토!', 1, '멘토님은 정말 따뜻하고 친절하세요. 어려운 질문도 잘 설명해 주셔서 큰 도움이 되었어요.', 5, sysdate, 3);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '기대보다 부족했던 멘토링', 1, '멘토님의 말씀이 유익하지 않아서 기대했던 만큼의 도움을 받지 못했습니다. 조금 아쉬워요.', 2, sysdate, 4);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '조금 더 구체적인 피드백을 원했어요', 1, '멘토님은 좋으셨지만, 제 상황에 대해 좀 더 구체적인 조언을 듣고 싶었어요. 전반적으로 괜찮은 경험이었습니다.', 3, sysdate, 5);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '매우 만족했습니다!', 1, '멘토님의 지도 덕분에 많은 것이 변했습니다. 제가 원하는 방향으로 도움을 주셔서 매우 만족했습니다. 감사해요!', 5, sysdate, 6);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '좋은 경험이었어요', 1, '멘토님이 친절하시고 세심하게 도와주셨습니다. 덕분에 많은 도움이 되었습니다.', 4, sysdate, 6);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '멘토님 덕분에 자신감을 얻었어요', 1, '멘토님의 격려 덕분에 제가 자신감을 얻었습니다. 제가 고민하던 문제를 해결할 수 있게 도와주셔서 감사해요!', 5, sysdate, 7);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '조금 더 상세했으면 좋겠어요', 1, '멘토님의 피드백은 좋았지만, 조금 더 구체적인 피드백을 주셨으면 좋았을 것 같아요. 그래도 유익한 시간이었어요.', 3, sysdate, 7);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '멘토링 내용은 좋았지만', 1, '멘토링 내용은 좋았지만, 시간 제약이 있어 충분히 논의하지 못한 점이 아쉬웠습니다. 다음에 더 많은 시간을 갖고 싶어요.', 4, sysdate, 8);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '대체로 괜찮았어요', 1, '멘토님은 좋으셨고 대체로 괜찮았지만, 제게 맞는 해결책을 더 많이 제공해 주셨으면 좋았을 것 같습니다.', 3, sysdate, 8);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '도움이 많이 되었어요', 1, '멘토님 덕분에 여러 문제를 해결할 수 있었어요. 구체적인 피드백을 통해 많은 것을 배우게 되었습니다.', 5, sysdate, 9);

insert into review (review_no, review_title, review_status, review_content, review_score, review_date, chat_room_no)
values (review_no_SEQ.nextval, '아쉬운 부분도 있었어요', 1, '멘토님이 좋은 사람임에도 불구하고, 제가 기대한 대로는 진행되지 않아 조금 아쉬웠어요. 하지만 도움이 되는 부분도 있었습니다.', 3, sysdate, 9);


/* 추천 등록 */
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,2,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,3,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,3,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,3,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,3,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,4,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,4,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,4,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,5,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,6,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,6,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,7,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,7,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,7,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,8,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,8,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,9,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,9,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,10,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,10,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,11,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,13,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,14,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,15,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,16,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,17,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,18,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,18,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,20,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,21,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,22,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,23,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,24,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,26,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,27,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,27,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,27,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,28,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,28,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,29,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,29,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,30,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,30,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,30,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,31,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,31,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,31,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,33,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,34,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,35,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,36,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,37,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,38,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,39,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,40,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,41,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,41,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,41,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,42,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,42,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,43,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,43,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,44,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,44,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,44,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,45,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,45,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,46,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,46,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,46,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,47,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,47,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,48,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,48,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,49,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,50,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,51,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,52,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,53,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,54,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,54,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,55,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,55,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,55,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,56,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,56,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,57,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,57,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,57,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,57,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,58,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,59,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,60,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,61,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,61,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,61,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,62,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,62,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,63,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,63,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,63,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,63,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,64,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,64,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,64,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,64,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,65,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,65,30);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,66,21);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,67,22);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,68,23);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,24);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,25);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,26);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,27);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,28);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,69,29);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,70,30);


/*알림 등록*/
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,2,1,'답변이 달렸습니다','answer','question');



commit;