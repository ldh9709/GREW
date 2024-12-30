export const typeName = (type) => {
    switch (type) {
        case "MEMBER":
            return "유저";
        case "ANSWER":
            return "답변게시글";
        case "INQUIRY":
            return "질문게시글";
        default:
            return "알 수 없음";
    }
};

export const reasonName = (reason) => {
    switch (reason) {
        case 1:
            return "욕설/비속어";
        case 2:
            return "스팸/광고";
        case 3:
            return "성적인 내용";
        case 4:
            return "폭력적인 내용";
        case 5:
            return "개인정보 유출";
        case 6:
            return "기타";
        default:
            return "알 수 없음";
    }
};

export const reportStatus = (status) => {
    switch (status) {
        case 1:
            return "접수 중";
        case 2:
            return "신고 처리";
        case 3:
            return "무고 처리";
        default:
            return "알 수 없음";
    }
};
