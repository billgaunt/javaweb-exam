package com.heima.web.serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.utils.BaseServlet;

/**
 * 
 */
public class IndexServlet extends BaseServlet {
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/main.jsp";
	}

}
