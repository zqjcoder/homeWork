package com.lagou.step2.service;

import com.lagou.step2.pojo.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> queryResumeList() throws Exception;
    void delete(long id) throws Exception;
    Resume queryOne(long id) throws Exception;
    void update(Resume resume) throws Exception;
}
