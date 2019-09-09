package shopping.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;
import product.model.vo.ProductAttachment;
import shopping.model.service.ShoppingService;

/**
 * Servlet implementation class ShoppingProductDetailViewServlet
 */
@WebServlet("/detail.sp")
public class ShoppingProductDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingProductDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		
		Product p = new ShoppingService().selectProduct(pNo);
		
		ArrayList<ProductAttachment> pList = new AdminService().selectProductAttachment(pNo);
		ArrayList<Product> goodpCount = new ShoppingService().selectgoodpCount();
		ArrayList<ProductAttachment> paList = new AdminService().mainProductPicList();
		
		request.setAttribute("p", p);
		request.setAttribute("pList", pList);
		request.setAttribute("paList", paList);
		request.setAttribute("goodpCount", goodpCount);
		
		
		request.getRequestDispatcher("views/shopping/product-detail.jsp").forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
