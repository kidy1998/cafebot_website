package com._thefull.dasom_web_demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
	
 
    
    /**
     * 로봇 => 서버로 홍보 멘트 요청 왔을 때 시간과 storeID 받아서 홍보 멘트 return
     * @param time
     * @param storeID
     * @return
     */
    @GetMapping("/robot/ment")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> MenuPromotionRobotMent(@RequestParam(name = "time") String time,
    		@RequestParam(name="storeID") Long storeID) {
    	
    	 // String으로 받은 시간을 LocalTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, formatter);

        // DB에서 조회된 값 또는 ChatGPT 응답 값 (String 형태)
        String response = menuPromotionService.checkMent(localTime, storeID);

        // JSON 응답을 위한 Map 객체 생성
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", storeID); // storeID를 JSON에 추가
        responseBody.put("time", time);  // time을 JSON에 추가
        responseBody.put("promotionMent", response); // 응답 String을 JSON에 추가

        // JSON 형태로 반환
        return ResponseEntity.ok().body(responseBody);
    }

}
