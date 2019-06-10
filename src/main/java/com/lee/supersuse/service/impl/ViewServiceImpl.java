package com.lee.supersuse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.supersuse.mapper.AffairMapper;
import com.lee.supersuse.mapper.ViewMapper;
import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.DateCountView;
import com.lee.supersuse.pojo.Ip;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.ViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ViewServiceImpl implements ViewService {

    @Resource
    private ViewMapper viewMapper;
    @Resource
    private AffairMapper affairMapper;

    @Override
    public PageInfo getAffairViewsByPage(Integer pageNum, Integer pageSize, Integer isDelete, String search, UserLoginView userLoginView) {
        PageHelper.offsetPage(pageNum, pageSize);
        List<AffairView> affairViewList = viewMapper.selectAffairViewsByPageAndRole(isDelete, search, userLoginView);
        return new PageInfo(affairViewList);
    }

    @Override
    public UserLoginView userLoginCheck(String logincode, String password, Integer type) {
        if (logincode != null && password != null && !logincode.isEmpty() && !password.isEmpty()) {
            //进行查询
            return viewMapper.userLoginCheck(logincode, password);
        } else return null;
    }

    @Override
    public Integer insertIpVisit(Ip ip) {
        return viewMapper.insertIpVisit(ip);
    }

    @Override
    public DateCountView getCountView() {
        //获取日期实例
        Calendar c = Calendar.getInstance();
        DateCountView dateCountView = new DateCountView();
        Date endDate = new Date();
        Date startDate = null;
        try {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            startDate = dateFormat1.parse("2019-03-01");
            dateCountView.setAllConut(affairMapper.countAffairByDate(startDate,endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //过去七天
        c.setTime(endDate);
        c.add(Calendar.DATE, - 7);
        startDate = c.getTime();
        dateCountView.setWeekConut(affairMapper.countAffairByDate(startDate,endDate));
        //过去一月
        c.setTime(endDate);
        c.add(Calendar.MONTH, -1);
        startDate = c.getTime();
        dateCountView.setMonthCont(affairMapper.countAffairByDate(startDate,endDate));
        dateCountView.setPeopleConut(affairMapper.countAllVisitNum());
        return dateCountView;
    }
}
