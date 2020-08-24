package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.ProductService;
import com.iiht.evaluation.coronokit.service.ProductServiceImpl;

/**
 * Servlet implementation class productController
 */
@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
	}

	@Override
	public void init() throws ServletException {
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
		ProductMaster product = null;

		try {
			switch (method) {
			case "add":
				add(request, response);
				view = "listproducts.jsp";
				break;
			case "update":
				update(request, response);
				view = "listproducts.jsp";
				break;
			case "delete":
				delete(request, response);
				view = "listproducts.jsp";
				break;
			case "list":
				List<ProductMaster> products = list();
				String type = request.getParameter("type");
				request.setAttribute("products", products);
				if ("visitor".equalsIgnoreCase(type)) {
					request.getSession().setAttribute("visitorid", generateVisitorId());
				}
				request.getSession().setAttribute("type", type);
				view = "listproducts.jsp";
				break;
			case "order":
				product = load(request, response);
				request.setAttribute("product", product);
				view = "order.jsp";
				break;
			case "productdetails":
				product = load(request, response);
				request.setAttribute("product", product);
				view = "productdetails.jsp";
				break;
			default:
				list();
				view = "listproducts.jsp";
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

	private void add(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String id = request.getParameter("productid");
		String name = request.getParameter("productname");
		String cost = request.getParameter("productcost");
		String description = request.getParameter("productdescription");

		// populating to the productmaster object
		ProductMaster product = new ProductMaster();
		product.setId(Integer.valueOf(id));
		product.setProductName(name);
		product.setCost(Integer.valueOf(cost));
		product.setProductDescription(description);

		try {
			productService.add(product);
		} catch (AdminException e) {
			throw e;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String id = request.getParameter("productid");
		String name = request.getParameter("productname");
		String cost = request.getParameter("productcost");
		String description = request.getParameter("productdescription");

		// populating to the productmaster object
		ProductMaster product = new ProductMaster();
		product.setId(Integer.valueOf(id));
		product.setProductName(name);
		product.setCost(Integer.valueOf(cost));
		product.setProductDescription(description);

		try {
			productService.update(product);
		} catch (AdminException e) {
			throw e;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String ids = request.getParameter("ids");
		try {
			productService.delete(ids);
		} catch (AdminException e) {
			throw e;
		}

	}

	private ProductMaster load(HttpServletRequest request, HttpServletResponse response) throws AdminException {
		String id = request.getParameter("productid");
		try {
			return productService.load(id);
		} catch (AdminException e) {
			throw e;
		}

	}

	private List<ProductMaster> list() throws AdminException {
		try {
			return productService.list();
		} catch (AdminException e) {
			throw e;
		}
	}

	public String generateVisitorId() {
		return UUID.randomUUID().toString();
	}

}
