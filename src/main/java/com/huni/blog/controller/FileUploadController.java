package com.huni.blog.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

  private final String uploadDir = Paths.get("C:", "tui-editor", "upload").toString();

  @PostMapping("/image-upload")
  public String uploadEditorImage(@RequestParam("image") final MultipartFile image) {
    if (image.isEmpty()) {
      return "";
    }

    String orgFilename = image.getOriginalFilename();
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);
    String saveFilename = uuid + "." + extension;
    String fileFullPath = Paths.get(uploadDir, saveFilename).toString();

    File dir = new File(uploadDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    try {
      // 파일 저장 (write to disk)
      File uploadFile = new File(fileFullPath);
      image.transferTo(uploadFile);
      return saveFilename;

    } catch (IOException e) {
      // 예외 처리는 따로 해주는 게 좋습니다.
      throw new RuntimeException(e);
    }
  }

  @GetMapping(value = "/image-print", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
  public byte[] printEditorImage(@RequestParam("filename") final String filename) {
    String fileFullPath = Paths.get(uploadDir, filename).toString();

    File uploadedFile = new File(fileFullPath);
    if (!uploadedFile.exists()) {
      throw new RuntimeException();
    }

    try {
      return Files.readAllBytes(uploadedFile.toPath());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}