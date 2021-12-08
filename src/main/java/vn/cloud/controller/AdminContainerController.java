package vn.cloud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.JSchException;

import vn.cloud.config.Config;
import vn.cloud.dao.HomeDao;
import vn.cloud.model.DetailModel;

@WebServlet(urlPatterns = {"/admincontainer"})
public class AdminContainerController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HomeDao hd = new HomeDao();
		List<DetailModel> list = new ArrayList<DetailModel>();
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
			list = hd.getAllContainer(ec2ip);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("listC", list);
		req.setAttribute("server", server);
		RequestDispatcher rq = req.getRequestDispatcher("/views/admincontainer.jsp");
		rq.forward(req, resp);
	}
	

}
