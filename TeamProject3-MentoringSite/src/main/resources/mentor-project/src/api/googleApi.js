import axios from "axios";

/* 클라이언트 애플리케이션이 이 주소를 통해 백엔드 API와 통신 */
const API_SERVER_HOST = 'http://192.168.15.39:8080';

/* GoogleOAuth2 클라이언트 ID, 인증 요청 시 클라이언트를 식별하는 데 사용 */
const client_id  =`402783451794-u7rkm04rveeonmgii4ckltqlnl673sf9.apps.googleusercontent.com`;

/* GoogleOAuth2 클라이언트 ID, 인증 요청 시 클라이언트를 식별하는 데 사용 */
const client_secret  =`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`;

/* Google OAuth2 인증이 완료되면 사용자가 리디렉션될 URL, 이 값은 Google Cloud Console에서 승인된 리디렉션 URI로 설정되어 있어야한다. */
const redirect_uri = `http://localhost:8080/login/oauth2/code/google`;

/* Google OAuth2 인증 서버의 Access Token 발급 엔드포인트 URL */
const access_token_url = `https://oauth2.googleapis.com/token`;

/*  Google의 OAuth2 인증 엔드포인트 URL. 사용자가 이 URL로 리디렉션되면 Google 로그인 페이지가 표시. */
const googleAuthCodePath = `https://accounts.google.com/o/oauth2/v2/auth`;

/* Google 로그인 URL을 생성하는 함수 */
/* 
client_id: Google 클라이언트 ID.
redirect_uri: 인증 후 리디렉션될 URI.
response_type: 응답 유형(여기서는 code, 즉 인증 코드를 요청).
scope: 사용자로부터 요청할 권한(여기서는 email과 profile).
 */
export const getGoogleLoginLink = () => {

  const googleURL = `${googleAuthCodePath}?client_id=${client_id }&redirect_uri=${redirect_uri}&response_type=code&scope=email profile`;

  console.log("googleURL : ", googleURL);
  return googleURL

}

/* 
  1. Google 인증 서버에 요청하여 Access Token을 가져온다.
  2. 구글에서 인증 코드를 Access Token으로 교환할 때 axios.post로 요청해야한다.
 */
export const getAccessToken = async (authCode) => {
  /* 
    1. Google 서버에 authCode와 클라이언트 정보를 POST 요청으로 전송.
    2. Google 서버는 요청을 검증하고, 성공하면 Access Token을 반환
    3. authCode: Google이 리디렉션 URI로 전달한 인증 코드.
    4. header: 요청 헤더로, 요청 본문 타입을 지정.
    5. params: 요청에 포함되는 데이터로, authCode, client_id, redirect_uri 등이 포함됩니다.
   */
  console.log("authCode : ", authCode);

  const header = {
   headers: {
     "Content-Type": "application/x-www-form-urlencoded",
   }
  }
  /* 
  grant_type : OAuth2에서 요청 유형을 지정합니다.
  "authorization_code"를 사용해 인증 코드를 Access Token으로 교환

  client_id: Google Cloud Console에서 생성한 클라이언트 ID로, 애플리케이션을 식별

  redirect_uri: Google 인증 과정에서 사용한 리디렉션 URI

  code: Google 인증 서버로부터 받은 인증 코드
  이 코드는 사용자의 인증 상태를 나타내며, Access Token을 요청하는 데 필요
   */
  const params =  new URLSearchParams({
    grant_type: "authorization_code",
    client_id: client_id ,
    client_secret: client_secret,
    redirect_uri: redirect_uri,
    code: authCode
  });
  console.log("params : ", params)
  
  /* axios.post : HTTP POST 요청을 보내는 함수
     Google의 Access Token 발급 URL (access_token_url)로 요청을 보내 Access Token을 요청
   */
  const response = await axios.post(access_token_url, params , header)

  console.log("axios.post : ", response);

  const accessToken = response.data.access_token
  console.log("accessToken : ", accessToken);

  return accessToken
}

/* 발급받은 Access Token을 사용해 백엔드 서버에서 사용자 정보를 가져오는 함수 */
export const getMemberWithAccessToken = async(accessToken) => {

  const headers = {
    Authorization: `Bearer ${accessToken}`,
  };
  /* 
    1. 클라이언트가 Access Token을 포함한 GET 요청을 백엔드 서버로 보냄.
    2. 백엔드 서버는 Access Token을 사용해 Google 사용자 정보를 검증하고, 관련 데이터를 반환.
   */
  const response = await axios.get(`${API_SERVER_HOST}/api/member/google?accessToken=${accessToken}`, {headers});
  console.log("getMemberWithAccessToken : ", response);


  return response.data

}
