package vn.cloud.config;

public class Config {
	public static  String ipServer1 = "172.31.9.188"; // thay đổi ip bằng ip public trên ec2 chứa database
	public static String ipServer2 = "172.31.3.196"; // thay đổi ip bằng ip public ec2 thứ 2
	public static String ipServer3 = "172.31.3.93"; // thay đôi ip bằng ip public ec2 thứ 3
	public static String userSql = "sa"; // thay đổi tài khoản đăng nhập sql container
	public static String pasSql = "caoanhvan@19133067"; // thay đổi mật khẩu đăng nhập sql container
	public static String privatekeyPath = "/home/ubuntu/key-cloud--main/DoAn_Docker_Ubuntu.pem"; // thay đổi path của key ec2
	public static String databaseName = "MyDb0";
}
