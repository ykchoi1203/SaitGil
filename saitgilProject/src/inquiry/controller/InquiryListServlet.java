package inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageInfo;

/**
 * Servlet implementation class InquiryListServlet
 */
@WebServlet("/list.in")
public class InquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int listCount = new InquiryService().getListCount(userId); 
		System.out.println(listCount);
		// -------- 페이징 처리 -------------// 
		int currentPage=1;     //현재페이지
		int pageLimit;       //한 페이지 하단에 보여질 페이지 수 
		int maxPage;         //전체 페이지에서 가장 마지막 페이지 
		int startPage;       //한 페이지 하단에 보여질 시작 페이지 
		int endPage;         //한 페이지 하단에 보여질 마지막 페이지 
		
	
		int boardLimit = 8; //한 페이지에 8개의 게시글이 보여질 것임 
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage")); 
		}
		
		pageLimit = 10; 
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage - 1)/pageLimit  * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1; 
		
		
		if(maxPage < endPage) {
			endPage = maxPage;   // maxPage = 13, endPage = 13 
		}
		
		ArrayList<Inquiry> list = new InquiryService().selectList(currentPage, boardLimit, userId);
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit); 
		

		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
			
		request.getRequestDispatcher("views/inquiry/inquiryListView.jsp").forward(request, response);

	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
