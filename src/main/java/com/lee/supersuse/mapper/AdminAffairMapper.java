package com.lee.supersuse.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminAffairMapper {

    /**
     * 获取所有的事务
     * @return
     */
    List<Map<String,Object>> selectAllAffairs();

    /**
     * 更新事务可见性
     * @param affId
     * @param isDelete
     * @return
     */
    Integer updateAffairStatus(@Param("affId") String affId,@Param("isDelete") Integer isDelete);

    /**
     * 获取所有的事务，带查询条件
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllAffairsBySearch(Map<String,Object> map);

    /**
     * 删除事务通过主键
     * @param affId
     * @return
     */
    Integer deleteAffairById(String affId);


}
