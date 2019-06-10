package com.lee.supersuse.Filter;

import com.lee.supersuse.pojo.UserLoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "*")
public class ALoginFilter implements Filter {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //拦截所有未登录的用户
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取访问链接
        logger.info("----------进入LoginCheckFilter--------");
        String url = request.getRequestURI();
        logger.info("拦截的url:"+url);
        HttpSession session = request.getSession();
        //判断是否是静态资源
        if(url.indexOf(".") > 0){
            //存在‘.’说明是静态资源,存在访问管理员界面
            logger.info("url状态:放行");
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //判断用户是否登录
            if (session.getAttribute("userLoginView") != null || url.equals("/login") || url.equals("/loginCheck")) {
                if (session.getAttribute("userLoginView") != null) {
                    logger.info("登录用户信息:" + ((UserLoginView) session.getAttribute("userLoginView")).toString());
                    if(url.equals("/login") || url.equals("/loginCheck")){
                        response.sendRedirect("/index");
                    }
                }
                logger.info("url状态:放行");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.error("url状态:重定向至登陆界面");
                response.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {
        logger.info("----------退出LoginCheckFilter--------");
    }
}
