package chat.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import chat.model.vo.Chat;
import member.model.dao.MemberDao;

import static common.JDBCTemplate.*;

public class ChatDao {

	private Properties prop = new Properties();
	
	public ChatDao() {
		String fileName = MemberDao.class.getResource("/sql/chat/chat-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Chat> getChatListByRecent(String fromID, String toID, int number) {
		ArrayList<Chat> chatList = new ArrayList<Chat>();;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getChatListByRecent"); 
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			//pstmt.setInt(5, number);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Chat chat = new Chat(); 
				chat.setChatID(rset.getInt("chatID"));
				chat.setFromID(rset.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				chat.setToID(rset.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rset.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rset.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType="오후";
					chatTime -= 12;
				}
				chat.setChatTime(rset.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rset.getString("chatTime").substring(14, 16) + " ");
				
				chatList.add(chat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset); 
			close(pstmt);
			close(conn);
		}
		
		return chatList;
	}
	
	
	
	
	public ArrayList<Chat> getChatListByID(String fromID, String toID, String listType) {
		ArrayList<Chat> chatList = new ArrayList<>();;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getChatListById"); 
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(listType));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Chat chat = new Chat(); 
				chat.setChatID(rset.getInt("chatID"));
				chat.setFromID(rset.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				chat.setToID(rset.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rset.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rset.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType="오후";
					chatTime -= 12;
				}
				chat.setChatTime(rset.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rset.getString("chatTime").substring(14, 16) + " ");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset); 
			close(pstmt);
			close(conn);
		}
		
		
		return chatList;
	}
	
	
	
	public int submit(Connection conn, String fromID, String toID, String chatContent, String cCode) {
		int result = -1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("submit");
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			pstmt.setString(2, fromID);
			pstmt.setString(3, toID);
			pstmt.setString(4, chatContent);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
