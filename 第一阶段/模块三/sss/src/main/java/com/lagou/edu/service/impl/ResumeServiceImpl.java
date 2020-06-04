package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> queryResumeList() throws Exception {
        return resumeDao.findAll();
    }

    @Override
    public void delete(long id) throws Exception {
        resumeDao.deleteById(id);
    }

    @Override
    public Resume queryOne(long id) throws Exception {
        Optional<Resume> byId = resumeDao.findById(id);
        return byId.get();
    }

    @Override
    public void update(Resume resume) throws Exception {
        resumeDao.save(resume);
    }
}
