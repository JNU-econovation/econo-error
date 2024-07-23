package com.example.demo.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    public List<Map<String, Object>> getFileList(String directory) {
        List<Map<String, Object>> fileList = new ArrayList<>();

        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request()
            .withBucketName(bucketName)
            .withPrefix(directory + "/");

        ListObjectsV2Result result = amazonS3Client.listObjectsV2(listObjectsV2Request);
        List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();

        int id = 1;
        for (S3ObjectSummary objectSummary : objectSummaries) {
            String key = objectSummary.getKey();
            if (!key.equals(directory + "/")) {
                Map<String, Object> fileInfo = new HashMap<>();
                fileInfo.put("id", id++);
                fileInfo.put("url", "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key);
                fileList.add(fileInfo);
            }
        }

        Collections.shuffle(fileList);
        return fileList.size() > 16 ? fileList.subList(0, 16) : fileList;
    }
}
