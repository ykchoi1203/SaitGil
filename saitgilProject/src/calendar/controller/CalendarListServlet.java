package calendar.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
import member.model.vo.Member;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/list.ca")
public class CalendarListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		String cCode = loginUser.getcCode();

	

			ArrayList<Calendar> arr = null;
			try {
				arr = new CalendarService().getCalendarList(cCode);
			} catch (Exception e) {
				e.printStackTrace();
			}


		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		if( arr != null && arr.size() > 0) {
			for(Calendar cal : arr) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sno", cal.getSno());
				jsonObject.put("schedule", cal.getSchedule());
				jsonObject.put("startdate", cal.getStartdate());
				jsonObject.put("enddate", cal.getEnddate());
				jsonObject.put("allDay", cal.getAllday());
				jsonObject.put("url", cal.getUrl());
				jsonObject.put("userId", cal.getUserid());
				jsonObject.put("backColor", cal.getBackColor());
				jsonObject.put("fontColor", cal.getFontColor());
				jsonArr.add(jsonObject);
			}
			
		}
		json.put("calList", jsonArr);
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
