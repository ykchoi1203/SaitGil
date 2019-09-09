package admin.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import inquiry.model.vo.Inquiry;
import member.model.vo.Member;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import product.model.vo.Order;
import product.model.vo.Product;
import product.model.vo.ProductAttachment;
import shopping.model.dao.ShoppingDao;

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

	public ArrayList<Product> selectProductList(int currentPage, int boardLimit) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new AdminDao().selectProductList(conn, currentPage, boardLimit);
		
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

	public int deleteNotice(int nNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().deleteNotice(conn, nNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Notice> selectNoticeList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new AdminDao().selectNoticeList(conn);
		
		close(conn);
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new AdminDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Notice selectNotice(int nNo) {
		Connection conn = getConnection();
		
		Notice n = new AdminDao().selectNotice(conn, nNo);
		
		return n;
	}

	public int editNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new AdminDao().editNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Order> selectOrderList() {
		Connection conn = getConnection();
		
		ArrayList<Order> list = new AdminDao().selectOrderList(conn);
		
		close(conn);
		
		return list;
	}

	public int OrderDeliveryStart(int bCode) {
		Connection conn = getConnection();
		
		int result = new AdminDao().OrderDeliveryStart(conn, bCode);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int addProductAmount(int pNo, int amount) {
		Connection conn = getConnection();
		
		int result = new AdminDao().addProductAmount(conn, pNo , amount);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteProduct(int pNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().deleteProduct(conn, pNo);
		
		int result2 = new AdminDao().deleteProductAttachment(conn, pNo);
		
		if(result > 0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<ProductAttachment> mainProductPicList() {
		Connection conn = getConnection();
		
		ArrayList<ProductAttachment> list = new AdminDao().mainProductPicList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Inquiry> selectInquiryList(int currentPage, int boardLimit) {
		Connection conn = getConnection();
		
		ArrayList<Inquiry> list = new AdminDao().selectInquiryList(conn, currentPage, boardLimit);
		
		close(conn);
		
		return list;
	}

	public Inquiry getInquiry(int qNo) {
		Connection conn = getConnection();
		
		Inquiry iq = new AdminDao().getInquiry(conn, qNo);
		
		close(conn);
		
		return iq;
	}

	public int answerInquiry(int qNo, String answer) {
		Connection conn = getConnection();
		
		int result = new AdminDao().answerInquiry(conn, qNo, answer);
		
		close(conn);
		
		return result;
	}
	
	public int getInquiryCount() {
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getInquiryListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public int updateProduct(Product p, ArrayList<ProductAttachment> fileList, int[] num, int oldNameSize) {
		Connection conn = getConnection();
		
		int result1 = new AdminDao().updateProduct(conn, p);
		int result3=1;
		int result2=1;
		if(!fileList.isEmpty()) {
			for(int i=0; i<num.length; i++) {
				if(num[i]!= 0 && num[i]<=oldNameSize) {
					result3 = new AdminDao().deleteOldPic(conn,p.getpNo(), num[i]);
					if(result3 == 0) break;
				}
			}
		}
		
		if(!fileList.isEmpty()) {
			result2 = new AdminDao().insertProductAttachment(conn, fileList, p.getpNo());
		}
		
		
		if(result1>0 && result2>0 && result3>0) {
			commit(conn);
		} else {
			rollback(conn);
			result1 = 0;
		}
		
		close(conn);
		
		return result1;
	}

	public int getProductCount() {
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getProductListCount(conn);
		
		close(conn);
		
		return listCount;
	}

}
