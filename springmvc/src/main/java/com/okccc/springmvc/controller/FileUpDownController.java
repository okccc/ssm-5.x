package com.okccc.springmvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: okccc
 * @Date: 2022/12/15 10:39
 * @Desc: 文件上传下载
 *
 * 下载：ResponseEntity作为控制器方法的返回值,表示响应到浏览器的响应报文
 *
 * 上传：form表单请求方式为post,且必须设置属性enctype="multipart/form-data"
 * 1.pom.xml引入commons-fileupload依赖
 * 2.springmvc.xml配置文件上传解析器
 */
@Controller
public class FileUpDownController {

    @RequestMapping("/ajax/upload")
    public String testUpload(MultipartFile photo, HttpSession session) throws IOException {
        // 获取上传文件的文件名
        String filename = photo.getOriginalFilename();
        // 截取上传文件的后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 使用uuid拼接新的文件名,防止文件重名
        filename = UUID.randomUUID() + suffix;
        // 获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        // 获取服务器文件photo真实路径
        String photoPath = servletContext.getRealPath("photo");
        // 创建photoPath对应的File对象
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + filename;
        // 上传文件
        photo.transferTo(new File(finalPath));
        return "success";
    }

    @RequestMapping("/ajax/download")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException {
        // 获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        // 获取服务器文件真实路径
        String realPath = servletContext.getRealPath("/static/css/img/a.png");
        // 创建输入流
        FileInputStream fis = new FileInputStream(realPath);
        // 创建输入流文件大小的字节数组
        byte[] bytes = new byte[fis.available()];
        // 读取字节数组
        fis.read(bytes);
        // 创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        // 设置下载方式为附件,并指定文件名
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        // 设置响应状态码
        HttpStatus status = HttpStatus.OK;
        // 创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        // 关闭输入流
        fis.close();
        return responseEntity;
    }
}
