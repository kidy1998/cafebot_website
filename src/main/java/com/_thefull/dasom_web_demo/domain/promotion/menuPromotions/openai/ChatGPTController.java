package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.openai;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.service.MenuService;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.ForMentOfMenuPromotionDTO;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.service.StoreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    private final StoreService dasomStoreService;

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
    public ResponseEntity<?> createAiMent(@ModelAttribute ForMentOfMenuPromotionDTO dto, HttpServletRequest request){
    	
    	HttpSession session = request.getSession(false);
        Long storeId= (Long)session.getAttribute("storeId");
        
        Store store = dasomStoreService.findStore(storeId);
        String name = store.getName();

        String prompt = createPrompt(dto, name);
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
    private String createPrompt(ForMentOfMenuPromotionDTO dto, String name){
    	
    	System.out.println("멘트정보 : " + dto.toString());
    	
    	// 한국어 프롬프트 생성
    	String prompt;
    	
    	if (dto.getDiscVal() != 0) { // 할인 조건이 있는 경우
    	    prompt = String.format(
    	        "이름이 %s인 카페에서 할인 홍보 멘트를 만들려고 합니다. 메뉴 이름은 %s이고, 할인 값은 %s입니다. "
    	        + "할인 기간은 %s부터 %s까지 유효하며, 할인 시간은 %s부터 %s까지입니다. "
    	        + "%s%s메뉴의 정가는 %s원이며, 정가에서 얼마나 할인되었는지 꼭 포함해서 설명해주세요. "
    	        + "멘트에는 연도를 제외한 할인 기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 말하고 싶어요. "
    	        + "그리고 나머지 정보를 참고해서 약 200자 정도의 홍보 멘트를 만들어주세요. 로봇이 말할 거예요. "
    	        + "숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해주세요. 추가로 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아 주세요",
    	        name,
    	        dto.getMenu(),
    	        dto.getDiscVal(), //얼마나 할인하는지
    	        dto.getStartDate(),
    	        dto.getEndDate(),
    	        dto.getStartTime(),
    	        dto.getEndTime(),
    	        dto.getIsAddDiscCond() ? "추가 조건: " + dto.getAddDiscCond() + " " : "",
    	        dto.getIsAddMenuDesc() ? "추가 메뉴 설명: " + dto.getAddMenuDesc() + " " : "",
    	        dto.getPrice()
    	    );
    	} else { // 할인 조건이 없는 경우
    		
    		  prompt = String.format(
    			        "할인 없이 이름이 %s인 카페에서 %s 메뉴를 홍보하는 멘트를 만들려고 합니다. "
    			        + "%s의 특징을 강조하고, 자연스럽게 제품을 소개해주세요. 다음은 수박 주스에 관하여 홍보하는 예시입니다. "
    			        + "[예시: 돌아온 베스트셀러! 수박주스가 출시되었어요! 직접 공수한 수박을 갈아서 달달하고 시원한 맛을 자랑합니다. "
    			        + "꿉꿉하고 더운 날씨에 시원한 수박주스 어떠세요?] "
    			        + "나머지 정보를 참고해서 300자의 홍보 멘트를 만들어주세요. 메뉴와 어울릴 만한 멘트를 작성해주고 특정 계절,날씨를 언급하는 것은 지양해주세요. "
    			        + "숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해주세요. 추가로 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아 주세요",
    			        name,
    			        dto.getMenu(),
    			        dto.getAddMenuDesc(), // 제품의 특징 설명
    			        dto.getMenu(),
    			        dto.getMenu()
    	    );
    	}



        return prompt;
    }
    
    
    /*=====================================================================================================================*/
    
    
    // 시나리오 멘트 생성하는 메서드
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
        
       // System.out.println("Content: " + content); // JSON 형식의 문자열 확인

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
		
		System.out.println("localTime : " + localTime);
		
		
		String prompt;
		
		if(lang.equals("ko")) {
			
			 prompt = "너는 한국의 홍보 멘트 생성 전문가야. 너가 해야 할 일은 인원, 시간 등에 관한 정보를 받고, 홍보 로봇 근처 메뉴에 대한 정보를 종합해서 홍보 멘트를 만들어야 해."
					+ " 반환 형태는 json 형식으로 반환하고 key 값들은 각각 store, time, people, greeting, rightMenu, leftMenu, frontMenu, seatingGuide, timeMent 순서대로 만들어줘."
					+ " 만약 각 위치에 해당하는 값이 null일 경우 해당 문장은 만들지 말고 건너뛰어줘."
					+ "store: " + storeName + ", time: " + localTime + ", people: " + people + ","
					+ "\"greeting\": \"첫 번째 문장에서는 카페 이름인 "+storeName+"을 넣은 인사말을 만들어 줘.\","
					+ "\"rightMenu\": \"" + ((dto.getRightSide() != null || dto.getRightFront() != null) ? "두 번째 문장에서는 오른쪽에 위치한 " 
					+ (dto.getRightSide() != null ? dto.getRightSide() : " ") 
					+ (dto.getRightSide() != null && dto.getRightFront() != null ? "와 " : " ") 
					+ (dto.getRightFront() != null ? dto.getRightFront() : " ") + "메뉴들을 홍보해줘." : " ") + "\","
					+ "\"leftMenu\": \"" + ((dto.getLeftSide() != null || dto.getLeftFront() != null) ? "세 번째 문장에서는 왼쪽에 위치한 " 
					+ (dto.getLeftSide() != null ? dto.getLeftSide() : " ") 
					+ (dto.getLeftSide() != null && dto.getLeftFront() != null ? "와 " : " ") 
					+ (dto.getLeftFront() != null ? dto.getLeftFront() : " ") + "메뉴들을 홍보해줘." : " ") + "\","
					+ "\"frontMenu\": \"" + (dto.getFront() != null ? "네 번째 문장에서는 앞에 위치한 " + dto.getFront() + " 메뉴를 홍보해줘." : " ") + "\","
					+ "만약 각 위치에 해당하는 값이 null일 경우 해당 문장은 만들지 말고 건너뛰어 줘. "
					+ "\"seatingGuide\": \"" 
				    + (people >= 2 
				        ? "여러 명의 손님이 있을 때는 '일행이 있으시군요! 위층에 단체석을 추천드립니다.'와 같이 사람이 많이 왔을 때 일반적으로 사용하는 좌석 안내를 추가해줘. " : ""
				    ) + "\","
				    + "\"timeMent\": \"" 
				    + ((localTime.isAfter(LocalTime.of(12, 0)) || localTime.equals(LocalTime.of(12, 0))) 
					           && (localTime.isBefore(LocalTime.of(13, 0)) || localTime.equals(LocalTime.of(13, 0))) 
					            ? "'지금은 사람이 많아 혼잡한 시간대입니다. 빈 자리를 찾고 싶으시면 위층이나 1층 창가 공간을 추천드립니다!'와 같이 피크 시간대에 발생할 수 있는 문제를 해결할 수 있는 문장을 작성해줘."
					            : (localTime.isBefore(LocalTime.of(10, 0)) || localTime.equals(LocalTime.of(10, 0)))
					                ? " '오늘도 즐거운 하루 되시길 바랍니다!'와 같은 아침 인사를 추가해줘." 
					                : ((localTime.isAfter(LocalTime.of(18, 0)) || localTime.equals(LocalTime.of(18, 0))) 
					                   && (localTime.isBefore(LocalTime.of(23, 0)) || localTime.equals(LocalTime.of(23, 0)))
					                    ? "'오늘 하루도 고생 많으셨습니다!'와 같은 저녁 인사를 추가해줘." 
					                    : " ")) + "\","
					+ " 전체 멘트를 한국어로 자연스럽게 작성해줘."
					+ " 예를 들어, '안녕하세요. 원더풀카페에 오신 것을 환영합니다. 제 오른쪽에는 다양한 디저트와 샌드위치가 위치해 있고, 왼쪽에는 갓 구운 맛있는 베이커리가 있습니다. "
					+ " 일행이 있으시군요? 위층에 단체석 공간이 있습니다! 오늘도 좋은 하루 되세요! 처럼 상황에 맞게 주어진 값들로 멘트를 작성해줘.'"
					+ " 숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해줘. 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아줘.";


			
		}else {
			
			 prompt = "You are a professional promotional script creator. Your task is to receive information such as the number of people, time, and menu details around the promotional robot, and create a promotional script based on these details."
				    + " The return format should be in JSON with key values in the order of store, time, people, greeting, rightMenu, leftMenu, frontMenu, seatingGuide, timeMent."
				    + " If any value corresponding to each position is null, skip that statement and do not generate it."
				    + " store: " + storeName + ", time: " + localTime + ", people: " + people + ","
				    + "\"greeting\": \"In the first sentence, create a greeting that includes the cafe name, " + storeName + ".\","
				    + "\"rightMenu\": \"" + ((dto.getRightSide() != null || dto.getRightFront() != null) ? "In the second sentence, promote the menu items located on the right side: " 
				    + (dto.getRightSide() != null ? dto.getRightSide() : " ") 
				    + (dto.getRightSide() != null && dto.getRightFront() != null ? " and " : " ") 
				    + (dto.getRightFront() != null ? dto.getRightFront() : " ") + "." : " ") + "\","
				    + "\"leftMenu\": \"" + ((dto.getLeftSide() != null || dto.getLeftFront() != null) ? "In the third sentence, promote the menu items located on the left side: " 
				    + (dto.getLeftSide() != null ? dto.getLeftSide() : " ") 
				    + (dto.getLeftSide() != null && dto.getLeftFront() != null ? " and " : " ") 
				    + (dto.getLeftFront() != null ? dto.getLeftFront() : " ") + "." : " ") + "\","
				    + "\"frontMenu\": \"" + (dto.getFront() != null ? "In the fourth sentence, promote the menu items located in front: " + dto.getFront() + "." : " ") + "\","
				    + "If any value corresponding to each position is null, skip that statement and do not generate it. "
				    + "\"seatingGuide\": \"" 
				    + (people >= 2 
				        ? "When there are multiple guests, include a seating guide such as 'You have a group! We recommend the group seating area on the upper floor.' to provide guidance for group seating." 
				        : ""
				    ) + "\","
				    + "\"timeMent\": \"" 
				    + ((localTime.isAfter(LocalTime.of(12, 0)) || localTime.equals(LocalTime.of(12, 0))) 
				           && (localTime.isBefore(LocalTime.of(13, 0)) || localTime.equals(LocalTime.of(13, 0))) 
				            ? "'It is currently a busy time. If you are looking for a seat, we recommend the upstairs area or the window seats on the first floor!' to address potential congestion during peak hours."
				            : (localTime.isBefore(LocalTime.of(10, 0)) || localTime.equals(LocalTime.of(10, 0)))
				                ? "'Have a wonderful day!' or a similar morning greeting to start the day positively." 
				                : ((localTime.isAfter(LocalTime.of(18, 0)) || localTime.equals(LocalTime.of(18, 0))) 
				                   && (localTime.isBefore(LocalTime.of(23, 0)) || localTime.equals(LocalTime.of(23, 0)))
				                    ? "'Hope you had a great day!' or a similar evening greeting to end the day on a positive note." 
				                    : " ")) + "\","
				    + " Write the entire script naturally in English."
				    + " For example, 'Hello, welcome to Wonder Cafe. On my right, we have a variety of desserts and sandwiches, and on my left, we have freshly baked delicious bread. "
				    + " You have a group? There is a group seating area available on the upper floor! Have a great day!' Create the script based on the given values to match the situation."
				    + " Use numerals for numbers and write everything else naturally in English. Do not include any emoticons or icons when generating the script.";

		}
		
		
		return prompt;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
