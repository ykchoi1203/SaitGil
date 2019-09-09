package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import inquiry.model.vo.Inquiry;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import notice.model.vo.Notice;
import product.model.vo.Order;
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

	public ArrayList<Product> selectProductList(Connection conn, int currentPage, int boardLimit) {
		ArrayList<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("productList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (currentPage - 1) * boardLimit + 1;
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
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
			close(pstmt);
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
				pstmt.setInt(4, at.getFileLevel());
				
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
				at.setFileLevel(rset.getInt("FILE_LEVEL"));
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

	public int deleteNotice(Connection conn, int nNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> selectNoticeList(Connection conn) {
		ArrayList<Notice> list = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Notice n = new Notice(rset.getInt("NOTICE_NO"),
									  rset.getString("NOTICE_TITLE"),
									  rset.getString("NOTICE_CONTENT"),
									  rset.getString("NOTICE_WRITER"),
									  rset.getDate("NOTICE_DATE"));
				list.add(n);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}

	public int insertNotice(Connection conn, Notice n) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice selectNotice(Connection conn, int nNo) {
		Notice n = new Notice();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}

	public int editNotice(Connection conn, Notice n) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("editNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Order> selectOrderList(Connection conn) {
		ArrayList<Order> list = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOrderList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Order o = new Order(rset.getInt("BUY_CODE"),
									  rset.getString("USER_ID"),
									  rset.getString("USER_NAME"),
									  rset.getString("NAME"),
									  rset.getInt("PNO"),
									  rset.getString("PNAME"),
									  rset.getDate("BUY_DATE"),
									  rset.getInt("BUY_AMOUNT"),
									  rset.getString("BUY_STATUE"),
									  rset.getString("DELIVERY_PAY"),
									  rset.getString("SHIPPING"),
									  rset.getString("PHONE"),
									  rset.getString("DELIVERY_STATUS"),
									  rset.getString("REQUEST"),
									  rset.getString("CANCEL_BUY"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int OrderDeliveryStart(Connection conn, int bCode) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deliveryStart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int addProductAmount(Connection conn, int pNo, int amount) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("addAmount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteProduct(Connection conn, int pNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<ProductAttachment> mainProductPicList(Connection conn) {
		ArrayList<ProductAttachment> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("mainProductPicList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				ProductAttachment at = new ProductAttachment();
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("change_name"));
				at.setpNo(rset.getInt("PNO"));
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

	public ArrayList<Inquiry> selectInquiryList(Connection conn, int currentPage, int boardLimit) {
		ArrayList<Inquiry> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("inquiryList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (currentPage - 1) * boardLimit + 1;
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				Inquiry iq = new Inquiry();
				iq.setInquiryNo(rset.getInt("INQUIRY_NO"));
				iq.setUserId(rset.getString("USER_ID"));
				iq.setInquiryTitle(rset.getString("INQUIRY_TITLE"));
				iq.setInquiryContent(rset.getString("INQUIRY_CONTENT"));
				iq.setWriter(rset.getString("INQUIRY_WRITER"));
				iq.setDate(rset.getDate("INQUIRY_DATE"));
				iq.setrDate(rset.getDate("R_DATE"));
				iq.setrContent(rset.getString("R_CONTENT"));
				iq.setDate(rset.getDate("INQUIRY_DATE"));
				iq.setrStatus(rset.getString("R_STATUS"));
				
				
				list.add(iq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Inquiry getInquiry(Connection conn, int qNo) {
		Inquiry iq = new Inquiry();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectInquiry");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				
				iq.setInquiryNo(rset.getInt("INQUIRY_NO"));
				iq.setUserId(rset.getString("USER_ID"));
				iq.setInquiryTitle(rset.getString("INQUIRY_TITLE"));
				iq.setInquiryContent(rset.getString("INQUIRY_CONTENT"));
				iq.setWriter(rset.getString("INQUIRY_WRITER"));
				iq.setDate(rset.getDate("INQUIRY_DATE"));
				iq.setrDate(rset.getDate("R_DATE"));
				iq.setrContent(rset.getString("R_CONTENT"));
				iq.setDate(rset.getDate("INQUIRY_DATE"));
				iq.setrStatus(rset.getString("R_STATUS"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return iq;
	}

	public int answerInquiry(Connection conn, int qNo, String answer) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("answerInquiry");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, qNo);
			pstmt.setString(1, answer);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int getInquiryListCount(Connection conn) {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getInquiryListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int updateProduct(Connection conn, Product p) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getContents());
			pstmt.setString(4, p.getCategory());
			pstmt.setInt(5, p.getpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getProductListCount(Connection conn) {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getProductListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int deleteOldPic(Connection conn, int pNo, int i) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteOldPic");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, i);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteProductAttachment(Connection conn, int pNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProductAt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertProductAttachment(Connection conn, ArrayList<ProductAttachment> fileList, int pNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			
			for(int i=0; i<fileList.size(); i++) {
				ProductAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pNo);
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				pstmt.setInt(5, at.getFileLevel());
				
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
}
