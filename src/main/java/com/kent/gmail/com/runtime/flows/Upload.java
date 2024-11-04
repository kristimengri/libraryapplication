package com.kent.gmail.com.runtime.flows;

import com.kent.gmail.com.runtime.Create;
import com.kent.gmail.com.runtime.request.CVSObjectCreate;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.MinioOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Upload {
  @Autowired private MinioOperator minioOperator;

  public CVSObjectCreate run(Create body, UserSecurityContext securityContext) {

    java.lang.String uploadUrl =
        minioOperator.getUrlForUpload(body.getBucketId(), body.getObjectId(), true);

    return new CVSObjectCreate()
        .setUploadDate(java.time.OffsetDateTime.now())
        .setObjectId(body.getObjectId())
        .setBucketId(body.getBucketId());
  }
}
