package com._thefull.dasom_web_demo.domain.dasomLocation.controller;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.DasomLocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings/dasom-locations")
public class DasomLocationController {
    private final DasomLocationService dasomLocationService;
    
    
    
    /** 
     * 로그인한 사용자인지 체크
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @ModelAttribute
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, Model model,
    		@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang) throws IOException {
        HttpSession session = request.getSession(false);
        
        System.out.println("userId : " + session.getAttribute("userId"));
        
        if (session == null || session.getAttribute("userId") == null) {
        	
        	
        	if ("eng".equals(lang)) {
                // 영어 페이지 반환
        		 return "user/login_eng?message=login_required";
            } else {
                // 기본값으로 한국어 페이지 반환
            	return "user/login?message=login_required";
            }
        	
           
        } else {
            model.addAttribute("userId", session.getAttribute("userId"));
            return null;
        }
    }
    

    @GetMapping("/main")
    public String mainPage(Model model,
                           HttpServletRequest request,
                           @ModelAttribute(name = "message", binding = false) String message,
                           @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){

        HttpSession session = request.getSession(false);
     
        Long storeId = (Long) session.getAttribute("storeId");
        Long robotId = (Long) session.getAttribute("robotId");
        List<DasomLocationResponseDTO> allRobotLocationCategories = dasomLocationService.findAllRobotLocation(robotId, storeId);
        model.addAttribute("all_robot_location_category_list", allRobotLocationCategories);
        
        
        System.out.println("활성화된 위치 : " + allRobotLocationCategories.get(0).toString());
      
        
        if (message != null) {
            model.addAttribute("message", message);
        }
        
        if ("eng".equals(lang)) {
            // 영어 페이지 반환
        	 return "settings/dasomlocation_eng";
        } else {
            // 기본값으로 한국어 페이지 반환
        	 return "settings/dasomlocation";
        }
    }

    @GetMapping("/updatepage")
    public String loadUpdatePage(HttpServletRequest request,
                                 @RequestParam(name = "id") Long id,
                                 Model model){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        DasomLocationResponseDTO dto = dasomLocationService.findLocationDetails(id);
        model.addAttribute("theLocation", dto);

        return "settings/fragments/location_update";

    }

    @PostMapping("/register")
    public String createDasomLocation(@ModelAttribute DasomLocationRequestDTO requestDTO,
                                      BindingResult bindingResult,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttribute){

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");
        Long robotId = (Long) session.getAttribute("robotId");
        dasomLocationService.createDasomLocation(requestDTO,storeId,robotId);
        
        redirectAttribute.addFlashAttribute("message", "register");

        return "redirect:/settings/dasom-locations/main";
    }

    /**
     * 활성화한 로봇 위치 변경
     * @param use
     * @param id
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @GetMapping("/use")
    public String changeWhetherUse(@RequestParam(name = "use")Boolean use,
                                   @RequestParam(name = "id")Long id,
                                   HttpServletRequest request,
                                   RedirectAttributes redirectAttribute){

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long robotId = (Long) session.getAttribute("robotId");

        dasomLocationService.changeUse(robotId, use, id);
        
        redirectAttribute.addFlashAttribute("message", "location");

        return "redirect:/settings/dasom-locations/main";
    }

    /* 카페봇 위치 설정 전체 수정 PUT */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PostMapping("/update")
    public String updateLocation(@ModelAttribute DasomLocationRequestDTO dto,
                                 BindingResult bindingResult,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttribute){

        System.out.println("변경하려는 정보 : " + dto.toString());

        HttpSession session = request.getSession(false);
      
        Long storeId = (Long) session.getAttribute("storeId");

        dasomLocationService.updateLocation(dto, dto.getId(), storeId);
        
        redirectAttribute.addFlashAttribute("message", "update");

        return "redirect:/settings/dasom-locations/main";

    }


    /* 카페봇 위치 설정 삭제 */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @GetMapping("/delete")
    public String deleteDasomLocation(@RequestParam(name = "id")Long id,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttribute){

        System.out.println("DasomLocationController.deleteDasomLocation");

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long robotId = (Long) session.getAttribute("robotId");

        dasomLocationService.deleteDasomLocation(robotId,id);
        
        redirectAttribute.addFlashAttribute("message", "delete");

        return "redirect:/settings/dasom-locations/main";

    }

}