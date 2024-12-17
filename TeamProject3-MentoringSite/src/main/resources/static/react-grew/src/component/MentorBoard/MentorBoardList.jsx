
import React, { useEffect, useState } from 'react';
import api from './api';

const MentorBoardList = () => {
    const [boards, setBoards] = useState([]);

    useEffect(() => {
        api.getMentorBoards()
            .then((data) => setBoards(data))
            .catch((error) => console.error(error));
    }, []);

    return (
        <div>
            <h1>멘토보드 리스트</h1>
            {boards.map((board) => (
                <div key={board.mentorBoardNo}>
                    <h2>{board.mentorBoardTitle}</h2>
                    <p>{board.mentorBoardContent}</p>
                    <img src={board.mentorBoardImage} alt="Mentor Board" />
                </div>
            ))}
        </div>
    );
};

export default MentorBoardList;

