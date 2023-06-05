package com.kangkang.web;

import com.kangkang.pojo.FileInfo;
import com.kangkang.pojo.LoginRequired;
import com.kangkang.pojo.Result;
import com.kangkang.util.ResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/resources")
public class ResourcesControl {
    @Autowired
    private FileInfo fileInfo;

    /**
     * 获得头像
     * @param image
     * @param resp
     */
    @LoginRequired
    @GetMapping("/{image}")
    public void getAvatar(@PathVariable String image, HttpServletResponse resp) {
        File file = new File(fileInfo.getFilePath() + image);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] aByte = new byte[fileInputStream.available()];
            fileInputStream.read(aByte);
            fileInputStream.close();
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(aByte);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传头像
     * @param request
     * @return
     */
    @LoginRequired
    @RequestMapping("/upload")
    public Result uploadImage(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        assert file != null;
        return Result.ok(ResourcesUtil.saveImage(file));
    }
}
