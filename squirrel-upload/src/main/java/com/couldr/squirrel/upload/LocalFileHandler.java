package com.couldr.squirrel.upload;

import com.couldr.squirrel.code.exception.ServiceException;
import com.couldr.squirrel.code.model.support.UploadResult;
import com.couldr.squirrel.code.utils.SquirrelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Locale;

import static com.couldr.squirrel.code.model.support.SquirrelConst.DELIMITER;
import static com.couldr.squirrel.code.model.support.SquirrelConst.USER_HOME;

/**
 * 本地文件上传程序
 * @Author: iksen
 * @Date: 2020/4/2 20:11
 */
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
    public UploadResult upload(MultipartFile file) {
        //获取当前时间
        Calendar current = Calendar.getInstance(Locale.getDefault());

        //获取年，月份
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;

        String key = SquirrelUtils.randomUUIDWithoutDash();

        String subDir = UPLOAD_DIR + year + DELIMITER + month + DELIMITER;
        //文件名称
        String basename = getPrefix(file.getName());
        String extension =  basename + "_" + key;
        String suffix = getSuffix(file.getName());
        String subFilePath = subDir + extension + '.' + suffix;
        System.out.println(subFilePath);
        Path uploadPath = Paths.get(workDir, subFilePath);
        try {
            //创建目录
            Files.createDirectories(uploadPath.getParent());
            Files.createFile(uploadPath);
            // 上传文件
            file.transferTo(uploadPath);
            UploadResult uploadResult = new UploadResult();
            uploadResult.setFilename(extension);
            uploadResult.setFilePath(subFilePath);
            uploadResult.setKey(key);
            uploadResult.setSuffix(suffix);
            /*uploadResult.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));*/
            uploadResult.setSize(file.getSize());
            return uploadResult;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("上传附件失败").setErrorData(uploadPath);
        }
    }

    @Override
    public void moveToPath(String key, String path) {
           try {
               String filePath = "C:\\Users\\38707\\.squirrel\\upload\\2020\\4\\123.jpg";
               File oldFile = new File(filePath);
               String subDir = workDir + UPLOAD_DIR + path + DELIMITER;
               Path uploadPath = Paths.get(subDir, oldFile.getName());
               File newFile = new File(uploadPath.toString());
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

    }

    @Override
    public void delete(String key) {

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