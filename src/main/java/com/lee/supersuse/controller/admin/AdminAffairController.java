package com.lee.supersuse.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminAffairService;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.MyFile;
import com.lee.supersuse.pojo.Result;
import com.lee.supersuse.service.AffairService;
import com.lee.supersuse.service.MajorService;
import com.lee.supersuse.utils.ResultUtil;
import com.lee.supersuse.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminAffairController {


    @Autowired
    private MajorService majorService;
    @Autowired
    private AdminAffairService adminAffairService;
    //导入文件储存地址
    @Value("${my.path}")
    private String path;

    @RequestMapping("/affair-list")
    public String getAffairList(Model model) {
        //级联下拉框初始数据
        List<Institute> instituteList = majorService.selectAllInstitute();
        model.addAttribute(instituteList);
        return "/admin/affair-list";
    }

    @RequestMapping("/affair-list/json")
    @ResponseBody
    public Result getJson(@RequestParam Integer limit,
                          @RequestParam Integer page,
                          @RequestParam Map map,
                          HttpServletRequest request) {
        log.info("page:" + page + "\n" + "limit:" + limit);
        log.info("map中的查询信息：" + map.toString());
        //级联下拉框初始数据
        String instCode = request.getParameter("instCode");
        log.info("instCode:" + instCode);
        PageInfo pageInfo = adminAffairService.getAllAffairsByPage(page, limit, map);
        List<Map<String, Object>> affairs = pageInfo.getList();
        return ResultUtil.success(affairs, ((Long) pageInfo.getTotal()).intValue());
    }

    @RequestMapping("/affair/status")
    @ResponseBody
    public Result changeAffairStatus(@RequestParam Integer isDelete,
                                     @RequestParam String affId) {
        log.info("进入更改文件删除状态controller方法changeStatus");
        Integer count = adminAffairService.changeAffairStatus(affId, isDelete);
        return ResultUtil.success(count);
    }

    @RequestMapping("/affair/delete")
    @ResponseBody
    public Result deleteAffair(@RequestParam String affId) {
        log.info("进入admin删除Affair的方法");
        Integer count = adminAffairService.deleteAffair(affId);
        return ResultUtil.success(count);
    }

    @RequestMapping("/affair/deleteAll")
    @ResponseBody
    public Result deleteAffair(@RequestParam List<String> affIds) {
        log.info("进入admin批量删除Affairs的方法");
        Integer count = adminAffairService.deleteAffairs(affIds);
        return ResultUtil.success(count);
    }


    @RequestMapping("/file-list")
    public String fileList() {
        return "/admin/file-list";
    }


    @RequestMapping("/file-list/json")
    @ResponseBody
    public Result getAllFiles(@RequestParam Integer page,
                              @RequestParam Integer limit,
                              @RequestParam Map map) {
        log.info("进入获取所有files的方法");
        log.info("page:" + page + "\n" + "limit:" + limit);
        log.info("map中的查询信息：" + map.toString());
        PageInfo pageInfo = adminAffairService.getAllFiles(page, limit, map);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue());
    }

    @RequestMapping("/file/status")
    @ResponseBody
    public Result changeFileStatus(@RequestParam Integer isDelete,
                                   @RequestParam String fileId) {
        log.info("进入更改文件删除状态controller方法changeStatus");
        Integer count = adminAffairService.changeFileStatus(fileId, isDelete);
        return ResultUtil.success(count);
    }

    @RequestMapping("/file/delete")
    @ResponseBody
    public Result deleteFile(@RequestParam String fileId) {
        log.info("进入admin删除file的方法");
        Integer count = adminAffairService.deleteFile(fileId);
        return ResultUtil.success(count);
    }

    @RequestMapping("/file/deleteAll")
    @ResponseBody
    public Result deleteFiles(@RequestParam List<String> fileIds) {
        log.info("进入admin批量删除Affairs的方法");
        Integer count = adminAffairService.deleteFiles(fileIds);
        return ResultUtil.success(count);
    }


    @RequestMapping("/file-add")
    public String addFile() {
        return "/admin/file-add";
    }

    @RequestMapping("/file/add")
    @ResponseBody
    public Result addFile(@RequestParam MultipartFile file,
                          @RequestParam String affId) {
        log.info("给指定的事务添加文件");
        log.info("上传的文件名："+file.getOriginalFilename());
        log.info("指定的affId："+affId);
        if (file != null) {
            //保存路径的文件夹名为附件所属事务的affId
            MyFile myFile = new MyFile();
            String savePath = UploadUtil.fileUpload(file, path + "/" + affId);
            myFile.setFileId(UUID.randomUUID().toString());
            myFile.setAffId(affId);
            myFile.setFilePath(savePath);
            myFile.setFileName(file.getOriginalFilename());
            adminAffairService.insertFile(myFile);
            log.info("附件保存路径：" + savePath);
            log.info("附件：" + file.getOriginalFilename() + " 保存成功！");
        }else {
            throw new JsonException(ErrorEnum.FILE_NOT_TYPE);
        }
        return ResultUtil.success();
    }
}
