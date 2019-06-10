package com.lee.supersuse.utils;

import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.mapper.AdminInstitutionMapper;

import java.util.List;
import java.util.Map;

public class InstUtil {
    /**
     * 查询是否重名工具类
     *
     * @param name
     * @param adminInstitutionMapper
     */
    public static void checkName(String name, AdminInstitutionMapper adminInstitutionMapper) {
        if (name != null) {
            List<Map<String, Object>> maps = adminInstitutionMapper.selectInstCodeByName(name);
            for (Map map:maps) {
                if (map != null && map.get("instCode") != null) {
                    throw new JsonException(ErrorEnum.NAME_DONE.setMsg(name + "已被使用"));
                }
            }
        }
    }

    public static void checkNameWhenUpdate(String name, String code, AdminInstitutionMapper adminInstitutionMapper) {
        if (name != null) {
            List<Map<String, Object>> maps = adminInstitutionMapper.selectInstCodeByName(name);
            if (maps == null) {
                return;
            }
            for (Map map : maps) {
                String instCode = (String) map.get("instCode");
                String deptCode = (String) map.get("deptCode");
                String majoCode = (String) map.get("majoCode");

                String iName = (String) map.get("iName");
                String dName = (String) map.get("dName");
                String mName = (String) map.get("mName");

                if (instCode != null && instCode.equals(code) && iName != null && iName.equals(name)) {
                    return;
                } else if (deptCode != null && deptCode.equals(code) && dName != null && dName.equals(name)) {
                    return;
                } else if (majoCode != null && majoCode.equals(code) && mName != null && mName.equals(name)) {
                    return;
                } else throw new JsonException(ErrorEnum.NAME_DONE.setMsg(name + "已被使用"));
            }
        }
    }
}
