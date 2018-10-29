import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmText {
    DruidDataSource dataSource =new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void fun(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //设置jdbcRealm权限的开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql="select password from test_user where username =?";
        jdbcRealm.setAuthenticationQuery(sql);

        String Rolesql="select role_name from test_user_role where user_name =?";
        jdbcRealm.setUserRolesQuery(Rolesql);


        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager =new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lnk", "123456");
        subject.login(token);

        System.out.println("isAuthenticated     "+subject.isAuthenticated());

        subject.checkRoles("user");
//        subject.checkRoles("admin","user");
//
//        subject.checkPermissions("user:select");
}

}
