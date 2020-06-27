package com.lagou.step2.controller;

import com.lagou.step2.pojo.Resume;
import com.lagou.step2.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @RequestMapping("/queryAll")
    public String queryAll(Model model) throws Exception {
        List<Resume> resumes = resumeService.queryResumeList();
        model.addAttribute("list", resumes);
        return "resume";
    }

    @RequestMapping("/delete")
    public String delete(long id, Model model) throws Exception {
        resumeService.delete(id);
        return queryAll(model);
    }

    @RequestMapping("/createView")
    public String create(long id, Model model) throws Exception {
        if (id > 0) {
            Resume resume = resumeService.queryOne(id);
            model.addAttribute("resume", resume);
        }
        return "create";
    }

    @RequestMapping("/create")
    public String createResume(HttpServletResponse response, Resume resume, Model model) throws Exception {
        if (null == resume.getId()) {
            response.sendRedirect("/resume/createView?id=0");
            return "success";
        }
        resumeService.update(resume);
        return queryAll(model);
    }
}
