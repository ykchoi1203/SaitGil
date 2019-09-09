package shopping.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import product.model.vo.Product;
import shopping.model.dao.ShoppingDao;
import shopping.model.vo.Cart;
import shopping.model.vo.Ggim;
import shopping.model.vo.Order;
public class ShoppingService {

	public ArrayList<Product> selectgoodSeller() {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShoppingDao().selectGoodSeller(conn);
		
		close(conn);
		return list;
	}

	public ArrayList<Product> selectgoodpCount() {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShoppingDao().selectGoodpCount(conn);
		
		close(conn);
		return list;
	}

	public ArrayList<Ggim> ggimList(String userId) {
		Connection conn = getConnection();
		ArrayList<Ggim> list = new ShoppingDao().ggimList(conn, userId);
		
		close(conn);
		return list;
	}

	public int addGgim(int pNo, String userId) {
		Connection conn = getConnection();
		int result = new ShoppingDao().addGgim(conn, pNo, userId);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteGgim(int pNo, String userId) {
		Connection conn = getConnection();
		int result = new ShoppingDao().deleteGgim(conn, pNo, userId);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Product selectProduct(int pNo) {
		Connection conn = getConnection();
		
		Product p = new ShoppingDao().selectProduct(conn, pNo);
		
		int result = new ShoppingDao().addpCount(conn,pNo);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return p;
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new ShoppingDao().getListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectList(int currentPage, int boardLimit) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShoppingDao().selectList(conn, currentPage, boardLimit);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Cart> CartList(String userId) {
		Connection conn = getConnection();
		ArrayList<Cart> list = new ShoppingDao().CartList(conn, userId);
		
		close(conn);
		return list;
	}

	public int addCart(int pNo, String userId, int amount) {
		Connection conn = getConnection();
		int result = new ShoppingDao().addCart(conn, pNo, userId, amount);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteCart(int pNo, String userId) {
		Connection conn = getConnection();
		int result = new ShoppingDao().deleteCart(conn, pNo, userId);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int orderInsert(ArrayList<Order> list) {
		Connection conn = getConnection();
		int result = 0;
		int result2 = 0;
		int result3=0;
		
		for(Order o : list) {
			result = new ShoppingDao().orderInsert(conn, o);
			result2 = new ShoppingDao().deleteCart(conn, o.getpNo(), o.getUserId());
			result3 = new ShoppingDao().minusAmount(conn, o.getBuyAmount(), o.getpNo());
			if(result==0 || result2==0 || result3==0) {
				break;
			}
		}
		
		
		if(result>0 && result2>0 && result3>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public ArrayList<Order> getOrderFinish(int currentPage, int boardLimit, String userId) {
		Connection conn = getConnection();
		ArrayList<Order> list = new ShoppingDao().orderFinishList(conn,currentPage, boardLimit, userId);
		
		close(conn);
		return list;
	}

	public int getOrderListCount(String userId) {
		Connection conn = getConnection();
		
		int listCount = new ShoppingDao().getOrderListCount(conn, userId);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectProductList() {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShoppingDao().selectProductList(conn);
		
		close(conn);
		return list;
	}

}
