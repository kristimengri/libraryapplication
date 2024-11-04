package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.CVSObject;
import com.kent.gmail.com.runtime.request.CVSObjectCreate;
import com.kent.gmail.com.runtime.request.CVSObjectFilter;
import com.kent.gmail.com.runtime.request.CVSObjectUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.CVSObjectService;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("CVSObject")
@Tag(name = "CVSObject")
public class CVSObjectController {

  @Autowired private CVSObjectService cVSObjectService;

  @DeleteMapping("{id}")
  @Operation(summary = "deleteCVSObject", description = "Deletes CVSObject")
  public CVSObject deleteCVSObject(@PathVariable("id") String id, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return cVSObjectService.deleteCVSObject(id, securityContext);
  }

  @PostMapping("getAllCVSObjects")
  @Operation(summary = "getAllCVSObjects", description = "lists CVSObjects")
  public PaginationResponse<CVSObject> getAllCVSObjects(
      @Valid @RequestBody CVSObjectFilter cVSObjectFilter, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return cVSObjectService.getAllCVSObjects(cVSObjectFilter, securityContext);
  }

  @PutMapping("updateCVSObject")
  @Operation(summary = "updateCVSObject", description = "Updates CVSObject")
  public CVSObject updateCVSObject(
      @Validated(Update.class) @RequestBody CVSObjectUpdate cVSObjectUpdate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return cVSObjectService.updateCVSObject(cVSObjectUpdate, securityContext);
  }

  @PostMapping("createCVSObject")
  @Operation(summary = "createCVSObject", description = "Creates CVSObject")
  public CVSObject createCVSObject(
      @Validated(Create.class) @RequestBody CVSObjectCreate cVSObjectCreate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return cVSObjectService.createCVSObject(cVSObjectCreate, securityContext);
  }
}
