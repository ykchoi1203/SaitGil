package shopping.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.model.service.ShoppingService;
import shopping.model.vo.Order;

/**
 * Servlet implementation class ShoppingOrderInsertServlet
 */
@WebServlet("/orderInsert.sp")
public class ShoppingOrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingOrderInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String address = request.getParameter("address1");
		String phone = request.getParameter("phone1");
		String name = request.getParameter("name");
		String email = request.getParameter("email1");
		String[] amount = request.getParameter("amount1").split(",");
		String[] pNo = request.getParameter("pNo").split(",");
		
		String rst  = request.getParameter("rst"); 
		System.out.println(rst);
		
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		ArrayList<Order> list = new ArrayList<>();
		
		for(int i=0; i<cnt; i++) {
			Order o = new Order();
			o.setUserId(userId);
			o.setAddress(address);
			o.setPhone(phone);
			o.setName(name);
			o.setRequest(rst);
			o.setBuyAmount(Integer.parseInt(amount[i]));
			o.setpNo(Integer.parseInt(pNo[i]));
			o.setEmail(email);
		
			list.add(o);
		}
		
		int result = new ShoppingService().orderInsert(list);
		
		if(result > 0) {
			response.getWriter().print("성공");
		} else {
			response.getWriter().print("실패");
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
