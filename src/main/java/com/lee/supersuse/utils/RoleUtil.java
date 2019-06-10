package com.lee.supersuse.utils;

import com.lee.supersuse.pojo.UserLoginView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RoleUtil {

    /**
     * 检查用户的菜单权限
     * @param userLoginView
     * @return
     */
    public static boolean checkRole(UserLoginView userLoginView,String code,String checkWhat){
        List<String> role_codes = null;
        switch (checkWhat){
            case "checkMenu":
                role_codes = userLoginView.getMenuCodes();
                break;
            case "checkRole":
                role_codes = userLoginView.getRoleCodes();
                break;
            case "checkRes":
                role_codes = userLoginView.getResCodes();
                break;
        }
        for(String role_code:role_codes) {
            log.info("当前代码："+role_code);
            log.info(code);
            if (role_code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
