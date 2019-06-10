package com.lee.supersuse.exception;

import com.lee.supersuse.enums.CodeEnum;

public class JsonException extends RuntimeException {
    private Integer code;

    public JsonException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        code = codeEnum.getCode();
    }

    public JsonException(CodeEnum codeEnum,String msg){
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
