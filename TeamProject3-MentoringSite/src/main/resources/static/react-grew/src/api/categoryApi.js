const BACKEND_SERVER = "";

//전체 카테고리 출력
export const ListCategory=async()=>{
    const response=await fetch(`${BACKEND_SERVER}/category`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
//카테고리별 항목 출력
export const childCategory=async(categoryNo)=>{
    const response=await fetch(`${BACKEND_SERVER}/category/${categoryNo}`,{
        method:'GET'
    });
    const responseJsonObject=await response.json();
    return responseJsonObject;
}
//카테고리넘버로 카테고리 뽑기
// export const getCategory=async(categoryNo)=>{
//     const response=await fetch(`${BACKEND_SERVER}/category/get/${categoryNo}`,{
//         method:'GET'
//     });
//     const responseJsonObject=await response.json();
//     return responseJsonObject;
// }
