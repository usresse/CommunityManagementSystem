package com.example.communitymanagementsystem.Excption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * \* @author: Predator
 * \* Date: 2022-10-2
 * \* Time: 13:53
 * \* Description:
 * \全局异常处理
 */
@Slf4j
@RestController
public class ExceptionHtml implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("/html/ExceptionHtml/ExceptionHtml");
        modelAndView.addObject("Exception",ex.getMessage());
        return modelAndView;
    }

}
