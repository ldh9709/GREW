package com.itwill.jpa.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 사용자가 자신의 정보를 확인할 수 있는 대시보드 페이지를 제공 */
@Controller
public class DashboardController {
    @GetMapping("/dashboard/myInfo")
    public String myInfo(){
        return "dashboard/myInfo";
    }

    
}

