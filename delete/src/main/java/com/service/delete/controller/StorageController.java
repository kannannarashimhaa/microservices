package com.service.delete.controller;



import com.service.delete.services.StorageService;
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

    @RequestMapping("/test3/test")
    public String test() {return "Hi There, this is service 3"; }

    //########################  TEST  ######################
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {

        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);

    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length).header("content-type", "application/octet-stream")
                .header("Content-disposition", "attachment;filename=\"" + fileName + "\"").body(resource);

    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(service.deletefile(fileName),HttpStatus.OK);
    }

}
