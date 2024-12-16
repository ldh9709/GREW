import React from 'react'
import * as inquiryApi from "../../api/inquiryApi";
export default function InquiryItem({inquiry}) {
  
  return (
          
          <tr className="inquiry_item">
            <td width="50" align="center" bgcolor="ffffff" height="20">
              {inquiry.inquiryNo}
            </td>
            <td width="150" bgcolor="ffffff" style={{ paddingLeft: 10 }}>
              <a
                href={`inquiry/${inquiry.inquiryNo}`}
                className="inquiry_item"
                inquiry_no={inquiry.inquiryNo}
                onClick={()=>inquiryApi.increaseView(inquiry.inquiryNo)}
              >
                {inquiry.inquiryTitle}
              </a>
            </td>
            <td width="150" align="center" bgcolor="ffffff">
              {inquiry.inquiryContent}
            </td>
            <td width="120" align="center" bgcolor="ffffff">
              {inquiry.inquiryDate.substring(0,10)}
            </td>
            <td width="120" align="center" bgcolor="ffffff">
              {inquiry.inquiryViews}
            </td>
            <td width="120" align="center" bgcolor="ffffff">
              {inquiry.memberName}
            </td>
          </tr>
          
        );
      }
      
