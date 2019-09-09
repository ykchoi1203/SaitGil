package shopping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import shopping.model.service.ShoppingService;

/**
 * Servlet implementation class ShoppingAddShoppingBagServlet
 */
@WebServlet("/addCart.sp")
public class ShoppingAddShoppingBagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingAddShoppingBagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		HttpSession session = request.getSession();
		Member user = (Member)session.getAttribute("loginUser");
		String userId = user.getUserId();
		int amount=1;
		if(request.getParameter("amount") == null) {
			amount=1;
		} else {
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		int result = new ShoppingService().addCart(pNo, userId, amount);
		
		if(result > 0) {
			request.setAttribute("msg", "장바구니 추가에 성공하였습니다");
			response.sendRedirect("goCart.sp");
		} else {
			request.setAttribute("msg", "장바구니 추가에 실패하였습니다");
			response.sendRedirect("goCart.sp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
