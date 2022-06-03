package com.service.download.controller;

import com.service.download.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/service")
public class StorageController
{
    @Autowired
    private StorageService service;
    //########################  TEST  ######################

    @RequestMapping("/test2/test")
    public String test() {return "Hi There, this is service 2"; }
    //########################  TEST  ######################

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length).header("content-type", "application/octet-stream")
                .header("Content-disposition", "attachment;filename=\"" + fileName + "\"").body(resource);

    }


}

