package vn.cloud.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.JSchException;

import vn.cloud.config.CheckTime;
import vn.cloud.config.Config;
import vn.cloud.dao.HomeDao;
import vn.cloud.model.LoginModel;

@WebServlet(urlPatterns = { "/storage" })
public class StorageController extends HttpServlet {
	private static final long serialVersionUID = 1845435035704284950L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		LoginModel info = (LoginModel) session.getAttribute("info");
		System.out.println(info.getRole());
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
		if (info.getRole() == 0) {
			req.setAttribute("server", server);
			req.setAttribute("id", info.getId());
			String name = "user" + Integer.toString(info.getId()) + "-";
			CheckTime check = new CheckTime();
			check.checkTimeContainner(name, ec2ip);
			HomeDao hd = new HomeDao();
			List<String> listS;
			try {
				listS = hd.storage(ec2ip, info.getId());
				req.setAttribute("listS", listS);
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setHeader("Refresh", "60");
			RequestDispatcher rq = req.getRequestDispatcher("/views/Storage.jsp");
			rq.forward(req, resp);
		} else {
			resp.sendRedirect("page404");
		}
	}
}
