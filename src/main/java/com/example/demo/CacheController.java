package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {




    private byte[] getBytes() {
        Path imagePath = Paths.get("/Users/ywz/http2/image2.jpg");
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }


    @GetMapping("/cache")
    public ResponseEntity<byte[]> getExampleResource() throws IOException {
        byte[] imageBytes = getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic().getHeaderValue());
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/return304")
    public ResponseEntity<byte[]> return304(@RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch,
            @RequestHeader(value = "If-Modified-Since", required = false) String ifModifiedSince) throws IOException {
        byte[] resource = getBytes();
        HttpHeaders headers = new HttpHeaders();
        // 设置ETag和Last-Modified属性
//        String etag = calculateETag(resource);
        String etag = "W/\"2a3b4c5d6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z\"";
        headers.setETag(etag);
        System.out.println("ifNoneMatch = " + ifNoneMatch);

//        headers.setLastModified(LocalDate.now().toString());
        // 如果客户端已经缓存了资源，并且资源没有变化，则返回304 Not Modified响应
//        if (ifNoneMatch != null && ifNoneMatch.equals(etag)
////                || ifModifiedSince != null && getLastModifiedTime().toEpochMilli() <= Date.parse(ifModifiedSince)
//        ) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).headers(headers).build();
//        }
        // 否则返回完整的资源
//        return ResponseEntity.ok().headers(headers).body(resource);
    }



}
