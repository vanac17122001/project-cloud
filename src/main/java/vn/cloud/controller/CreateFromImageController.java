package vn.cloud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.JSchException;

import vn.cloud.dao.HomeDao;
import vn.cloud.model.LoginModel;
@WebServlet(urlPatterns = {"/createfromimage"})
public class CreateFromImageController extends HttpServlet{
	private static final long serialVersionUID = 5092259573084310288L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		LoginModel info = (LoginModel) session.getAttribute("info");
		String os = req.getParameter("os");
		if(info.getRole() == 0)
		{
			req.setAttribute("os", os);
			RequestDispatcher rq = req.getRequestDispatcher("/views/createfromimage.jsp");
			rq.forward(req, resp);
		}
		else
		{
			resp.sendRedirect("page404");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String os = req.getParameter("os");
		String ram  = req.getParameter("ram");
		String cpu = req.getParameter("cpu");
		HomeDao hd = new HomeDao();
		HttpSession session = req.getSession();
		LoginModel info = (LoginModel) session.getAttribute("info");
		String port = hd.maxPort();
		String cname = "user" +Integer.toString(info.getId()) +"-" + "yourImage" + "-" + port; 
		try {
			hd.createContainer(cname,os, ram, cpu, port);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		hd.insertCreate(cname, info.getId(), ram, cpu, port);
		resp.sendRedirect("home");
	}

}
