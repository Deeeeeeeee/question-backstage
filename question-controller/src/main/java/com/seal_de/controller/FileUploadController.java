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
import java.util.LinkedList;
import java.util.List;

@RestController
public class FileUploadController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "fileupload", method= RequestMethod.POST)
    public List<String> processUpload(
            @RequestPart(value = "files", required = false) MultipartFile[] files) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String dirPath = createFileDir();

        File dir = new File(realPath + dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        return fileUploads(files, realPath, dirPath);
    }

    private String createFileDir(){
        String dirPath = "uploads";
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        return dirPath + "/" + year + "/" + month;
    }

    private String createNewFilename(MultipartFile file) {
        String oldFilename = file.getOriginalFilename();
        String fileSuffix = oldFilename.substring(oldFilename.lastIndexOf('.'));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        long randomCode = Math.round(Math.random() * 10000) + 1;

        return "/" + date + randomCode + fileSuffix;
    }

    private List<String> fileUploads(MultipartFile[] files, String realPath, String dirPath) throws IOException {
        List<String> list = new LinkedList<String>();
        String filePath = realPath + dirPath;
        for(MultipartFile file : files) {
            String newFilename = createNewFilename(file);
            file.transferTo(new File(filePath + newFilename));
            list.add(dirPath + newFilename);
        }
        return list;
    }
}
