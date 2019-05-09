package net.product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.action.Action;
import net.action.ActionForward;
import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class ProductDetailAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		ProductDAO productdao = new ProductDAO();
		ProductBean productbean = new ProductBean();
		System.out.println("해");
		productbean.setProduct_code(Integer.parseInt(request.getParameter("code")));
		
		if(request.getParameter("code") != null) {
			if(!productdao.deleteProduct(productbean.getProduct_code())) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('Deletion is failed try again')");
				out.print("</script>");
				out.close();
				return null;
			}
		}else{
			return null;
		}
	

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./Productsale.po");
		productdao.conClose();
		return forward;
		
	}
}
