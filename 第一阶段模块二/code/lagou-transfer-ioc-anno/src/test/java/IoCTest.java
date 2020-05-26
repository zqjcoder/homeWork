import com.lagou.edu.service.impl.TransferServiceImpl;
import com.lagou.edu.utils.MyAnnotationConfigApplicationContext;
import org.junit.Test;

/**
 * @author 应癫
 */
public class IoCTest {
    @Test
    public void testIocTransfer() throws Exception {
        //使用自定义解析及注解完成bean的初始化
        MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext("com.lagou.edu");
        //从自定义容器中取出bean，获取成功
        TransferServiceImpl transferServiceImpl = (TransferServiceImpl) applicationContext.getBean("transferServiceImpl");
        //实现转账逻辑韩梅梅--》李大雷转账
        transferServiceImpl.transfer("6029621011001", "6029621011000", 100);
        System.out.println("Transfer end ");
    }
}
