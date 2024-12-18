import React, { useEffect, useState, useCallback } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear } from '@fortawesome/free-solid-svg-icons';
import * as memberApi from "../../../api/memberApi"

export default function MemberSummary({ memberNo }) {

    const [loading, setLoading] = useState(true); // 로딩 상태 추가

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
            const response = await memberApi.memberCountSummary(memberNo);
            const data = await response.data;
            console.log(data);

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
            const response = await memberApi.memberInfoSummary(memberNo);
            const data = await response.data;
            console.log(data);

            setSummary((prevState)=>({
                ...prevState,
                name: data.memberName,
                points:data.mamberPoints,
            }));
        } catch (error) {
            console.error('멘티 요약정보 조회 실패:', error);
        }
    }


    useEffect(() => {
        const fetchData = async () => {
            setLoading(true); // 로딩 시작
            await Promise.all([fetchCountSummary(), fetchMemberSummary()]);
            setLoading(false); // 로딩 완료
        };

        fetchData();
    }, [memberNo]);

    // 로딩 상태 처리
    if (loading) {
        return <div>로딩 중...</div>;
    }

    return (
    <section className="summary">
        <div className="summary-box">
            <h1>{summary.name}님 안녕하세요 <a href="/profile/edit"><FontAwesomeIcon icon={faGear} /> 개인정보 변경</a></h1>
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
