package com.lee.supersuse.service.impl;

import com.lee.supersuse.mapper.AffairMapper;
import com.lee.supersuse.mapper.ViewMapper;
import com.lee.supersuse.pojo.Affair;
import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.MyFile;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.AffairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class AffairServiceImpl implements AffairService {

    @Resource
    private AffairMapper affairMapper;
    @Resource
    private ViewMapper viewMapper;

    @Override
    public void insertAffair(Affair affair) {
        affairMapper.insertAffair(affair);
    }

    @Override
    public void insertFile(MyFile myFile) {
        affairMapper.insertFile(myFile);
    }

    @Override
    public List<MyFile> selectAffairFiles(String affId) {
        return affairMapper.selectAffairFiles(affId);
    }

    @Override
    public MyFile selectAffairFile(String fileId) {
        return affairMapper.selectAffairFile(fileId);
    }

    @Override
    public Affair getAffairById(String affId, HttpSession session) {
        //获取用户可查看所有事务
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        List<AffairView> affairViewList = viewMapper.selectAffairViewsByPageAndRole(0, null, userLoginView);
        //查询出当前要访问的事务
        Affair affair = affairMapper.selectAffairById(affId);
        //设置事务的学院路径
        affair.setInstPath(affairMapper.selectAffairInstPathById(affId));
        //遍历用户角色，如果是管理员可以直接查看所有事务
        for (String role : userLoginView.getRoleCodes()) {
            if (role.equals("ADMIN")){
                return affair;
            }
        }
        //不是管理员的话需要遍历用户可查看事务的范围，当可见事务id==当前查询事务id才返回事务
        for (AffairView a : affairViewList) {
            if (a.getAffId().equals(affair.getAffId())) {
                return affair;
            }
        }
        return null;
    }

    @Override
    public List<Affair> getAffairs(Integer isDelete) {
        return affairMapper.selectAffairs(isDelete);
    }

}
