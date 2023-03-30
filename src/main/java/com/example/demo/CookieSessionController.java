package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CookieSessionController {

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookie_name", "cookie_value");
        response.addCookie(cookie);
        return "cookie_set_successfully";
    }

    @GetMapping("/get-cookie")
    public String getCookie(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie_name")) {
                    model.addAttribute("cookie_value", cookie.getValue());
                    break;
                }
            }
        }
        return "cookie_value";
    }

    @GetMapping("/set-session")
    public String setSession(HttpSession session, @RequestParam("session_value") String sessionValue) {
        session.setAttribute("session_name", sessionValue);
        return "session_set_successfully";
    }

    @GetMapping("/get-session")
    public String getSession(HttpSession session, Model model) {
        String sessionValue = (String) session.getAttribute("session_name");
        model.addAttribute("session_value", sessionValue);
        return "session_value";
    }
}