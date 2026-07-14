package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.file.S3FileStorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final S3FileStorageService s3FileStorageService;

    public FileController(S3FileStorageService s3FileStorageService) {
        this.s3FileStorageService = s3FileStorageService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = s3FileStorageService.uploadFile(file);

        return ResponseEntity.ok(
                "File uploaded successfully to AWS S3: " + fileName
        );
    }
}