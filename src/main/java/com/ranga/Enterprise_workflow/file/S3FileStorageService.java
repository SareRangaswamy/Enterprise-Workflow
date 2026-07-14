package com.ranga.Enterprise_workflow.file;

import com.ranga.Enterprise_workflow.entity.Document;
import com.ranga.Enterprise_workflow.service.DocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class S3FileStorageService {

    private final S3Client s3Client;
    private final DocumentService documentService;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    public S3FileStorageService(S3Client s3Client,
                                DocumentService documentService) {
        this.s3Client = s3Client;
        this.documentService = documentService;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        // Generate unique file name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Upload to AWS S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromBytes(file.getBytes())
        );

        // Generate S3 URL
        String fileUrl = String.format(
                "https://%s.s3.%s.amazonaws.com/%s",
                bucketName,
                region,
                fileName
        );

        // Save metadata in MySQL
        Document document = new Document();
        document.setFileName(fileName);
        document.setFileUrl(fileUrl);
        document.setFileType(file.getContentType());
        document.setUploadedAt(LocalDateTime.now());

        documentService.saveDocument(document);

        return fileUrl;
    }
}