import { useState } from 'react'
import { useMemberAuth } from "../../../util/AuthContext"
import FollowList from './MemberFollowList'
import CounselList from './MemberCounselList';
import ReviewList from './MemberReviewList';
import InquiryAnswerList from "./MemberInquiryAnswerList";
import MemberMentorBoardList from "./MemberMentorBoardList";

export default function MemberTabs({handleUpdate}) {
    /* Context에 저장된 토큰, 멤버정보 */
    const { token, member } = useMemberAuth();

    //활성화 된 탭 상태를 저장하는 state
    const [activeTab, setActiveTab] = useState(
        member.memberRole==='ROLE_MENTEE' ?
            "inquiry" : "answer"
    )
    //탭 클릭시 실행되는 함수
    const handleTabClick = (tab) => {
        setActiveTab(tab);
    }

  return (
    <section className="tab-container">
        <nav>
              {member.memberRole === 'ROLE_MENTEE' ? (
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
                        className={`tab ${activeTab === "board" ? "active" : ""}`} 
                        onClick={() => handleTabClick("board")}
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
                <FollowList handleUpdate={handleUpdate} />
            </div>
            )}
            {activeTab === "answer" && (
                <div id="answer">
                    <InquiryAnswerList />
                </div>
            )}
            {activeTab === "review" && (
            <div id="review">
                <ReviewList />
            </div>
            )}
            {activeTab === "board" && (
            <div id="boards">
                <MemberMentorBoardList/>
            </div>
            )}
        </div>
    </section>
  )
}