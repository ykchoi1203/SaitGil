package photoAlbum.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoAlbum.model.service.FolderService;
import photoAlbum.model.vo.Attachment;
import photoAlbum.model.vo.Folder;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet("/list.ph")
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Folder> folderList = new FolderService().selectFolderList();
		
		// 2. 사진 리스트들 불러오기(Attachment 테이블에 담겨있는 애들 filelevel=0)
		ArrayList<Attachment> atList = new FolderService().selectAtList();
		
		request.setAttribute("folderList", folderList);
		request.setAttribute("atList", atList);
		
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
