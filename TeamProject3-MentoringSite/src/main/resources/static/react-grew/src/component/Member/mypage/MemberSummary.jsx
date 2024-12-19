import { getCookie } from "../../../util/cookieUtil"
import image from '../../../image/images.jpeg'
import React, { useEffect, useState, useCallback } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear, faHeart } from '@fortawesome/free-solid-svg-icons';
import * as memberApi from "../../../api/memberApi"

export default function MemberSummary() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;
    const role = memberCookie.memberRole;

    // State로 회원정보를 저장
    const [summary, setSummary] = useState({
        name: "",
        points: 0,
    })
    //회원 요약정보 count 가져옴
    const fetchCountSummary = async () => {
        try {
            if(role ==='ROLE_MENTEE'){
                const response = await memberApi.menteeSummary(token);
                const { data } = await response;
                setSummary((prevState) => ({
                    ...prevState,
                    inquiryCount: data.inquiryCount,
                    counselCount: data.counselCount,
                    followCount: data.followCount,
                }));
                console.log("memberSummary",response);
            }else if(role==='ROLE_MENTOR'){
                const response = await memberApi.mentorSummary(token);
                const { data } = await response;
                setSummary((prevState) => ({
                    ...prevState,
                    answerCount: data.answerCount,
                    counselCount: data.counselCount,
                    followCount: data.followCount,
                    boardCount: data.boardCount
                }));
                console.log("memberSummary",response);
            }
        } catch (error) {
            console.error('요약정보 조회 실패:', error);
        }
    }; // memberNo가 변경되면 함수 재생성

    const fetchMemberSummary = async () => {
        try {
            const response = await memberApi.memberProfile(token);
            const { data } = await response;
            setSummary((prevState)=>({
                ...prevState,
                name: data.memberName,
                points:data.memberPoint,
            }));
        } catch (error) {
            console.error('요약정보 조회 실패:', error);
        }
    }


    useEffect(() => {
        fetchMemberSummary();
        fetchCountSummary();
    }, []);


    return (
    <section className="summary">
        <div className="summary-box">
            { role === 'ROLE_MENTEE' ? 
            (
            <>
                <h1>{summary.name}님 안녕하세요 <a href="/member/profile/edit"><FontAwesomeIcon icon={faGear} /> 개인정보 변경</a></h1>
                <p>적립금 <span>{summary.points}자루</span></p>
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
                <button className="role-change">멘토전환</button>
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
            <button className="role-change">멘티전환</button>
            </>
            )}
        </div>
    </section>
  )
}
