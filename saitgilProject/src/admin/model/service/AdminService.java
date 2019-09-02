package admin.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import member.model.vo.Member;
import product.model.vo.Product;
import product.model.vo.ProductAttachment;

public class AdminService {

	public ArrayList<Member> selectMemberList() {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDao().selectMemberList(conn);
		
		close(conn);
		
		return list;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		String partnerId = new AdminDao().findPartnerId(conn, userId);
		
		int result1 = new AdminDao().deleteMember(conn, userId);
		int result2 = 0;
		if(result1>0) {
			result2 = new AdminDao().deletePartnerCCode(conn, partnerId);
		}
		
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result2;
	}

	public ArrayList<Product> selectProductList() {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new AdminDao().selectProductList(conn);
		
		close(conn);
		
		return list;
	}

	public int insertProduct(Product p, ArrayList<ProductAttachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = new AdminDao().insertProduct(conn, p);
		int result2 = new AdminDao().insertProductAttachment(conn, fileList);
		int result = 0;
		
		if(result1>0  && result2 > 0) {
			commit(conn);
			result=5;
		} else {
			rollback(conn);
			result=-5;
		}
		
		close(conn);
		
		return result;
	}

	public Product selectProduct(int pNo) {
		Connection conn = getConnection();
		
		Product p = new AdminDao().selectProduct(conn, pNo);
		
		close(conn);
		
		return p;
	}

	public ArrayList<ProductAttachment> selectProductAttachment(int pNo) {
		Connection conn = getConnection();
		
		ArrayList<ProductAttachment> list = new AdminDao().selectProductAttachment(conn, pNo);
		
		close(conn);
		
		return list;
	}

}
