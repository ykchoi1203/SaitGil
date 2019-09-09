package chat.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;

import chat.model.dao.ChatDao;

public class ChatService {
	
	
	public int submit(String fromID, String toID, String chatContent, String cCode) {
		Connection conn = getConnection();
		
		int result = new ChatDao().submit(conn, fromID, toID, chatContent, cCode);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
