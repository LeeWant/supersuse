package com.lee.supersuse.utils;

import com.lee.supersuse.enums.CodeEnum;
import com.lee.supersuse.pojo.Result;

public class ResultUtil {

    public static Result success(Object object){
        return success(object,null);
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg("操作失败:"+msg);
        return result;
    }
    public static Result error(CodeEnum ce){
        return error(ce.getCode(),ce.getMsg());
    }

    public static Result success(Object object,Integer count){
        return success(object,count,null);
    }
    public static Result success(Object object,Integer count,Integer pages){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("操作成功!");
        result.setData(object);
        result.setCount(count);
        result.setPages(pages);
        return result;
    }


}
