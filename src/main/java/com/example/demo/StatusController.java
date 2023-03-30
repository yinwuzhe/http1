package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/return302")
    public ResponseEntity return302(){
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "重定向的URL").build();
    }

    @PostMapping("/return400")
    public ResponseEntity<String> return400() {
        return ResponseEntity.badRequest().body("Name is required");
    }



    @GetMapping("/return500")
    public ResponseEntity<byte[]> return500() throws IOException {
       int a=2,b=0;
        int i = a / b;
        return null;
    }



}
