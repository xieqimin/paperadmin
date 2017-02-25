package com.wit.paperadmin.controller;

import com.wit.paperadmin.pojo.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 何鹏帅 on 2016/11/23.
 */
@ControllerAdvice
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public BaseResponse<String> exp(HttpServletRequest request, HttpServletResponse response, Exception e) {

        response.setStatus(700);
        BaseResponse<String> errorMsg = new BaseResponse<>();
        errorMsg.setCode(0);
        errorMsg.setMessage("操作失败");
        return errorMsg;
    }
}
