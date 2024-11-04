package com.kent.gmail.com.runtime.service;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MinioOperator {

  @Autowired private MinioClient minioClient;

  public String getUrlForUpload(String bucketKey, String objectKey, boolean createBucket) {
    try {
      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketKey).build());
      if (!found) {
        if (!createBucket) {
          throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "bucket " + bucketKey + " does not exist");
        }
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketKey).build());
      }
      return minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .bucket(bucketKey)
              .object(objectKey)
              .method(Method.PUT)
              .expiry(3, TimeUnit.MINUTES)
              .build());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public String getUrlForDownload(String bucketKey, String objectKey) {
    try {
      return minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .bucket(bucketKey)
              .object(objectKey)
              .method(Method.GET)
              .expiry(3, TimeUnit.MINUTES)
              .build());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
