package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private String name="hello,buddy!";
    @RequestMapping(value = "/",method = {RequestMethod.HEAD,RequestMethod.OPTIONS})
    public void hello() {
    }
    @GetMapping("/getName")
    public String getName() {
        System.out.println("name = " + name);
       return name;
    }
    @RequestMapping(value = "/modifyName",method = {RequestMethod.POST,RequestMethod.PUT})
    public Boolean modifyName(@RequestBody  String name) {
        System.out.println("name = " + name);
        this.name=name;
        return true;
    }
    @DeleteMapping("/delName")
    public Boolean delName() {
       this.name="";
        return true;
    }

}
