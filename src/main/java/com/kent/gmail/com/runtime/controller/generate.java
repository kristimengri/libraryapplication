package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.Create;
import com.kent.gmail.com.runtime.flows.Upload;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generate")
@Tag(name = "generate")
public class generate {

  @Autowired private Upload upload;

  @PostMapping("/generateUploadUrl")
  @Operation(summary = "generate", description = "generate")
  public void generate(@RequestBody Create create, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    upload.run(create, securityContext);
  }
}
