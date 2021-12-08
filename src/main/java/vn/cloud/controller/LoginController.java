package vn.cloud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import vn.cloud.dao.LoginDao;
import vn.cloud.model.LoginModel;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher rq = req.getRequestDispatcher("/views/login.jsp");
		rq.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			resp.setContentType("text/htm");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			String name = req.getParameter("username");
			String pass = req.getParameter("pass");
			System.out.println(pass);
			System.out.println(name);
			LoginDao loginDao = new LoginDao();
			LoginModel info = loginDao.Login(name, pass);
			System.out.println(info);
			if (info == null) {
				resp.setContentType("text/htm");
				resp.setCharacterEncoding("UTF-8");
				req.setCharacterEncoding("UTF-8");
				req.setAttribute("mess", 1);
				RequestDispatcher rq = req.getRequestDispatcher("/views/login.jsp");
				rq.forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("info", info);
				if (info.getRole() == 0) {
					resp.sendRedirect("home?server=1");
				} else {
					resp.sendRedirect("adminhome");
				}
			}

		} catch (Exception e) {

		}
	}
	}

