package admin.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static common.JDBCTemplate.*;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import product.model.vo.Product;
import product.model.vo.ProductAttachment;

public class AdminDao {
	private Properties prop = new Properties();
	
	public AdminDao() {
		String fileName = MemberDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Member> selectMemberList(Connection conn) {
		ArrayList<Member> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("memberList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				Member m = new Member(rset.getString("USER_ID"),
									  rset.getString("USER_NAME"),
									  rset.getString("BIRTH"),
									  rset.getString("PHONE"),
									  rset.getString("EMAIL"),
									  rset.getString("GENDER"),
									  rset.getString("ADDRESS"),
									  rset.getDate("JOIN_DATE"),
									  rset.getInt("REPORTS"),
									  rset.getString("STATUS"),
									  rset.getString("c_Code"));
				list.add(m);
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

	public String findPartnerId(Connection conn, String userId) {
		String partnerId = "";
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String sql = prop.getProperty("findPartnerId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				partnerId = rset.getString("user_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return partnerId;
	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deletePartnerCCode(Connection conn, String partnerId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partnerId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Product> selectProductList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("productList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				Product p = new Product(rset.getInt("PNO"),
									  rset.getString("PNAME"),
									  rset.getInt("PRICE"),
									  rset.getInt("AMOUNT"),
									  rset.getString("CONTENTS"),
									  rset.getInt("PURCHASE"),
									  rset.getString("URL"),
									  rset.getString("CATEGORY"));
				list.add(p);
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

	public int insertProduct(Connection conn, Product p) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getAmount());
			pstmt.setString(4, p.getContents());
			pstmt.setString(5, p.getCategory());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertProductAttachment(Connection conn, ArrayList<ProductAttachment> fileList) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			
			for(int i=0; i<fileList.size(); i++) {
				ProductAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				
				result = pstmt.executeUpdate();
				
				if(result <= 0) {
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Product selectProduct(Connection conn, int pNo) {
		Product p = new Product();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				p.setpNo(pNo);
				p.setpName(rset.getString("pName"));
				p.setPrice(rset.getInt("PRICE"));
				p.setAmount(rset.getInt("AMOUNT"));
				p.setContents(rset.getString("CONTENTS"));
				p.setCategory(rset.getString("CATEGORY"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public ArrayList<ProductAttachment> selectProductAttachment(Connection conn, int pNo) {
		ArrayList<ProductAttachment> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				ProductAttachment at = new ProductAttachment();
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
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
}
