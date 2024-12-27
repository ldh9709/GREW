import React from "react";
import imageSrc from './Member/mentor-content1.jpg';
export default function MainMentorBoard({mentorBoard}) {
  return (
    
    <div className="tab-boards">
      <div className="board-list2">
          <div className="main-board-card">
            <div className="board-image-container">
              <img src={imageSrc} alt="content-image" className="board-image" />
            </div>
            <div className="board-details">
              <h3 className="board-title">{mentorBoard.mentorBoardTitle}</h3>
              <p className="board-description">
                {mentorBoard.mentorBoardContent.substring(0, 100)}...
              </p>
              <p className="board-info">
                <span className="board-date">
                  {mentorBoard.mentorBoardDate.substring(0, 10)}
                </span>
                <span className="board-views">
                  {" "}
                  | 조회수 : {mentorBoard.mentorBoardViews}
                </span>
              </p>
            </div>
          </div>
       
      </div>
    </div>
  );
}
