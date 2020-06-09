package com.myblog.controller;

import com.myblog.pojo.PageRequest;
import com.myblog.pojo.PageResult;
import com.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService service;

    @RequestMapping("/index")
    public String index(PageRequest request, Model model) {
        if (null == request || request.getPageSize() == 0 || request.getPageNum() == 0) {
            request = new PageRequest();
            request.setPageSize(2);
            request.setPageNum(1);
        }
        PageResult page = service.findPage(request);
        model.addAttribute("page", page);
        SimpleDateFormat  simpleDateFormat= new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(new Date());
        model.addAttribute("currentYear", format);
        return "client/index";
    }
}
