package com.example.demo.login;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebParam.Mode;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login2Controller {

    @RequestMapping("/home")
    public String home(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // Check if the user is logged in

        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        String username = cookieMap.get("user");

        if ( username== null) {
            // Redirect to the login page
            response.sendRedirect("/login2.html");
            return "redirect:/login2";
        } else {
            // Perform the required operation
            return "Hello " + username;
        }
    }




    @PostMapping("/login2")
    public void login(@RequestParam String username, @RequestParam String password,
            HttpServletResponse response) throws IOException {
        // Check username and password
        if (username.equals("admin") && password.equals("password")) {
            // Login successful, set cookie
            Cookie cookie = new Cookie("user", username);
            cookie.setMaxAge(20); // 1 hour
            cookie.setPath("/home");
            response.addCookie(cookie);
            // Redirect to original program with 302 status code
            response.sendRedirect("/home");
        } else {
            // Login failed, show error message
            response.getWriter().println("Invalid username or password");
        }
    }

}