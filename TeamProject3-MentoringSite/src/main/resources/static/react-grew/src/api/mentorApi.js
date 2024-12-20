const BACKEND_SERVER = "";


//멘토프로필 상세보기기
export const getMentorProfile = async (mentorProfileNo) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}`, {
      method: "GET",
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
  };
