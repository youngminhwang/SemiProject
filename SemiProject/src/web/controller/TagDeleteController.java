package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Tag;
import web.service.face.TagService;
import web.service.impl.TagServiceImpl;


@WebServlet("/tag/tagdelete")
public class TagDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private TagService tagService = new TagServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/tag/tagdelete[POST]");
		
		Tag tag = tagService.getTag_no(req);
		System.out.println(tag);
		
		tagService.delete(tag);
		
		//목록 리다이렉트
		resp.sendRedirect("/tag/taglist");

		
	
	
	}
	
}
