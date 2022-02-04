package env;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class 로그인확인자 extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		boolean 로그인중 = false;
		
		if(session != null && session.getAttribute("conven_session_id") != null) {
			로그인중 = true;
		}
		
		if(!로그인중) {
						
			String url = request.getServletPath();
	        System.out.println("url : " + url);
	        String query = request.getQueryString(); //파라미터가 있는지 없는지
	        System.out.println("query: " + query);
	        
	        if(url == null) {
	        	url = "/main";
	        }
	        
	        if(query != null){ //주소가 board?no=34&page=33
	            url = url + "?" + query;
	        }			
	       
	        System.out.println("최종 url: " + url);	       
	        
	        request.setAttribute("prev_url", url);
	        RequestDispatcher rd = request.getRequestDispatcher("/login");	        
	        rd.forward(request, response);
			
			return 로그인중;			
		}
		
		return 로그인중;
	}
	
	
}
