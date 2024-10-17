package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.controller;


import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.menu.service.MenuService;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionResponseDTO;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service.MenuPromotionService;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.domain.robot.repository.RobotRepository;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping("/api/promotion-discount")
@RequiredArgsConstructor
public class MenuPromotionController {

    private final MenuPromotionService menuPromotionService;
    private final RobotRepository robotRepository;
    private final StoreRepository storeRepository;
    private final MenuService menuService;
    
    
   
    /** 
     * 로그인한 사용자인지 체크
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @ModelAttribute
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        
        //System.out.println("userId : " + session.getAttribute("userId"));
        
        if (session == null || session.getAttribute("userId") == null) {
           return "redirect:/page/user/login?message=login_required";
        } else {
            model.addAttribute("userId", session.getAttribute("userId"));
            return null;
        }
    }

   
    /**
     * 프로모션 메인 페이지 : 할인 항목, 할인 완료 항목들 나열 
     * @param request
     * @param message
     * @param model
     * @return
     */
    @GetMapping("/main")
    public String mainPage(HttpServletRequest request,@ModelAttribute(name = "message", binding = false) String message,
                           Model model, @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang) {
        HttpSession session = request.getSession(false);
       
        Long storeId= (Long)session.getAttribute("storeId");
       // System.out.println("storeID : " + storeId);

        List<MenuPromotionResponseDTO> allPromotionList = menuPromotionService.findAllPromotionList(storeId);
        model.addAttribute("all_promotion_list",allPromotionList);
        
//        for(MenuPromotionResponseDTO dto : allPromotionList) {
//        	System.out.println("Promotion : " + dto.toString());
//        }
        

        List<Menu> menuList = menuService.findAllMenu(storeId);
        model.addAttribute("menu_list",menuList);

        List<MenuPromotionResponseDTO> completedPromotionList = menuPromotionService.findCompletedPromotionList(storeId);
        model.addAttribute("completed_promotion_list",completedPromotionList);
        
        if (message != null) {
            model.addAttribute("message", message);
        }
           
        if ("eng".equals(lang)) {
            // 영어 페이지 반환
            return "promotion/promotion_main_eng";
        } else {
            // 기본값으로 한국어 페이지 반환
            return "promotion/promotion_main";
        }
       
    }

    
    /**
     * 제품홍보 등록 메서드 : 입력 필드 값들을 requestDTO에 담아서 전달
     * @param requestDTO
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("/register")
    public String registerMenuPromotion(@ModelAttribute MenuPromotionRequestDTO requestDTO,
                                        BindingResult bindingResult,
                                        HttpServletRequest request,
                                        RedirectAttributes redirectAttribute,
                                        @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
        HttpSession session = request.getSession(false);
        
        if(requestDTO.getBoolIsAlways() == null) {
        	requestDTO.setBoolIsAlways(false);
        }
        
        
        //System.out.println("등록 메뉴정보 : " + requestDTO.toString());

        Long storeId = (Long)session.getAttribute("storeId");
        menuPromotionService.registerMenuPromotion(storeId, requestDTO);
        redirectAttribute.addFlashAttribute("message", "register");
        
        return "redirect:/api/promotion-discount/main?lang=" + lang;
    }

    /**
     * 업데이트 페이지로 이동, 수정하려는 메뉴의 정보를 조회해서 가져옴
     * @param request
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/updatepage")
    public String loadUpdatePage(HttpServletRequest request,
            @RequestParam(name = "id") Long id, Model model,
            @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
        HttpSession session = request.getSession(false);
      
        Long storeId = (Long)session.getAttribute("storeId");
        List<Menu> menuList = menuService.findAllMenu(storeId);
        model.addAttribute("menu_list",menuList);

        MenuPromotionResponseDTO dto = menuPromotionService.findOneMenuPromotion(id);
        
        //System.out.println("해당 메뉴정보 : " + dto.toString());
        model.addAttribute("thepromo",dto);
        
        if ("eng".equals(lang)) {
            // 영어 페이지 반환
        	return "promotion/fragments/contentupdate_eng";
        } else {
            // 기본값으로 한국어 페이지 반환
        	return "promotion/fragments/contentupdate";
        }
        
    }

    
    /**
     * 홍보메뉴 업데이트: 업데이트내용 requestDTO 에 담음
     * @param requestDTO
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PostMapping("/update")
    public String updatePromotionContent(@ModelAttribute MenuPromotionRequestDTO requestDTO,
                                         BindingResult bindingResult,
                                         HttpServletRequest request,
                                         RedirectAttributes redirectAttribute,
                                         @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
      
        if(requestDTO.getBoolIsAlways() == null) {
        	requestDTO.setBoolIsAlways(false);
        }
        
       // System.out.println("수정하려는 메뉴 정보 : " + requestDTO.toString());
        
        menuPromotionService.updatePromotionContent(requestDTO);
        
        redirectAttribute.addFlashAttribute("message", "update");
        
        return "redirect:/api/promotion-discount/main?lang=" + lang;
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PatchMapping("/status")
    public String changeMenuPromotionStatus(@RequestParam(name = "id") Long id,
                                            @RequestParam(name = "status")String status,
                                            HttpServletRequest request){
       
        menuPromotionService.changeMenuPromotionStatus(id, status);

        return "redirect:/api/promotion-discount/main";

    }

   
    /**
     * 메뉴 id 받아와서 삭제
     * @param id
     * @param request
     * @param redirectAttribute
     * @return
     */
    @GetMapping("/delete")
    public String deleteMenuPromotion(@RequestParam(name = "id")Long id,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttribute,
                                      @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
    
        menuPromotionService.deleteMenuPromotion(id);
        
        redirectAttribute.addFlashAttribute("message", "delete");
        
        return "redirect:/api/promotion-discount/main?lang=" + lang;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class testDto{
        private String testText;
    }

//    @PostMapping("/test")
//    public String testpage(@ModelAttribute testDto dto,
//                            BindingResult bindingResult,
//            /*@RequestParam(value = "testText") String testText,*/
//                           HttpSession session){
//
//        System.out.println("JSPmainController.testpage");
//        
//        Long storeId = (Long)session.getAttribute("storeId");
//
//        Store store = storeRepository.findById(storeId)
//                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));
//
//        List<Robot> robotList = robotRepository.findByStore(store);
//        Robot robot = robotList.get(0);
//        System.out.println(robot.getId());
//
//
//        return "promotion/test";
//    }
//    

    
}
