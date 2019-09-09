package calendar.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import calendar.model.dao.CalendarDao;
import calendar.model.vo.*;

public class CalendarDao {
	private Properties prop = new Properties();
	public CalendarDao() {
		String fileName = CalendarDao.class.getResource("/sql/calendar/calendar-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertCalendar(Connection conn, Calendar cal) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCalendar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cal.getcCode());
			pstmt.setString(2, cal.getUserId());
			pstmt.setString(3, cal.getSchedule());
			pstmt.setString(4, cal.getStartdate());
			pstmt.setString(5, cal.getEnddate());
			pstmt.setString(6, cal.getBackColor());
			pstmt.setString(7, cal.getFontColor());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public ArrayList<Calendar> getCalendarList(Connection conn, String cCode)  {
		ArrayList<Calendar> list = new ArrayList<>(); 
		String sql = prop.getProperty("calendarList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Calendar addCal = new Calendar();
				addCal.setcCode(rset.getString("c_code"));
				addCal.setUserid(rset.getString("user_id"));
				addCal.setSno(rset.getString("sno"));
				addCal.setSchedule(rset.getString("SCHEDULE"));
				addCal.setStartdate(rset.getString("STARTDATE"));
				addCal.setEnddate(rset.getString("ENDDATE"));
				addCal.setBackColor(rset.getString("BACKCOLOR"));
				addCal.setFontColor(rset.getString("FONTCOLOR"));
				list.add(addCal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	
	public int deleteCalendar(Connection conn, String sno) throws SQLException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCalendar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
