package web.service.face;

import javax.servlet.http.HttpServletRequest;

public interface MemberJoinService {

	public void join(HttpServletRequest req);

	public boolean duplicateUserNick(String user_nick);

	public boolean duplicateUserId(String user_id);
	
	public boolean duplicateUserEmail(String user_email);

}


















