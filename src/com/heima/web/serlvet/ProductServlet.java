package com.heima.web.serlvet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.heima.domain.Product;
import com.heima.service.ProductService;
import com.heima.service.impl.ProductServiceImpl;
import com.heima.utils.BaseServlet;
import com.heima.utils.PageBean;
import com.heima.utils.UUIDUtils;

public class ProductServlet extends BaseServlet {
	private ProductService productService = new ProductServiceImpl();
	
	/**
	 * 去添加页面
	 */
	public String toAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/addProduct.jsp";
	}
	
	/**
	 * 添加商品数据到数据框
	 */
	public String addProduct(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product producer = 	new Product();
		try {
			BeanUtils.populate(producer, parameterMap);
			producer.setPid(UUIDUtils.getUUID());
			productService.addProduct(producer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "r:/ProductServlet?method=findByPage";
	}
	
	/**
	 * 异步校验商品名是否可用
	 */
	public String checkPName(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");
		try {
			String checkPName = productService.checkPName(pname);
			
			if (checkPName == "1") {				// 1 代表商品名可以用
				response.getWriter().print("1");
			}else {									// 0 代表商品名不可以用
				response.getWriter().print("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 去修改页面
	 */
	public String toUpdateProduct(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		try {
			Product producer =  productService.findProcudtByPid(pid);
			request.setAttribute("producer", producer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/editProduct.jsp";
	}
	
	/**
	 * 修改商品数据到数据框
	 */
	public String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product producer = 	new Product();
		try {
			BeanUtils.populate(producer, parameterMap);
			productService.updateProduct(producer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "r:/ProductServlet?method=findByPage";
	}
	
	/**
	 * 删除商品数据
	 */
	public String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		try {
			productService.deleteProduct(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "r:/ProductServlet?method=findByPage";
	}
	
	/**
	 * 分页查询商品数据
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) {
		String currPageStr = request.getParameter("currPage");
		Integer currPage = 1;
		try {
			currPage = Integer.parseInt(currPageStr);
		} catch (NumberFormatException e1) {
			//若currPage 传递为null或不是数字类型的字符串, 默认让 currPage = 1
			currPage = 1;
			//e1.printStackTrace();   
		}
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrPage(currPage);
		try {
			productService.findByPage(pageBean);	//传入pageBean 引用
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/productList.jsp";
	}
}
