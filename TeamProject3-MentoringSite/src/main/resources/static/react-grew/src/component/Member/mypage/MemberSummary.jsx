import React, { useEffect, useState, useCallback } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear } from '@fortawesome/free-solid-svg-icons';
import * as memberApi from "../../../api/memberApi"

export default function MemberSummary() {

    // State로 회원정보를 저장
    const [summary, setSummary] = useState({
        name: "테스트",
        points: 0,
        inquiryCount: 0,
        counselCount: 0,
        followCount: 0
    })

    //회원 요약정보 count 가져옴
    const fetchCountSummary = async () => {
        try {
            const response = await memberApi.memberCountSummary();
            const { data } = await response;

            setSummary((prevState) => ({
                ...prevState,
                inquiryCount: data.inquiryCount,
                counselCount: data.counselCount,
                followCount: data.followCount,
            }));
        } catch (error) {
            console.error('멘티 요약정보 조회 실패:', error);
        }
    }; // memberNo가 변경되면 함수 재생성

    const fetchMemberSummary = async () => {
        try {
            const response = await memberApi.memberProfile();
            const { data } = await response;

            setSummary((prevState)=>({
                ...prevState,
                name: data.memberName,
                points:data.memberPoint,
            }));
        } catch (error) {
            console.error('멘티 요약정보 조회 실패:', error);
        }
    }


    useEffect(() => {
        fetchMemberSummary();
        fetchCountSummary();
    }, []);


    return (
    <section className="summary">
        <div className="summary-box">
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
            <button className="mentor-btn">멘토전환</button>
        </div>
    </section>
  )
}
