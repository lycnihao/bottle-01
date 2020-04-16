package run.bottle.app.upload;

import static run.bottle.app.upload.utils.BottleFileUtils.getPrefix;
import static run.bottle.app.upload.utils.BottleFileUtils.getSuffix;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import run.bottle.app.upload.exception.FileOperationException;
import run.bottle.app.upload.exception.ServiceException;
import run.bottle.app.upload.model.UploadResult;
import run.bottle.app.upload.utils.BottleFileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import run.bottle.app.upload.model.FileConst;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

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
        workDir = FileConst.USER_HOME + FileConst.DELIMITER + ".bottle" + FileConst.DELIMITER;
    }

    @Override
    public UploadResult upload(MultipartFile file, String path) {
        //获取当前时间
        Calendar current = Calendar.getInstance(Locale.getDefault());

        //获取年，月份
        /*int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;

        String subDir = UPLOAD_DIR + year + DELIMITER + month + DELIMITER;*/

        String subDir =  path + FileConst.DELIMITER;

        String key = BottleFileUtils.randomUUIDWithoutDash();


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

    /**
     * 文件移动
     * @param path 原路径
     * @param newPath 目标路径
     * @return
     */
    @Override
    public UploadResult moveToPath(String path, String newPath) {
        String subDir = UPLOAD_DIR + newPath;
           try {
               File oldFile = new File(workDir + path);
               Path uploadPath = Paths.get(subDir);
               File newFile = new File(workDir + uploadPath.toString());
               System.out.println(workDir + path);
               System.out.println(workDir + uploadPath.toString());
               //创建文件夹
               Files.createDirectories(uploadPath.getParent());
               if (oldFile.renameTo(newFile)){
                   UploadResult uploadResult = new UploadResult();
                   uploadResult.setFilename(newFile.getName());
                   uploadResult.setFilePath(uploadPath.toString());
                   uploadResult.setSuffix(getSuffix(newFile.getName()));
                   uploadResult.setMediaType(MediaType.valueOf(Files.probeContentType(Paths.get(workDir,uploadPath.toString()))));
                   uploadResult.setSize(newFile.length());
                   System.out.println(uploadResult);
                   System.out.println("File is moved successful!");
                   return uploadResult;
               } else {
                   System.out.println("重命名失败！新文件名已存在");
               }
           } catch (IOException e){
               e.printStackTrace();
           }
        return null;
    }

    @Override
    public UploadResult copyFile(String source, String target) {
        try {
            File file = new File(workDir + source);
            Path sourcePath = Paths.get(workDir + source);
            Path targetPath = Paths.get(workDir + UPLOAD_DIR + target);
            System.out.println(sourcePath.toString());
            System.out.println(targetPath.toString());

            Files.copy(sourcePath,targetPath);

            UploadResult uploadResult = new UploadResult();
            uploadResult.setFilename(file.getName());
            uploadResult.setFilePath(UPLOAD_DIR + target);
            uploadResult.setSuffix(getSuffix(file.getName()));
            uploadResult.setMediaType(MediaType.valueOf(Files.probeContentType(Paths.get(sourcePath.toString()))));
            uploadResult.setSize(file.length());
            System.out.println(uploadResult);
            return uploadResult;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param path 文件路径
     */
    @Override
    public void delete(String path) {
        Assert.hasText(path, "文件路径不能为空");
        Path destinationPath = Paths.get(workDir, path);
        try {
            if (destinationPath.toFile().isDirectory()){
                deleteDirectory(destinationPath.toFile());
            } else {
                Files.delete(destinationPath);
            }
        } catch (Exception e) {
            throw new FileOperationException("附件 " + path + " 删除失败", e);
        }
    }

    // 递归删除文件夹下所有文件
    private boolean deleteDirectory(File file){
        /*if (!file.exists()) {
            return false;
        }*/
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteDirectory(f);
            }
        }
        return file.delete();
    }
}
