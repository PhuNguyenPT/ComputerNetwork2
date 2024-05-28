package com.example.jpa.file;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file")MultipartFile multipartFile
            ) {
        var uploadFile = fileService.saveFile(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
    }

    @GetMapping("/search/{file-name}")
    public ResponseEntity<?> findFileList(
            @PathVariable("file-name") String fileName) {
        var dto = fileService.findFile(fileName);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/files/{file-name}")
    public ResponseEntity<?> deleteFile(
            @PathVariable("file-name") String fileName) {
        Long num = fileService.deleteFile(fileName);
        if (num > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new String("Delete " + num + " " + fileName));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
