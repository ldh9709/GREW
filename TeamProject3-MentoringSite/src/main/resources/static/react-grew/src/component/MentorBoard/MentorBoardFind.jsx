import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from './api';

const MentorBoardDetail = () => {
    const { id } = useParams();
    const [board, setBoard] = useState(null);

    useEffect(() => {
        api.getMentorBoardDetail(id)
            .then((data) => setBoard(data))
            .catch((error) => console.error(error));
    }, [id]);

    if (!board) {
        return <p>로딩 중...</p>;
    }

    return (
        <div>
            <h1>{board.mentorBoardTitle}</h1>
            <p>{board.mentorBoardContent}</p>
            <img src={board.mentorBoardImage} alt="Mentor Board" />
        </div>
    );
};

export default MentorBoardDetail;
