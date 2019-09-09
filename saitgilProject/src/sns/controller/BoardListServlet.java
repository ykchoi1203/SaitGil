package sns.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sns.model.service.SnsService;
import sns.model.vo.Board;
import sns.model.vo.Comment;
import sns.model.vo.Photo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. sns 湲� 由ъ뒪�듃 遺덈윭�삤湲�(isPublic = Y �씤 �븷�뱾)
		ArrayList<Board> bList = new SnsService().selectShareList(1, 5);
		
		// 2. sns 湲� �궗吏� 由ъ뒪�듃 遺덈윭�삤湲�
		ArrayList<Photo> phList = new SnsService().selectSharePhList();
		
		// 3. �뙎湲� 由ъ뒪�듃 遺덈윭�삤湲� (bNo�씠 isPublic=Y �씤 �븷�뱾�쓽 bNo�씤 �븷�뱾)
		ArrayList<Comment> comList = new SnsService().selectShareComment();
		
		ArrayList<Board> likeList = new SnsService().selectLike();
		
		ArrayList<Board> scrapList = new SnsService().selectScrap();
		
		request.setAttribute("bList", bList);
		request.setAttribute("phList", phList);
		request.setAttribute("comList", comList);
		request.setAttribute("likeList", likeList);
		request.setAttribute("scrapList", scrapList);
		
		request.getRequestDispatcher("views/sns/boardListView.jsp").forward(request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
