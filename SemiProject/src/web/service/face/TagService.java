package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.Tag;

public interface TagService {

	
	/**
	 * 태그 페이징 목록 조회
	 * @param paging - 페이징 정보 객체
	 * @return List<Tag> - 패이징이 반영된 태그 조회 목록 
	 */
	public List<Tag> getList(Paging paging);

	/**
	 * 페이징 객체 생성
	 * @param req - 요청 정보 객체
	 * @return - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	
	/**
	 * 요청 파라미터 얻어오기
	 * @param req - 요청 정보 객체
	 * @return Tag - 전달파라미터 tag_no를 포함한 DTO
	 */
	public Tag getTag_no(HttpServletRequest req);

	/**
	 * 회원 삭제
	 * @param tag - 삭제할 태그 번호 가진 객체
	 */
	public void delete(Tag tag);

	
	/**
	 * 전달된 tag-no이용해 태그 조회
	 * @param tag_no - 조회할 태그 번호 갖고있는 DTO
	 * @return Tag - 조회한 태그 정보
	 */
	public Tag view(Tag tag_no);

	
	/**
	 * 태그 작성(추가)
	 * 입력한 태그를 DB에 저장
	 * @param req - 요청정보 객체(태그 내용)
	 */
	public void write(HttpServletRequest req);

	
	/**
	 * 태그 전체 조회
	 * @return List<Tag> - 태그 전체 조회 결과 목록
	 */
	public List<Tag> getList();

	
	public List<Tag> viewListSearchT(Paging paging);
	
	public Paging getSearchPagingT(HttpServletRequest req);


	






}
