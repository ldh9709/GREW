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
VALUES(category_no_SEQ.nextval, '재활 운동', 2, 22);
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
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 송대현 멘토입니다.', 5, 10, '/images/mentor-profile/1.jpeg', 100, 50, 3, 4, member_no_SEQ.CURRVAL, '반갑습니둥');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 2);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ggg','ggg','ggg@naver.com','양한수',6300,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 양한수 멘토입니다.', 5, 10, '/images/mentor-profile/2.png', 100, 50, 3, 8, member_no_SEQ.CURRVAL, '나도반갑습둥');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 3);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'hhh','hhh','hhh@naver.com','이도현',4000,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', 5, 10, '/images/mentor-profile/3.jpeg', 100, 50, 3, 12, member_no_SEQ.CURRVAL, '반갑습니다람쥐');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '경력자입니다', '2020.01.01', '2024.01.01', 4);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor01', 'pass01', 'mentor01@naver.com', '김민수', 3000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김민수 멘토입니다.', 5, 25, '/images/mentor-profile/4.jpg', 150, 75, 3, 2, member_no_SEQ.currval, '집가고싶다.');
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

등을 드릴 수 있습니다 :)', 5, 40, '/images/mentor-profile/10.jpg', 170, 90, 3, 12, member_no_SEQ.currval, '하이');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '영업시스템 PM팀_ 서비스기획', '2024.01.01', null, 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발팀_ 서비스 기획', '2020.01.01', '2024.01.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '공간설계개발TF팀_서비스 기획', '2019.01.01', '2020.01.01', 11);
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '리하우스 영업관리팀_ 인테리어 설계', '2016.01.01', '2019.01.01', 11);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor08', 'pass08', 'mentor08@naver.com', '김도옌', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도옌 멘토입니다.', 5, 40, '/images/mentor-profile/11.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '내가 최고 경력자');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 12);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor09', 'pass09', 'mentor09@naver.com', '김도예', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도예 멘토입니다.', 5, 40, '/images/mentor-profile/12.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '하하하');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '17년 경력', '2024.01.01', null, 13);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor10', 'pass10', 'mentor10@naver.com', '김도요', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도요 멘토입니다.', 5, 40, '/images/mentor-profile/13.png', 170, 90, 3, 18, member_no_SEQ.currval, '호호호');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 14);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor11', 'pass11', 'mentor11@naver.com', '김윤하', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김윤하 멘토입니다.', 5, 40, '/images/mentor-profile/14.jpg', 170, 90, 3, 18, member_no_SEQ.currval, '헤헤헤');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '한샘', '12년 경력', '2024.01.01', null, 15);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor12', 'pass12', 'mentor12@naver.com', '김태윤', 3500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김태윤 멘토입니다. 중학생들을 위한 학습 및 진로 상담 전문가입니다.', 4.8, 25, '/images/mentor-profile/16.jpg', 90, 40, 3, 6, member_no_SEQ.currval, '중학생 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '스마트러닝 교육센터', '중학생 학습 프로그램 개발자', '2015-03-01', '2023-12-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor12')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor13', 'pass13', 'mentor13@naver.com', '이지훈', 4000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이지훈 멘토입니다. 고등학생들의 진학 및 학습 방법에 대해 전문적으로 상담합니다.', 4.7, 30, '/images/mentor-profile/17.jpg', 120, 60, 3, 7, member_no_SEQ.currval, '고등학생 상담 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '입시 전문가 협회', '고등학생 진학 컨설턴트', '2010-01-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor13')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor14', 'pass14', 'mentor14@naver.com', '박수민', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박수민 멘토입니다. 음악 이론과 실기에 대해 전문적으로 가르칩니다.', 5, 50, '/images/mentor-profile/18.jpg', 150, 70, 3, 10, member_no_SEQ.currval, '음악 멘토링 전문가');
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
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이현아 멘토입니다. 건강한 식단과 영양 상담을 전문적으로 도와드립니다.', 4.8, 28, '/images/mentor-profile/20.jpg', 110, 55, 3, 26, member_no_SEQ.currval, '영양 상담 전문가');
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
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이강민 멘토입니다. 사진과 영상 제작에 대한 전문적인 상담을 제공합니다.', 5, 40, '/images/mentor-profile/22.jpg', 170, 80, 3, 13, member_no_SEQ.currval, '사진/영상 제작 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '크리에이티브 스튜디오', '영상 제작자', '2011-04-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor18')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor19', 'pass19', 'mentor19@naver.com', '최유진', 5500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최유진 멘토입니다. 마케팅 전략과 성공 사례를 전문적으로 상담합니다.', 5, 50, '/images/mentor-profile/23.jpg', 200, 100, 3, 17, member_no_SEQ.currval, '마케팅 전략 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '마케팅 솔루션 기업', '마케팅 컨설턴트', '2009-03-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor19')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor20', 'pass20', 'mentor20@naver.com', '이정훈', 4600, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이정훈 멘토입니다. 피트니스와 운동 프로그램 설계 전문가입니다.', 4.9, 35, '/images/mentor-profile/24.jpg', 140, 70, 3, 23, member_no_SEQ.currval, '피트니스 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '헬스 퍼스널 트레이닝 센터', '피트니스 트레이너', '2012-06-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor20')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor21', 'pass21', 'mentor21@naver.com', '박지연', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박지연 멘토입니다. 요가와 필라테스 전문가로서 여러분의 건강을 도와드립니다.', 4.8, 30, '/images/mentor-profile/25.jpg', 120, 60, 3, 24, member_no_SEQ.currval, '요가/필라테스 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '요가 웰니스 센터', '요가 및 필라테스 강사', '2014-08-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor21')));

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor22', 'pass22', 'mentor22@naver.com', '한상우', 4700, 1, SYSDATE, 0, 'ROLE_MENTOR', '');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no, mentor_headline)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 한상우 멘토입니다. 재활 운동과 물리치료를 전문적으로 상담합니다.', 4.9, 28, '/images/mentor-profile/26.jpg', 130, 65, 3, 25, member_no_SEQ.currval, '재활 운동 전문가');
INSERT INTO career(CAREER_NO, CAREER_COMPANY_NAME, CAREER_JOB_TITLE, CAREER_START_DATE, CAREER_END_DATE, MENTOR_PROFILE_NO)
VALUES(career_no_SEQ.nextval, '재활 트레이닝 센터', '재활 운동 전문가', '2015-09-01', '2024-01-01', (SELECT mentor_profile_no FROM mentor_profile WHERE member_no = (SELECT member_no FROM member WHERE member_id = 'mentor22')));



/*멘토 보드 등록*/
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status,member_no) 
VALUES (mentor_board_no_SEQ.nextval, '멘토링 꿀팁 대방출!', '멘티분들을 위해 멘토링에 필요한 모든 팁을 공유합니다.', '/images/mentor-board/image01.jpg', TO_DATE('2024-08-01 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.',  '/images/mentor-board/image02.jpg', TO_DATE('2024-02-26 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 195, 1, 8);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '공기업 취업, 필기 준비 언제 시작하나요?', '우선은 말씀드리고 싶은 건 서류 전형을 준비하면서 필기시험도 함께 준비를 해야만 합니다.
작년 기준이나 혹은 이전 기수를 기준으로 유명 공기업 카페에 들어가 보면 대략 합격 스펙은 아실 수 있을 겁니다. 그것과 거의 대동소이하다고 판단이 될 때에는 그때부터는 필기시험을 준비를 하셔야만 해요. 서류 합격하고 필기 준비를 하면 늦어서 떨어질 가능성이 매우 농후합니다.
','/images/mentor-board/image03.jpg',TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 1500, 1, 9);


INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '이미지 리터러시와 시각적 해석_미술을 통한 이미지 소통의 본질', '이미지 리터러시와 시각적 해석은 미술을 통한 이미지 소통의 본질 이미지 리터러시와 시각적 해석은 현대 사회에서 더욱 중요한 역할을 맡고 있습니다. 미술은 이러한 관점에서 특히 이미지를 통한 소통의 핵심을 형성하고 있습니다. ',  '/images/mentor-board/image04.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 10);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '핵심만 콕! 바로쓰는 총무 실무', '"핵심만 뽑아 쓰는 총무 업무의 모든 것!" NCS 학습모듈 집필위원이 알려주는 학습포인트와 현업 꿀팁!',  '/images/mentor-board/image05.jpg', TO_DATE('2024-12-13 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 185, 1, 11);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '영업 관리의 기본 - 성과를 높이기 위한 팁!', '"영업 관리"는 광범위한 업무처럼 보이지만, 영업팀의 일반적인 업무를 말하는 것은 아닙니다.영업 관리는 다시 말해 하나의 큰 선박을 안내하는 역할입니다. 
한마디로 영업관리는 전략적으로 지원하여 영업 프로세스에서 마찰을 줄임으로써 영업팀이 보다 효율적으로 업무를 수행할 수 있도록 하는 것입니다.',  '/images/mentor-board/image06.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 12);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '개발 직군, 다른 직무부터 시작해도 될까요?', '멘티님, 산다는 게 참 어려운 결정의 연속인 것 같습니다. 당연하게도 제가 뭔가 해답을 제시해 드리기는 어렵겠지만 제 경우의 경험을 말씀드려보자면, 저는 고등학교는 문과를 졸업했어요',  '/images/mentor-board/image07.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 13);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '대학입시 꿀팁과 도움되는 추천 사이트 한눈에 알아보기~!', '진로진학전문가가 알려주는 대학입시 면접 전형 기초 가이드',  '/images/mentor-board/image08.jpg', TO_DATE('2024-10-02 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 1, 14);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '스타트업 A to Z', '스타트업은 고수익과 고성장에 이미지가 강하지만, 고위험에 대한 얘기들이 많이 없습니다. 스타트업은 야생이고, 여기서 살아남기 위한 생존전략이 필요합니다.',  '/images/mentor-board/image09.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 1800, 1, 15);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, 'UIUX 포트폴리오, 유저리서치를 넣어야 할까요?', '포트폴리오를 준비하면서 유저 리서치를 어떻게 넣을지, 꼭 넣어야할지 등에 대해 고민이 많으실 겁니다. 
말 그대로 UXUI디자인 포트폴리오에는 UX와 UI가 꼭 구성이 되어야합니다. 모든 결과물이 UX 인사이트를 근거로 설계가 되었다는 과정을 보여주는 것이 중요하기 때문이죠. 실무에서는 정해진 업무 기한 또는 다양한 이유로 인해.',  '/images/mentor-board/image10.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 16);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '입시미술 개꿀팁, 미대가는방법', '안녕하세요! 입시미술을 준비하는 학생을 위한 꿀팁을 소개해드릴게요. 입시미술을 준비하는 것은 정말 중요한 과정이죠. 많은 학생들이 입시미술을 준비하면서 고민하는 부분을 도와드리기 위해 다양한 팁을 준비했어. 이 팁들을 참고하시면 미술 공부에 큰 도움이 될 거예요.', '/images/mentor-board/image11.jpg', TO_DATE('2023-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 165, 1, 17);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '변리사 무료 상담, 범위, 절차, 팁 설명해드립니다.', '첨단 기술의 발전은 이제 기업을 넘어 국가의 경쟁력까지 좌우하는 중요한 요소가 되었습니다. 이러한 흐름 속에서, 기업들은 자신들이 개발한 기술을 보호하기 위해 지식 재산권 확보에 더욱 관심을 기울이고 있습니다.',  '/images/mentor-board/image12.png', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 2000, 1, 18);
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '중학생을 위한 학습 노하우', '중학생들이 효율적으로 공부할 수 있는 방법을 알려드립니다. 스터디 플랜과 학습 습관을 형성하는 팁을 제공합니다.', '/images/mentor-board/image13.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 6));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '고등학생 학습법과 대학입시 준비', '고등학생들이 학업 성취를 극대화하고 대학 입시에 성공하기 위한 실질적인 가이드를 제공합니다.', '/images/mentor-board/image14.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 7));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '음악 이론과 실기 완벽 가이드', '음악을 배우고자 하는 학생들을 위한 이론과 실기 학습법을 소개합니다. 기초부터 고급 단계까지 다룹니다.', '/images/mentor-board/image15.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 10));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '연기와 연극의 세계', '연극과 연기 수업을 통해 자신을 표현하는 방법을 배워보세요. 전문적인 팁과 실질적인 조언을 제공합니다.', '/images/mentor-board/image16.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 14));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '건강한 식단과 영양 상담', '건강한 몸을 유지하기 위한 식단 설계와 영양학적 조언을 제공합니다. 목표 달성을 돕는 맞춤형 가이드입니다.', '/images/mentor-board/image17.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 26));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '창의적인 글쓰기 비법', '창의적인 글쓰기를 위한 아이디어 발굴 방법과 실질적인 글쓰기 노하우를 제공합니다.', '/images/mentor-board/image18.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 11));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '사진과 영상 제작의 모든 것', '사진 촬영과 영상 제작의 기초부터 고급 기술까지 실질적인 조언과 팁을 제공합니다.', '/images/mentor-board/image19.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 13));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '효과적인 마케팅 전략', '마케팅 전략의 기본과 실제 성공 사례를 통해 효과적인 전략을 설계하는 방법을 제공합니다.', '/images/mentor-board/image20.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 17));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '운동 성과를 극대화하는 피트니스 팁', '효과적인 피트니스 계획 수립 및 운동 루틴 조정을 통해 성과를 높이는 방법을 소개합니다.', '/images/mentor-board/image21.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 23));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '요가와 필라테스로 건강한 몸 만들기', '요가와 필라테스의 기초와 자세 교정을 통해 건강과 유연성을 개선하는 방법을 공유합니다.', '/images/mentor-board/image22.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 24));
INSERT INTO mentor_board(mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no)
VALUES(mentor_board_no_SEQ.nextval, '재활 운동으로 건강 회복하기', '부상 후 건강 회복을 위한 맞춤형 재활 운동 프로그램을 소개합니다. 단계별 가이드와 효과적인 재활 전략을 통해 건강을 되찾으세요.', '/images/mentor-board/image23.jpg', SYSDATE, 0, 1, (SELECT member_no FROM mentor_profile WHERE category_no = 25));



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