package vn.cloud.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import vn.cloud.model.DetailModel;;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		LoginModel info = (LoginModel) session.getAttribute("info");
		if(info.getRole() == 0)
		{
			String name = "user" + Integer.toString(info.getId()) + "-";
			  HomeDao p = new HomeDao(); 
			  List<DetailModel> list; 
			  try { 
				  list =p.getDetail(name);
			  Date nowTime = new Date();
			  for(DetailModel detail : list)
			  {
				  Date pastTime = p.checktime(detail.getName());
				  if(pastTime !=null)
				  {
					  if(TimeUnit.MILLISECONDS.toSeconds(nowTime.getTime() - pastTime.getTime()) >= 120)
					  {
						  p.stopContainer(detail.getId());
					  }
				  }
			  }
			  req.setAttribute("listC", list);
			  } 
			  catch (JSchException e) 
			  {
			  e.printStackTrace(); 
			  }
			resp.setHeader("Refresh", "60");
			RequestDispatcher rq = req.getRequestDispatcher("/views/home.jsp");
			rq.forward(req, resp);
		}
		else
		{
			resp.sendRedirect("page404");
		}
	}
}
