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
		urlPatterns={"/pages/*"}	
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
		MemberBean bean = (MemberBean) session.getAttribute("user");
		if(bean!=null){
			 chain.doFilter(request, response);
		}
		String path = request.getContextPath();
		response.sendRedirect(path+"/secure/login.jsp");
	}

	@Override
	public void destroy() {


	}

}
