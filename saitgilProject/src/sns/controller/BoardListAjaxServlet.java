package sns.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sns.model.service.SnsService;
import sns.model.vo.Board;

/**
 * Servlet implementation class BoardListAjaxServlet
 */
@WebServlet("/listAjax.bo")
public class BoardListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int startPage = page+(4*(page-1));
		int endPage = page*5;
		ArrayList<Board> bList = new ArrayList<>();
		// 1. sns 湲� 由ъ뒪�듃 珥� 媛쒖닔
		int listCount = new SnsService().getListCount();
		
		if(startPage<=listCount) {
			// 2. sns 湲� 由ъ뒪�듃 遺덈윭�삤湲�(isPublic = Y �씤 �븷�뱾)
			bList = new SnsService().selectShareList(startPage, endPage);
		}
		
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson();
		
		gson.toJson(bList, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
