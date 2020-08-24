package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;
import com.iiht.evaluation.coronokit.service.LoginService;
import com.iiht.evaluation.coronokit.service.LoginServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		loginService = new LoginServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("action");
		String view = "";

		if ("login".equalsIgnoreCase(method))
			try {
				boolean isLoginSuccess = login(request.getParameter("loginid"), request.getParameter("password"));
				if (isLoginSuccess) {
					/*
					 * HttpSession session = request.getSession(); session.setAttribute("userId",
					 * user.getId());
					 */
					view = "adminhome.jsp";
				} else {
					throw new AdminException("Admin user not found");
				}
			} catch (AdminException e) {
				e.printStackTrace();
				view = "errorPage.jsp";
			}
		RequestDispatcher dispatch = request.getRequestDispatcher(view);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private User doLogin(String userId, String password) throws AdminException {
		User user = loginService.login(userId, password);
		return user;
	}

	private boolean login(String userId, String password) throws AdminException {
		if ("admin".equalsIgnoreCase(userId) && "admin".equalsIgnoreCase(password))
			return true;
		return false;

	}

}
