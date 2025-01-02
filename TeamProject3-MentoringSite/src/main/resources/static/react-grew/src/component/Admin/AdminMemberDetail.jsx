import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from 'react'
import * as adminApi from '../../api/adminApi';
import * as categoryApi from '../../api/categoryApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
export default function AdminMemberDetail({mentor, onClose, refreshMentorData}) {
    const {token} = useMemberAuth();
    const [category,setCategory] = useState(null)
    
    console.log('mentor',mentor)
    //카테고리 조회
    const fetchCategory = async() => {
        const response = await categoryApi.childCategory(mentor.categoryNo);
        setCategory(response.data)
        console.log('category',response.data)
    }
    const handleCancel = () => {    
        onClose();
    }

    //멘토 승인
    const handleApproval = async() => {
        try {
            const confirmation = window.confirm('멘토를 승인하시겠습니까?')
            if(confirmation) {
                await adminApi.adminUpdateMentorStatus(token,mentor.memberNo,3);
                alert('승인이 완료되었습니다.')
                refreshMentorData();
                onClose();
            }
        } catch (error) {
            console.error("멘토 승인 실패", error);
        }
       
    }
    //멘토 거절
    const handleReject = async() => {
        const confirmation = window.confirm('멘토를 거절절하시겠습니까?')
        if(confirmation) {
            await adminApi.adminUpdateMentorStatus(token,mentor.memberNo,5);
            alert('거절되었습니다.')
            onClose();
        }
    }

    useEffect(()=>{
        fetchCategory();
    },[])

  return (
        <div className="mentor-detail-container">
            <div className='cancel-button' onClick={handleCancel}>
                <FontAwesomeIcon icon={faXmark}/>
            </div>
            <h2 className="mentor-title">멘토 신청 내역</h2>
            <div className="mentor-details">
                <div className="mentor-row">
                    <span className="mentor-label">전문 분야</span>
                    <span className="mentor-value"> {category ? `${category.parentCategory.categoryName} > ${category.categoryName}` : ''} </span>
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
                <button className="approve-btn" onClick={handleApproval}>승인</button>
                <button className="reject-btn" onClick={handleReject}>거절</button>
            </div>
        </div>

  )
}
