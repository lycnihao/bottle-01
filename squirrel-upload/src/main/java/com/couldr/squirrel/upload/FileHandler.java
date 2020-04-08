package com.couldr.squirrel.upload;

import com.couldr.squirrel.upload.model.UploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理程序
 *
 * @Author: iksen
 * @Date: 2020/4/2 19:47
 */
public interface FileHandler {

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    UploadResult upload(MultipartFile file);

    /**
     * 移动文件
     * @param key 文件key
     * @param path 移动路径
     */
    void moveToPath(String key,String path);

    /**
     * 删除文件
     * @param path 文件路径
     */
    void delete(String path);

    String getPrefix(String fileName);

    String getSuffix(String fileName);
}
