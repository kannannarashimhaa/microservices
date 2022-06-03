package com.service.upload.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketname;

    @Autowired
    private AmazonS3 linodeclient;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMPF_F(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        linodeclient.putObject((new PutObjectRequest(bucketname, fileName, fileObj)));
        fileObj.delete();
        return "File uploaded" + fileName;
    }

    public byte[] downloadFile(String filename) {
        S3Object linodeobj = linodeclient.getObject(bucketname, filename);
        S3ObjectInputStream inputstream = linodeobj.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputstream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deletefile(String fileName) {
        linodeclient.deleteObject(bucketname, fileName);
        return fileName + "  removed ***";

    }


    private File convertMPF_F(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("error", e);
        }
        return convertedFile;

    }
}
