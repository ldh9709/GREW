import { getCookie, setCookie } from "../../../util/cookieUtil";
import { useMemberAuth } from "../../../util/AuthContext";
import image from "../../../image/images.jpeg";
import profileDefault from "../../../image/mentor_profile_default.jpg";
import React, { useEffect, useState, useCallback } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGear, faHeart } from "@fortawesome/free-solid-svg-icons";
import * as memberApi from "../../../api/memberApi";
import * as mentorProfileApi from "../../../api/mentorProfileApi"; /////////////////
import { useNavigate } from "react-router-dom";

export default function MemberSummary() {

  /* 멘토 프로필 선언 */
  const [mentorProfile, setMentorProFile] = useState({});

  /* Context에 저장된 토큰, 멤버정보 */
  const { token, member, login } = useMemberAuth();

  const navigate = useNavigate();

  console.log("token쳌", token);
  console.log("member쳌", member);

  // State로 회원정보를 저장
  const [summary, setSummary] = useState({
    name: "",
    points: 0,
    inquiryCount: 0,
    answerCount: 0,
    counselCount: 0,
    followCount: 0,
    boardCount: 0,
    reviewCount: 0,
  });
  const [mentorImage, setMentorImage] = useState(profileDefault); //////////////
  const fetchMentorImage = async () => {
    if (member?.mentorProfileNo) {
      const response = await mentorProfileApi.getMentorProfileImageUrl(
        member.mentorProfileNo
      );
      console.log(response.imageUrl);
      setMentorImage(response.imageUrl || profileDefault);
    }
  };
  ///////////////

  //회원 요약정보 count 가져옴
  const fetchCountSummary = async () => {
    try {
      if (member.memberRole === "ROLE_MENTEE") {
        const response = await memberApi.menteeSummary(token);
        const { data } = await response;
        setSummary((prevState) => ({
          ...prevState,
          inquiryCount: data.inquiryCount,
          counselCount: data.counselCount,
          followCount: data.followCount,
        }));
      } else if (member.memberRole === "ROLE_MENTOR") {
        const response = await memberApi.mentorSummary(token);
        const { data } = await response;

        setSummary((prevState) => ({
          ...prevState,
          answerCount: data.answerCount,
          counselCount: data.counselCount,
          followCount: data.followCount,
          boardCount: data.boardCount,
          reviewCount: data.reviewCount,
        }));
      }
    } catch (error) {
      console.error("요약정보 조회 실패:", error);
    }
  };
  //회원 기본 정보
  const fetchMemberSummary = async () => {
    try {
      const response = await memberApi.memberProfile(token);
      console.log("멘토기본정보", response);
      setSummary((prevState) => ({
        ...prevState,
        name: response.data.memberName,
        points: response.data.memberPoint,
      }));
    } catch (error) {
      console.error("요약정보 조회 실패:", error);
    }
  };

  //멘토 정보 확인
  const fetchMentorInfo = async (mentorProfileNo) => {
    try {
      const response = await memberApi.getMentorProfile(mentorProfileNo);
      /* 멘토 프로필 저장 */
      setMentorProFile(response);
      console.log("멘토 프로필 : ", mentorProfile);
      
      setSummary((prev) => ({
        ...prev,
        mentorCategoryNo: response.data.categoryNo,
      }));
    } catch (error) {
      console.log("멘토정보 조회 실패");
    }
  };

  const underReview = () => {
    alert("심사 중입니다.");
    return;
  }

  //회원 권한 변경
  const handleUpdateRole = async (role) => {
    try {
      if (member.mentorProfileNo === 0) {
        const confirmation = window.confirm("멘토를 신청 하시겠습니까?");
        if (!confirmation) {
          return;
        }
        navigate(`/mentor/join`);
      } else {
        const confirmation = window.confirm(
          member.memberRole === "ROLE_MENTEE"
            ? "멘티로 변경하시겠습니까?"
            : "멘토로 변경하시겠습니까?"
        );
        if (!confirmation) {
          return;
        }
      }

      const response = await memberApi.updateMemberRole(token, role);
      if (response.status === 2012) {
        // 기존 쿠키 삭제
        // document.cookie = "member=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        login(response.data.accessToken);
      }
      //강제 리로드
      // window.location.reload();
      // //성공 후 메인으로 이동
      navigate(`/main`);
    } catch (error) {
      console.error("회원 권한 변경 실패", error);
    }
  };

  //이미지 클릭시 멘토프로필 수정폼 이동
  const handleClickImage = () => {
    navigate(`/mentor/modify`);
  };

  useEffect(() => {
    if (member && token) {
      fetchMemberSummary();
      fetchCountSummary();
    }
    if (member.memberRole === "ROLE_MENTOR") {
      fetchMentorInfo(member.mentorProfileNo);
      fetchMentorImage();
    }
  }, [member, token]);

  return (
    <section className="summary">
      <div className="summary-box">
        {member.memberRole === "ROLE_MENTEE" ? (
          <>
            <h1>
              {summary.name}님 안녕하세요{" "}
              <a href="/member/profile/edit">
                <FontAwesomeIcon icon={faGear} /> 개인정보 변경
              </a>
            </h1>
            {/* <p>적립금 <span>{summary.points}자루</span></p> */}
            <div className="summary-stats">
              <div>
                <h2>{summary.inquiryCount}</h2>
                <p>질문 수</p>
              </div>
              <div>
                <h2>{summary.counselCount}</h2>
                <p>상담 횟수</p>
              </div>
              <div>
                <h2>{summary.followCount}</h2>
                <p>팔로잉</p>
              </div>
            </div>
            <button
              className={`role-change ${
                member.mentorProfileNo === 0
                ? "mentor-apply"
                : mentorProfile?.mentorStatue === 2
                ? "mentor-review"
                : "mentee-convert"
              }`}
              onClick={() =>
                mentorProfile?.mentorStatue === 2
                  ? underReview() // 심사 중 알림
                  : handleUpdateRole("ROLE_MENTOR") // 멘토 전환
              }
            >
              {
              member.mentorProfileNo === 0 
              ? "멘토 신청"
              : mentorProfile.mentorStatus === 2 
              ? "심사 중"
              : "멘토 전환"
              }
            </button>
          </>
        ) : (
          <>
            <h1>
              {summary.name}님 안녕하세요{" "}
              <a href="/member/profile/edit">
                <FontAwesomeIcon icon={faGear} /> 개인정보 변경
              </a>
            </h1>
            {summary.mentorCategoryNo === 26 ? (
              <div
                className="summary_image_container mentor-image-default"
                onClick={handleClickImage}
              >
                <img src={profileDefault} alt="멘토 프로필 기본 이미지" />
              </div>
            ) : (
              <div
                className="summary_image_container mentor-image"
                onClick={handleClickImage}
              >
                <img src={mentorImage} alt="멘토 프로필 이미지" />
              </div>
            )}
            <div>
              <FontAwesomeIcon icon={faHeart} className="red" /> 팔로워{" "}
              {summary.followCount}
            </div>
            <div className="summary-stats">
              <div>
                <h2>{summary.answerCount}</h2>
                <p>답변 수</p>
              </div>
              <div>
                <h2>{summary.counselCount}</h2>
                <p>상담 수</p>
              </div>
              <div>
                <h2>{summary.reviewCount}</h2>
                <p>리뷰 수</p>
              </div>
              <div>
                <h2>{summary.boardCount}</h2>
                <p>컨텐츠</p>
              </div>
            </div>
            <button
              className="role-change"
              onClick={() => {
                handleUpdateRole("ROLE_MENTEE");
              }}
            > 
            멘티 전환
            </button>
          </>
        )}
      </div>
    </section>
  );
}
