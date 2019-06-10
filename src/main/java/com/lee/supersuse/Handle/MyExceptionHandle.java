package com.lee.supersuse.Handle;

import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.pojo.Result;
import com.lee.supersuse.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class MyExceptionHandle {
    /**
     * 拦截返回给error页面的错误
     * @param e
     * @return
     */
    @ExceptionHandler(WebException.class)
    public ModelAndView WebHandle(Exception e) {
        log.error("进入方法：WebHandle");
        ModelAndView mv = new ModelAndView();
        WebException webException = (WebException) e;
        mv.setViewName("/error");
        mv.addObject("msg", webException.getMessage());
        mv.addObject("code", webException.getCode());
        return mv;
    }

    /**
     * 拦截返回Json字符串的错误
     * @param e
     * @return
     */
    @ExceptionHandler(JsonException.class)
    @ResponseBody
    public Result JsonHandle(Exception e) {
//        if (e instanceof JsonException) {
            //Json异常
            JsonException jsonException = (JsonException) e;
            return ResultUtil.error(jsonException.getCode(), jsonException.getMessage());
//        } else {
//            //拦截普通异常并打印
//            log.error("【"+e.toString()+"】:"+e);
//            return ResultUtil.error(-1, e.toString());
//        }
    }
}
