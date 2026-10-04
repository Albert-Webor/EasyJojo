package com.easyjojo.common.database;

import com.easyjojo.common.config.DbSourceConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import static com.easyjojo.common.utils.PrintUtils.printGreen;
import static com.easyjojo.common.utils.PrintUtils.printRed;

@Configuration
@PropertySource(value = "classpath:application-common.properties", ignoreResourceNotFound = true)
public class DbDataSource {
    @Autowired
    private DbSourceConfigProperties properties;

    @Bean
    public DataSource dataSource() {
        for (DbSourceConfigProperties.DbConfigItem db : properties.getDatabases()) {
            if (db.getId().equals(properties.getDefaultDbId())) {
                printGreen("已绑定自定义数据源, 数据库ID是: " + db.getId());
                return new DriverManagerDataSource(db.getUrl(), db.getUsername(), db.getPassword()) {
                    @Override
                    public Connection getConnection() throws SQLException {
                        Connection realConn = super.getConnection();
                        // 动态代理包装 Connection，直接拦截 close() 方法
                        return (Connection) Proxy.newProxyInstance(
                                Connection.class.getClassLoader(),
                                new Class<?>[]{Connection.class},
                                (proxy, method, args) -> {
                                    if ("close".equals(method.getName())) {
                                        // 👉 就在这行打红点断点！
                                        printRed("💥【检测到连接关闭】MyBatis 正在调用 connection.close() 断开数据库连接！");
                                    }
                                    return method.invoke(realConn, args);
                                }
                        );
                    }
                };
            }
        }
        printRed("找不到默认数据库配置, 请检查 application-common.properties 中的 app.defaultDbId 是否正确");
        throw new IllegalStateException("【数据源初始化失败】未找到数据库配置！");
    }
}
