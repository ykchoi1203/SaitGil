package photoAlbum.model.dao;

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

import photoAlbum.model.vo.Attachment;
import photoAlbum.model.vo.Folder;

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
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				

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
	
	
	public ArrayList<Folder> selectFolderList(Connection conn) {

		ArrayList<Folder> list = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectFolderList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				list.add(new Folder(rset.getInt("fId"),
									rset.getString("fName")));
				/*list.add(new Folder(rset.getInt(1), rset.getInt(2), rset.getString(3)));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
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
				// change_name, fid
				Attachment at = new Attachment();
				at.setChangeName(rset.getString("change_name"));
				at.setfId(rset.getInt("fid"));

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
				f = new Folder(rset.getInt("fId"), rset.getString("fName"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return f;

	}
	
	
}


