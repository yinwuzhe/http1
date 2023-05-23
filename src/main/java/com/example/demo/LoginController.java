package com.example.demo;

import java.net.URI;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/")
    public String home(HttpSession session) {
        // Check if the user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // Redirect to the login page
            return "redirect:/login";
        } else {
            // Perform the required operation
            return "Hello " + username;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String username, @RequestParam String password) {
        // Authenticate user credentials here
        boolean authenticated = authenticate(username, password);
        if (authenticated) {
            System.out.println("authenticated = " + authenticated);
            // Set the user information in the session
            setUserInSession(username);
            // Redirect to the original application with 302 status code
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    private boolean authenticate(String username, String password) {
        // Authenticate user credentials here
        return true;
    }
    private void setUserInSession(String username) {
        // Set the user information in the session
    }
}