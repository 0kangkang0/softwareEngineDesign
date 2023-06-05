package com.kangkang.util;

import com.kangkang.pojo.FileInfo;
import com.kangkang.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class ResourcesUtil {
    private static FileInfo fileInfo;
    @Autowired
    public void setFileInfo(FileInfo fileInfo) {
        ResourcesUtil.fileInfo = fileInfo;
    }

    public static Result saveImage(MultipartFile file){
        if(file.isEmpty())return Result.infoError("文件为空");
        String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(file.getContentType())
                .substring(file.getContentType().lastIndexOf("/") + 1);
        try {
            // 获取保存路径
            String path = fileInfo.getFilePath();
            File files = new File(path, fileName);
            File parentFile = files.getParentFile();
            byte[] bytes = file.getBytes();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(files);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(fileName);
    }
}
