import React, { useEffect, useState } from 'react'
import image from '../../image/images.jpeg'
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import * as memberApi from "../../api/memberApi"

export default function MemberTabs({memberNo}) {

    const [followList, setFollowList] = useState([{
        name: '',
        primaryCategory: '',
        subCategory:''
    }])

    const fetchFollowList = async () => {
        try {
            const response = await memberApi.followList(memberNo);
            const data = response.data.content;
            setFollowList(data);
        } catch (error) {
            console.log('팔로우 리스트 조회 실패', error);
        }
    }    

    useEffect(()=>{
        fetchFollowList();
    },[])

  return (
    <section class="tab-container">
        <nav>
            <ul class="tabs">
                <li class="tab" data-tab="questions">질문내역</li>
                <li class="tab" data-tab="counseling">상담내역</li>
                <li class="tab active" data-tab="following">팔로잉</li>
            </ul>
        </nav>
        <div class="tab-content hidden" id="questions">
            <ul class="list">
                <li>
                    <strong>질문 제목</strong> - 10월 25일 질문 신간 리스트
                    <span>조회수: 32 | 좋아요: 1</span>
                </li>
                <li>
                    <strong>질문 제목</strong> - 10월 20일 질문 리스트
                    <span>조회수: 25 | 좋아요: 3</span>
                </li>
            </ul>
        </div>
        <div class="tab-content hidden" id="counseling">
            <p>상담내역이 여기에 표시됩니다.</p>
        </div>
        <div class="tab-content" id="following">
            <ul class="follow-list">

                {/* 팔로우 리스트 map으로 반복*/}
                {followList.map((follow)=>(
                    <li class="follow-card">
                        <div class="profile">
                            <img src={image} alt="프로필 이미지" />
                        </div>
                        <div class="info">
                            <p class="name"><strong>{follow.mentorName} 멘토</strong></p>
                            <p>{follow.primaryCategory}</p>
                            <p>{follow.subCategory}</p>
                        </div>
                        <div class="heart">
                            <i class="fa-solid fa-heart"></i>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    </section>
  )
}
