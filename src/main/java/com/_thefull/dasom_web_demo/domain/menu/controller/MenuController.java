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

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/all")
    public String getAllMenuList(HttpServletRequest request,
                                 Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/page/user/login";
        }

        Long storeId= (Long)session.getAttribute("storeId");

        List<Menu> allMenu = menuService.findAllMenu(storeId);

        model.addAttribute("menu_list",allMenu);

        return "promotion/fragments/menuModal";

    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> getSearchedMenuList(HttpServletRequest request,
                                              @RequestParam(name = "search")String search) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            // return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");

        List<SimpleMenuResponseDTO> searchedMenu = menuService.findSearchedMenu(storeId, search);

        return ResponseEntity.ok(Collections.singletonMap("menu_list", searchedMenu));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<DetailedMenuResponseDTO> getMenuDetails(HttpServletRequest request,
                                                                  @RequestParam(name = "id")Long id){

        HttpSession session = request.getSession(false);
        if (session == null) {
            // return "redirect:/page/user/login";
        }

        Long storeId = (Long) session.getAttribute("storeId");

        DetailedMenuResponseDTO oneMenuDetails = menuService.findOneMenuDetails(storeId, id);

        return ResponseEntity.ok().body(oneMenuDetails);

    }

}
