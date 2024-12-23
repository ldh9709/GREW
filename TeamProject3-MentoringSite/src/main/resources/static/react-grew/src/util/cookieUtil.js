import { Cookies } from "react-cookie";

const cookies = new Cookies()

export const setCookie = (name, value, days) => {

  const expires = new Date()
  expires.setUTCDate(expires.getUTCDate() + days ) //보관기한 

  return cookies.set(name, value, {path:'/', expires:expires})

}

export const getCookie = (cookieName) => {
  const name = `${cookieName}=`;
  const decodedCookie = decodeURIComponent(document.cookie);
  const cookieArray = decodedCookie.split(";");

  for (let cookie of cookieArray) {
      cookie = cookie.trim();
      if (cookie.startsWith(name)) {
          const value = cookie.substring(name.length);
          try {
              // Base64로 인코딩된 경우 디코딩
              const decodedValue = atob(value);
              return JSON.parse(decodedValue);
          } catch (error) {
              console.warn("Failed to decode cookie. Returning raw value:", error);
              return JSON.parse(value); // Base64가 아닌 경우 그대로 반환
          }
      }
  }
  return null; // 쿠키가 없을 경우 null 반환
};

export const removeCookie = (name , path="/") => {

  cookies.remove(name, {path} )
  
}

