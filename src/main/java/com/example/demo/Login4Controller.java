package com.example.demo;

import com.example.demo.SessionManager.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login4Controller {

    @RequestMapping("/redisSession")
    public String home(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        String mysession = cookieMap.get("redisSession");


        if ( mysession== null) {
            response.sendRedirect("/login4.html");
            return "redirect:/login4";
        } else {
            String username = sessionManager.getSession(mysession).getUsername();
            System.out.println("username = " + username);
            return "Hello " + username;
        }
    }

    RedisSessionManager sessionManager=new RedisSessionManager();



    @PostMapping("/login4")
    public void login(@RequestParam String username, @RequestParam String password,
            HttpServletResponse response) throws IOException {
        if (username.equals("admin") && password.equals("password")) {
            RedisSessionManager.Session session = sessionManager.createSession(username, 3600);
            Cookie cookie = new Cookie("redisSession", session.getSessionId());
            cookie.setMaxAge(3600); // 1 hour
            response.addCookie(cookie);
            response.sendRedirect("/redisSession");
        } else {
            response.getWriter().println("Invalid username or password");
        }
    }

}