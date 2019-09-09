package photo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.service.FolderService;
import photo.model.vo.Folder;

/**
 * Servlet implementation class PhotoMoveServlet2
 */
@WebServlet("/realMovePhoto.ph")
public class PhotoMoveServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoMoveServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fId = Integer.parseInt(request.getParameter("fId"));
		int atId = Integer.parseInt(request.getParameter("atId"));
		
		int result = new FolderService().movePhoto(fId, atId);
		
		if(result >0) {
			response.sendRedirect("detail.ph?fId=" + fId);
		}else {
			request.getSession().setAttribute("msg", "폴더이동 실패");
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
