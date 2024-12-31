import { toast } from "react-toastify";
import "../css/toast.css";

export const toastMessage = (type, message, position = "bottom-center") => {
  const toastOptions = {
    position,
    className: "custom-toast",
    autoClose: 3000, // 자동 닫힘 설정
    closeOnClick: true, // 클릭 시 닫힘
  };

  switch (type.toLowerCase()) {
    case "success":
    case "성공":
      toast.success(message, toastOptions);
      break;
    case "error":
    case "에러":
      toast.error(message, toastOptions);
      break;
    default:
      toast(message, toastOptions);
  }
};
