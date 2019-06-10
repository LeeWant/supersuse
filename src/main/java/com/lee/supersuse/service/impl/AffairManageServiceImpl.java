package com.lee.supersuse.service.impl;

import com.lee.supersuse.mapper.AffairMapper;
import com.lee.supersuse.mapper.ViewMapper;
import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.service.AffairManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AffairManageServiceImpl implements AffairManageService {

    @Resource
    ViewMapper viewMapper;
    @Resource
    AffairMapper affairMapper;

    @Override
    public List<AffairView> getAffairsByUserId(Integer userId) {
        return viewMapper.selectAffairViewsByUserId(0, userId);
    }

    @Override
    public Integer deleteAffair(String affId) {
        return affairMapper.deleteAffair(affId);
    }
}
