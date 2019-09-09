package calendar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
import member.model.vo.Member;
import shareFile.model.vo.*;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/insert.ca")
public class CalendarInsertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String startdatetime = request.getParameter("startdatetime"); // "10", "20", ...
		String enddatetime = request.getParameter("enddatetime");
		String schedule = request.getParameter("schedule");
		String cCode = ((ShareFile)session.getAttribute("sf")).getcCode();
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		
	
		Calendar cal = new Calendar();
		cal.setStartdate(startdatetime);
		cal.setEnddate(enddatetime);
		cal.setSchedule(schedule);
		cal.setUserId(userId);
		cal.setcCode(cCode);

		
		try {
			result = new CalendarService().insertCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JSONObject json = new JSONObject();
		
		json.put("result", result);
		response.setContentType("application/json; charset=utf-8");
		
		response.getWriter().print(json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
