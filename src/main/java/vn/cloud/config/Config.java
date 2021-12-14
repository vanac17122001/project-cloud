package vn.cloud.config;

public class Config {
	public static  String ipServer1 = "172.31.23.162"; // thay đổi ip bằng ip public trên ec2 chứa database
	public static String ipServer2 = "172.31.94.35"; // thay đổi ip bằng ip public ec2 thứ 2
	public static String ipServer3 = "172.31.31.74"; // thay đôi ip bằng ip public ec2 thứ 3
	public static String userSql = "sa"; // thay đổi tài khoản đăng nhập sql container
	public static String pasSql = "vophucson17110360"; // thay đổi mật khẩu đăng nhập sql container
	public static String privatekeyPath = "/usr/local/tomcat/webapps/vophucson_aws.pem"; // thay đổi path của key ec2
	public static String databaseName = "usercloud";
}
