package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.UserInfo;

public interface GetDataForMemberJoinService {

	public UserInfo getUserInfo(HttpServletRequest req);
	
}
