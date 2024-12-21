import { getCookie } from "../../../util/cookieUtil"
import { useState } from 'react'
import FollowList from './MemberFollowList'
import CounselList from './MemberCounselList';
import ReviewList from './MemberReviewList';
import InquiryAnswerList from "./MemberInquiryAnswerList";
import MemberMentorContentList from "./MemberMentorContentList";

export default function MemberTabs() {
    //쿠키에 저장된 인증 유저 정보
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;
    const role = memberCookie.memberRole;

    //활성화 된 탭 상태를 저장하는 state
    const [activeTab, setActiveTab] = useState(
        role==='ROLE_MENTEE' ?
            "inquiry" : "answer"
    )
    //탭 클릭시 실행되는 함수
    const handleTabClick = (tab) => {
        setActiveTab(tab);
    }

  return (
    <section className="tab-container">
        <nav>
              {role === 'ROLE_MENTEE' ? (
                <ul className="tabs">
                    <li 
                        className={`tab ${activeTab === "inquiry" ? "active" : ""}`} 
                        onClick={() => handleTabClick("inquiry")}
                    >
                        질문내역
                    </li>
                    <li 
                        className={`tab ${activeTab === "counseling" ? "active" : ""}`} 
                        onClick={() => handleTabClick("counseling")}
                    >
                        상담내역
                    </li>
                    <li 
                        className={`tab ${activeTab === "following" ? "active" : ""}`} 
                        onClick={() => handleTabClick("following")}
                    >
                        팔로잉
                    </li>
                </ul>
              
              ) : (
                <ul className="tabs">
                    <li 
                        className={`tab ${activeTab === "answer" ? "active" : ""}`} 
                        onClick={() => handleTabClick("answer")}
                    >
                        답변내역
                    </li>
                    <li 
                        className={`tab ${activeTab === "counseling" ? "active" : ""}`} 
                        onClick={() => handleTabClick("counseling")}
                    >
                        상담내역
                    </li>
                    <li 
                        className={`tab ${activeTab === "review" ? "active" : ""}`} 
                        onClick={() => handleTabClick("review")}
                    >
                        리뷰내역
                    </li>
                    <li 
                        className={`tab ${activeTab === "contents" ? "active" : ""}`} 
                        onClick={() => handleTabClick("contents")}
                    >
                        컨텐츠
                    </li>
                </ul>    
            )}
        </nav>
          
        {/* 탭별 콘텐츠 */}
        <div>
            {activeTab === "inquiry" && (
            <div id="inquiry">
                <InquiryAnswerList />
            </div>
            )}
            {activeTab === "counseling" && (
            <div id="counseling">
                <CounselList/>
            </div>
            )}
            {activeTab === "following" && (
            <div id="following">
                <FollowList />
            </div>
            )}
            {activeTab === "review" && (
            <div id="review">
                <ReviewList />
            </div>
            )}
            {activeTab === "contents" && (
            <div id="contents">
                <MemberMentorContentList/>
            </div>
            )}
        </div>
    </section>
  )
}