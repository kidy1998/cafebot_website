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
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/promotion-discount")
@RequiredArgsConstructor
public class MenuPromotionController {

    private final MenuPromotionService menuPromotionService;
    private final RobotRepository robotRepository;
    private final StoreRepository storeRepository;
    private final MenuService menuService;

    @GetMapping("/main")
    public String mainPage(HttpServletRequest request,
                           Model model) {
        HttpSession session = request.getSession(false);
        
        // 세션이 없으면 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("userId") == null) {
        	
        	return "redirect:/page/user/login?message=login_required";
        }
        
        Long storeId= (Long)session.getAttribute("storeId");

        List<MenuPromotionResponseDTO> allPromotionList = menuPromotionService.findAllPromotionList(storeId);
        model.addAttribute("all_promotion_list",allPromotionList);

        List<Menu> menuList = menuService.findAllMenu(storeId);
        model.addAttribute("menu_list",menuList);

        List<MenuPromotionResponseDTO> completedPromotionList = menuPromotionService.findCompletedPromotionList(storeId);
        model.addAttribute("completed_promotion_list",completedPromotionList);

        return "promotion/promotion_main";
    }

    @PostMapping("/register")
    public String registerMenuPromotion(@ModelAttribute MenuPromotionRequestDTO requestDTO,
                                        BindingResult bindingResult,
                                        HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }
        
        
        if(requestDTO.getBoolIsAlways() == null) {
        	requestDTO.setBoolIsAlways(false);
        }
        
        System.out.println(requestDTO.toString());

        Long storeId = (Long)session.getAttribute("storeId");
        menuPromotionService.registerMenuPromotion(storeId, requestDTO);

        return "redirect:/api/promotion-discount/main";
    }

    @GetMapping("/updatepage")
    public String loadUpdatePage(HttpServletRequest request,
            @RequestParam(name = "id") Long id, Model model){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        Long storeId = (Long)session.getAttribute("storeId");
        List<Menu> menuList = menuService.findAllMenu(storeId);
        model.addAttribute("menu_list",menuList);

        MenuPromotionResponseDTO dto = menuPromotionService.findOneMenuPromotion(id);
        model.addAttribute("thepromo",dto);

        return "promotion/fragments/contentupdate";
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PostMapping("/update")
    public String updatePromotionContent(@ModelAttribute MenuPromotionRequestDTO requestDTO,
                                         BindingResult bindingResult,
                                         HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        menuPromotionService.updatePromotionContent(requestDTO);

        return "redirect:/api/promotion-discount/main";
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PatchMapping("/status")
    public String changeMenuPromotionStatus(@RequestParam(name = "id") Long id,
                                            @RequestParam(name = "status")String status,
                                            HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        menuPromotionService.changeMenuPromotionStatus(id, status);

        return "redirect:/api/promotion-discount/main";

    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @DeleteMapping("/delete")
    public String deleteMenuPromotion(@RequestParam(name = "id")Long id,
                                      HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        menuPromotionService.deleteMenuPromotion(id);

        return "redirect:/api/promotion-discount/main";

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class testDto{
        private String testText;
    }

    @PostMapping("/test")
    public String testpage(@ModelAttribute testDto dto,
                            BindingResult bindingResult,
            /*@RequestParam(value = "testText") String testText,*/
                           HttpSession session){

        System.out.println("JSPmainController.testpage");
        User user = (User)session.getAttribute("userId");
        Long storeId = (Long)session.getAttribute("storeId");

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));

        List<Robot> robotList = robotRepository.findByStore(store);
        Robot robot = robotList.get(0);
        System.out.println(robot.getId());


        return "promotion/test";
    }

}
