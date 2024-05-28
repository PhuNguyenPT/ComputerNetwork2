package com.example.jpa.file;

import com.example.jpa.exception.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final ExceptionHandler exceptionHandler;
    public FileService(FileRepository fileRepository, FileMapper fileMapper, ExceptionHandler exceptionHandler) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.exceptionHandler = exceptionHandler;
    }

    public ResponseEntity<?> saveFile(MultipartFile multipartFile) {
        var file = fileMapper.toFile(multipartFile);
        try {
            fileRepository.save(file);
        } catch (DataIntegrityViolationException e) {
            return exceptionHandler.handleDataIntegrityException(e);
        }
        String message = "Upload " + multipartFile.getOriginalFilename() + " successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    public FileResponseDTO findFile(String fileName) {
        return fileRepository.findByName(fileName)
                .map(fileMapper::toFileResponseDTO)
                .orElse(null);
    }

    public Long deleteFile(String fileName) {
        return fileRepository.deleteByName(fileName);
    }
}
