import com.lagou.edu.SpringConfig;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.service.impl.TransferServiceImpl;
import com.lagou.edu.utils.MyAnnotationConfigApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 应癫
 */
public class IoCTest {
    @Test
    public void testIoC() throws Exception {

        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");

        System.out.println(accountDao);

    }

    @Test
    public void testIocTransfer() throws Exception {
        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //MyAnnotationConfigApplicationContext serviceapplicationContext = new MyAnnotationConfigApplicationContext("com.lagou.edu");
        //使用自定义解析及注解完成bean的初始化
        MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext("com.lagou.edu");
        //从自定义容器中取出bean，获取成功
        TransferServiceImpl transferServiceImpl = (TransferServiceImpl) applicationContext.getBean("transferServiceImpl");
        //实现转账逻辑韩梅梅--》李大雷转账
        transferServiceImpl.transfer("6029621011000", "6029621011001", 100);
        System.out.println("Transfer end ");
    }
}
