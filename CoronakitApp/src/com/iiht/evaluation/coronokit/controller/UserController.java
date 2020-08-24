package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.AdminDAOImpl;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;
import com.iiht.evaluation.coronokit.service.UserService;
import com.iiht.evaluation.coronokit.service.UserServiceImpl;

@WebServlet({ "/user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public void init(ServletConfig config) {
		this.userService = new UserServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String view = "";
		try {
			switch (action) {
			case "add":
				add(request, response);
				view = "adminhome.jsp";
				break;
			default:
				view = "notfound.jsp";
			}
		} catch (AdminException ex) {
			view = "errorPage.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(view);
		dispatch.forward(request, response);

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		User user = new User();
		user.setId(request.getParameter("userid"));
		user.setPassword(request.getParameter("password"));
		user.setType(request.getParameter("type"));

		try {
			userService.add(user);
		} catch (AdminException e) {
			throw e;
		}

	}
}