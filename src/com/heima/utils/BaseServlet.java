package com.heima.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟struts2 MVC 框架
 * 
 * 此BaseServlet对service方法进行了重写增强。 他作为父类由子类继承，  继承此BaseServlet的子类，是一个HttpServlet，并且他可以处理多个请求。 
 * @creationTime:2017年9月9日 下午1:12:50
 * @version: 1.1
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		 * 2. 获取方法名参数，用来识别用户想请求的方法 。
		 * 提示：超链接或表单中，都必须带有method参数，参数值代表将要调用的方法 ，并且Servlet中必须有与之对应的方法。
		 */
		String methodName = request.getParameter("method");
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("您和传递的Method为null或为空字符串无效, 无法确定您要调用的方法");
		}

		/*
		 * 3. 用获取的方法名,通过反射的来得到将要请求的方法 Method对象.
		 * 提示：  得到的是本类中方法, 所以使用本类对象来获取class,所以使用this.getClass().
		 */
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			// 如果抛出异常说明,词方法名不存在.
			throw new RuntimeException( "您要调用的:" + methodName
							+ "(HttpServletRequest request, HttpServletResponse response); 不存在");
		}
		
		/*
		 * 4. 调用method方法对象。 获取请求处理方法执行后返回的字符串，它表示转发或重定向的路径！ 帮它完成转发或重定向！
		 * 提示： 返回值统一为String。  代表路径
		 */
		try {
			//调用method方法对象表示的方法.
			String result = (String) method.invoke(this, request, response);
			
			//如果用户返回的是字符串为null，或为"", 那么我们什么也不做！ 
			if (result == null || result.trim().isEmpty()) {
				return;
			}
			/*
			 * 5. 判断用的调用方法后的返回值是否包含":",		如果没有说明默认执行转发
			 * 提示： 返回值字符串由标识和路径组成,使用:分割。  标识： f代表转发, r代表重定向。 路径决定跳转路径。
			 * 如：
			 * 	 1  f:/servlet/update.jsp				代表转发到	"/servlet/update.jsp"
			 * 	 2  /servlet/update.jsp					前缀没有f或r和冒号, 默认转发到  "/servlet/update.jsp"
			 * 	 3  r:/baseServlet2/servlet/select.jsp	代表重定向到 "/baseServlet2/servlet/select.jsp"
			 */
			if (result.contains(":")) {
				
				//如果包含":" 使用":"切割,
				String[] strings = result.split(":");
				
				// 冒号前为 "f" 说明 执行 转发.后缀为路径
				if ("f".equalsIgnoreCase(strings[0])) {			
					request.getRequestDispatcher(strings[1]).forward(request, response);
					
				// 冒号前为 "r" 说明执行重定向.后缀为路径	
				} else if ("r".equalsIgnoreCase(strings[0])) {	
					response.sendRedirect(request.getContextPath() + strings[1]);
					
				// 冒号前面不为"f"或"r"说明此操作不存在	
				} else {										
					throw new RuntimeException("你指定的操作：" + strings[0] + "，当前版本还不支持！");
				}
			} else {
				request.getRequestDispatcher(result).forward(request, response);
			}
		
		} catch (Exception e) {									// 若是执行以下catch说明调用的方法抛出了理异常
			System.out.println("您调用的方法：" + methodName + ",　它内部抛出了异常！");
			throw new RuntimeException(e);
		}
	}
}
