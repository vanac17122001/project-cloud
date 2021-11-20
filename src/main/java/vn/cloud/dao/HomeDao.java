package vn.cloud.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import vn.cloud.connection.DBconnect;
import vn.cloud.model.DetailModel;
import vn.cloud.model.ImageModel;

public class HomeDao {
	String ip = "172.31.23.162"; // thầy thay đổi địa chỉ ip public ec2
	String privateKeyPath = "/home/ubuntu/vophucson_aws.pem"; // thay đổi path của tệp khóa .pem của ec2
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<DetailModel> getDetail(String name) throws JSchException, IOException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		InputStream in = channel.getInputStream();
		((ChannelExec) channel).setCommand("docker ps -a --filter \"" + "name" + "=" + name + "\"");
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		List<DetailModel> list = new ArrayList<DetailModel>();
		while ((line = reader.readLine()) != null) {
			ArrayList<String> arr = new ArrayList<String>();
			String test = line.replaceAll("\\s\\s+", ",");
			String[] words = test.split(",");
			for (String w : words) {
				arr.add(w);
			}
			if (arr.size() == 7) {
				list.add(new DetailModel(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5),
						arr.get(6)));
			}
			if (arr.size() == 6) {
				list.add(new DetailModel(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), " ", arr.get(5)));
			}
		}
		channel.disconnect();
		session.disconnect();

		return list;
	}

	public void createContainer(String name, String os, String ram, String cpu, String port)
			throws JSchException, IOException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("docker create --name " + name + " " + " --memory=\"" + ram + "\""
				+ " --cpus=\"" + cpu + "\" -p " + port + ":22 " + os);
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);
		channel.disconnect();
		session.disconnect();

	}

	public void insertCreate(String cname, int id, String ram, String cpu, String port) {
		String sql = "insert into containers values(?,?,?,?,?,?)";
		try {
			// kết nối sql
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			ps.setInt(2, id);
			ps.setString(3, port);
			ps.setString(4, ram);
			ps.setString(5, cpu);
			ps.setString(6, "created and still in use");
			ps.executeUpdate();
		} catch (Exception e) {

		}

	}

	public void updateRemove(String cname) {
		String sql = "update containers set status = 'Deleted' where  cName = ?";
		try {
			// kết nối sql
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

	public String maxPort() {
		String sql = "select max(port) + 1 as max from containers";
		try {
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {

		}
		return "1000";
	}

	public String port(String name) {
		String sql = "select port from containers where cName = ?";
		try {
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {

		}
		return null;
	}

	public void startContainer(String cid) throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("docker start " + cid);
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);
		channel.disconnect();
		session.disconnect();

	}

	public void stopContainer(String cid) throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		try {
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand("docker stop " + cid);
			channel.connect();
			((ChannelExec) channel).setErrStream(System.err);
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void remvoContainer(String cid) throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("docker rm " + cid);
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);
		channel.disconnect();
		session.disconnect();

	}

	public List<DetailModel> getAllContainer() throws JSchException, IOException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		InputStream in = channel.getInputStream();
		((ChannelExec) channel).setCommand("docker ps -a");
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		List<DetailModel> list = new ArrayList<DetailModel>();
		while ((line = reader.readLine()) != null) {
			ArrayList<String> arr = new ArrayList<String>();
			String test = line.replaceAll("\\s\\s+", ",");
			String[] words = test.split(",");
			for (String w : words) {
				arr.add(w);
			}
			if (arr.size() == 7) {
				list.add(new DetailModel(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5),
						arr.get(6)));
			}
			if (arr.size() == 6) {
				list.add(new DetailModel(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), " ", arr.get(5)));
			}
		}
		channel.disconnect();
		session.disconnect();

		return list;
	}

	public String publicIprealtime() throws JSchException, IOException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		InputStream in = channel.getInputStream();
		((ChannelExec) channel).setCommand(" dig +short myip.opendns.com @resolver1.opendns.com");
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			return line;
		}
		channel.disconnect();
		session.disconnect();

		return null;
	}
	public void createImage(String name,String containerId) throws JSchException 
	{
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("docker commit " + containerId +" " + name + ":image");
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);
		channel.disconnect();
		session.disconnect();
	}
	public List<ImageModel> listImage(String name) throws JSchException, IOException
	{
		List<ImageModel> list = new ArrayList<ImageModel>();
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		InputStream in = channel.getInputStream();
		((ChannelExec) channel).setCommand("docker images --filter \"" +"reference=" + name + "*\"");
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			ArrayList<String> arr = new ArrayList<String>();
			String test = line.replaceAll("\\s\\s+", ",");
			String[] words = test.split(",");
			for (String w : words) {
				arr.add(w);
			}
			list.add(new ImageModel(arr.get(0),arr.get(1),arr.get(2),arr.get(3),arr.get(4)));
			channel.disconnect();
			session.disconnect();
		}
		return list;
	}
	public void removeImage(String imageId) throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath);
		Session session = jsch.getSession("ubuntu", ip, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("docker rmi " + imageId);
		channel.connect();
		((ChannelExec) channel).setErrStream(System.err);
		channel.disconnect();
		session.disconnect();

	}
	public void setTime (Date startime,String cName)
	{
		String sql = "update containers set time = ? where cName = ?";
		try {
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(2, cName);
			ps.setTimestamp(1, new Timestamp(startime.getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Date checktime(String cName)
	{
		String sql = "select time from containers where cName = ?";
		try {
			conn = new DBconnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getTimestamp(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void main(String[] args) throws IOException, JSchException {
		HomeDao p = new HomeDao();
		Date date = p.checktime("user3-Ubuntu-1019");
		System.out.println(date.getTime());
	}
}
