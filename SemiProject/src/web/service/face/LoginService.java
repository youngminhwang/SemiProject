package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.UserInfo;

public interface LoginService {
	
	public boolean login(HttpServletRequest req);

	public UserInfo getLoginMember(HttpServletRequest req);

	public UserInfo info(UserInfo userInfo);
		
}
