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


/* 멘티 등록 */
/***** 테스트용 아이디 등록 *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'asdf1234', '{bcrypt}$2a$10$Lhr9iLqvAzcQhu4Bd.VXmOHSu0LepHnKlrx43i5EbsEgkJBsNaVke', 'asdf1234@gmail.com', '김진영', 500, 1, sysdate, 0, 'ROLE_MENTEE', 'Email');
/* 관심사 등록 */
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 4);

/* 멘티 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'bbb','bbb','bbb@naver.com','김찬영',500,1,sysdate,0,'ROLE_MENTEE','');
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ccc','ccc','ccc@naver.com','나문정',5000,1,sysdate,0,'ROLE_MENTEE','naver');
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ddd','ddd','ddd@naver.com','문준형',700,1,sysdate,0,'ROLE_MENTEE','');
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'eee','eee','eee@naver.com','박은미',9000,1,sysdate,0,'ROLE_MENTEE','');

/***** 테스트용 아이디 등록 *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'qwer1234', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'zszz5434@gmail.com', '이도현', 500, 1, sysdate, 0, 'ROLE_MENTOR', 'Email');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', 5, 10, '/images/mentor-profile/15.jpeg', 100, 50, 3, 2, member_no_SEQ.CURRVAL, '반갑습니다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 1);

/***** 테스트용 아이디 등록 *****/
INSERT INTO member (member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider) 
VALUES (member_no_SEQ.nextval, 'admin1234', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'admin1234@gmail.com', '관리자', 500, 1, sysdate, 0, 'ROLE_ADMIN', 'Email');


/* 관심사 등록 */
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 6, 4);

/* 멘토 등록 */
INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'fff','fff','fff@naver.com','송대현',5500,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 송대현 멘토입니다.', 5.0, 10, '/images/mentor-profile/1.jpeg', 100, 50, 3, 4, member_no_SEQ.CURRVAL, '반갑습니둥');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 2);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ggg','ggg','ggg@naver.com','양한수',6300,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 양한수 멘토입니다.', 5.0, 10, '/images/mentor-profile/2.png', 100, 50, 3, 8, member_no_SEQ.CURRVAL, '나도반갑습둥');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 3);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'hhh','hhh','hhh@naver.com','이지현',4000,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이지현 멘토입니다.', 5.0, 10, '/images/mentor-profile/3.jpeg', 100, 50, 3, 12, member_no_SEQ.CURRVAL, '반갑습니다람쥐');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 4);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor01', 'pass01', 'mentor01@naver.com', '김민수', 3000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김민수 멘토입니다.', 5.0, 25, '/images/mentor-profile/4.jpg', 150, 75, 3, 2, member_no_SEQ.currval, '집가고싶다.');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '10년 경력', '2020.01.01', '2024.01.01', 5);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor02', 'pass02', 'mentor02@naver.com', '이서준', 4000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이서준 멘토입니다.', 4.8, 20, '/images/mentor-profile/5.jpeg', 120, 60, 3, 3, member_no_SEQ.currval, '너도나도유캔두');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '8년 경력', '2020.01.01', '2024.01.01', 6);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor03', 'pass03', 'mentor03@naver.com', '박지우', 3500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박지우 멘토입니다.', 4.5, 15, '/images/mentor-profile/6.jpg', 110, 50, 3, 4, member_no_SEQ.currval, '야 너도 집갈 수 있어');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '6년 경력', '2020.01.01', '2024.01.01', 7);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor04', 'pass04', 'mentor04@naver.com', '정하늘', 3200, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정하늘 멘토입니다.', 4.7, 18, '/images/mentor-profile/7.jpeg', 130, 65, 3, 8, member_no_SEQ.currval, '집가자');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '7년 경력', '2024.01.01', null, 8);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor05', 'pass05', 'mentor05@naver.com', '최예진', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최예진 멘토입니다.', 4.6, 22, '/images/mentor-profile/8.jpeg', 140, 70, 3, 16, member_no_SEQ.currval, '嬉しいです');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '5년 경력', '2024.01.01', null, 9);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor06', 'pass06', 'mentor06@naver.com', '홍서연', 4800, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 홍서연 멘토입니다.', 4.9, 30, '/images/mentor-profile/9.png', 160, 80, 3, 8, member_no_SEQ.currval, 'HELLO');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '9년 경력', '2024.01.01', null, 10);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor07', 'pass07', 'mentor07@naver.com', '김도윤', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, 
'- 특성화고 직업상담사의 업무
- 직업상담사의 직무 역량
- 대학원 졸업장 없이 심리학 전공을 살릴 수 있었던 방법
- 계약직 기회를 제대로 활용하는 방법
- 대학원 진학의 득과 실

등을 드릴 수 있습니다 :)', 5.0, 40, '/images/mentor-profile/10.jpg', 170, 90, 3, 12, member_no_SEQ.currval, '하이');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '영업시스템 PM팀_ 서비스기획', '2024.01.01', null, 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발팀_ 서비스 기획', '2020.01.01', '2024.05.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발TF팀_서비스 기획', '2019.01.01', '2020.01.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '리하우스 영업관리팀_ 인테리어 설계', '2016.01.01', '2019.01.01', 11);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor08', 'pass08', 'mentor08@naver.com', '김도옌', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도옌 멘토입니다.', 5.0, 40, '/images/mentor-profile/11.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '내가 최고 경력자');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 12);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor09', 'pass09', 'mentor09@naver.com', '김도예', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도예 멘토입니다.', 5.0, 40, '/images/mentor-profile/12.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '하하하');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '17년 경력', '2024.01.01', null, 13);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor10', 'pass10', 'mentor10@naver.com', '김도요', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도요 멘토입니다.',5.0, 40, '/images/mentor-profile/13.png', 170, 90, 3, 18, member_no_SEQ.currval, '호호호');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 14);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor11', 'pass11', 'mentor11@naver.com', '김윤하', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김윤하 멘토입니다.',5.0, 40, '/images/mentor-profile/14.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '헤헤헤');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 15);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor12', 'pass12', 'mentor12@naver.com', '김태윤', 3500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김태윤 멘토입니다. 중학생들을 위한 학습 및 진로 상담 전문가입니다.', 4.8, 25, '/images/mentor-profile/16.jpg', 90, 40, 3, 6, member_no_SEQ.currval, '중학생 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '스마트러닝 교육센터', '중학생 학습 프로그램 개발자', '2015-03-01', '2023-12-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor12')));



INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor14', 'pass14', 'mentor14@naver.com', '박수민', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박수민 멘토입니다. 음악 이론과 실기에 대해 전문적으로 가르칩니다.', 5.0, 50, '/images/mentor-profile/18.jpg', 150, 70, 3, 10, member_no_SEQ.currval, '음악 멘토링 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '국립음악원', '음악 이론 강사', '2012-05-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor14')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor15', 'pass15', 'mentor15@naver.com', '정해인', 4800, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정해인 멘토입니다. 연기와 연극에 대한 전문 지식을 제공합니다.', 4.9, 35, '/images/mentor-profile/19.jpg', 100, 50, 3, 14, member_no_SEQ.currval, '연기 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '서울연극협회', '연극 연출 및 배우 지도', '2010-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor15')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor16', 'pass16', 'mentor16@naver.com', '이현아', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이현아 멘토입니다. 건강한 식단과 영양 상담을 전문적으로 도와드립니다.', 4.8, 28, '/images/mentor-profile/20.jpg', 110, 55, 3, 25, member_no_SEQ.currval, '영양 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '영양학 연구소', '임상 영양사', '2015-07-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor16')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor17', 'pass17', 'mentor17@naver.com', '손민호', 4200, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 손민호 멘토입니다. 글쓰기와 창작에 대한 멘토링을 제공합니다.', 4.6, 20, '/images/mentor-profile/21.jpg', 130, 65, 3, 11, member_no_SEQ.currval, '글쓰기 멘토링 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '창작 글쓰기 워크숍', '글쓰기 강사', '2013-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor17')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor18', 'pass18', 'mentor18@naver.com', '이강민', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이강민 멘토입니다. 사진과 영상 제작에 대한 전문적인 상담을 제공합니다.', 5.0, 40, '/images/mentor-profile/22.jpg', 170, 80, 3, 13, member_no_SEQ.currval, '사진/영상 제작 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '크리에이티브 스튜디오', '영상 제작자', '2011-04-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor18')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor19', 'pass19', 'mentor19@naver.com', '최유진', 5500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최유진 멘토입니다. 마케팅 전략과 성공 사례를 전문적으로 상담합니다.', 5.0, 50, '/images/mentor-profile/23.jpg', 200, 100, 3, 17, member_no_SEQ.currval, '마케팅 전략 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '마케팅 솔루션 기업', '마케팅 컨설턴트', '2009-03-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor19')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor20', 'pass20', 'mentor20@naver.com', '이정훈', 4600, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이정훈 멘토입니다. 피트니스와 운동 프로그램 설계 전문가입니다.', 4.9, 35, '/images/mentor-profile/24.jpg', 140, 70, 3, 23, member_no_SEQ.currval, '피트니스 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '헬스 퍼스널 트레이닝 센터', '피트니스 트레이너', '2012-06-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor20')));











INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor50', 'qwer1234', 'mentor50@naver.com', '이재훈', 5200, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이재훈 멘토입니다. 금융 및 투자 전문가로 활동하고 있습니다.', 4.8, 50, '/images/mentor-profile/1a.jpg', 120, 45, 3, 3, member_no_SEQ.currval, '금융 투자 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '투자 컨설팅 회사', '금융 컨설턴트', '2016-01-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor50')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor51', 'qwer1234', 'mentor51@naver.com', '김유나', 4800, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김유나 멘토입니다. UI/UX 디자인 전문가로 활동 중입니다.', 4.7, 35, '/images/mentor-profile/2a.jpg', 140, 55, 3, 4, member_no_SEQ.currval, 'UI/UX 디자인 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '디자인 에이전시', 'UI/UX 디자이너', '2015-03-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor51')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor52', 'qwer1234', 'mentor52@naver.com', '박정민', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박정민 멘토입니다. 법률 및 상담 전문가입니다.', 4.9, 42, '/images/mentor-profile/3a.jpg', 160, 70, 3, 9, member_no_SEQ.currval, '법률 상담 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '법률 사무소', '법률 컨설턴트', '2014-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor52')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor53', 'qwer1234', 'mentor53@naver.com', '최서연', 5100, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최서연 멘토입니다. 디지털 마케팅 전문가로 활동 중입니다.', 4.8, 38, '/images/mentor-profile/4a.jpg', 130, 65, 3, 9, member_no_SEQ.currval, '디지털 마케팅 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '마케팅 에이전시', '디지털 마케터', '2017-04-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor53')));


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor54', 'qwer1234', 'mentor54@naver.com', '이민재', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이민재 멘토입니다. 콘텐츠 크리에이션 전문가입니다.', 4.8, 29, '/images/mentor-profile/5a.jpg', 140, 58, 3, 13, member_no_SEQ.currval, '콘텐츠 크리에이션 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '미디어 회사', '콘텐츠 제작자', '2016-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor54')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor55', 'qwer1234', 'mentor55@naver.com', '박하은', 4800, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박하은 멘토입니다. 교육 및 학습 코칭 전문가입니다.', 4.9, 40, '/images/mentor-profile/6a.jpg', 150, 60, 3, 7, member_no_SEQ.currval, '학습 코칭 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '교육 컨설팅 센터', '학습 코치', '2015-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor55')));


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor57', 'qwer1234', 'mentor57@naver.com', '정유진', 5100, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정유진 멘토입니다. 금융 컨설팅 전문가입니다.', 4.7, 35, '/images/mentor-profile/8a.jpg', 110, 47, 3, 17, member_no_SEQ.currval, '금융 컨설팅 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '금융 서비스 회사', '재무 컨설턴트', '2018-01-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor57')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor58', 'qwer1234', 'mentor58@naver.com', '이서윤', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이서윤 멘토입니다. 소프트웨어 개발 전문가입니다.', 4.8, 60, '/images/mentor-profile/9a.jpg', 180, 85, 3, 4, member_no_SEQ.currval, '소프트웨어 개발 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, 'IT 기업', '소프트웨어 개발자', '2014-07-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor58')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor59', 'qwer1234', 'mentor59@naver.com', '최민지', 4900, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최민지 멘토입니다. 디자인 및 일러스트 전문가입니다.', 4.7, 45, '/images/mentor-profile/10a.jpg', 160, 68, 3, 9, member_no_SEQ.currval, '디자인 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '디자인 스튜디오', '일러스트레이터', '2015-06-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor59')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor60', 'qwer1234', 'mentor60@naver.com', '김도현', 5100, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도현 멘토입니다. 헬스 및 피트니스 전문가입니다.', 4.8, 50, '/images/mentor-profile/11a.jpg', 190, 90,3, 22, member_no_SEQ.currval, '피트니스 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '헬스 트레이닝 센터', '퍼스널 트레이너', '2013-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor60')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor61', 'qwer1234', 'mentor61@naver.com', '정민호', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정민호 멘토입니다. IT 보안 및 네트워크 전문가입니다.', 4.9, 40, '/images/mentor-profile/12a.jpg', 140, 55, 3, 4, member_no_SEQ.currval, 'IT 보안 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, 'IT 보안 회사', '네트워크 엔지니어', '2012-11-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor61')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor62', 'qwer1234', 'mentor62@naver.com', '한지원', 5200, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 한지원 멘토입니다. 창업 및 비즈니스 컨설팅 전문가입니다.', 4.8, 55, '/images/mentor-profile/13a.jpg', 170, 75, 3, 15, member_no_SEQ.currval, '창업 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '스타트업 인큐베이터', '창업 컨설턴트', '2014-02-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor62')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor63', 'qwer1234', 'mentor63@naver.com', '이수민', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이수민 멘토입니다. 데이터 분석 및 통계 전문가입니다.', 4.7, 32, '/images/mentor-profile/14a.jpg', 120, 45, 3, 5, member_no_SEQ.currval, '데이터 분석 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '분석 컨설팅 회사', '데이터 분석가', '2015-08-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor63')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor65', 'qwer1234', 'mentor65@naver.com', '유민재', 4600, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 유민재 멘토입니다. 창업 컨설팅 전문가입니다.', 4.8, 50, '/images/mentor-profile/16a.jpg', 130, 65, 3, 15, member_no_SEQ.currval, '창업 컨설팅 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '창업 지원 센터', '창업 컨설턴트', '2012-10-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor65')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor66', 'qwer1234', 'mentor66@naver.com', '한유진', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 한유진 멘토입니다. 영어 교육 전문가입니다.', 4.9, 44, '/images/mentor-profile/17a.jpg', 150, 80, 3, 6, member_no_SEQ.currval, '영어 교육 전문가');

INSERT INTO career(career_no, career_company_name, career_job_title, career_start_date, career_end_date, mentor_profile_no)
VALUES(career_no_SEQ.nextval, '영어 교육 센터', '영어 교사', '2014-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor66')));






















/*멘토 보드 등록*/

/* 인사/총무/노무 분야 category 2번 member 번호 6번 ,11번*/
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '핵심만 콕! 바로쓰는 총무 실무', '''핵심만 뽑아 쓰는 총무 업무의 모든 것!''  NCS 학습모듈 집필위원이 알려주는 학습포인트와 현업 꿀팁!',  '/images/mentor-board/인사총무01.jpg', TO_DATE('2024-12-13 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 185, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '총무 업무 자동화로 시간 절약하기', '반복적인 총무 업무를 효율적으로 처리할 수 있는 자동화 도구와 팁에 대해 소개합니다.', '/images/mentor-board/인사총무02.jpg', TO_DATE('2024-08-10 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 85, 1, 6);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '노무 관리에서의 주요 법적 이슈와 해결 방안', '최근 발생한 주요 노무 관련 분쟁 사례와 이를 예방하고 해결하는 방법을 알려드립니다.', '/images/mentor-board/인사총무03.jpg', TO_DATE('2024-08-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 6);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '신입사원 온보딩 프로세스 최적화하기', '신입사원이 빠르게 조직에 적응하고 성과를 낼 수 있도록 하는 온보딩 프로세스를 소개합니다.', '/images/mentor-board/인사총무04.jpg', TO_DATE('2024-08-20 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 97, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '임직원 복리후생 제도 설계의 핵심 포인트', '직원 만족도를 높이고 회사 경쟁력을 강화할 수 있는 복리후생 제도 설계 방법에 대해 논의합니다.', '/images/mentor-board/인사총무05.jpg', TO_DATE('2024-08-25 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 110, 1, 11);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 인사 평가 시스템 구축 방법', '인사 평가의 객관성을 높이고 직원들의 동기를 유발할 수 있는 평가 시스템 구축 방법에 대해 공유합니다.', '/images/mentor-board/인사총무06.jpg', TO_DATE('2024-08-05 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 6);


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


/* IT개발/데이터 category 3번 member 번호 8번, 13번 */
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


/* 고등학생 학습/교육 category 7번 member 번호 23번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '고등학생 학습법과 대학입시 준비', '고등학생들이 학업 성취를 극대화하고 대학 입시에 성공하기 위한 실질적인 가이드를 제공합니다.', '/images/mentor-board/고등학생01.jpg', TO_DATE('2024-12-03 22:12:00', 'YYYY-MM-DD HH24:MI:SS'), 320, 1, 23);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효율적인 수능 대비 전략', '수능 준비 과정을 체계적으로 계획하고 학습 효율을 높이는 방법에 대해 다룹니다.', '/images/mentor-board/고등학생02.jpg', TO_DATE('2024-08-13 18:20:00', 'YYYY-MM-DD HH24:MI:SS'), 280, 1, 23);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '고등학생을 위한 시간 관리 기술', '학업과 생활의 균형을 맞추기 위한 시간 관리 기술과 실천 팁을 제공합니다.', '/images/mentor-board/고등학생03.jpg', TO_DATE('2024-10-07 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 100, 1, 23);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '고등학교 주요 과목 학습법', '국어, 영어, 수학, 과학 등 주요 과목별 효과적인 학습 전략과 문제 해결 팁을 소개합니다.', '/images/mentor-board/고등학생04.jpg', TO_DATE('2024-10-07 15:50:00', 'YYYY-MM-DD HH24:MI:SS'), 132, 1, 23);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효과적인 진로 탐색 방법', '고등학생들이 자신의 진로를 탐색하고 목표를 설정할 수 있도록 돕는 실질적인 방법과 사례를 제공합니다.', '/images/mentor-board/고등학생05.jpg', TO_DATE('2024-09-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 23);


/* 대학입시상담 category 8번 member 번호 9,14,16번 */
-- member_no 9번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '대학 입시 면접에서 성공하는 방법', '면접에서 자신을 돋보이게 하는 팁과 성공 사례를 소개합니다.', '/images/mentor-board/대학입시상담01.jpg', TO_DATE('2024-10-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '대학별 지원 전략 세우기', '본인에게 적합한 대학을 선택하고 지원 전략을 세우는 방법을 알아봅니다.', '/images/mentor-board/대학입시상담02.jpg', TO_DATE('2024-10-08 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 9);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '합격을 부르는 수험 생활 관리 팁', '수험생들이 스트레스를 줄이고 효율적으로 학습할 수 있는 관리 방법을 제공합니다.', '/images/mentor-board/대학입시상담03.jpg', TO_DATE('2024-10-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 130, 1, 9);

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


/* 음악 category 10번 member 번호 24번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '음악 이론과 실기 완벽 가이드', '음악을 배우고자 하는 학생들을 위한 이론과 실기 학습법을 소개합니다. 기초부터 고급 단계까지 다룹니다.', '/images/mentor-board/음악01.jpg',TO_DATE('2024-10-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 230, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 악기 선택 가이드', '처음 음악을 시작하는 분들을 위한 악기 선택 팁과 추천 정보를 제공합니다.', '/images/mentor-board/음악02.jpg', TO_DATE('2024-04-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 130, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '작곡을 시작하는 사람들을 위한 팁', '초보자들이 작곡을 시작할 때 알아야 할 기초 지식과 창작 방법을 소개합니다.', '/images/mentor-board/음악03.jpg', TO_DATE('2024-10-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 145, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '음악 이론 쉽게 배우기', '복잡해 보이는 음악 이론을 쉽게 이해하고 활용하는 방법을 단계별로 설명합니다.', '/images/mentor-board/음악04.jpg', TO_DATE('2024-10-20 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 24);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '음악 경연대회 준비 팁', '음악 경연대회에서 좋은 결과를 얻기 위한 준비 과정과 실전 노하우를 소개합니다.', '/images/mentor-board/음악05.jpg', TO_DATE('2024-10-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 155, 1, 24);


/* 글쓰기 category 11번 member 번호 27번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '창의적인 글쓰기 비법', '창의적인 글쓰기를 위한 아이디어 발굴 방법과 실질적인 글쓰기 노하우를 제공합니다.', '/images/mentor-board/글쓰기01.jpg', TO_DATE('2024-11-17 10:20:00', 'YYYY-MM-DD HH24:MI:SS'), 155, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '효과적인 스토리텔링 기술', '독자의 관심을 끌고 감동을 줄 수 있는 스토리텔링 기술과 사례를 공유합니다.', '/images/mentor-board/글쓰기02.jpg', TO_DATE('2024-11-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 140, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '글쓰기 블록 극복하기', '글쓰기 과정에서 마주치는 창작의 벽을 극복하기 위한 유용한 팁과 방법을 제공합니다.', '/images/mentor-board/글쓰기03.jpg', TO_DATE('2024-11-23 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '설득력 있는 글쓰기의 비밀', '독자를 설득할 수 있는 글쓰기 방법과 사례를 통해 설득력을 높이는 노하우를 배웁니다.', '/images/mentor-board/글쓰기04.jpg', TO_DATE('2024-11-25 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 27);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '다양한 장르의 글쓰기 도전하기', '에세이, 소설, 시 등 다양한 장르에서 글을 쓰는 방법과 재미를 소개합니다.', '/images/mentor-board/글쓰기05.jpg', TO_DATE('2024-11-30 16:15:00', 'YYYY-MM-DD HH24:MI:SS'), 160, 1, 27);


/* 미술 category 12번 member 번호 10,17번 */
-- member_no 10번
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '이미지 리터러시와 시각적 해석_미술을 통한 이미지 소통의 본질', '이미지 리터러시와 시각적 해석은 미술을 통한 이미지 소통의 본질 이미지 리터러시와 시각적 해석은 현대 사회에서 더욱 중요한 역할을 맡고 있습니다. 미술은 이러한 관점에서 특히 이미지를 통한 소통의 핵심을 형성하고 있습니다. ',  '/images/mentor-board/미술01.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 10);

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
	

/* 사진/영상제작 category 13번 member 28번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '사진과 영상 제작의 모든 것', '사진 촬영과 영상 제작의 기초부터 고급 기술까지 실질적인 조언과 팁을 제공합니다.', '/images/mentor-board/사진영상제작01.jpg', TO_DATE('2024-11-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 120, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '스마트폰으로 멋진 사진 촬영하기', '스마트폰 카메라를 활용해 전문가 못지않은 사진을 찍는 팁과 기법을 공유합니다.', '/images/mentor-board/사진영상제작02.jpg', TO_DATE('2024-11-12 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 85, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '영상 제작을 위한 스토리보드 작성법', '영상 제작의 기본 단계인 스토리보드 작성법과 이를 활용한 효율적인 제작 과정을 다룹니다.', '/images/mentor-board/사진영상제작03.jpg', TO_DATE('2024-10-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 110, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '촬영 후 보정 작업의 중요성', '사진과 영상 촬영 후 보정 작업의 중요성과 이를 위한 기본 툴 사용법을 설명합니다.', '/images/mentor-board/사진영상제작04.jpg', TO_DATE('2024-09-18 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 95, 1, 28);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '자연광을 활용한 촬영 기법', '자연광을 활용해 감성적인 사진과 영상을 제작하는 기술과 사례를 소개합니다.', '/images/mentor-board/사진영상제작05.jpg', TO_DATE('2024-12-20 18:15:00', 'YYYY-MM-DD HH24:MI:SS'), 125, 1, 28);


/* 연기/연극 category 14번 member 25번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '연기와 연극의 세계', '연극과 연기 수업을 통해 자신을 표현하는 방법을 배워보세요. 전문적인 팁과 실질적인 조언을 제공합니다.', '/images/mentor-board/연기연극01.jpg', TO_DATE('2024-07-18 14:17:00', 'YYYY-MM-DD HH24:MI:SS'), 300, 1, 25);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 연기 기본기 연습법', '연기의 기본기를 다지고 자신감을 키우기 위한 연습 방법을 단계별로 설명합니다.', '/images/mentor-board/연기연극02.jpg', TO_DATE('2024-11-20 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 210, 1, 25);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '무대에서의 자신감 향상 비법', '무대 공포증을 극복하고 관객 앞에서 자신 있게 연기할 수 있는 노하우를 공유합니다.', '/images/mentor-board/연기연극03.jpg', TO_DATE('2024-09-22 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 275, 1, 25);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '연극 대본 분석과 캐릭터 이해하기', '연극 대본을 분석하고 캐릭터를 깊이 이해하여 더 나은 연기를 하는 방법을 소개합니다.', '/images/mentor-board/연기연극04.jpg', TO_DATE('2024-12-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 190, 1, 25);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '감정 표현의 다양성 연습법', '다양한 감정을 자연스럽게 표현할 수 있도록 도와주는 연습 방법과 팁을 제공합니다.', '/images/mentor-board/연기연극06.jpg', TO_DATE('2024-12-28 18:15:00', 'YYYY-MM-DD HH24:MI:SS'), 225, 1, 25);


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


/* 마케팅전략 category 17번 member 29번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '효과적인 마케팅 전략', '마케팅 전략의 기본과 실제 성공 사례를 통해 효과적인 전략을 설계하는 방법을 제공합니다.', '/images/mentor-board/마케팅전략01.jpg', TO_DATE('2024-10-14 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 100, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '디지털 마케팅의 핵심 요소', '디지털 마케팅의 성공을 위한 핵심 요소와 실전 적용 방법을 다룹니다.', '/images/mentor-board/마케팅전략02.jpg', TO_DATE('2024-03-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '브랜드 인지도를 높이는 방법', '브랜드 인지도를 효과적으로 높이기 위한 전략과 성공적인 사례를 소개합니다.', '/images/mentor-board/마케팅전략03.jpg', TO_DATE('2024-05-20 14:45:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '소셜 미디어를 활용한 마케팅 전략', '소셜 미디어 플랫폼을 활용해 효과적으로 고객과 소통하고 마케팅 성과를 극대화하는 방법을 설명합니다.', '/images/mentor-board/마케팅전략04.jpg', TO_DATE('2024-08-15 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 29);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '타겟 고객 분석과 맞춤형 전략', '타겟 고객을 분석하고 그에 맞는 맞춤형 마케팅 전략을 세우는 방법을 공유합니다.', '/images/mentor-board/마케팅전략05.jpg', TO_DATE('2024-11-01 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 225, 1, 29);


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


/* 피트니스 category 23번 member 30번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '운동 성과를 극대화하는 피트니스 팁', '효과적인 피트니스 계획 수립 및 운동 루틴 조정을 통해 성과를 높이는 방법을 소개합니다.', '/images/mentor-board/피트니스01.jpg', TO_DATE('2024-03-19 15:45:00', 'YYYY-MM-DD HH24:MI:SS'), 379, 1, 30);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '근육 성장에 최적화된 운동 방법', '근육 성장을 위해 필수적인 운동 루틴과 영양 섭취 요령을 다룹니다.', '/images/mentor-board/피트니스02.jpg', TO_DATE('2024-04-10 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 250, 1, 30);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '다이어트를 위한 효과적인 유산소 운동', '체중 감량을 위한 유산소 운동의 선택과 지속 가능성을 높이는 팁을 제공합니다.', '/images/mentor-board/피트니스03.jpg', TO_DATE('2024-05-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 310, 1, 30);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '피트니스 초보자를 위한 가이드', '초보자들이 운동을 시작할 때 유의해야 할 사항과 단계별 운동 계획을 소개합니다.', '/images/mentor-board/피트니스04.jpg', TO_DATE('2024-06-20 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 285, 1, 30);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '체형 교정을 위한 스트레칭과 운동법', '체형 교정에 효과적인 스트레칭과 운동법을 설명하며 바른 자세 유지 방법을 소개합니다.', '/images/mentor-board/피트니스05.jpg', TO_DATE('2024-08-05 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), 295, 1, 30);


/* 요가/필라테스 category 24번 member 31번 */
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '요가와 필라테스로 건강한 몸 만들기', '요가와 필라테스의 기초와 자세 교정을 통해 건강과 유연성을 개선하는 방법을 공유합니다.', '/images/mentor-board/요가필라테스01.jpg', TO_DATE('2024-08-06 19:37:00', 'YYYY-MM-DD HH24:MI:SS'), 337, 1, 31);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '초보자를 위한 요가 입문 가이드', '요가를 처음 시작하는 사람들을 위한 기초 자세와 호흡법을 소개합니다.', '/images/mentor-board/요가필라테스02.jpg', TO_DATE('2024-08-10 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 220, 1, 31);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '필라테스로 코어 근육 강화하기', '필라테스를 통해 코어 근육을 강화하고 자세를 개선하는 효과적인 운동법을 다룹니다.', '/images/mentor-board/요가필라테스03.jpg', TO_DATE('2024-09-01 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 280, 1, 31);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '요가와 명상을 통한 스트레스 해소법', '요가와 명상의 조합으로 스트레스를 해소하고 마음의 평화를 찾는 방법을 공유합니다.', '/images/mentor-board/요가필라테스04.jpg', TO_DATE('2024-09-15 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 310, 1, 31);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES (mentor_board_no_SEQ.nextval, '필라테스를 활용한 체형 교정', '필라테스를 통해 체형 교정과 유연성을 높이는 효과적인 자세와 운동법을 소개합니다.', '/images/mentor-board/요가필라테스05.jpg', TO_DATE('2024-10-05 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 250, 1, 31);


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
VALUES(follow_no_SEQ.nextval,6,8);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,9);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,10);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,11);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,12);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,13);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,14);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,15);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,16);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,2,6);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,3,6);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,4,7);
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
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,411,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,11,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,864,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '장원급제의 정확한 뜻은 무엇인가요?!', '장원급제 뜻에 대해 아래 내용이 궁금해요.

• 장원급제의 정확한 뜻은 무엇인가요?
• 역사적으로 유명한 장원급제자는 누가 있나요?
• 장원급제를 한 사람이 조선시대에 맡았던 직책은 무엇인가요??',sysdate,1,351,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문아이폰 시리 설정', '아이폰16인데 설정에서 Siri 및 검색이 없어요
아이오에스18로 업데이트하면서 사라진 건가요??
그리고 시리 설정하는 방법도 알려주세요ㅠㅠ
아무리 제가 시리라고 불러도 대답을 안 해요ㅠ?',sysdate,1,984,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문인스타 상대방 스토리 캡쳐하면 뜨나요??', '중학교 선배 인스타 스토리 캡쳐했는데 상대방 스토리에 캡쳐한거 뜨나요?',sysdate,1,84,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '스타벅스 램프', '2024.12.23일날 품절 되었나요? 아님 그 전에 품절 된건가요??',sysdate,1,1025,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문영웅vs하얼빈', '영웅이 재밌나요?

하얼빈이 재밌나요?

둘다 안중근 의사의 이야기를 다룬 영화라지만

제 친구가 오늘 하얼빈 보고 왔다는데

영웅보다 하얼빈이 재밌다고 하더라구요

오늘 개봉 했다고 하던데

굳이 둘중 하나 고르자면 뭐가 더 볼만하고 재밌나요??',sysdate,1,887,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '강철부대w 새 회차 넷플릭스에 올라오는 시간', '강철부대w 새 회차는 방송이 끝나야 넷플릭스에 올라오나요? 지금 하고있는 13화(전우회) 편이 아직 넷플엔 없어서요',sysdate,1,151,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '전화 추합이 마지막 날 제일 많이 빠지나요?', '전화 추합이 마지막 날 제일 많이 돌고 빠질까요ㅠㅠ??? 정말 피가 마르네요…',sysdate,1,18,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '2024 SBS 가요대전 순서별 예측 시간', '안녕하세여! 저는 스트레이키즈 분들의 무대만 보고 싶은데 정확한 시간을 몰라서 글을 올립니다.. 가요대전을 보는 게 이번이 처음이라서.. 대략 몇시에 시작할 지도 잘 몰라서ㅜ 스키즈 븐들 무대가 3부에 될 거 같은데.. 언제 시작할 까요?? 큐피드 사진 빨갛게 밑줄 친 데가 스키즈분들 순서 입니당..',sysdate,1,97,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '월세 2회 미납 관련', '매월24일 월세를 드리는 날입니다.
11월에는 급여를 받는날이 늦어져, 12월10일에 드리게 되었습미다...
이번달도 회사에서도 사정이 안되어,,,다음달 10일 급여를 주신다고 하여... 집주인께 다음달 10일까지 양해를 규하는 연락을 드리게 되었습니다. 두번째 미입금으로 임대차법 적용에 해당이 되니, 일주일 이내로 짐을 빼라고 하셔서... 이러는 상황에서 보증금도 받을수 있을까요?? ㅠㅠ 법적으로 조취하시겠다고 하셔서,,, 무서워서, 빨리 다른집을 찾아보고 있는데...ㅠㅠ',sysdate,1,6,16,1);

insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '조명가게 이해안되는 부분 있어서 질문남겨요', '1,박보영과 주지훈은 개인적으로 현생에서는 즉 이승에서는 인연이 없는 관계인건가요?  박보영이 코마상태였을때 박보영도 조명가게를 들르게 된 건가요??',sysdate,1,105,16,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '멀티탭에 이 버튼은 뭔가요 ??', '눌러도 고정이안되고 무슨 용도인가요?',sysdate,1,47,2,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '뱀파이어 웹툰 찾아주세요', '주인공이 여자고 학생인데 학교에서 왕따 당하는 있는 상황이고
돈이 필요해서 친구의 도움으로 어떤 술집?에 갔는데
거기 술집이 뱀파이어 술집 같은 곳이여서 주인공 피 빼고 뱀파이어가 주인공 피 맛을 봤는데 주인공 피가 특별해서 정기적으로 거기 술집 가서 피 빼고 그런 내용이었던 것 같은데 찾을 수 있나요?',sysdate,1,888,7,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '임영웅 리사이틀 콘서트 부스 위치요?', '임영웅 리사이틀 콘서트 각 부스들이 어디에 있는지 알고 가면 좋을거 같아서 그러는데 혹시 안내도 있을까요?',sysdate,1,777,16,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '피파 스쿼드좀 짜줏실분ㅠㅠ', '2050억 있는데 처음해봐서 어떻게 팀을 만들어야 할지 모르겠어요ㅠㅠ 어떤 팀이던 다 상관 없으니 2050억 맞춰서만 최대한 좋게 부탁드립니다!
++++ 2444억 으로 바꼈어요!!!?',sysdate,1,333,2,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '질문고등학생 크리스마스 서울 데이트장소 추천', '오늘부로 100일된 커플입니다
서울에서 데이트 할 예정인데
뭐하고 놀아야할지 감이 잘안잡혀 질문 올립니다
크리스마스에 여자친구랑 재밌게 놀만한 장소나
분위기 좋은곳 추천해주시면 정말 감사하겠습니다',sysdate,1,222,7,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '오징어게임 2 개봉시간 언제인가요?', '기사에서는 26일 정각이라하고
지식인에서는 5시 정각이라하는데
오전 오후 언제 인건가요??',sysdate,1,987,16,6);


/* 답변 등록 */
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '비트코인투자를 시작하세요!!',sysdate, 1, 1, 2, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '은행적금을 활용해보세요',sysdate, 1, 1, 8, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '장원급제는 과거 시험에서 1등으로 합격한 것을 뜻하며, 

조선시대 최고의 학문적 성취를 상징합니다. 

정몽주, 이이, 김상헌 등이 대표적인 장원급제자로, 

이들은 주로 대제학, 판서, 좌의정 같은 국가의 핵심 직책을 맡아 중요한 역할을 했습니다.

더자세한내용은 아래사이트에있으니 참고하시면됩니다.',sysdate, 1, 1, 8, 5);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '오징어게임2 공개시간 안내드립니다.

26일 오후 5시에 오징어게임2 공개됩니다.

자세한 내용 아래를 확인 바랍니다.

​

​',sysdate, 1, 1, 3, 6);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '일반적으로 품절 여부는 재고 관리 시스템에 실시간으로 업데이트되므로 

해당 날짜 이전에 이미 품절되었을 가능성도 있습니다. 

아래사이트에는 스타벅스램프예약방법에 대해 자세히나와있으니

읽어보시면됩니다 현재로서는 전국 품절같습니다.

',sysdate, 1, 1, 6, 6);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '서울 데이트 할만한곳  

​

​

​

12월 서울시 축제 

서울라이트 광화문 2024.12.13 ~2025.01.05 광화문역 

광화문 마켓 2024.12.13금 ~2025.01.05 광화문역 광화문광장 

서울빛초롱축제 2024.12.13금~2025.01.12 광화문역 5번출구 청계광장 

서울라이트DDP 2024.12.19 ~2025. 12.31 DDP 동대문역사문화공원역 

서울광장 스케이트장 1천원 2024.12.20~2025.02.9 시청역 ',sysdate, 1, 1, 6, 6);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '러브 액츄얼리

로맨틱 홀리데이

라스트 홀리데이

크리스마스 캐롤

나홀로집에 1,2&3

호두까기 인형과 4개의 왕국

미녀와 야수: 마법의 크리스마스

아이스에이지: 매머드 크리스마스

곰돌이푸 - 즐거운 크리스마스! 신나는 새해!',sysdate, 1, 1, 6, 7);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '뱀파이어 웹툰 뱀파이어의 꽃을 찾아보세요',sysdate, 1, 1, 6, 3);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '국가근로장학금은 별도로 신청해야 해서, 국가장학금 신청만으로는 진행되지 않아요.

부모님 동의와 서류 제출이 없으면 신청이 완료되지 않고 취소될 수 있으니 꼭 챙겨야 해요.

네, 국가장학금 신청 후 1유형으로 뜨는 게 맞아요! ',sysdate, 1, 1, 6, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '임영웅 가수 2024년 리사이틀 콘서트 부스 위치 사진으로 보여드릴게요',sysdate, 1, 1, 6, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '안녕하세요!  상황을 차근차근 계산해 드릴게요.

문제 정리

처음 6명이 29만 원을 이미 냈습니다.

각자 부담한 금액은:

29만원 나누기 6명=48,333.33… = 약 48,333원

나중에 한 명이 추가로 들어와 총 금액이 30만 5천 원이 되었습니다.

새로 추가된 금액:

30만5천원−29만원=15,000원

7명이 공평하게 부담해야 할 총 금액은:

30만5천원 나누기 7명=43,571.43…= 약 43,571원',sysdate, 1, 1, 6, 2);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '1.차미령의 정체는 무엇인가요?

-차미령의 정체는 아직 명확히 밝혀지지 않았습니다. 

-그녀는 드라마 속에서 중요한 역할을 맡고 있으며, 여러 가지 복잡한 성격을 가지고 있습니다.',sysdate, 1, 1, 6, 3);


/* 채팅방 요정 등록 */
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 1, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 4, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 5, 6);

insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 6, 8);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 6, 9);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 6, 10);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 6, 11);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 6, 12);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7500, sysdate, null, 6, 13);


/* 채팅방 활성화 등록 */
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 이도현님의 채팅방', 7600, 1, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '이도현님에게 채팅방을 신청했습니다.', 7500, 4, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '문준형님에게서 채팅방 신청이 도착했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 5, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '송대현님에게 채팅방을 신청했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '이도현님에게서 채팅방 신청이 도착했습니다.', 7500, 8, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '양한수님에게 채팅방을 신청했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '이도현님에게서 채팅방 신청이 도착했습니다.', 7500, 9, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '이도현님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '이도현님과 이도현님의 채팅방', 7600, 10, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김민수님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김민수님의 채팅방', 7600, 11, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (8, '이도현님과 이서준님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (8, '이도현님과 이서준님의 채팅방', 7600, 12, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 박지우님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 박지우님의 채팅방', 7600, 13, chat_room_status_no_SEQ.nextval);



/* 채팅방 내용 등록 */
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '안녕하세요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 6, '반가워요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 6, '코딩에 관심이 많으세요?');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '네');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '최근에 배우다가 어려운 부분이 있어서 조언을 구하고자 신청했어요!');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 6, '그렇군요 잘되었네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 6, '제가 이 분야 탑이에요  ㅎㅎ');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 1, '아 진짜요? 든든하네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 1, 6, '좋아요 그럼 먼저 뭐 때문에 신청하셨나요?');
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
values(vote_no_SEQ.nextval, 1, sysdate,1,1);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,2);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,3);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 1, sysdate,1,4);
insert into vote(vote_no, vote_type, vote_date, answer_no, member_no)
values(vote_no_SEQ.nextval, 2, sysdate,1,5);


/*알림 등록*/
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');


commit;