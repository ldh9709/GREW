import { getCookie, setCookie } from "../../../util/cookieUtil"
import image from '../../../image/images.jpeg'
import React, { useEffect, useState, useCallback } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear, faHeart } from '@fortawesome/free-solid-svg-icons';
import * as memberApi from "../../../api/memberApi"
import { useNavigate } from "react-router-dom";

export default function MemberSummary() {
    const navigate = useNavigate();
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
    })
    const [memberCookie, setMemberCookie] = useState(getCookie("member"));
    const [isModalOpen, setIsModalOpen] = useState(false);

    //회원 요약정보 count 가져옴
    const fetchCountSummary = async (cookie) => {
        try {
            if (cookie.memberRole === 'ROLE_MENTEE') {
                const response = await memberApi.menteeSummary(cookie.accessToken);
                const { data } = await response;
                setSummary((prevState) => ({
                    ...prevState,
                    inquiryCount: data.inquiryCount,
                    counselCount: data.counselCount,
                    followCount: data.followCount,
                }));
            }else if(cookie.memberRole==='ROLE_MENTOR'){
                const response = await memberApi.mentorSummary(cookie.accessToken);
                console.log('response', response);
                const { data } = await response;

                setSummary((prevState) => ({
                    ...prevState,
                    answerCount: data.answerCount,
                    counselCount: data.counselCount,
                    followCount: data.followCount,
                    boardCount: data.boardCount,
                    reviewCount: data.reviewCount
                }));
            }
        } catch (error) {
            console.error('요약정보 조회 실패:', error);
        }
    }; 
    //회원 기본 정보
    const fetchMemberSummary = async (cookie) => {
        try {
            const response = await memberApi.memberProfile(cookie.accessToken);
            
            setSummary((prevState)=>({
                ...prevState,
                name: response.data.memberName,
                points:response.data.memberPoint,
            }));
        } catch (error) {
            console.error('요약정보 조회 실패:', error);
        }
    }

    //회원 권한 변경
    const handleUpdateRole = async (role) => {
        try {
        const confirmation = window.confirm(
            role === "ROLE_MENTEE" 
                ? "멘티로 변경하시겠습니까?" 
                : "멘토로 변경하시겠습니까?"
        );
          // 권한 변경 로직에서 쿠키 재확인
          const memberCookie = getCookie("member");
          const token = memberCookie.accessToken;
          
          const response = await memberApi.updateMemberRole(token, role);
          if (response.status === 2012) {
                //변경회원 정보
                const updatedMember = {
                    ...memberCookie,
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    memberRole: role
              }
              
                console.log("변경된 권한",response.data);
                console.log('권한 변경 성공:', response.message);


                // 기존 쿠키 삭제
                document.cookie = "member=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

                // 새로운 쿠키 생성
                setCookie("member", JSON.stringify(updatedMember),1);
                console.log("getCookies : " , getCookie("member"));
                console.log("getCookies.accessToken : ", getCookie("member").accessToken);
              
                //강제 리로드
                window.location.reload();
                // //성공 후 메인으로 이동
                // navigate(`/main`);
        }
            console.log("변경된 권한",response.data);
            console.log('권한 변경 성공:', response.message);
        } catch (error) {
          console.error('회원 권한 변경 실패', error);
        }
      };

    useEffect(() => {
        const updateCookie = getCookie("member");
        setMemberCookie(updateCookie);
        fetchMemberSummary(updateCookie);
        fetchCountSummary(updateCookie);
    }, []);


    return (
    <section className="summary">
        <div className="summary-box">
            { memberCookie.memberRole === 'ROLE_MENTEE' ? 
            (
            <>
                <h1>{summary.name}님 안녕하세요 <a href="/member/profile/edit"><FontAwesomeIcon icon={faGear} /> 개인정보 변경</a></h1>
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
                <button className="role-change" onClick={()=>{ handleUpdateRole('ROLE_MENTOR')}}>멘토전환</button>
            </>
            ):
            (  
            <>
             <h1>{summary.name}님 안녕하세요 <a href="/member/profile/edit"><FontAwesomeIcon icon={faGear} /> 개인정보 변경</a></h1>
                <div className="summary_image_container">
                    <img src={image} alt="멘토 프로필 이미지" />
                    <div><FontAwesomeIcon icon={faHeart}  className="red"/> 팔로워 {summary.followCount}</div>
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
            <button className="role-change"  onClick={()=>{handleUpdateRole('ROLE_MENTEE')}} >멘티전환</button>
            </>
            )}
            </div>
    </section>
  )
}
