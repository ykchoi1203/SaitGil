package photo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photo.model.service.FolderService;
import photo.model.vo.Attachment;
import photo.model.vo.Folder;
import member.model.vo.Member;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet("/list.ph")
public class FolderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cCode = ((Member)session.getAttribute("loginUser")).getcCode();
		String move = request.getParameter("move");
		String atTest = request.getParameter("atId");
		System.out.println(move);
		int atId;
		if(atTest == null) {
			atId = 0;
		} else {
			atId = Integer.parseInt(request.getParameter("atId"));
		}
		ArrayList<Folder> folderList = new FolderService().selectFolderList(cCode);
		
		ArrayList<Attachment> atList = new FolderService().selectAtList();
		
		request.setAttribute("folderList", folderList);
		request.setAttribute("atList", atList);
		request.setAttribute("move", move);
		request.setAttribute("atId", atId);
		request.getRequestDispatcher("views/photo/folderListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
