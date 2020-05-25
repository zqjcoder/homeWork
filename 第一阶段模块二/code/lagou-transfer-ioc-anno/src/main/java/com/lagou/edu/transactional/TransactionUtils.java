package com.lagou.edu.transactional;

import com.lagou.edu.annotion.MyAutoWired;
import com.lagou.edu.annotion.MyService;
import com.lagou.edu.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * 事务辅助类
 * 使用自定义注解完成实例初始化
 */
@MyService
public class TransactionUtils {
    // 事物管理器
    @MyAutoWired
    private ConnectionUtils connectionUtils;

    public void begin() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }
    public void rollback() throws SQLException{
        connectionUtils.getCurrentThreadConn().rollback();
    }
}