package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.DasomLocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/user")
public class UserRegisterPageController {
    private final DasomLocationService dasomLocationService;

    @GetMapping("/register")
    public String getRegisterPage(){
        System.out.println("UserPageController.getRegisterPage");
        return "/user/register";
    }

    @GetMapping("/registersuccess")
    public String getRegisterSuccessPage(){
        return "/user/registersuccess";
    }


    @GetMapping("/index")
    public String indexpage(){
        return "../static/index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/user/login";
    }

    @GetMapping("/promotion")
    public String productPage(){
        return "/promotion/main";
    }

    @GetMapping("/resetpassword")
    public String resetpasswordPage(){
        return "/user/resetpassword";
    }

//    @GetMapping("/dasomlocation")
//    public String dasomlocation(Model model,
//                                HttpServletRequest request){
//        HttpSession session = request.getSession(false);
//
//        System.out.println("여기로 로딩되면 안됨");
//
//        if (session==null){
//            return "redirect:/page/user/login";
//        }
//
//        Long storeId = (Long) session.getAttribute("storeId");
//        List<DasomLocation> allRobotLocationCategories = dasomLocationService.findAllRobotLocationCategories(storeId);
//        model.addAttribute("all_robot_location_category_list", allRobotLocationCategories);
//
//
//        return "settings/dasomlocation";
//
//    }

}
