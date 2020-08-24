package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.Kit;
import com.iiht.evaluation.coronokit.service.KitService;
import com.iiht.evaluation.coronokit.service.KitServiceImpl;
import com.iiht.evaluation.coronokit.service.ProductService;
import com.iiht.evaluation.coronokit.service.ProductServiceImpl;

/**
 * Servlet implementation class KitController
 */
@WebServlet("/kit")
public class KitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitService kitService;
	private ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KitController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		kitService = new KitServiceImpl();
		productService = new ProductServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("action");
		String view = "";
		try {
			switch (method) {
			case "add":
				addProductToKit(request, response);
				request.setAttribute("products", productService.list());
				view = "listproducts.jsp";
				break;
			case "update":
				break;
			case "delete":
				break;
			case "show":
				Kit kit = showKit(request, response);
				request.setAttribute("kit", kit);
				view = "showkit.jsp";
				break;
			case "placeorder":
				String orderId = placeOrder(request, response);
				request.setAttribute("orderid", orderId);
				view = "ordersummary.jsp";
				break;
			default:
			}
		} catch (AdminException e) {
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

	private void addProductToKit(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String productId = request.getParameter("productid");
		String kitId = (String) request.getSession().getAttribute("visitorid");
		try {
			kitService.addProductToKit(kitId, productId);
		} catch (AdminException e) {
			throw e;
		}

	}

	private Kit showKit(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String kitId = (String) request.getSession().getAttribute("visitorid");
		try {
			return kitService.showKit(kitId);
		} catch (AdminException e) {
			throw e;
		}
	}

	private String placeOrder(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String kitId = (String) request.getSession().getAttribute("visitorid");
		String delAddress = request.getParameter("address");
		try {
			return kitService.placeOrder(kitId, delAddress);
		} catch (AdminException e) {
			throw e;
		}
	}

}
