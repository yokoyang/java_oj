package myThread.myThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    //将需要被多线程访问的属性使用ThreadLocal变量来定义
    private static final ThreadLocal<Connection> dbConnectionLocal = new ThreadLocal<>() {
        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection("", "", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public Connection getConnection() {
        return dbConnectionLocal.get();
    }
}