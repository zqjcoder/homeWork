package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void getAllUsers() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }

    public IUserDao getCommonConn() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return  sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void addUser() throws Exception {
        IUserDao userDao =getCommonConn();
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        userDao.addUser(user);
    }

    @Test
    public void udpUser() throws Exception {
        IUserDao userDao =getCommonConn();
        User user = new User();
        user.setId(2);
        user.setUsername("tom");
        userDao.udpUser(user);
    }

    @Test
    public void delUser() throws Exception {
        IUserDao userDao =getCommonConn();
        User user = new User();
        user.setId(4);
        userDao.delUser(user);
    }
}
