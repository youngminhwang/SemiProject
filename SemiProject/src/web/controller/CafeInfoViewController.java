package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Paging;
import web.dto.CafeReviewFile;
import web.dto.CafeTag;
import web.dto.CafeFile;
import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.service.face.CafeInfoService;
import web.service.impl.CafeInfoServiceImpl;

/**
 * Servlet implementation class CafeInfoViewController
 */
@WebServlet("/cafe/view")
public class CafeInfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CafeInfoService cafeInfoService = new CafeInfoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cafe/view [get]");
		
		CafeReviewFile cafereviewFile= new CafeReviewFile();
		
		
		HttpSession session=req.getSession();
		int userno = Integer.parseInt(String.valueOf(session.getAttribute("user_no")));
		
		//카페 번호 받아오기
		CafeInfo cafeno = cafeInfoService.getCafeno(req);
		
		//페이징에 들어갈 카페 넘버
		int cafeinfo=cafeno.getCafe_no();
		
		//1 or 0 찜 했는지 안했는지 유무
		int res = cafeInfoService.getUserRcm(userno, cafeno);
		
		//카페 파일 받아오기
		List<CafeFile> cafefile=cafeInfoService.getcafeFile(cafeno);		
		//카페 리뷰 사진 불러오는
		List<CafeReviewFile> cafereviewfile= cafeInfoService.getFileno(req,cafereviewFile);
		
		//카페 정보 불러오기
		CafeInfo viewCafe=cafeInfoService.view(cafeno);
		//리뷰 페이징할건데 아직안했네...
		Paging paging = cafeInfoService.getPaging(req,cafeno);
		//리뷰 페이징할건데 아직 안했네...
		List<CafeReview> cafeList = cafeInfoService.getreviewlist(paging);

		//리뷰 페이징 없이 전부 가져오기(사진용)
		List<CafeReview> cafereviewList = cafeInfoService.getreviewlist();
		
		//별점 평균 받아옴.
		float starAvg=cafeInfoService.getstarAvg(cafereviewList,cafeno);
		
		//카페 태그 불러오기
		List<CafeTag> cafeTag = cafeInfoService.getTagList(cafeno);
		
		//카페정보
		req.setAttribute("cafeinfo", cafeinfo);
		//별점 평균 보내주기
		req.setAttribute("starAvg", starAvg);
		//리뷰 페이징(x)
		req.setAttribute("cafereviewList", cafereviewList);
		//회원 넘버 정보 보냄
		req.setAttribute("userno", userno);
		//카페 태그 정보 보냄
		req.setAttribute("cafetag", cafeTag);
		//카페정보 보냄		
		req.setAttribute("viewCafe", viewCafe);
		//리뷰 리스트 보냄
		req.setAttribute("reviewList", cafeList);
		//리뷰 사진 보냄
		req.setAttribute("cafereviewFile", cafereviewfile);
		//카페 사진 보냄
		req.setAttribute("CafeFileList", cafefile);
		//리뷰 페이징
		req.setAttribute("paging", paging);
		//유저 카페 좋아요 확인 보냄
		req.setAttribute("res", res);
		System.out.println(viewCafe+ "viewcontroller");
		
		
		
		req.getRequestDispatcher("/WEB-INF/views/cafeinfo/view.jsp").forward(req, resp);
	}

}
