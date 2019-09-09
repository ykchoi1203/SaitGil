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
 * Servlet implementation class FolderUpdateServlet
 */
@WebServlet("/updateFolder.fo")
public class FolderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int fId = Integer.parseInt(request.getParameter("fId"));
		String fName = request.getParameter("fName");
		String mainAttachment = request.getParameter("mainAttachment");
		Folder f = new Folder(fId, fName, mainAttachment);
		
		Folder changeFolder = new FolderService().changefName(f);
		
		if(changeFolder != null) {
			
			request.getSession().setAttribute("FolderList", fName);
			
			response.sendRedirect("list.ph");
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
