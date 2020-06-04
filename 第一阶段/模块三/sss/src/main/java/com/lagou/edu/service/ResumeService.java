package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<Resume> queryResumeList() throws Exception;
    void delete(long id) throws Exception;
    Resume queryOne(long id) throws Exception;
    void update(Resume resume) throws Exception;
}
