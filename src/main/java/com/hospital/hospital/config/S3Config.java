package com.hospital.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class S3Config {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretKey);

  
        S3Configuration s3Configuration = S3Configuration.builder()
                .checksumValidationEnabled(false)
                .pathStyleAccessEnabled(true) 
                .build();

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .serviceConfiguration(s3Configuration)
                .build();
    }
}