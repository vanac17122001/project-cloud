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

import vn.cloud.dao.HomeDao;
import vn.cloud.model.ImageModel;
import vn.cloud.model.LoginModel;

@WebServlet(urlPatterns = { "/image" })
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1329619935494948047L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		LoginModel info = (LoginModel) session.getAttribute("info");
		if (info.getRole() == 0) {
			String name = "user" + Integer.toString(info.getId()) + "-";
			HomeDao p = new HomeDao();
			List<ImageModel> list;
			try {
				list = p.listImage(name);
				req.setAttribute("listI", list);
			} catch (JSchException e) {
				e.printStackTrace();
			}

			RequestDispatcher rq = req.getRequestDispatcher("/views/image.jsp");
			rq.forward(req, resp);
		} else {
			resp.sendRedirect("page404");
		}
	}

}
