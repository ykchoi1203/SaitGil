package photo.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import photo.model.vo.Attachment;
import photo.model.vo.Folder;

public class FolderDao {

	private Properties prop = new Properties();

	public FolderDao() {
		String fileName = FolderDao.class.getResource("/sql/photo/photo-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertFolder(Connection conn, Folder f) {
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertFolder");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, f.getfName());
			pstmt.setString(2, f.getcCode());
			pstmt.setString(3, f.getWriter());
			pstmt.setString(4, f.getMainAttachment());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAttachment");

		try {
			for (int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, at.getcCode());
				pstmt.setString(2, at.getWriter());
				pstmt.setString(3, at.getOriginName());
				pstmt.setString(4, at.getChangeName());
				pstmt.setString(5, at.getFilePath());
				

				result = pstmt.executeUpdate();

				if (result <= 0) {
					break;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}
	
	
	public ArrayList<Folder> selectFolderList(Connection conn, String cCode) {

		ArrayList<Folder> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectFolderList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Folder(rset.getInt("fId"),
									rset.getString("fName"),
									rset.getString("main_attachment"))
							
						);
				
				/*list.add(new Folder(rset.getInt(1), rset.getInt(2), rset.getString(3)));*/
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}
	
	
	
	public ArrayList<Attachment> selectAtList(Connection conn) {
		ArrayList<Attachment> list = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAtList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {

				Attachment at = new Attachment();
				at.setfId(rset.getInt("fid"));
				at.setChangeName(rset.getString("change_name"));
				at.setFilePath(rset.getString("file_path"));

				list.add(at);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	
	public ArrayList<Attachment> selectAttachment(Connection conn, int fId) {
		ArrayList<Attachment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, fId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				// change_name, fid, atId
				Attachment at = new Attachment();
				at.setChangeName(rset.getString("change_name"));
				at.setOriginName(rset.getString("origin_name"));
				at.setfId(rset.getInt("fId"));
				at.setAtId(rset.getInt("atId"));

				list.add(at);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public Folder selectFolder(Connection conn, int fId) {
		
		Folder f = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectFolder");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			

			rset = pstmt.executeQuery();

			if (rset.next()) {
				f = new Folder(rset.getInt("fId"), rset.getString("fName"), rset.getString("main_Attachment"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return f;

	}

	public int deleteFolder(Connection conn, int fId) {
		int result=0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteFolder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public int insertAtt(Connection conn, int fId, ArrayList<Attachment> fileList) {
		
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAtt");

		try {
			for (int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, fId);
				pstmt.setString(2, at.getcCode());
				pstmt.setString(3, at.getWriter());
				pstmt.setString(4, at.getOriginName());
				pstmt.setString(5, at.getChangeName());
				pstmt.setString(6, at.getFilePath());
				

				result = pstmt.executeUpdate();

				if (result <= 0) {
					break;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

		
		
	}

	public int deleteAttachment(Connection conn, int atId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, atId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
		
	}

	public int changefName(Connection conn, Folder f) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("changefName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, f.getfName());
			pstmt.setInt(2, f.getfId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}

	public int movePhoto(Connection conn, int fId, int atId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("movePhoto");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fId);
			pstmt.setInt(2, atId);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	

	
	
}


