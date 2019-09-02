package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageInfo;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/list.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount = new NoticeService().getListCount(); 
		
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
		
		ArrayList<Notice> list = new NoticeService().selectList(currentPage, boardLimit);
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit); 
		

		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
			
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
