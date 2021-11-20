package vn.cloud.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	String serverName = "172.31.23.162"; // thầy thay đổi địa chỉ ip public của ec2
	String dbName = "usercloud"; // tên database đã import
	String portNumber = "1433"; 
	String instance = "";
	String userID = "sa"; //tài khoản kết nối sql
	String password = "vophucson17110360"; // mật khẩu tài khoản kết nối sql
	public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName;
        if(instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    } 	
	public static void main(String[] args) {
		try {
			System.out.println(new DBconnect().getConnection());
	}catch(Exception e) {
		}
	}

}
