package run.bottle.app.upload;

import run.bottle.app.upload.model.UploadResult;
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
    UploadResult upload(MultipartFile file, String path);

    /**
     * 移动文件
     * @param path 原路径
     * @param newPath 目标路径
     */
    UploadResult moveToPath(String path,String newPath);

    /**
     * 复制文件
     * @param path 原路径
     * @param targetPath 目标路径
     */
    UploadResult copyFile(String path,String targetPath);

    /**
     * 删除文件
     * @param path 文件路径
     */
    void delete(String path);

    /**
     * 创建文件夹
     * @param path 文件夹路径
     */
    void createDirectories(String path);
}
