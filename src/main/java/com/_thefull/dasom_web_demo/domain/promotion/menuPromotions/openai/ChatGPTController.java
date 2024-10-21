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
//    	    prompt = String.format(
//    	        "이름이 %s인 카페에서 할인 홍보 멘트를 만들려고 합니다. 메뉴 이름은 %s이고, 할인 값은 %s입니다. "
//    	        + "할인 기간은 %s부터 %s까지 유효하며, 할인 시간은 %s부터 %s까지입니다. "
//    	        + "%s%s메뉴의 정가는 %s원이며, 정가에서 얼마나 할인되었는지 꼭 포함해서 설명해주세요. "
//    	        + "멘트에는 연도를 제외한 할인 기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 말하고 싶어요. "
//    	        + "그리고 나머지 정보를 참고해서 약 200자 정도의 홍보 멘트를 만들어주세요. 로봇이 말할 거예요. "
//    	        + "숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해주세요. "
//    	        + "Let's think step by step"
//    	        + "추가로 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아 주세요",
//    	        name,
//    	        dto.getMenu(),
//    	        dto.getDiscVal(), //얼마나 할인하는지
//    	        dto.getStartDate(),
//    	        dto.getEndDate(),
//    	        dto.getStartTime(),
//    	        dto.getEndTime(),
//    	        dto.getIsAddDiscCond() ? "홍보 멘트에 " + dto.getAddDiscCond() + "를 추가해서 자연스롭게 작성해 주세요" : "",
//    	        dto.getPrice()
//    	 );
//    	        
    		prompt = "이름이 "+name+"인 카페에서 "+ dto.getMenu()+"메뉴를 홍보하려 해. " + dto.getDiscVal()+"원 만큼 할인이 적용되고 할인기간은" 
    			   +  (dto.getIsAlways() ? "상시 할인이야" : dto.getStartDate()+" 부터 "+ dto.getEndDate()+"까지야 "
    			   		+ "멘트에는 연도를 제외한 할인 기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 작성해줘. 메뉴의 정가는")
    			   +   dto.getPrice() + "이고 정가에서 얼마나 할인되었는지 꼭 포함해서 설명해줘."
    			   +  (!dto.getAddDiscCond().isEmpty() ? "할인 멘트에" + dto.getAddDiscCond()+"를 추가해서 작성해줘" :  "")
    			   +  "길이는 약 200자 정도로 작성해줘"
    			   + "숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해줘. "
    			   + "Let's think step by step"
    			   + "추가로 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아줘 나중에 tts 로 발화할 거야";
    	        
    	   
    	} else { // 할인 조건이 없는 경우
    		
    		  prompt = String.format(
    			        "할인 없이 이름이 %s인 카페에서 %s 메뉴를 홍보하는 멘트를 만들려고 합니다. "
    			        + "%s의 특징을 강조하고, 자연스럽게 제품을 소개해주세요. 다음은 수박 주스에 관하여 홍보하는 예시입니다. "
    			        + "[예시: 돌아온 베스트셀러! 수박주스가 출시되었어요! 직접 공수한 수박을 갈아서 달달하고 시원한 맛을 자랑합니다. "
    			        + "꿉꿉하고 더운 날씨에 시원한 수박주스 어떠세요?] "
    			        + "나머지 정보를 참고해서 200자의 홍보 멘트를 만들어주세요. 메뉴와 어울릴 만한 멘트를 작성해주고 특정 계절,날씨는 언급하지 말아주세요."
    			        + "Let's think step by step"
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
		
		String prompt;
		
		if(lang.equals("ko")) {
			
			 prompt = "너는 한국의 홍보 멘트 생성 전문가야. 너가 해야 할 일은 인원, 시간 등에 관한 정보를 받고, 홍보 로봇 근처 메뉴에 대한 정보를 종합해서 홍보 멘트를 만들어야 해."
					+ " 반환 형태는 json 형식으로 반환하고 key 값들은 각각 store, time, people, promotionMent 순서대로 만들어줘."
					+ "store: " + storeName + ", time: " + localTime + ", people: " + people + ","
					+ "\"promotionMent\": \"첫 번째 순서로 카페 이름인 "+storeName+"을 넣은 인사말을 만들어 줘."
					+ ((dto.getRightSide() != null && dto.getRightFront() != null) ? 
						    "두 번째 순서로 오른쪽에 위치한 " +
						    (dto.getRightSide() != null ? dto.getRightSide() + " 와 " : "") +
						    (dto.getRightFront() != null ? dto.getRightFront() : "") + 
						    " 메뉴들을 홍보해줘. 홍보 메뉴는 최대 1개까지로 해줘." 
						    : "") 
					+ ((dto.getLeftSide() != null && dto.getLeftFront() != null) ? 
						    "세 번째 순서로 왼쪽에 위치한 " +
						    (dto.getLeftSide() != null ? dto.getLeftSide() + " 와 " : "") +
						    (dto.getLeftFront() != null ? dto.getLeftFront() : "") + 
						    " 메뉴들을 홍보해줘. 홍보 메뉴는 최대 1개까지로 해줘."
						    : "" )  
					+ ((dto.getFront() != null) ? 
						    "네 번째 순서로 앞쪽에 위치한 " + dto.getFront() + " 메뉴를 홍보해줘. 홍보 메뉴는 최대 1개까지로 해줘."
						    : "" )
				    + (people >= 2 
				        ? "여러 명의 손님이 있을 때는 '일행이 있으시군요! 위층에 단체석을 추천드립니다.'와 같이 사람이 많이 왔을 때 일반적으로 사용하는 좌석 안내를 추가해줘. " : ""
				    ) + ","
				    + ((localTime.isAfter(LocalTime.of(12, 0))) && (localTime.isBefore(LocalTime.of(13, 0))) 
					            ? "'지금은 사람이 많아 혼잡한 시간대입니다. 빈 자리를 찾고 싶으시면 위층이나 1층 창가 공간을 추천드립니다!'와 같이 피크 시간대에 발생할 수 있는 문제를 해결할 수 있는 문장을 작성해줘."
					            : (localTime.isBefore(LocalTime.of(10, 0)))
					                ? " '오늘도 즐거운 하루 되시길 바랍니다!'와 같은 아침 인사를 추가해줘." 
					                : ((localTime.isAfter(LocalTime.of(18, 0))) && (localTime.isBefore(LocalTime.of(23, 0)))
					                    ? "'오늘 하루도 고생 많으셨습니다!'와 같은 저녁 인사를 추가해줘." 
					                    : " ")) + "\","
					+ " 전체 멘트를 한국어로 자연스럽게 작성해줘."
					+ " 예를 들어, '안녕하세요. 원더풀카페에 오신 것을 환영합니다. 제 오른쪽에는 다양한 디저트와 샌드위치가 위치해 있고, 왼쪽에는 갓 구운 맛있는 베이커리가 있습니다. "
					+ " 일행이 있으시군요? 위층에 단체석 공간이 있습니다! 오늘도 좋은 하루 되세요! 처럼 상황에 맞게 주어진 값들로 멘트를 작성해줘.' "
					+ " Let's think step by step"
					+ " 숫자는 숫자로 작성하고, 나머지는 한글로 매우 자연스럽게 작성해줘. 멘트를 생성할 때 이모티콘이나 아이콘은 만들지 말아줘.";


			
		}else {
			
			// 영어 프롬프트 생성
			 prompt = "You are a promotion expert in the United States. Your task is to receive information about the number of people, current time, and other relevant details, as well as information about menus near the promotion robot, and create a promotional message."
		               + " The response should be in JSON format with the following keys in order: store, time, people, promotionMent."
		               + " store: " + storeName + ", time: " + localTime + ", people: " + people + ","
		               + "\"promotionMent\": \"Start with a greeting that includes the store name, " + storeName + ".\""
		               + ((dto.getRightSide() != null && dto.getRightFront() != null) ? 
		                       " Next, promote the menu items located on the right side, mentioning up to one item. " 
		                       : "") 
		               + ((dto.getLeftSide() != null && dto.getLeftFront() != null) ? 
		                       " Then, promote the menu items on the left side, mentioning up to one item. " 
		                       : "" )
		               + ((dto.getFront() != null) ? 
		                       " Additionally, promote the menu item located at the front, mentioning up to one item. " 
		                       : "" )
		               + (people >= 2 
		                   ? "Since there are multiple guests, add a seating recommendation such as 'It looks like you're with a group! We recommend our upstairs seating for larger parties.' " 
		                   : ""
		               ) + ","
		               + ((localTime.isAfter(LocalTime.of(12, 0)) && localTime.isBefore(LocalTime.of(13, 0))) 
		                       ? "'It's currently a busy time. If you're looking for a quieter spot, we recommend the upstairs or the window seating on the first floor!' Please include a sentence addressing peak hours."
		                       : (localTime.isBefore(LocalTime.of(10, 0))
		                           ? " 'Have a great morning!' Please include a morning greeting."
		                           : ((localTime.isAfter(LocalTime.of(18, 0)) && localTime.isBefore(LocalTime.of(23, 0)))
		                               ? "'Thank you for visiting today!' Please include an evening greeting."
		                               : " "))) + "\","
		               + " Please compose the entire message in natural English."
		               + " For example, 'Hello and welcome to Wonderful Cafe! To my right, you'll find a variety of desserts and sandwiches, and to my left, there's freshly baked bread from our bakery. It looks like you're with a group! We have a large table upstairs available. Have a great day!' Please create a message that fits the given conditions."
		               + " Let's think step by step."
		               + " Use numbers as digits and write the rest in English naturally. Do not include any emojis or icons.";

		}
		
		
		return prompt;
	}
    
    
}
