const BACKEND_SERVER = "";
/*
POST /answer
PUT  /answer/update/{answerNo}
PUT  /answer/delete/{answerNo}
PUT  /answer/accept
GET  /answer/{answerNo}/answerDetail
GET  /answer/answerList/{inquiryNo}/inquiryVote
GET  /answer/answerList/{inquiryNo}/inquiryDate
GET  /answer/answerList/{categoryNo}/categoryVote
GET  /answer/answerList/{categoryNo}/categoryDate
GET  /answer/answerList/recently-vote
*/
//질문하나의 답변 최신순
export const AnswerByDate=async(inquiryNo,page,size)=>{
    const response=await fetch(`${BACKEND_SERVER}/answer/${inquiryNo}/answer-date?page=${page}&size=${size}`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
//답변 추천순순
export const AnswerByVote=async(inquiryNo,page,size)=>{
    const response=await fetch(`${BACKEND_SERVER}/answer/${inquiryNo}/answer-vote?page=${page}&size=${size}`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
export const countVote = async(answerNo)=>{
    const response= await fetch(`${BACKEND_SERVER}/vote/${answerNo}/votes`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
export const upVote = async (answerNo, memberNo) => {
    const response = await fetch(`${BACKEND_SERVER}/vote/upvote`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ answerNo, memberNo })  // answerNo와 memberNo를 body에 포함
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
export const downVote = async()=>{
    const response= await fetch(`${BACKEND_SERVER}/vote/downvote`,{
        method:'POST'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
