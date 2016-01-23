package misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberBean;


@WebFilter(
		urlPatterns={"/PointServlet.do",
				   "/PointServlet50.do",
				   "/front/Entrancepage/Entrancepage1.jsp",
				   "/back/member/MemberPage.jsp",
				   "/front/member/main/member.do"
				   
		}	
		)
public class LoginFilter implements Filter {

	
	private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response  = (HttpServletResponse) resp;
		
		 HttpSession session = request.getSession();
		 MemberBean bean = (MemberBean) session.getAttribute("isLogin");
		 if(bean==null){
			 String path = request.getContextPath();
				response.sendRedirect(path+"/front/member/login/login.jsp");
		 }else{
			 chain.doFilter(request, response); 
		 }		
	
		
	}

	@Override
	public void destroy() {


	}

}
