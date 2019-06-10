package com.lee.supersuse.Filter;

import com.lee.supersuse.pojo.UserLoginView;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CAdminFilter",urlPatterns = {"/admin/*"})
@Slf4j
public class CAdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取访问链接
        String url = request.getRequestURI();
        log.info("----------进入AdminFilter--------");
        log.info("拦截的url:"+url);
        HttpSession session = request.getSession();
        //获取登录用户信息
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        if(userLoginView.getRoleCodes().contains("ADMIN")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect("/myError");
        }
    }

    @Override
    public void destroy() {

    }
}
