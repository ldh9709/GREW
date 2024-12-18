import { useState } from 'react'
import FollowList from './MemberFollowList'
import InquiryList from './MemberInquiryList'

export default function MemberTabs() {
    //활성화 된 탭 상태를 저장하는 state
    const [activeTab, setActiveTab] = useState("inquiry");
    
    //탭 클릭시 실행되는 함수
    const handleTabClick = (tab) => {
        setActiveTab(tab);
    }

  return (
    <section className="tab-container">
        <nav   nav>
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
        </nav>
          
        {/* 탭별 콘텐츠 */}
        <div>
            {activeTab === "inquiry" && (
            <div id="inquiry">
                <InquiryList />
            </div>
            )}
            {activeTab === "counseling" && (
            <div id="counseling">
                <p>상담내역이 여기에 표시됩니다.</p>
            </div>
            )}
            {activeTab === "following" && (
            <div id="following">
                <FollowList />
            </div>
            )}
        </div>
    </section>
  )
}
