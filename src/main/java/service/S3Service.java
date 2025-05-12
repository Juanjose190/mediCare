package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class S3Service {

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private S3Client s3Client;

    public S3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsAccessKey, awsSecretKey)
                ))
                .build();
    }

    public String uploadFile(String filePath, String key) {
        File file = new File(filePath);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));

        return "File uploaded successfully to " + key;
    }

    public List<S3Object> listFiles() {
        ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket(bucketName)
                .build();

        ListObjectsResponse listObjectsResponse = s3Client.listObjects(listObjectsRequest);
        return listObjectsResponse.contents();
    }

    public String downloadFile(String key, String downloadPath) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        Path path = Paths.get(downloadPath);
        s3Client.getObject(getObjectRequest, path);

        return "File downloaded successfully to " + downloadPath;
    }

    public String deleteFile(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.deleteObject(deleteObjectRequest);

        return "File deleted successfully: " + key;
    }

    public void close() {
        s3Client.close();
    }
}
