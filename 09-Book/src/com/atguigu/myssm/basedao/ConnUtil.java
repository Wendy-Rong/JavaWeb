package com.atguigu.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 使用Druid数据库连接池技术
     */
    private static DataSource source;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConn(){
        try {
//            //1.加载驱动
//            Class.forName(DRIVER);
//            //2.通过驱动管理器获取连接对象
//            return DriverManager.getConnection(URL,USER,PWD);

            return source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn==null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get() ;
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn==null){
            return ;
        }
        if(!conn.isClosed()){
            conn.close();
            //threadLocal.set(null);
            threadLocal.remove();
        }
    }
}
