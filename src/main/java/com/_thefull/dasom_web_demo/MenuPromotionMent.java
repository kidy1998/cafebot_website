package com._thefull.dasom_web_demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com._thefull.dasom_web_demo.domain.menu.service.MenuService;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service.MenuPromotionService;
import com._thefull.dasom_web_demo.domain.robot.repository.RobotRepository;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuPromotionMent {
	
	  private final MenuPromotionService menuPromotionService;
	
 /* 로봇 발화 멘트 전송 - 제품 홍보 */
    
    @GetMapping("/robot/ment")
    @ResponseBody
    public ResponseEntity<String> MenuPromotionRobotMent(@RequestParam(name = "time") String time) {
    	
    	// String으로 받은 시간을 LocalTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, formatter);

        String response = menuPromotionService.checkMent(localTime);
        return ResponseEntity.ok().body(response);
    }

}
