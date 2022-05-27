package web.service.face;

import javax.servlet.http.HttpServletRequest;

public interface FindUserIdService {

	public String findUserIdByEmail(HttpServletRequest req); 
	
}