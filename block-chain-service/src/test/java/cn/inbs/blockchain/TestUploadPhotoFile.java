package cn.inbs.blockchain;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static cn.inbs.blockchain.common.third.FileUpload2AliYun.photoFileUpload;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年05月16-16:14
 * @Author: createBy:zhangmingyang
 **/
public class TestUploadPhotoFile {
    public static void main(String[] args) throws IOException {
        File file = new File("E:/QQ20180516155905.png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("QQ20180516155905.png", file.getName(), "text/plain", input);
        String s = photoFileUpload(multipartFile, "sit-upload/head_photo/");
        System.err.println(s);

    }
}
