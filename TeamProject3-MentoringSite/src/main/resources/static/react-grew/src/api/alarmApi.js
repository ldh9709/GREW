const BACKEND_SERVER = " ";

export const findByMemberNo = async (memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/alarm/${memberNo}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
