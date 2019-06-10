package com.lee.supersuse.exception;

import com.lee.supersuse.enums.CodeEnum;

/**
 * 返回给web的自定义错误
 */
public class WebException extends RuntimeException {
    private Integer code;

    public WebException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        code = codeEnum.getCode();
    }

    public WebException(CodeEnum codeEnum,String msg){
        super(codeEnum.getMsg()+msg);
        this.code = codeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
