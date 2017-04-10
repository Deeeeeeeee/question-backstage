package com.seal_de.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class FileUploadController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "fileupload", method= RequestMethod.POST)
    public String processUpload(
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        String dirPath = createFileDir();
        String oldFilename = file.getOriginalFilename();
        String newFilename = "/" + createFilename() +
                oldFilename.substring(oldFilename.lastIndexOf('.'));
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        file.transferTo(new File(dir.getAbsolutePath() + newFilename));
        return dirPath + newFilename;
    }

    private String createFileDir(){
        String dirPath = request.getSession().getServletContext().getRealPath("/uploads");
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        return dirPath + "/" + year + "/" + month;
    }

    private String createFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        long randomCode = Math.round(Math.random() * 10000) + 1;
        return date + randomCode;
    }
}
