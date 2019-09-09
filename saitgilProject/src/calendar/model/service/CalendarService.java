package calendar.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import calendar.model.dao.CalendarDao;
import calendar.model.vo.Calendar;
public class CalendarService {
	
	public int insertCalendar(Calendar cal) {
		Connection conn = getConnection();
		
		int result = new CalendarDao().insertCalendar(conn, cal);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Calendar> getCalendarList(String cCode) throws Exception {
		Connection conn = getConnection();

		ArrayList<Calendar>	arr = new CalendarDao().getCalendarList(conn, cCode);

		close(conn);
		
		return arr;
	}

	public int deleteCalendar(String sno) throws Exception {
		int result = 0;
		
		Connection conn = getConnection();
		
		result = new CalendarDao().deleteCalendar(conn, sno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
}
