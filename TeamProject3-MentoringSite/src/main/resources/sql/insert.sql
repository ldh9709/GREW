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
VALUES (member_no_SEQ.nextval, 'qwer1234', '{bcrypt}$2a$10$2Vj/MwAon9U1UFSHcF8DSe9VtT86qtIFmWo2.tocHk1Px1NAXFU3K', 'zszz5434@gmail.com', '이도현', 500, 1, sysdate, 0, 'ROLE_MENTEE', 'Email');
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
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status,member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '멘토링 꿀팁 대방출!', '멘티분들을 위해 멘토링에 필요한 모든 팁을 공유합니다.', 'image1.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6,1);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.', 'image2.jpg', TO_DATE('2024-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 2, 6,2);

INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.', 'image2.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 6,3);


INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '개발 직군, 다른 직무부터 시작해도 될까요?', '멘티님, 산다는 게 참 어려운 결정의 연속인 것 같습니다. 당연하게도 제가 뭔가 해답을 제시해 드리기는 어렵겠지만 제 경우의 경험을 말씀드려보자면, 저는 고등학교는 문과를 졸업했어요', 'image1.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6,4);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.', 'image2.jpg', TO_DATE('2024-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 2, 6,5);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.', 'image2.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 6,6);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, 'UIUX 포트폴리오, 유저리서치를 넣어야 할까요?', '포트폴리오를 준비하면서 유저 리서치를 어떻게 넣을지, 꼭 넣어야할지 등에 대해 고민이 많으실 겁니다. 
말 그대로 UXUI디자인 포트폴리오에는 UX와 UI가 꼭 구성이 되어야합니다. 모든 결과물이 UX 인사이트를 근거로 설계가 되었다는 과정을 보여주는 것이 중요하기 때문이죠. 실무에서는 정해진 업무 기한 또는 다양한 이유로 인해.', 'image1.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6,7);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.', 'image2.jpg', TO_DATE('2024-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 2, 6,8);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.', 'image2.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 6,9);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, 'UIUX 포트폴리오, 유저리서치를 넣어야 할까요?', '포트폴리오를 준비하면서 유저 리서치를 어떻게 넣을지, 꼭 넣어야할지 등에 대해 고민이 많으실 겁니다. 
말 그대로 UXUI디자인 포트폴리오에는 UX와 UI가 꼭 구성이 되어야합니다. 모든 결과물이 UX 인사이트를 근거로 설계가 되었다는 과정을 보여주는 것이 중요하기 때문이죠. 실무에서는 정해진 업무 기한 또는 다양한 이유로 인해.', 'image1.jpg', TO_DATE('2024-12-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 105, 1, 6,10);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '효율적인 코딩 학습법', '초보자를 위한 효율적인 코딩 학습 방법을 소개합니다.', 'image2.jpg', TO_DATE('2024-12-11 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 200, 2, 6,11);
INSERT INTO mentor_board (mentor_board_no, mentor_board_title, mentor_board_content, mentor_board_image, mentor_board_date, mentor_board_views, mentor_board_status, member_no,category_no) 
VALUES (mentor_board_no_SEQ.nextval, '제목은 없습니다', '더미데이터에요 헤헷.', 'image2.jpg', TO_DATE('2024-12-11 15:31:01', 'YYYY-MM-DD HH24:MI:SS'), 200, 1, 6,12);


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
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,1);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,2);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,1);

insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '부자가 되고 싶어요!', '부자가될려면 어떻게 해야하나요?',sysdate,1,0,2,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '지식을 늘리고 싶어요', '지식을 늘리는 방법에는 무엇이 있나요?',sysdate,1,0,7,6);
insert into inquiry(inquiry_no,inquiry_title, inquiry_content, inquiry_date, inquiry_status, inquiry_views, category_no, member_no)
values(inquiry_no_SEQ.nextval, '웹개발자 관련 질물', '웹개발자가 되려면 어떤언어를 공부하면 될까요?',sysdate,1,0,16,6);


/* 답변 등록 */
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '비트코인투자를 시작하세요!!',sysdate, 1, 1, 7, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '은행적금을 활용해보세요',sysdate, 1, 1, 8, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '독서와, 신문, 뉴스를 자주보세요',sysdate, 1, 1, 8, 2);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, 'JAVA를 배우세요 추천합니다!',sysdate, 1, 1, 3, 3);


insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '비트코인투자를 시작하세요!!',sysdate, 1, 1, 6, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '은행적금을 활용해보세요',sysdate, 1, 1, 6, 1);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, '독서와, 신문, 뉴스를 자주보세요',sysdate, 1, 1, 6, 2);
insert into answer(answer_no, answer_content, answer_date, answer_accept, answer_status, member_no, inquiry_no)
values(answer_no_SEQ.nextval, 'JAVA를 배우세요 추천합니다!',sysdate, 1, 1, 6, 3);


/* 채팅방 요정 등록 */
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 1, 5);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 1, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 1, 7);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7000, sysdate, null, 1, 8);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 3, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 4, 7);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 2, 8);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7500, sysdate, null, 2, 5);

/*채팅방 테스트*/
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 6, 7);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 6, 8);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 6, 9);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 6, 10);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 6, 11);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 6, 12);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7500, sysdate, null, 6, 13);

insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 2, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7100, sysdate, null, 4, 6);
insert into chat_room (chat_room_no, chat_room_status, chat_room_start_date, chat_room_end_date, mentee_no, mentor_no)
values(chat_room_no_SEQ.nextval, 7200, sysdate, null, 5, 6);

/* 채팅방 활성화 등록 */
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 박은미님의 채팅방', 7600, 1, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (1, '김진영님과 박은미님의 채팅방', 7600, 5, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '김진영님과 이도현님의 채팅방', 7600, 1, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (2, '김진영님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '나문정님과 이도현님의 채팅방', 7600, 3, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (5, '나문정님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '양한수님과 문준형님의 채팅방', 7600, 4, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (6, '양한수님과 문준형님의 채팅방', 7600, 7, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김찬영님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (7, '이도현님과 김찬영님의 채팅방', 7600, 8, chat_room_status_no_SEQ.nextval);
/* 방 최대 갯수 확인용  */
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 관리자님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (9, '이도현님과 관리자님의 채팅방', 7600, 7, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (10, '이도현님과 송대현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (10, '이도현님과 송대현님의 채팅방', 7600, 8, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (11, '이도현님과 양한수님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (11, '이도현님과 양한수님의 채팅방', 7600, 9, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (12, '이도현님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (12, '이도현님과 이도현님의 채팅방', 7600, 10, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (13, '이도현님과 김민수님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (13, '이도현님과 김민수님의 채팅방', 7600, 11, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (14, '이도현님과 이서준님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (14, '이도현님과 이서준님의 채팅방', 7600, 12, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (16, '김찬영님과 이도현님의 채팅방', 7600, 2, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (16, '김찬영님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (17, '문준형님과 이도현님의 채팅방', 7600, 4, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (17, '문준형님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (18, '박은미님과 이도현님의 채팅방', 7600, 5, chat_room_status_no_SEQ.nextval);
insert into chat_room_status (chat_room_no, chat_room_name, chat_room_status, member_no, chat_room_status_no) 
values (18, '박은미님과 이도현님의 채팅방', 7600, 6, chat_room_status_no_SEQ.nextval);


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

commit;