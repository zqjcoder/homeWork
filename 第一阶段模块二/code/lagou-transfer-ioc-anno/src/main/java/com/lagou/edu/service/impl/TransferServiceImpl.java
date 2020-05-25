package com.lagou.edu.service.impl;

import com.lagou.edu.annotion.MyAutoWired;
import com.lagou.edu.annotion.MyService;
import com.lagou.edu.annotion.MyTransactional;
import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 应癫
 * 使用自定义注解完成实例初始化
 */
@MyService
public class TransferServiceImpl implements TransferService {

    // 最佳状态
    // @Autowired 按照类型注入 ,如果按照类型无法唯一锁定对象，可以结合@Qualifier指定具体的id
    //类中的关联关系，依赖注入使用自定义注解@MyAutoWired
    @MyAutoWired
    private JdbcAccountDaoImpl jdbcAccountDaoImpl;

    /**
     * s事务部分使用自定义注解 @MyTransactional
     * @param fromCardNo
     * @param toCardNo
     * @param money
     * @throws Exception
     */
    @Override
    @MyTransactional
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        /*try{
            // 开启事务(关闭事务的自动提交)
            TransactionManager.getInstance().beginTransaction();*/

            Account from = jdbcAccountDaoImpl.queryAccountByCardNo(fromCardNo);
            Account to = jdbcAccountDaoImpl.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            jdbcAccountDaoImpl.updateAccountByCardNo(to);
            //int c = 1/0;
            jdbcAccountDaoImpl.updateAccountByCardNo(from);
    }
}
