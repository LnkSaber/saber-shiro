import com.saber.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {
    @Test
    public void fun(){
        CustomRealm customRealm = new CustomRealm();
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager =new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Saber", "123456");
        subject.login(token);

        System.out.println("isAuthenticated     "+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:add");
    }

}
