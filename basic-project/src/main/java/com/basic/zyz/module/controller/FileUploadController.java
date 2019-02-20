package com.basic.zyz.module.controller;

import com.basic.zyz.common.file.entity.FileResponse;
import com.basic.zyz.common.file.exception.StorageFileNotFoundException;
import com.basic.zyz.common.file.service.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Api(value = "文件上传", description = "文件上传")
@RestController
@CrossOrigin
public class FileUploadController {

    @Value("${oss.url}")
    private String downloadUrl;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ApiOperation("文件目录列表")
    @GetMapping("/listUpload")
    public Object listUploadedFiles() {
        return storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                        .build().toString())
                .collect(Collectors.toList());
    }

    /**
     * filename:.+是正则匹配字符串的意思
     * 文件下载
     * 正则表达式匹配, 语法: {varName:regex} 前面式变量名，后面式表达式
     * 匹配出现过一次或多次.的字符串 如: "zyz.png"
     *
     * @param filename
     * @return
     */
    @ApiOperation("根据文件名搜索文件")
    @GetMapping("/userfiles/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    /**
     * filename:.+是正则匹配字符串的意思
     * 文件下载
     * 正则表达式匹配, 语法: {varName:regex} 前面式变量名，后面式表达式
     * 匹配出现过一次或多次.的字符串 如: "zyz.png"
     *
     * @param filename
     * @return
     */
    @ApiOperation("根据文件名搜索文件")
    @GetMapping("/userfiles/{uploadDate}/{filename:.+}")
    public ResponseEntity<Resource> serveDateFile(@PathVariable String uploadDate, @PathVariable String filename) {
        Resource file = storageService.loadAsResourceByDate(uploadDate, filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    /**
     * 文件上传
     */
    @PostMapping("/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
        try {
            //		目录按照每天分配
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String today = df.format(new Date());
//		重命名文件名
            String fileName = storageService.storeAsDate(file, today);
//		访问文件的路径，可以是相对路径和绝对路径，这里是绝对路径
            String url = downloadUrl + "/" + today + "/" + fileName;
            return FileResponse.success(0, url);
        } catch (JsonProcessingException e) {
            return FileResponse.error(1, "error");
        } catch (RuntimeException e) {
            return FileResponse.error(1, "error");
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
