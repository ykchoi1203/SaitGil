package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import inquiry.model.vo.Inquiry;
import product.model.vo.PageInfo;
import product.model.vo.Product;

/**
 * Servlet implementation class AdminProductListServlet
 */
@WebServlet("/pList.ad")
public class AdminProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int listCount = new AdminService().getProductCount();
		
		// 페이징 처리
		int currentPage; // 현재 페이지
		int pageLimit;	// 한 페이지 하단에 보여질 페이지 갯수
		int maxPage;	// 전체페이지중 마지막페이지
		int startPage; // 한 페이지 하단에 보여질 시작 페이지
		int endPage; // 한 페이지 하단에 보여질 마지막 페이지
		int boardLimit = 9; // 한 페이지에 보여질 게시글 최대 갯수
		
		// * currentPage : 현재 페이지
		currentPage = 1;
		
		// 페이지 전환시 전달받은 현재 페이지가 있을 경우 전달받은 페이지를 currentPage로
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// * pageLimit : 한 페이지 하단에 보여질 페이지 수
		pageLimit = 10;
		
		// * maxPage : 총 페이지수
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// * startPage : 현재 페이지에 보여지는 페이징바의 시작 수
		// ex ) pageLimit : 10
		// 1, 11, 21, 31, ... 	=> n * 10 + 1
		// currentPage = 1 		=> 0 * 10 + 1
		// currentPage = 5		=> 0 * 10 + 1
		
		// n = Math.floor(((double)currentPage-1)/pageLimit)
		
		startPage = ((currentPage-1) / pageLimit) * pageLimit + 1;
		endPage = startPage + pageLimit -1;
		
		// ex) endPage = 20 , maxPage=13일 경우
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
		
		request.setAttribute("pi", pi);
		
		ArrayList<Product> list = new AdminService().selectProductList(currentPage, boardLimit);
		
		
		request.setAttribute("pList", list);
		request.getRequestDispatcher("views/admin/adminProductList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
