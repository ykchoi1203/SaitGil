package shopping.model.dao;

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

import member.model.dao.MemberDao;
import product.model.vo.Product;
import shopping.model.vo.Cart;
import shopping.model.vo.Ggim;
import shopping.model.vo.Order;

public class ShoppingDao {

	private Properties prop = new Properties();
	
	public ShoppingDao() {
		String fileName = MemberDao.class.getResource("/sql/shopping/shopping-query.properties").getPath();
		
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

	public ArrayList<Product> selectGoodSeller(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("goodSeller");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				Product p = new Product();
				p.setpNo(rset.getInt("PNO"));
				p.setpName(rset.getString("PNAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setAmount(rset.getInt("AMOUNT"));
				p.setContents(rset.getString("CONTENTS"));
				p.setPurchase(rset.getInt("PURCHASE"));
				p.setCategory(rset.getString("CATEGORY"));
				p.setpCount(rset.getInt("PCOUNT"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> selectGoodpCount(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("goodpCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				Product p = new Product();
				p.setpNo(rset.getInt("PNO"));
				p.setpName(rset.getString("PNAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setAmount(rset.getInt("AMOUNT"));
				p.setContents(rset.getString("CONTENTS"));
				p.setPurchase(rset.getInt("PURCHASE"));
				p.setCategory(rset.getString("CATEGORY"));
				p.setpCount(rset.getInt("PCOUNT"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Ggim> ggimList(Connection conn, String userId) {
		ArrayList<Ggim> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("ggimList");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				Ggim g = new Ggim();
				
				g.setUserId(userId);
				g.setpNo(rset.getInt("pNo"));
				g.setpName(rset.getString("pName"));
				g.setChangeName(rset.getString("CHANGE_NAME"));
				g.setPrice(rset.getInt("price"));
				
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int addGgim(Connection conn, int pNo, String userId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("addGgim");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, userId);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteGgim(Connection conn, int pNo, String userId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteGgim");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
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

	public int addpCount(Connection conn, int pNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("addpCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getListCount");
		
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

	public ArrayList<Product> selectList(Connection conn, int currentPage, int boardLimit) {
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// currentPage = 1		--> startRow : 1 ~ endRow : 10
			// currentPage = 2		--> startRow : 11~ endRow : 20
			
			int startRow = (currentPage - 1) * boardLimit + 1;
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				
				p.setpNo(rset.getInt("PNO"));
				p.setpName(rset.getString("pName"));
				p.setPrice(rset.getInt("PRICE"));
				p.setAmount(rset.getInt("AMOUNT"));
				p.setContents(rset.getString("CONTENTS"));
				p.setCategory(rset.getString("CATEGORY"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Cart> CartList(Connection conn, String userId) {
		ArrayList<Cart> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("cartList");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// change_name, fid
				Cart c = new Cart();
				
				c.setUserId(userId);
				c.setpNo(rset.getInt("pNo"));
				c.setpName(rset.getString("pName"));
				c.setChangeName(rset.getString("CHANGE_NAME"));
				c.setPrice(rset.getInt("price"));
				c.setpAmount(rset.getInt("AMOUNT"));
				c.setAmount(rset.getInt("SBAMOUNT"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int addCart(Connection conn, int pNo, String userId, int amount) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("addCart");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(3, amount);
			pstmt.setString(2, userId);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteCart(Connection conn, int pNo, String userId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCart");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int orderInsert(Connection conn, Order o) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertOrder");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, o.getUserId());
			pstmt.setInt(2, o.getpNo());
			pstmt.setInt(3, o.getBuyAmount());
			pstmt.setString(4, o.getAddress());
			pstmt.setString(5, o.getPhone());
			pstmt.setString(6, o.getRequest());
			pstmt.setString(7, o.getName());
			pstmt.setString(8, o.getEmail());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Order> orderFinishList(Connection conn, int currentPage, int boardLimit, String userId) {
		ArrayList<Order> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("orderHistory");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (currentPage - 1) * boardLimit + 1;
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				// change_name, fid
				Order o = new Order();
				
				o.setpName(rset.getString("pName"));
				o.setPrice(rset.getInt("PRICE"));
				o.setBuyCode(rset.getInt("BUY_CODE"));
				o.setUserId(rset.getString("USER_ID"));
				o.setpNo(rset.getInt("pNo"));
				o.setBuyDate(rset.getDate("BUY_DATE"));
				o.setBuyAmount(rset.getInt("BUY_AMOUNT"));
				o.setBuyStatue(rset.getString("BUY_STATUE"));
				o.setAddress(rset.getString("SHIPPING"));
				o.setPhone(rset.getString("PHONE"));
				o.setDeliveryStatus(rset.getString("DELIVERY_STATUS"));
				o.setRequest(rset.getString("REQUEST"));
				o.setCancelStatus(rset.getString("CANCEL_BUY"));
				o.setEmail(rset.getString("EMAIL"));
				o.setChangeName(rset.getString("CHANGE_NAME"));
				
				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int minusAmount(Connection conn, int amount, int pNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("minusAmount");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, amount);
			pstmt.setInt(3, pNo);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getOrderListCount(Connection conn, String userId) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getOrderListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
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

}
