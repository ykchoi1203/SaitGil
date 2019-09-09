package inquiry.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class InquiryInsertServlet
 */
@WebServlet("/insert.in")
public class InquiryInsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String title = request.getParameter("title"); //문의제목
      String writer = request.getParameter("writer"); //이름
      String content = request.getParameter("content"); //문의 내용
      String userId = request.getParameter("userId"); //아이디
      
      Inquiry i = new Inquiry(userId, title, content, writer);
      
      int result = new InquiryService().insertInquiry(i);
      
      if(result > 0) {
         response.sendRedirect("list.in?userId=" + userId);
      } else {
         request.setAttribute("msg", "문의사항 등록 실패");
         request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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