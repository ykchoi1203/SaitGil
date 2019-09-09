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
 * Servlet implementation class GoShoppingMainServlet
 */
@WebServlet("/goMain.sp")
public class GoShoppingMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoShoppingMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> pList = new ShoppingService().selectProductList();
		
		ArrayList<Product> goodSeller = new ShoppingService().selectgoodSeller();
		
		ArrayList<Product> goodpCount = new ShoppingService().selectgoodpCount();
		
		// 2. 사진 리스트들 불러오기
		ArrayList<ProductAttachment> paList = new AdminService().mainProductPicList();
		
		request.setAttribute("pList", pList);
		request.setAttribute("paList", paList);
		request.setAttribute("goodSeller", goodSeller);
		request.setAttribute("goodpCount", goodpCount);
		
		request.getRequestDispatcher("views/shopping/shoppingMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
