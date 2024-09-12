package com._thefull.dasom_web_demo.domain.dasomLocation.controller;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.DasomLocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings/dasom-locations")
public class DasomLocationController {
    private final DasomLocationService dasomLocationService;

    @GetMapping("/main")
    public String mainPage(Model model,
                           HttpServletRequest request){

        System.out.println("DasomLocationController.mainPage");
        HttpSession session = request.getSession(false);
        if (session==null){
            return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");
        Long robotId = (Long) session.getAttribute("robotId");
        List<DasomLocationResponseDTO> allRobotLocationCategories = dasomLocationService.findAllRobotLocation(robotId, storeId);
        model.addAttribute("all_robot_location_category_list", allRobotLocationCategories);

        return "settings/dasomlocation";
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

    @PostMapping
    public String createDasomLocation(@ModelAttribute DasomLocationRequestDTO requestDTO,
                                      BindingResult bindingResult,
                                      HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");
        Long robotId = (Long) session.getAttribute("robotId");
        dasomLocationService.createDasomLocation(requestDTO,storeId,robotId);

        return "redirect:/settings/dasom-locations/main";
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PatchMapping("/use")
    public String changeWhetherUse(@RequestParam(name = "use")Boolean use,
                                   @RequestParam(name = "id")Long id,
                                   HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long robotId = (Long) session.getAttribute("robotId");

        dasomLocationService.changeUse(robotId, use, id);

        return "redirect:/settings/dasom-locations/main";
    }

    /* 카페봇 위치 설정 전체 수정 PUT */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @PostMapping("/update")
    public String updateLocation(@ModelAttribute DasomLocationRequestDTO dto,
                                 BindingResult bindingResult,
                                 HttpServletRequest request){

        System.out.println("DasomLocationController.updateLocation");

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");

        dasomLocationService.updateLocation(dto, dto.getId(), storeId);

        return "redirect:/settings/dasom-locations/main";

    }


    /* 카페봇 위치 설정 삭제 */
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @DeleteMapping
    public String deleteDasomLocation(@RequestParam(name = "id")Long id,
                                      HttpServletRequest request){

        System.out.println("DasomLocationController.deleteDasomLocation");

        HttpSession session = request.getSession(false);
        if(session==null){
            return "redirect:/page/user/login";
        }

        Long robotId = (Long) session.getAttribute("robotId");

        dasomLocationService.deleteDasomLocation(robotId,id);

        return "redirect:/settings/dasom-locations/main";

    }

}