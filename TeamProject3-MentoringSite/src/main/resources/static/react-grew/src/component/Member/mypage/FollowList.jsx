import React, { useEffect, useState } from "react";
import image from '../../../image/images.jpeg'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import * as followApi from "../../../api/followApi"

export default function FollowList({memberNo}) {
    const [followList, setFollowList] = useState([{
            name: '',
            primaryCategory: '',
            subCategory:''
        }])
    
    const fetchFollowList = async () => {
        try {
            const response = await followApi.followList(memberNo);
            const data = response.data.content;
            setFollowList(data);
        } catch (error) {
            console.log('팔로우 리스트 조회 실패', error);
        }
    }    

    const fetchFollowDelete = async () => {
        try {
            
        } catch (error) {
            console.log('팔로우 취소 실패', error);
        }
    }

    useEffect(()=>{
        fetchFollowList();
    }, [])
    
  return (
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
                        <FontAwesomeIcon icon={faHeart} />
                    </div>
                </li>
            ))}
        </ul>
    </div>
  )
}
