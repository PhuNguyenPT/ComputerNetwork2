package com.example.jpa.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileService(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    public String saveFile(MultipartFile multipartFile) {
        var file = fileMapper.toFile(multipartFile);
        var savedFile = fileRepository.save(file);
        return String.format("File %s upload successfully.", savedFile.getName());
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
