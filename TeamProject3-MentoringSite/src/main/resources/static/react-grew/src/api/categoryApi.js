const BACKEND_SERVER = "";

export const ListCategory=async()=>{
    const response=await fetch(`${BACKEND_SERVER}/category`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}