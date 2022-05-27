package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.User;

public interface MypageService {
	
	
	public User mypageview(User user);

	public List<User> getMypageList(User user);

	
	public void mypageupdate(HttpServletRequest req);


	

	public boolean duplicate(String user_nick);

	public User getUserpw(String userid);

	public void mypagedelete(User user);
	
}
