package com.lee.supersuse.controller;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.mapper.FileMapper;
import com.lee.supersuse.pojo.*;
import com.lee.supersuse.service.AffairService;
import com.lee.supersuse.service.MajorService;
import com.lee.supersuse.service.ViewService;
import com.lee.supersuse.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 事务控制器
 */
@Controller
@Slf4j
public class AffairController {

    @Autowired
    private AffairService affairService;
    @Autowired
    private ViewService viewService;
    @Autowired
    private MajorService majorService;

    //导入文件储存地址
    @Value("${my.path}")
    private String path;

    /**
     * 上传事务
     *
     * @param files
     * @param session
     * @param affair
     * @return
     */
    @RequestMapping("/affair")
    public String upload(@RequestParam(required = false) MultipartFile[] files,
                         HttpSession session,
                         Affair affair,
                         Model model) {
        //给事务赋值学院专业
        if(affair.getInstCode() == null || affair.getInstCode() == ""){
            affair.setInstCode("ALLINS");
            affair.setDeptCode("ALLDEP");
            affair.setMajoCode("ALLMAJ");
        }
        if (affair.getDeptCode() == null || affair.getDeptCode() == ""){
            affair.setDeptCode("ALLDEP");
            affair.setMajoCode("ALLMAJ");
        }
        if (affair.getMajoCode() == null || affair.getMajoCode() == "")
        {
            affair.setMajoCode("ALLMAJ");
        }
        //生成事务的主键
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        if (userLoginView != null) {
            affair.setUserId(userLoginView.getUserId());
        }
        String affId = UUID.randomUUID().toString();
        affair.setAffId(affId);
        affair.setLevel(0);
        affairService.insertAffair(affair);
        log.info(affair.toString());
        //上传的附件
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
            if (file != null) {
                //保存路径的文件夹名为附件所属事务的affId
                String savePath = UploadUtil.fileUpload(file, path + File.separator + affId);
                MyFile myFile = new MyFile();
                myFile.setAffId(affId);
                myFile.setFileId(UUID.randomUUID().toString());
                myFile.setFileName(file.getOriginalFilename());
                myFile.setFilePath(savePath);
                affairService.insertFile(myFile);
                log.info("附件保存路径：" + savePath);
                log.info("附件：" + file.getOriginalFilename() + " 保存成功！");
            }
        }
        //重定向至展示页面
        return "redirect:/showAffair/" + affId;
    }

    /**
     * 根据affId展示事务
     *
     * @param affId
     * @param model
     * @return
     */
    @RequestMapping("/showAffair/{affId}")
    public String showAffair(@PathVariable String affId,
                             HttpSession session,
                             Model model) {
        //根据Id查询事务
        Affair affair = affairService.getAffairById(affId,session);
        List<MyFile> myFileList = affairService.selectAffairFiles(affId);
        //对comment中的html代码进行附件html代码拼接
        if (affair != null) {
            StringBuilder stringBuilder = new StringBuilder(affair.getComment());
            //判断affair存在并对affair的附件进行html拼接
            if (myFileList.size() > 0) {
                stringBuilder.append("<p style='font-weight:bold'>附件下载:</p>");
                for (MyFile myFile : myFileList) {
                    stringBuilder.append("<div><a style='text-decoration:underline' href=\"/download/" +
                            myFile.getFileId() + "\">" +
                            myFile.getFileName() + "</a></div>");
                }
            }
            stringBuilder.append("<div style='padding-bottom: 1%'></div>");
            affair.setComment(stringBuilder.toString());
        }else {
            throw new WebException(ErrorEnum.NOT_FOUND_ERROR);
        }
        model.addAttribute("affair", affair);
        return "showAffair";
    }

    @RequestMapping("/publish")
    public ModelAndView publish(HttpSession session,
                                ModelAndView mv) {
        log.info("----进入LoginController控制器:publish()方法----");
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        mv.setViewName("login");
        //如果用户名和密码不为空
        if (userLoginView != null) {
            //进行查询
            log.info("当前登录用户：" + userLoginView.getName());
            //级联下拉框初始数据
            List<Institute> instituteList = majorService.selectAllInstitute();
            log.info(instituteList.toString());
            List<Department> deptList = majorService.selectAllDepartment();
            log.info(deptList.toString());
            List<Major> majorList = majorService.selectAllMajor();
            log.info(majorList.toString());
            mv.addObject("instituteList", instituteList);
//                mv.addObject("deptList", deptList);
//                mv.addObject("majorList", majorList);
            mv.setViewName("publish");
            return mv;
        } else {
            mv.addObject("errmsg", "请登陆后再访问");
        }
        return mv;
    }

    /**
     * 分页展示所有事务
     *
     * @return
     */
    @RequestMapping("/showAffairs")
    @ResponseBody
    public DataTablesView showAffairs(@RequestParam(required = false, defaultValue = "1") Integer start,
                                      @RequestParam(required = false, defaultValue = "5") Integer length,
                                      @RequestParam(required = false) Map map,
                                      HttpServletRequest request,
                                      Integer draw) {
        log.info("---------------进入showAffairs方法----------------");
        String search = null;
        if (map.get("search[value]") != null && !map.get("search[value]").toString().equals("")) {
            search = map.get("search[value]").toString();
        }
        log.info("搜索条件:" + search);
        //获取用户session
        UserLoginView userLoginView = (UserLoginView) request.getSession().getAttribute("userLoginView");
        PageInfo pageInfo = viewService.getAffairViewsByPage(start, length, 0, search, userLoginView);
        //新建返回对象
        DataTablesView<AffairView> affairDataTablesView = new DataTablesView();
        //寻找未被删除的Affair
        List<AffairView> affairViewList = pageInfo.getList();
        for (AffairView a : affairViewList) {
            log.info(a.toString());
        }
        affairDataTablesView.setRecordsFiltered(pageInfo.getTotal());
        affairDataTablesView.setDraw(draw);
        affairDataTablesView.setRecordsTotal(pageInfo.getTotal());
        affairDataTablesView.setData(affairViewList);
        log.info(affairDataTablesView.toString());
        return affairDataTablesView;
    }

    /**
     * 下载附件
     *
     * @param fileId
     * @param response
     * @return
     */
    @RequestMapping("/download/{fileId}")
    public void download(@PathVariable String fileId,
                         HttpServletResponse response) {
        MyFile myFile = affairService.selectAffairFile(fileId);// 文件信息
        String myFileName;
        if (myFile != null) {
            //设置文件路径
            File file = new File(myFile.getFilePath());
            if (file.exists()) {
                byte[] buffer = new byte[10 * 1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    //解决文件名中文乱码
                    myFileName = URLEncoder.encode(myFile.getFileName(), "UTF-8");
                    //设置强制下载不打开
                    log.info("当前下载的文件名：" + myFile.getFileName());
                    response.setContentType("multipart/form-data");
                    response.addHeader("Content-Disposition", "attachment;fileName=" + myFileName);
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i > 0) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
