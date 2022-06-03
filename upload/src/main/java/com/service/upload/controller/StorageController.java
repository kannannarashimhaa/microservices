package com.service.upload.controller;


import com.service.upload.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service")
public class StorageController
{
    @Autowired
    private StorageService service;
    //########################  TEST  ######################

    @RequestMapping("/test1/test")
    public String test() {return "Hi There, this is service 1"; }
    //########################  TEST  ######################
    @PostMapping("/upload")

    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {

        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);

    }



}

