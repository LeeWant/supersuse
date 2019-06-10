package com.lee.supersuse.service;

import com.lee.supersuse.pojo.AffairView;

import java.util.List;

public interface AffairManageService {

    /**
     * 通过userId查询该用户发布的所有Affair
     * @param userId
     * @return
     */
    List<AffairView> getAffairsByUserId(Integer userId);

    /**
     * 删除Affair通过affairId
     * @param affId
     * @return
     */
    Integer deleteAffair(String affId);
}
