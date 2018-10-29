import org.apache.commons.beanutils.DynaBeanMapDecorator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class Text {
    SimpleAccountRealm simpleAccountRealm =new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Saber","123456789","admin","lnk");
    }

    @Test
    public void fun(){
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager =new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Saber", "123456789");
        subject.login(token);

        System.out.println("isAuthenticated     "+subject.isAuthenticated());
        subject.checkRoles("admin");
    }
}
