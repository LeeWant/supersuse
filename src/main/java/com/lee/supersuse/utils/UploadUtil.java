package com.lee.supersuse.utils;

import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {


    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     */
    public static String fileUpload(MultipartFile file,String path){
        //新的文件名
        String newFileName;
        String oldFileName = file.getOriginalFilename();
        try {
            //为图片重命名
            newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            File desFile = new File(path + File.separator + newFileName);
            //如果目录不存在则创建新目录
            if (!desFile.getParentFile().exists()) {
                desFile.getParentFile().mkdirs();
            }
            //将文件输出到指定位置
            file.transferTo(desFile);
            return path+File.separator+newFileName;
        } catch (IOException e) {
            throw new JsonException(ErrorEnum.FILE_UPLOAD_ERROR);
        }
    }


}
