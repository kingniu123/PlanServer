package db;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
/*
 * 数据库工具类，获得数据库连接,单例
 */
public class DBUtil {
	public static final String PASSWORD = "root";  
    public static final String USERINFO = "root";
    private static Connection conn;
      
    public synchronized static Connection getConnect() {  
        String url = "jdbc:mysql://localhost:3306/planApp_db?characterEncoding=utf8"; // 数据库的Url
        
        try {  
            Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法  
            if(conn == null) conn = (Connection) DriverManager.getConnection(url, USERINFO, PASSWORD);
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            System.out.println("SQLException: " + e.getMessage());  
            System.out.println("SQLState: " + e.getSQLState());  
            System.out.println("VendorError: " + e.getErrorCode());  
        }  
        return conn;  
    }  
}
