package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.TagDao;
import web.dao.impl.TagDaoImpl;
import web.dto.Tag;
import web.service.face.TagService;

public class TagServiceImpl implements TagService {

	private TagDao tagDao = new TagDaoImpl();
	
	@Override
	public List<Tag> getList(Paging paging) {
		return tagDao.selectAll(JDBCTemplate.getConnection(), paging);
	}
	
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] UserListService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = tagDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Tag getTag_no(HttpServletRequest req) {

		//전달 파라미터 user_no를 저장할 DTO객체 생성
		Tag tag_no = new Tag();
		
		//전달 파라미터를 String 타입에 저장
		String param = req.getParameter("tag_no");

		//전달받은 param이 null값이 아니거나 비어있지 않다면 param을 int로 파싱해서 boardno에 넣기!
		//전달받은 param이 null값이거나 비어있다면 경고메시지 출력!
		if(param != null && !"".equals(param)) {
			tag_no.setTag_no(Integer.parseInt(param));
			System.out.println("들어온 태그 번호는 : " + tag_no);
		} else {
			System.out.println("[WARRING] tagService getTag_no() - tag_no값이 null이거나 비어있음");
		}
		
		//DTO 반환
		return tag_no;
	}
	
	@Override
	public void delete(Tag tag) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if (tagDao.deleteUser(conn, tag) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("commit성공!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("실패!");
		}
		
	}
	
	@Override
	public Tag view(Tag tag_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		
		//회원정보 조회
		Tag tag = tagDao.selectTagByTag_no(conn, tag_no);
		
		
		return tag;
	}
	
	@Override
	public void write(HttpServletRequest req) {
		//---기본 글쓰기 형식---
		
		Tag tag = new Tag();
		
		tag.setTag_name(req.getParameter("tag_name"));
		
		
		if(tag.getTag_name()==null || "".equals(tag.getTag_name())) {
			tag.setTag_name("(내용없음)");
		
		}
		
		Connection conn = JDBCTemplate.getConnection();
		
		int tag_no = tagDao.selectTag_no(conn);
		
		tag.setTag_no(tag_no);
		
		if(tagDao.insert(conn, tag) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}//write end
	
	@Override
	public List<Tag> getList() {
		return tagDao.selectAll(JDBCTemplate.getConnection());
	}
	
	
	@Override
	public List<Tag> viewListSearchT(Paging paging) {
		return tagDao.searchTagList(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public Paging getSearchPagingT(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 seach 추출하기
		String data = req.getParameter("data");
		
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] tagService getSearchPaging - curPage값이 null이거나 공백");
		}
		
		//총 게시글 수 조회하기
		int totalCount = tagDao.selectCntTagSearch(JDBCTemplate.getConnection(), data);
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
				
		return searchPaging;
	}
	
	
}
