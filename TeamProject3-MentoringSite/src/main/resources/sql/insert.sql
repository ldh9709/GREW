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

INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (1)',3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (2)',3, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval,'관심사를 선택해주세요 (3)',3, null);

VALUES(category_no_SEQ.nextval, '건강/운동', 1, null);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '피트니스', 2, 19);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '요가/필라테스', 2, 19);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '재활 운동', 2, 19);
INSERT INTO category(category_no, category_name, category_depth, parent_category_no)
VALUES(category_no_SEQ.nextval, '식단/영양 상담', 2, 19);
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
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', '경력자입니다', 5, 10, '/images/mentor-profile/15.jpeg', 100, 50, 1, 2, member_no_SEQ.CURRVAL);


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
VALUES(member_no_SEQ.nextval,'fff','fff','fff@naver.com','송대현',5500,1,sysdate,0,'ROLE_MENTEE','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 송대현 멘토입니다.', '경력자입니다', 5, 10, '/images/mentor-profile/1.jpeg', 100, 50, 3, 2, member_no_SEQ.CURRVAL);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'ggg','ggg','ggg@naver.com','양한수',6300,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 양한수 멘토입니다.', '경력자입니다', 5, 10, '/images/mentor-profile/2.png', 100, 50, 3, 8, member_no_SEQ.CURRVAL);

INSERT INTO member(member_no,member_id,member_password,member_email,member_name,member_points,member_status,member_join_date,member_report_count,member_role,member_provider)
VALUES(member_no_SEQ.nextval,'hhh','hhh','hhh@naver.com','이도현',4000,1,sysdate,0,'ROLE_MENTOR','');
INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES (mentor_profile_no_SEQ.NEXTVAL, '안녕하세요, 이도현 멘토입니다.', '경력자입니다', 5, 10, '/images/mentor-profile/3.jpeg', 100, 50, 3, 12, member_no_SEQ.CURRVAL);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor01', 'pass01', 'mentor01@naver.com', '김민수', 3000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김민수 멘토입니다.', '10년 경력', 5, 25, '/images/mentor-profile/4.jpg', 150, 75, 3, 2, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor02', 'pass02', 'mentor02@naver.com', '이서준', 4000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 이서준 멘토입니다.', '8년 경력', 4.8, 20, '/images/mentor-profile/5.jpeg', 120, 60, 3, 3, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor03', 'pass03', 'mentor03@naver.com', '박지우', 3500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 박지우 멘토입니다.', '6년 경력', 4.5, 15, '/images/mentor-profile/6.jpg', 110, 50, 3, 4, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor04', 'pass04', 'mentor04@naver.com', '정하늘', 3200, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 정하늘 멘토입니다.', '7년 경력', 4.7, 18, '/images/mentor-profile/7.jpeg', 130, 65, 3, 8, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor05', 'pass05', 'mentor05@naver.com', '최예진', 5000, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 최예진 멘토입니다.', '5년 경력', 4.6, 22, '/images/mentor-profile/8.jpeg', 140, 70, 3, 9, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor06', 'pass06', 'mentor06@naver.com', '홍서연', 4800, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 홍서연 멘토입니다.', '9년 경력', 4.9, 30, '/images/mentor-profile/9.png', 160, 80, 3, 12, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor07', 'pass07', 'mentor07@naver.com', '김도윤', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도윤 멘토입니다.', '12년 경력', 5, 40, '/images/mentor-profile/10.jpg', 170, 90, 3, 15, member_no_SEQ.currval);

INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor08', 'pass08', 'mentor08@naver.com', '김도옌', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도옌 멘토입니다.', '12년 경력', 5, 40, '/images/mentor-profile/11.jpg', 170, 90, 3, 15, member_no_SEQ.currval);





INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor09', 'pass09', 'mentor09@naver.com', '김도예', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도예 멘토입니다.', '17년 경력', 5, 40, '/images/mentor-profile/12.jpg', 170, 90, 3, 15, member_no_SEQ.currval);


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor10', 'pass10', 'mentor10@naver.com', '김도요', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김도요 멘토입니다.', '12년 경력', 5, 40, '/images/mentor-profile/13.png', 170, 90, 3, 15, member_no_SEQ.currval);


INSERT INTO member(member_no, member_id, member_password, member_email, member_name, member_points, member_status, member_join_date, member_report_count, member_role, member_provider)
VALUES(member_no_SEQ.nextval, 'mentor11', 'pass11', 'mentor11@naver.com', '김윤하', 4500, 1, SYSDATE, 0, 'ROLE_MENTOR', '');

INSERT INTO mentor_profile(mentor_profile_no, mentor_introduce, mentor_career, mentor_rating, mentor_mentoring_count, mentor_image, mentor_activity_count, mentor_follow_count, mentor_status, category_no, member_no)
VALUES(mentor_profile_no_SEQ.nextval, '안녕하세요, 김윤하 멘토입니다.', '12년 경력', 5, 40, '/images/mentor-profile/14.jpg', 170, 90, 3, 15, member_no_SEQ.currval);












/*멘토 보드 등록*/
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status,member_no) 
VALUES (mentor_board_no_SEQ.nextval, '멘토링 꿀팁 대방출!', '멘티분들을 위해 멘토링에 필요한 모든 팁을 공유합니다.', '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-08-01 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-02-26 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 195, 2, 8);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '준형', '더미데이터에요 헤헷.','/images/mentor-board/fuuu.jpeg',TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 1500, 1, 9);


INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '개발 직군, 다른 직무부터 시작해도 될까요?', '멘티님, 산다는 게 참 어려운 결정의 연속인 것 같습니다. 당연하게도 제가 뭔가 해답을 제시해 드리기는 어렵겠지만 제 경우의 경험을 말씀드려보자면, 저는 고등학교는 문과를 졸업했어요',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 10);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-13 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 185, 2, 11);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 150, 1, 12);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, 'UIUX 포트폴리오, 유저리서치를 넣어야 할까요?', '포트폴리오를 준비하면서 유저 리서치를 어떻게 넣을지, 꼭 넣어야할지 등에 대해 고민이 많으실 겁니다. 
말 그대로 UXUI디자인 포트폴리오에는 UX와 UI가 꼭 구성이 되어야합니다. 모든 결과물이 UX 인사이트를 근거로 설계가 되었다는 과정을 보여주는 것이 중요하기 때문이죠. 실무에서는 정해진 업무 기한 또는 다양한 이유로 인해.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 13);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-10-02 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 175, 2, 14);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 1800, 1, 15);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, 'UIUX 포트폴리오, 유저리서치를 넣어야 할까요?', '포트폴리오를 준비하면서 유저 리서치를 어떻게 넣을지, 꼭 넣어야할지 등에 대해 고민이 많으실 겁니다. 
말 그대로 UXUI디자인 포트폴리오에는 UX와 UI가 꼭 구성이 되어야합니다. 모든 결과물이 UX 인사이트를 근거로 설계가 되었다는 과정을 보여주는 것이 중요하기 때문이죠. 실무에서는 정해진 업무 기한 또는 다양한 이유로 인해.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 16);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.', '/images/mentor-board/fuuu.jpeg', TO_DATE('2023-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 165, 2, 17);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.',  '/images/mentor-board/fuuu.jpeg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 2000, 1, 18);


/* 팔로우 등록 */
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,1,7);
INSERT INTO follow(follow_no,mentee_member_no,mentor_member_no)
VALUES(follow_no_SEQ.nextval,6,7);
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
VALUES (report_no_SEQ.nextval, 'USER' , 3, 3,'광고쟁이', sysdate, 1, sysdate , 1);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 1, 4,'욕쟁이', sysdate, 1, sysdate , 3);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'ANSWER' , 2, 1,'나쁜사람', sysdate, 1, sysdate , 4);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'USER' , 2, 3,'스팸', sysdate, 1, sysdate , 2);
INSERT INTO report(report_no,  report_type, report_target,report_reason, report_content, report_date, report_status, resolved_date, member_no) 
VALUES (report_no_SEQ.nextval, 'INQUIRY', 2, 4,'별로임', sysdate, 1, sysdate , 1);

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
values (2, '이도현님 에게 채팅방을 신청했습니다.', 7500, 4, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '문준형님 에게서 채팅방 신청이 도착했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 5, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (3, '박은미님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '송대현님 에게 채팅방을 신청했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (4, '이도현님 에게서 채팅방 신청이 도착했습니다.', 7500, 8, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '양한수님 에게 채팅방을 신청했습니다.', 7500, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '이도현님 에게서 채팅방 신청이 도착했습니다.', 7500, 9, chat_room_status_no_SEQ.nextval);
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
values (chat_message_SEQ.nextval, 1, sysdate, 2, 1, '안녕하세요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 6, '반가워요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 6, '코딩에 관심이 많으세요?');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 1, '네');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 1, '최근에 배우다가 어려운 부분이 있어서 조언을 구하고자 신청했어요!');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 6, '그렇군요 잘되었네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 6, '제가 이 분야 탑이에요  ㅎㅎ');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 1, '아 진짜요? 든든하네요');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 6, '좋아요 그럼 먼저 뭐 때문에 신청하셨나요?');
insert into chat_message (chat_message_no, chat_message_check, chat_message_date, chat_room_no, member_no, chat_message_content) 
values (chat_message_SEQ.nextval, 1, sysdate, 2, 1, 'java에서 객체지향이 어려워서 질문하려고요');
/* 채팅방 나갔는지 여부 확인 */

/* 리뷰 등록 */
insert into review (review_no, review_title,review_status,review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목1',1, '그냥그래요', 3, sysdate, 1);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목2',1, '적절해요', 4, sysdate, 1);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목3',1, '최고에요', 5, sysdate, 1);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목4',1, '별로에요', 1, sysdate, 2);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목5',1, '최고에요', 5, sysdate, 2);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목6',1, '그냥그래요', 3, sysdate, 2);
insert into review (review_no, review_title,review_status, review_content,review_score,review_date,chat_room_no) 
values (review_no_SEQ.nextval, '제목6',1, '그냥그래요', 4, sysdate, 10);

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



/* 관심사 등록 */
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 2);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 3);
insert into interest (interest_no, member_no, category_no) 
values(interest_no_SEQ.nextval, 1, 4);

/*알림 등록*/
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');
insert into alarm(is_read,alarm_date,alarm_no,member_no,reference_no,alarm_content,alarm_type,reference_type)
values(1,sysdate,alarm_no_SEQ.nextval,6,1,'답변이 달렸습니다','answer','question');


commit;