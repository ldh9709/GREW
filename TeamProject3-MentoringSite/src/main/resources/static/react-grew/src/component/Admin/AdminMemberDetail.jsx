import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import * as adminApi from '../../api/adminApi';
import * as categoryApi from '../../api/categoryApi';
export default function AdminMemberDetail({mentor, onClose}) {
    const [category,setCategory] = useState({})

    //카테고리 조회
    const fetchCategory = async() => {
        const response = await categoryApi.childCategory(mentor.categoryNo);
        setCategory(response.data)
        // console.log('category',response.data)
    }
    console.log(mentor)
    const handleCancel = () => {    
        onClose();
    }

    useEffect(()=>{
        fetchCategory();
    },[])

  return (
        <div className="mentor-detail-container">
            <h2 className="mentor-title">멘토 신청 내역</h2>
            <div className="mentor-details">
                <div className="mentor-row">
                    <span className="mentor-label">전문 분야</span>
                    {/* <span className="mentor-value">{category.parentCategory.categoryName} &gt; {category.categoryName}</span> */}
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">프로필 사진</span>
                    <div className="mentor-profile-pic"></div>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">한줄 소개</span>
                    <span className="mentor-value">{mentor.mentorHeadline}</span>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">소개글</span>
                    <textarea className="mentor-textarea" value={mentor.mentorIntroduce} readOnly></textarea>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">경력</span>
                    <div>
                        {mentor.careerDtos !=null ? (
                            mentor.careerDtos.map((career,index)=>(
                                <div className="mentor-experience" key={index}>
                                    <p>{career.careerStartDate} ~ {career.careerEndDate}</p>
                                    <p>{career.careerCompanyName}</p>
                                </div>
                            ))
                        ) :(<div> - </div>)
                        }
                    </div>
         
                </div>
            </div>
            <div className="mentor-actions">
                <button className="approve-btn">승인</button>
                <button className="reject-btn">거절</button>
            </div>
        </div>

  )
}
