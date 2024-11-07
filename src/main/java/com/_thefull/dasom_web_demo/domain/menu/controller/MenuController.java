package com._thefull.dasom_web_demo.domain.menu.controller;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.menu.domain.dto.DetailedMenuResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.domain.dto.SimpleMenuResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * 메뉴 등록에서 MODAL 부분
 */
@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    long storeNum = 1;  //만약 storeId 에 해당하는 메뉴가 없을 경우에 기본 세팅인 Wonderfull 로 나오게 

    @GetMapping("/all")
    public String getAllMenuList(HttpServletRequest request,
                                 Model model,
                                 @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/page/user/login";
        }

        Long storeId= (Long)session.getAttribute("storeId");

        List<Menu> allMenu = menuService.findAllMenu(storeId);
        
        // 메뉴가 없을 경우
        if(allMenu.isEmpty()) {
        	allMenu = menuService.findAllMenu(storeNum);
        }else {
        	storeNum = storeId;
        }

        model.addAttribute("menu_list",allMenu);

        if ("eng".equals(lang)) {
        	return "promotion/fragments/menuModal_eng";
        } else {
        	return "promotion/fragments/menuModal";
        }
        

    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> getSearchedMenuList(HttpServletRequest request,
                                              @RequestParam(name = "search")String search) {
//        HttpSession session = request.getSession(false);
//        Long storeId = (Long) session.getAttribute("storeId");

        List<SimpleMenuResponseDTO> searchedMenu = menuService.findSearchedMenu(storeNum, search);

        return ResponseEntity.ok(Collections.singletonMap("menu_list", searchedMenu));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<DetailedMenuResponseDTO> getMenuDetails(HttpServletRequest request,
                                                                  @RequestParam(name = "id")Long id){

//        HttpSession session = request.getSession(false);
//
//        Long storeId = (Long) session.getAttribute("storeId");

        DetailedMenuResponseDTO oneMenuDetails = menuService.findOneMenuDetails(storeNum, id);

        return ResponseEntity.ok().body(oneMenuDetails);

    }

}
