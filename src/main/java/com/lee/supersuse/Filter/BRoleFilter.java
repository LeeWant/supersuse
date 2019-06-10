package com.lee.supersuse.Filter;

import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.utils.RoleUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "RoleFilter",urlPatterns = {"/manage/*","/publish"})
public class BRoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("权限拦截器启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取访问链接
        log.info("----------RoleFilter--------");
        String url = request.getRequestURI();
        log.info("拦截的url:"+url);
        HttpSession session = request.getSession();
        UserLoginView userLoginView = (UserLoginView)session.getAttribute("userLoginView");
        //设置权限验证类型
        String checkWhat = "checkMenu";
        //设置权限代码
        String code = null;
        //判断用户权限
        if(url.indexOf("/manage/clazz") >-1||url.indexOf("/manage/user") > -1 || url.indexOf("/manage/student") > -1) {
            //大于-1说明访问了该链接
            code = "MEN003";
        }else if (url.indexOf("/manage/affair")>-1 || url.indexOf("/publish") > -1){
            code = "MEN004";
        }
        //判断权限是否通过
        if (RoleUtil.checkRole(userLoginView,code,checkWhat)){
            log.info("权限验证通过");
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            log.info("权限验证失败");
            response.sendRedirect("/myError");
        }
    }

    @Override
    public void destroy() {
        log.info("权限拦截器销毁");
    }
}
