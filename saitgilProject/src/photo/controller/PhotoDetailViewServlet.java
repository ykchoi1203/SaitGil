package photo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.service.FolderService;
import photo.model.vo.Attachment;
import photo.model.vo.Folder;

/**
 * Servlet implementation class PhotoDetailViewServelt
 */
@WebServlet("/detail.ph")
public class PhotoDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fId = Integer.parseInt(request.getParameter("fId"));
		
		Folder f = new FolderService().selectFolder(fId);
		
		ArrayList<Attachment> fileList = new FolderService().selectAttachment(fId);
		
		/*if(f != null) {*/
			request.setAttribute("fId", fId);
			request.setAttribute("f", f);
			request.setAttribute("fileList", fileList);
			request.getRequestDispatcher("views/photo/photoDetailView.jsp").forward(request, response);
		/*}else {
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
