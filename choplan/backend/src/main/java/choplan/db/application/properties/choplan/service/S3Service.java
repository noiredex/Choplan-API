package choplan.db.application.properties.choplan.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName = "your-bucket-name"; // 수정

    public S3Service() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                System.getenv("AWS_ACCESS_KEY_ID"), // 수정
                System.getenv("AWS_SECRET_ACCESS_KEY") // 수정
        );

        this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_2) // 서울 리전
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

        // URL 반환
        return "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + fileName; // 수정
    }
}
