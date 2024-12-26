import enter from "../image/enter.png";
import * as mentorProfileApi from "../api/mentorProfileApi";
import * as mentorBoardApi from "../api/mentorBoardApi";
import * as inquiryApi from "../api/inquiryApi";
import MentorProfileItem from "./MentorProfile/MentorProfileItem";

import { useEffect, useState } from "react";
import MainInquiryListItem from "./AnswerInquiry/MainInquiryListItem";
import MainMentorBoard from "./MainMentorBoard";
export const MainPage = () => {
  const [mentorProfile, setMentorProFile] = useState([]);
  const [inquiry, setInquiry] = useState([]);
  const [mentorBoard, setMentorBoard] = useState([]);

  const fetchMentorBoard = async() =>{
    const response = await mentorBoardApi.listMentorBoardsByViews(0,4);
    console.log(response.data.content);
    setMentorBoard(response.data.content);
  }
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
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <img
        src={enter}
        alt="메인이미지"
        width="1150px"
        height="500px"
        style={{ margin: "10px" }}
      />
      <h1>우수 멘토</h1>
      <div className="profile-grid">
        {mentorProfile&&mentorProfile.length > 0 ? (
          mentorProfile
            .slice(0, 4)
            .map((mentor) => (
              <MentorProfileItem key={mentor.mentorProfileNo} mentor={mentor} />
            ))
        ) : (
          <p>멘토 프로필이 없습니다.</p>
        )}
      </div>
      <h1>관심 많은 질문</h1>
      <div>
        {inquiry&&inquiry.length > 0 ? (
          inquiry.map((inquiry) => (
            <MainInquiryListItem key={inquiry.inquiryNo} inquiry={inquiry} />
          ))
        ) : (
          <p>질문이 없습니다</p>
        )}
      </div>
      <h1>멘토들의 꿀팁 방출</h1>
      <div className="main-mentor-board">
        {mentorBoard&&mentorBoard.length > 0 ? (
          mentorBoard.slice(0,4).map((mentorBoard) => (
            <MainMentorBoard key ={mentorBoard.mentorBoardNo} mentorBoard={mentorBoard}/>
          ))
        ) : (
          <p>게시글글이 없습니다</p>
        )}
      </div>
    </>
  );
};
