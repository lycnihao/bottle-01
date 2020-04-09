package com.couldr.squirrel.upload;

import com.couldr.squirrel.upload.exception.FileOperationException;
import com.couldr.squirrel.upload.exception.ServiceException;
import com.couldr.squirrel.upload.model.UploadResult;
import com.couldr.squirrel.upload.utils.SquirrelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import static com.couldr.squirrel.upload.model.FileConst.DELIMITER;
import static com.couldr.squirrel.upload.model.FileConst.USER_HOME;

/**
 * 本地文件上传程序
 * @Author: iksen
 * @Date: 2020/4/2 20:11
 */
@Component
public class LocalFileHandler implements FileHandler {

    /**
     * 上传目录
     */
    private final static String UPLOAD_DIR = "upload/";

    private final String workDir;

    public LocalFileHandler() {
        // Get work dir
        workDir = USER_HOME + DELIMITER + ".squirrel" + DELIMITER;
    }

    @Override
    public UploadResult upload(MultipartFile file, String path) {
        //获取当前时间
        Calendar current = Calendar.getInstance(Locale.getDefault());

        //获取年，月份
        /*int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;

        String subDir = UPLOAD_DIR + year + DELIMITER + month + DELIMITER;*/

        String subDir =  path + DELIMITER;

        String key = SquirrelUtils.randomUUIDWithoutDash();


        //文件名称
        String basename = getPrefix(Objects.requireNonNull(file.getOriginalFilename()));
        String extension =  basename + "_" + key;
        String suffix = getSuffix(file.getOriginalFilename());
        String subFilePath =  UPLOAD_DIR + subDir + extension + '.' + suffix;
        System.out.println(subFilePath);
        Path uploadPath = Paths.get(workDir, subFilePath);
        try {
            //创建目录
            Files.createDirectories(uploadPath.getParent());
            Files.createFile(uploadPath);
            // 上传文件
            file.transferTo(uploadPath);
            UploadResult uploadResult = new UploadResult();
            uploadResult.setFilename(extension + '.' + suffix);
            uploadResult.setFilePath(subFilePath);
            uploadResult.setKey(key);
            uploadResult.setSuffix(suffix);
            uploadResult.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
            uploadResult.setSize(file.getSize());
            return uploadResult;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("上传附件失败").setErrorData(uploadPath);
        }
    }

    @Override
    public String moveToPath(String path, String newPath) {
        String subDir = UPLOAD_DIR + newPath;
           try {
               File oldFile = new File(workDir + path);
               Path uploadPath = Paths.get(subDir);
               File newFile = new File(workDir + uploadPath.toString());
               //创建文件夹
               Files.createDirectories(uploadPath.getParent());
               if (oldFile.renameTo(newFile)){
                   System.out.println("File is moved successful!");
               } else {
                   System.out.println("File is failed to move !");
               }
           } catch (IOException e){
               e.printStackTrace();
           }
        return subDir;
    }

    @Override
    public void delete(String key) {
        Assert.hasText(key, "文件路径不能为空");
        Path path = Paths.get(workDir, key);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FileOperationException("附件 " + key + " 删除失败", e);
        }

    }

    @Override
    public String getPrefix(String fileName) {
        int suffixFirstIndex = fileName.lastIndexOf(".");
        if (suffixFirstIndex == -1){
            return StringUtils.EMPTY;
        }
        return fileName.substring(0, suffixFirstIndex);
    }

    @Override
    public String getSuffix(String fileName) {
        int suffixFirstIndex = fileName.lastIndexOf(".");
        if (suffixFirstIndex == -1){
            return StringUtils.EMPTY;
        }
        return fileName.substring(suffixFirstIndex + 1);
    }
}
