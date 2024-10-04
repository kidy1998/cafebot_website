package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.openai;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.service.MenuService;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.ForMentOfMenuPromotionDTO;
import com._thefull.dasom_web_demo.domain.store.service.StoreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.Collections;

@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
public class ChatGPTController {
	
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

	// ObjectMapper는 JSON 변환에 사용
    private final ObjectMapper mapper = new ObjectMapper(); // ObjectMapper 선언
    

    /**
     * 홍보 멘트 테스트 버튼 클릭시 작동 => 현재 구현 X
     * @param prompt
     * @return
     */
    @PostMapping("/testchat")
    public String chat(@RequestParam(name = "prompt")String prompt){
        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res=template.postForObject(apiURL,req, ChatGPTResponseDTO.class);
        return res.getChoices().get(0).getMessage().getContent();
    }

    /**
     * 홍보 메뉴 등록 시 홍보 멘트 chatgpt 작성
     * @param dto
     * @return
     */
    @PostMapping("/ment")
    public ResponseEntity<?> createAiMent(@ModelAttribute ForMentOfMenuPromotionDTO dto){

        String prompt = createPrompt(dto, dto.getMenu());
        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res=template.postForObject(apiURL,req, ChatGPTResponseDTO.class);
        return ResponseEntity.ok(Collections.singletonMap("ment", res.getChoices().get(0).getMessage().getContent()));

    }

    /**
     * createAiMent 에서의 프롬프트 생성
     * @param dto
     * @param menuName
     * @return
     */
    private String createPrompt(ForMentOfMenuPromotionDTO dto, String menuName){

        String prompt = "카페에서 할인 홍보 멘트를 만들려고 합니다. 메뉴 이름은 "+menuName+"이고,"
                +dto.getDiscType() + "은 다음과 같고, 할인 값은 " + dto.getDiscVal() + "입니다. "
                + "할인기간은" + dto.getStartDate() + "부터 " + dto.getEndDate() + "까지 유효하며, "
                + "할인 시간은" + dto.getStartTime() + "부터 " + dto.getEndTime() + "까지입니다. "
                + (dto.getIsAddDiscCond() ? "추가 조건: " + dto.getAddDiscCond() + " " : "")
                + (dto.getIsAddMenuDesc() ? "추가 메뉴 설명: " + dto.getAddMenuDesc() : "")
                + "메뉴의 정가는 "+dto.getPrice()+"이고, 정가에서 얼마나 할인했는지 꼭 포함해서 설명해주세요."
                +"멘트에는 연도를 제외한 할인기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 말하고 싶어요. 그리고 나머지 정보를 참고해서 300자의 홍보멘트를 만들어주세요. 로봇이 말할거야. 숫자는 숫자로 작성하고 "
                + ", 나머지는 한글로, 매우 자연스럽게 작성해주세요.";



        return prompt;
    }
    
    
    /*=====================================================================================================================*/
    
    
 // 실제 응답을 생성하는 메서드
    public JsonNode createScenarioMent(DasomLocationResponseDTO dto, String storeName, LocalTime localTime, int people, String lang) {
    	
        
        // ChatGPT API 호출을 위한 프롬프트 생성
        String prompt = createScenario(dto, storeName, localTime, people, lang);
        //System.out.println("프롬프트 : " + prompt);

        // ChatGPT API 요청 생성 및 호출
        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res = template.postForObject(apiURL, req, ChatGPTResponseDTO.class);
     
        // System.out.println("응답 : " + res);

        // ChatGPT 응답에서 content 추출 (JSON 형식의 문자열)
        String content = res.getChoices().get(0).getMessage().getContent();
        
        content = content.replaceAll("```json", "").replaceAll("```", "").trim();
        
        System.out.println("Content: " + content); // JSON 형식의 문자열 확인

        // content를 JsonNode로 변환
        JsonNode jsonResponse = null;
        try {
            jsonResponse = mapper.readTree(content); // content를 JSON 객체로 파싱
        } catch (Exception e) {
            e.printStackTrace();
            // 파싱에 실패하면 에러 내용을 JSON으로 생성
            ObjectNode errorNode = mapper.createObjectNode();
            errorNode.put("error", "Invalid JSON format");
            errorNode.put("originalContent", content);
            return errorNode;
        }

        return jsonResponse; // JSON 객체 반환
    }
    
    

	/**
	 * createScenarioMent 에서의 프롬프트 생성
	 * @param dto
	 * @param storeName
	 * @param localTime
	 * @param people
	 * @param lang 
	 * @return
	 */
	private String createScenario(DasomLocationResponseDTO dto, String storeName, LocalTime localTime, int people, String lang) {
		
		System.out.println("lang : " + lang);
		String prompt;
		
		if(lang.equals("kor")) {
			
			 prompt = "너는 한국의 홍보 멘트 생성 전문가야. 너가 해야할 일은 인원, 시간 등에 관한 정보를 받고 홍보 로봇 근처 메뉴에 대한 정보를 종합해서 홍보 멘트를 만들어야 돼."
					+ " 반환 형태는 json 형식으로 반환하고 store:" + storeName + "time : "+localTime + "people :" + people + "scenarioMent : "
					+ "로봇에 왼쪽에 있는 메뉴들은 " + dto.getLeftSide() + ", 왼쪽 앞은 " + dto.getLeftFront() + "오른쪽 앞은" + dto.getRightFront() + 
					"오른쪽은" + dto.getRightSide() + "이고 앞에는 " + dto.getFront() + "에 해당하는 메뉴가 있어. 여기서 null 로 표시된 메뉴는 없다는 뜻이니 "
					+ "말을 안하면 돼. 메뉴 홍보에 대한 내용은 2~3개 정도로 작성해줘. 현재 시간은 " + localTime + "인데 이게 낮 12시~1시일 경우 손님이 많은 피크 시간대로 간주하면 돼. 사람 수는 "
					+ people + "명이고 가게 이름은 " + storeName + "이야. 한국어로 자연스럽게 작성해 주고 다음은 예시 멘트인데  '안녕하세요. 원더풀카페 에 오신 것을 환영합니다. 제 오른쪽에는 각종가 있는 디저트와 샌드위치가 있고 왼쪽에는 먹음직스러운 케이크가 있습니다.	\r\n"
					+ "앞쪽에 위치한 아메리카노와 함께 먹는다면 정말 맛있지 않을까요? 일행이 있으시다면 2층에 단체 공간을 추천드립니다!' 이렇게 위치에 따른 메뉴를 발화하고 사람이 여러 명일 때는 "
					+ "위와 같이 단체 자리로 안내하고 피크 시간일 경우에는 '빈 자리를 찾고 싶으시다면 위층이나 1층 창가 공간을 추천드립니다!' 같은 멘트를 작성해줘.";
			
		}else {
			
			 prompt = "You are an expert in generating promotional messages for the US market. Your task is to receive information about the number of people, time, and relevant details about the menu around the robot, and then create a promotional message. Return the result in JSON format as follows: store: " + storeName + ", time: " + localTime + ", people: " + people + ", scenarioMent : "

					+ " The menu items are located as follows: on the left side are " + dto.getLeftSide() + ", in the left front are " + dto.getLeftFront() + ", in the right front are " + dto.getRightFront() + ", on the right side are " + dto.getRightSide() + ", and in the front are " + dto.getFront()
					+ ". If any menu is marked as null, it means there are no items in that position, so there is no need to mention them. Please create 2-3 sentences of promotional content for the menu. The current time is " + localTime + ", which, if it's between 12 PM and 1 PM, should be considered peak time with many customers. The number of people is " + people + " and the store name is " + storeName
					+ ". Write the content in natural English. Here's an example sentence: 'Hello, welcome to Wonderful Café! To my right, we have a variety of delicious desserts and sandwiches, and to the left, we have some mouth-watering cakes. How about pairing one of our cakes with a freshly brewed Americano from the front section? If you're here with a group, "
					+ "we recommend the spacious seating area on the second floor!' Based on the number of people, suggest a group seating area, and if it's peak time, suggest: 'If you're looking for a spot, we recommend the upstairs seating or the window seats on the first floor!'";
										 
		}
		
		
		return prompt;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
