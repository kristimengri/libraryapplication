package com.kent.gmail.com.runtime;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Minio {

  @Value("${minio.url:http://minio:9000}")
  private String endpoint;

  @Value("${minio.rootUsername:admin}")
  private String rootUsername;

  @Value("${minio.rootPassword:admin}")
  private String rootPassword;

  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder().endpoint(endpoint).credentials(rootUsername, rootPassword).build();
  }
}
