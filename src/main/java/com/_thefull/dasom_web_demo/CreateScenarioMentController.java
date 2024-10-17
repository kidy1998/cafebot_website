package com._thefull.dasom_web_demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.DasomLocationService;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.openai.ChatGPTController;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service.MenuPromotionService;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CreateScenarioMentController {
	
	private final DasomLocationService dasomLocationService;
	private final StoreService dasomStoreService;
	private final ChatGPTController chatGPTController;
	private final MenuPromotionService menuPromotionService;
	
	
	/**
	 * 로봇 에서 사람 파악 후 서버에 요청 => 서버에서 시나리오에 따른 멘트 발화
	 * @param robotID
	 * @param storeID
	 * @param people
	 * @param time
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/createment")
    public JsonNode createMeeting(
            @RequestParam(name = "robotID") int robotID,
            @RequestParam(name = "storeID") long storeID,
            @RequestParam(name = "people") int people,
            @RequestParam(name = "time") String time,
            @RequestParam(value = "lang", required = false, defaultValue = "ko") String lang) throws JsonMappingException, Exception {
        
        // String으로 받은 시간을 LocalTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, formatter);
        
        // robot에 해당하는 DasomLocation 중 use가 true인 첫 번째 항목 조회
        DasomLocationResponseDTO dasomLocation = dasomLocationService.findFirstRobotLocation(robotID);
        Store store = dasomStoreService.findStore(storeID);
        
        String name = store.getName();
        
        // 로직 예시: 받은 값들을 콘솔에 출력
//        System.out.println("robotID: " + robotID);
//        System.out.println("storeID: " + storeID);
//        System.out.println("People: " + people);
//        System.out.println("Time: " + localTime);
//        System.out.println("name: " + name);
        
        
        //DB에서 조회된 값 또는 ChatGPT 응답 값 (String 형태)
        String ment = menuPromotionService.checkMent(localTime, storeID);
        
        if(ment != null && !ment.trim().isEmpty()) {
        	
        	//System.out.println("홍보멘트 존재");
        	// response를 JsonNode로 바로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree("{\"id\":\"" + storeID + "\", \"time\":\"" + time + "\", \"promotionMent\":\"" + ment + "\"}");
            
            return jsonResponse;
        }
        
        JsonNode response = chatGPTController.createScenarioMent(dasomLocation, name, localTime, people, lang);
        

        return response;
        
        
    }

}
