package vn.cloud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.JSchException;

import vn.cloud.config.Config;
import vn.cloud.dao.HomeDao;


@WebServlet(urlPatterns = { "/connect" })
public class ConnectController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		HomeDao hd = new HomeDao();
		String port = hd.port(name);
		req.setAttribute("port",port);
		String publicip = "";
		String ec2ip ="";
		String server = req.getParameter("server");
		if(server.equals("1"))
		{
			ec2ip = Config.ipServer1;
		}
		if(server.equals("2"))
		{
			ec2ip = Config.ipServer2;
		}
		if(server.equals("3"))
		{
			ec2ip = Config.ipServer3;
		}
		try {
			publicip = hd.publicIprealtime(ec2ip);
			req.setAttribute("publicip", publicip);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rq = req.getRequestDispatcher("/views/connect.jsp");
		rq.forward(req, resp);
	}

}
