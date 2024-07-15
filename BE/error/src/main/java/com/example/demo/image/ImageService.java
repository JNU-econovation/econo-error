package com.example.demo.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public List<String> getFileList(String directory) {
        List<String> fileList = new ArrayList<>();

        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request()
            .withBucketName(bucketName)
            .withPrefix(directory+"/");

        ListObjectsV2Result result = amazonS3Client.listObjectsV2(listObjectsV2Request);
        List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();

        for (S3ObjectSummary objectSummary : objectSummaries) {
            String key = objectSummary.getKey();
            if (!key.equals(directory + "/")) {
                fileList.add("https://"+bucketName+".s3."+region+".amazonaws.com/" + key);
            }
        }

        Collections.shuffle(fileList);
        return fileList.size() > 16 ? fileList.subList(0, 16) : fileList;
    }
}
