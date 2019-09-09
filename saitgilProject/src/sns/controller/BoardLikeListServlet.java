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
 * Servlet implementation class BoardLikeListServlet
 */
@WebServlet("/likeList.bo")
public class BoardLikeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		// 1. sns 湲� 由ъ뒪�듃 遺덈윭�삤湲�(isPublic = Y �씤 �븷�뱾)
		ArrayList<Board> bList = new SnsService().selectLikeList(1, 5, userId);
		
		// 2. sns 湲� �궗吏� 由ъ뒪�듃 遺덈윭�삤湲�
		ArrayList<Photo> phList = new SnsService().selectPhList();
		
		// 3. �뙎湲� 由ъ뒪�듃 遺덈윭�삤湲� (bNo�씠 isPublic=Y �씤 �븷�뱾�쓽 bNo�씤 �븷�뱾)
		ArrayList<Comment> comList = new SnsService().selectComList();
		
		ArrayList<Board> likeList = new SnsService().selectLike();
		
		ArrayList<Board> scrapList = new SnsService().selectScrap();
		
		request.setAttribute("bList", bList);
		request.setAttribute("phList", phList);
		request.setAttribute("comList", comList);
		request.setAttribute("likeList", likeList);
		request.setAttribute("scrapList", scrapList);
		
		request.getRequestDispatcher("views/sns/boardLikeListView.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
