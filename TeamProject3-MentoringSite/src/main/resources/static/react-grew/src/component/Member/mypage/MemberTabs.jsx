import FollowList from './FollowList'

export default function MemberTabs({memberNo}) {
  return (
    <section className="tab-container">
        <nav>
            <ul className="tabs">
                <li className="tab" data-tab="questions">질문내역</li>
                <li className="tab" data-tab="counseling">상담내역</li>
                <li className="tab active" data-tab="following">팔로잉</li>
            </ul>
        </nav>
        <div className="tab-content hidden" id="questions">
            <ul className="list">
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
        <div className="tab-content hidden" id="counseling">
            <p>상담내역이 여기에 표시됩니다.</p>
        </div>
        <div>
              <FollowList memberNo={ memberNo } />      
        </div>
    </section>
  )
}
