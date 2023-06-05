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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {

    //上传下载图片

    @GetMapping(value = "/getImg",produces = {"image/jpg"})
    public byte[] getImg() {
        byte[] imageBytes = getBytes();
        return imageBytes;
    }

    @GetMapping(value = "/chunkDownload")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        // 从文件系统或其他来源获取InputStream
        InputStream inputStream = new FileInputStream("/Users/ywz/Documents/XIAOYUANGUI/video/generic.mp4");
        // 创建一个InputStreamResource并将其传递给ResponseEntity
        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(resource);
    }

    @GetMapping(value = "/gzipDownload", produces = {"image/jpg"})
    public ResponseEntity<byte[]> getData() throws IOException {
        // 获取原始数据，例如从数据库或其他来源
        byte[] imageBytes = getBytes();
        System.out.println("image.length = " + imageBytes.length);

        // 使用gzip压缩数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(imageBytes);
        }
        byte[] compressedData = baos.toByteArray();
        System.out.println("compressedData.length = " + compressedData.length);

        // 返回使用gzip压缩的响应
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Encoding", "gzip");
        headers.setContentType(MediaType.IMAGE_JPEG);
//        headers.setContentLength(compressedData.length);

        return new ResponseEntity<>(compressedData, headers, HttpStatus.OK);
    }

    private byte[] getBytes() {
        Path imagePath = Paths.get("/Users/ywz/http1/resourceleak.png");
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }



}
