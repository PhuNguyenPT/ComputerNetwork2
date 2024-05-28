package com.example.jpa.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file")MultipartFile multipartFile
            ) {
        return fileService.saveFile(multipartFile);
    }

    @GetMapping("/search/{file-name}")
    public ResponseEntity<?> findFileList(
            @PathVariable("file-name") String fileName) {
        var dto = fileService.findFile(fileName);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{file-name}")
    public ResponseEntity<?> deleteFile(
            @PathVariable("file-name") String fileName) {
        Long num = fileService.deleteFile(fileName);
        if (num > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Delete " + num + " " + fileName);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
