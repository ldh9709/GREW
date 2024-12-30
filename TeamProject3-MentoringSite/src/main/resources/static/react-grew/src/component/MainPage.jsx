import enter from "../image/enter.png";
import enter2 from "../image/아이티윌.png";
import enter3 from "../image/수고.png";
import enter4 from "../image/getImage (1).png";
import * as mentorProfileApi from "../api/mentorProfileApi";
import * as mentorBoardApi from "../api/mentorBoardApi";
import * as inquiryApi from "../api/inquiryApi";
import MentorProfileItem from "./MentorProfile/MentorProfileItem";

import { useEffect, useState } from "react";
import MainInquiryListItem from "./AnswerInquiry/MainInquiryListItem";
import MainMentorBoard from "./MainMentorBoard";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleChevronRight, faRightLong } from "@fortawesome/free-solid-svg-icons";
export const MainPage = () => {
  const [currentIndex, setCurrentIndex] = useState([]);
  const [mentorProfile, setMentorProFile] = useState([]);
  const [inquiry, setInquiry] = useState([]);
  const [mentorBoard, setMentorBoard] = useState([]);

  const fetchMentorBoard = async () => {
    const response = await mentorBoardApi.listMentorBoardsByViews(0, 4);
    setMentorBoard(response.data.content);
  };
  const fetchInquiry = async () => {
    const response = await inquiryApi.listInquiryView(0, 3);
    setInquiry(response.data.content);
  };

  const fetchMentorProfile = async () => {
    const response = await mentorProfileApi.listMentorsByRating();
    setMentorProFile(response.data);
  };
  useEffect(() => {
    fetchMentorProfile();
    fetchInquiry();
    fetchMentorBoard();
  }, []);
  const slides = document.querySelectorAll(".slide");

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % 3); // 슬라이드를 3초마다 자동으로 넘기기
    }, 7000);

    return () => clearInterval(intervalId); // 컴포넌트 언마운트 시 interval을 정리
  }, []);

  const moveSlide = (direction) => {
    let newIndex = currentIndex + direction;
    if (newIndex < 0) {
      newIndex = slides.length - 1; // 첫 번째 슬라이드로 돌아감
    } else if (newIndex >= slides.length) {
      newIndex = 0; // 마지막 슬라이드에서 첫 번째 슬라이드로 돌아감
    }
    setCurrentIndex(newIndex); // 새로운 인덱스로 상태 업데이트
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>

      <div className="slider-container">
        <div
          className="slider"
          style={{ transform: `translateX(-${currentIndex * 100}%)` }}
        >
          <div className="slide">
            <img src={enter} alt="Image 1" />
          </div>
          <div className="slide">
            <img src={enter2} alt="Image 2" />
          </div>
          <div className="slide">
            <img src={enter3} alt="Image 3" />
          </div>
          <div className="slide">
            <img src={enter4} alt="Image 4" />
          </div>
        </div>
        <button className="prev" onClick={() => moveSlide(-1)}>
          &#10094;
        </button>
        <button className="next" onClick={() => moveSlide(1)}>
          &#10095;
        </button>
      </div>
      <h1>우수 멘토</h1>
      <div className="profile-container main-profile-container">
        {mentorProfile && mentorProfile.length > 0 ? (
          mentorProfile
            .slice(0, 4)
            .map((mentor) => (
              <MentorProfileItem key={mentor.mentorProfileNo} mentor={mentor} />
            ))
        ) : (
          <p>멘토 프로필이 없습니다.</p>
        )}
      </div>
      <div className="view-more">
        <a href="/mentorprofile/list">
          멘토 프로필 더 보기 <FontAwesomeIcon icon={faCircleChevronRight} />
        </a>
      </div>
      <h1>관심 많은 질문</h1>
      <div className="main-inquiry">
        {inquiry && inquiry.length > 0 ? (
          inquiry.map((inquiry) => (
            <MainInquiryListItem key={inquiry.inquiryNo} inquiry={inquiry} />
          ))
        ) : (
          <p>질문이 없습니다</p>
        )}
      </div>
      <div className="view-more">
        <a href="/inquiry">
          질문 더 보기 <FontAwesomeIcon icon={faCircleChevronRight} />
        </a>
      </div>
      <h1>멘토들의 꿀팁 방출</h1>
      <div className="main-mentor-board">
        {mentorBoard && mentorBoard.length > 0 ? (
          mentorBoard
            .slice(0, 4)
            .map((mentorBoard) => (
              <MainMentorBoard
                key={mentorBoard.mentorBoardNo}
                mentorBoard={mentorBoard}
              />
            ))
        ) : (
          <p>게시글이 없습니다</p>
        )}
      </div>
      <div className="view-more">
        <a href="/mentorboard/list">
          멘토 컨텐츠 더 보기 <FontAwesomeIcon icon={faCircleChevronRight} />
        </a>
      </div>
    </>
  );
};
