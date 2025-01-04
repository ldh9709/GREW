import { faL } from "@fortawesome/free-solid-svg-icons";

const BACKEND_SERVER = "";

//리프레시 토큰 발급 
export const refreshToken = async() => {
    const refreshToken = getRefreshTokenFromCookie();

    const response = await fetch(``,{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body: JSON.stringify({refreshToken})
    });

    if(response.ok){
        const data = await response.json();
        //새로운 엑세스 토큰과 리프레시 토큰 저장 
        storeTokens(data.accessToken, data.refreshToken);
        return true;
    }else{
        console.error("토큰 갱신 실패")
        return false;
    }
}