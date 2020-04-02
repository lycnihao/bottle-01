package com.couldr.squirrel.upload;

import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: iksen
 * @Date: 2020/4/2 20:42
 */
public class UploadTest {
    public static void main(String[] args) {
        moveto();
    }

    private static void upload(){
        FileHandler fileHandler = new LocalFileHandler();
        try {
            File file = new File("C:\\Users\\38707\\.squirrel\\upload\\2020\\4\\123.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            MockMultipartFile mockMultipartFile = new MockMultipartFile(file.getName(),fileInputStream);
            fileHandler.upload(mockMultipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void moveto(){
        FileHandler fileHandler = new LocalFileHandler();
        fileHandler.moveToPath("","2020\\4\\a");
    }
}
