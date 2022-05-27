package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.CafeInfoDao;
import web.dto.CafeReviewFile;
import web.dto.CafeTag;
import web.dto.CafeFile;
import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.dto.UserInfo;


public class CafeInfoDaoImpl implements CafeInfoDao {
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	@Override
	public CafeInfo cafeview(Connection conn, CafeInfo cafe) {
		String sql = "";
		sql+="select * from cafe_info where cafe_no=?";		
		CafeInfo cafeInfo = null ;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafe.getCafe_no());
			rs=ps.executeQuery();
			while(rs.next()) {
				cafeInfo=new CafeInfo();
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_menu(rs.getString("cafe_menu"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
		return cafeInfo;
		
	}
	@Override
	public List<CafeInfo> selectAll(Connection conn, Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			cafe_no, cafe_name, cafe_tel";
		sql += "			, cafe_time,cafe_park,cafe_rcm, cafe_loc";
		sql += "		FROM cafe_info";
		sql += "		ORDER BY cafe_no DESC";
		sql += " 	) B";
		sql += " ) cafe_info";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<CafeInfo> cafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo(); //결과값 저장 객체
				
				//결과값 한 행 처리
				cafeInfo.setCafe_no( rs.getInt("cafe_no") );
				cafeInfo.setCafe_name( rs.getString("cafe_name") );
				cafeInfo.setCafe_tel( rs.getString("cafe_tel") );
				cafeInfo.setCafe_tel( rs.getString("cafe_loc") );
				cafeInfo.setCafe_time( rs.getString("cafe_time") );
				cafeInfo.setCafe_park( rs.getString("cafe_park") );
				cafeInfo.setCafe_rcm( rs.getInt("cafe_rcm") );
				
				//리스트객체에 조회한 행 객체 저장
				cafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return cafeList;
		
	}
	@Override
	public int selectCntAll(Connection conn, CafeInfo cafeno) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM cafe_review";
		sql	+=" where cafe_no = ?";
		
		//총 게시글 수
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cafeno.getCafe_no());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
		
		return count;
	}
	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
				String sql = "";
				sql += "SELECT count(*) cnt FROM cafe_info";
			
				
				//총 게시글 수
				int count = 0;
				
				try {
					ps = conn.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					while( rs.next() ) {
						count = rs.getInt("cnt");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(ps);
					JDBCTemplate.close(rs);
					
				}
				
				return count;
	}
	@Override
	public List<CafeReview> selectreview(Connection conn, Paging paging) {
		String sql="";
		sql+="select * from (";
		sql+="	select rownum rnum,B.* from(";
		sql+="		select";
		sql+="			review_no,cafe_no,review_cont,star_score";
		sql+="			,user_nick,review_date,user_no from cafe_review";
		sql+="			ORDER BY review_no DESC";
		sql+="		) B";
		sql+="	)cafe_review";
		sql+=" where rnum between ? and ?";
		List<CafeReview> cafeList = new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			rs=ps.executeQuery();
			while(rs.next()) {
				CafeReview c = new CafeReview();
				c.setReview_no(rs.getInt("review_no"));
				c.setCafe_no(rs.getInt("cafe_no"));
				c.setReview_cont(rs.getString("review_cont"));
				c.setStar_score(rs.getInt("star_score"));
				c.setUser_nick(rs.getString("user_nick"));
				c.setReview_date(rs.getDate("review_date"));
				c.setUser_no(rs.getInt("user_no"));
				
				cafeList.add(c);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cafeList;
	}
	
	@Override
	public List<CafeReview> selectreview(Connection conn) {
		String sql="";
		sql += "select * from cafe_review";
		sql+=" order by review_no desc ";
		
		List<CafeReview> cafeList = new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				CafeReview c = new CafeReview();
				c.setReview_no(rs.getInt("review_no"));
				c.setCafe_no(rs.getInt("cafe_no"));
				c.setReview_cont(rs.getString("review_cont"));
				c.setStar_score(rs.getInt("star_score"));
				c.setUser_nick(rs.getString("user_nick"));
				c.setReview_date(rs.getDate("review_date"));
				c.setUser_no(rs.getInt("user_no"));
				
				cafeList.add(c);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cafeList;
	}
	@Override
	public int reviewInsert(Connection conn, CafeInfo cafeno,CafeReview review,UserInfo user) {
		String sql="";
		sql+=" insert into cafe_review";
//		sql+= " (review_no,cafe_no,user_no,user_nick,review_cont,star_score)";
		sql+= " (review_no,cafe_no,review_cont,star_score)";
//		sql+=" values(review_seq.nextval,?,?,?,?)";
		sql+=" values(review_seq.nextval,?,?,?)";
		int res=0;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafeno.getCafe_no());
//			ps.setInt(2,user.getUser_no());
//			ps.setString(3,user.getUser_nick());
			ps.setString(2, review.getReview_cont());
			ps.setInt(3, review.getStar_score());
			
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return res;
	}
	@Override
	public int reviewfileno(Connection conn) {
		String sql = "";
		sql+= "select reviewfile_seq.nextval from dual";
		
		int nextreviewfile = 0;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				nextreviewfile=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
		return nextreviewfile;
	}
	@Override
	public int insertFile(Connection conn, CafeReviewFile cafeFile) {
		String sql="";
		sql += "insert into review_attachments(rvw_file_no,review_no,rvw_org_file_name,rvw_cpy_file_name,rvw_file_size)"; 
		sql += " values(?,?, ?, ?, ?) ";
		
		int res=0;
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, cafeFile.getRvw_file_no());
			ps.setInt(2, cafeFile.getReview_no());
			ps.setString(3,cafeFile.getRvw_org_file_name());
			ps.setString(4,cafeFile.getRvw_cpy_file_name());
			ps.setInt(5, cafeFile.getFilesize());
			
			res=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return res;
	}
	@Override
	public int reviewno(Connection conn) {
		String sql="";
		sql+= "select review_seq.nextval from dual";
		
		int nextreview = 0;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				nextreview=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			
		}
		return nextreview;
	}
	@Override
	public int reviewInsert(Connection conn, CafeReview review, UserInfo user) {
		String sql = "";
		sql += "INSERT INTO cafe_review(review_no, cafe_no , user_no, user_nick, review_cont, star_score)";
		sql += " VALUES (?, ?, ?, ?, ?,?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, review.getReview_no());
			ps.setInt(2, review.getCafe_no());
			ps.setInt(3, user.getUser_no());
			ps.setString(4, user.getUser_nick());
			ps.setString(5, review.getReview_cont());
			ps.setInt(6, review.getStar_score());
			System.out.println(user.getUser_no());
			System.out.println("test");

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return res;
	}
	@Override
	public List<CafeReviewFile> selectfile(Connection conn,CafeReviewFile cafeFile) {
		String sql = "";
		sql+="select * from review_attachments ";
		sql+=" order by review_no";
		CafeReviewFile cafereviewfile= null;
		List<CafeReviewFile> reviewFileList=new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				cafereviewfile= new CafeReviewFile();
				cafereviewfile.setReview_no(rs.getInt("review_no"));
				cafereviewfile.setFilesize(rs.getInt("rvw_file_size"));
				cafereviewfile.setRvw_cpy_file_name(rs.getString("rvw_cpy_file_name"));
				cafereviewfile.setRvw_file_no(rs.getInt("rvw_file_no"));
				reviewFileList.add(cafereviewfile);												
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return reviewFileList;
	}
	@Override
	public int reviewdelete(Connection conn, CafeReview cafereview) {
		String sql="";
		sql+="delete from cafe_review where review_no = ?";
		int res =0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafereview.getReview_no());
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
	
		return res;
	}
	
	@Override
	public int reviewfiledelete(Connection conn, CafeReview cafereview) {
		String sql="";
		sql+="delete from review_attachments where review_no = ?";
		int res =0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafereview.getReview_no());
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
	
		return res;
	}
	
	@Override
	public CafeReview getrCafeno(Connection conn, HttpServletRequest req) {
		CafeReview cafereview= new CafeReview();
		String sql="";
		sql+="select cafe_no from cafe_review where review_no=? ";
		int param =Integer.valueOf(req.getParameter("reviewno"));
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, param);
			rs=ps.executeQuery();
			while(rs.next()){
				cafereview.setCafe_no(rs.getInt("cafe_no"));
			}
			System.out.println(cafereview.getCafe_no()+"삭제 카페 넘버");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return cafereview;
	}
	
	@Override
	public List<CafeFile> selectCafeFile(Connection conn, CafeInfo cafeno) {
		String sql="";
		sql+="select cafe_cpy_file_name from cafe_photo where cafe_no = ? ";
		
		List<CafeFile> cafefile=new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafeno.getCafe_no());
			rs=ps.executeQuery();
			while(rs.next()) {
				CafeFile file = new CafeFile();
				
				file.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				cafefile.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cafefile;
	}
	
	@Override
	public List<CafeTag> selectCafeTag(Connection conn, CafeInfo cafeno) {
		String sql = "";
		sql+= "select tag_name from tag";
		sql+= " where tag_no in (select tag_no from tag_conn where cafe_no=?)";
		List<CafeTag> list = new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafeno.getCafe_no());
			
			rs=ps.executeQuery();
			while(rs.next()) {
				CafeTag tag = new CafeTag();
				tag.setTag_name(rs.getString("tag_name"));
				
				list.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return list;
	}
	@Override
	public CafeReview getReviewInfo(Connection conn, CafeReview cafereview) {
		String sql ="";
		sql+="select * from cafe_review where review_no = ?";
		CafeReview review=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafereview.getReview_no());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				review=new CafeReview();
				
				review.setCafe_no(rs.getInt("cafe_no"));
				review.setReview_no(rs.getInt("review_no"));
				review.setUser_no(rs.getInt("user_no"));
				review.setUser_nick(rs.getString("user_nick"));
				review.setReview_cont(rs.getString("review_cont"));
				review.setStar_score(rs.getInt("star_score"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
	
		return review;
	}
	@Override
	public int reviewUpdate(Connection conn, CafeReview review) {
		String sql="";
		sql +="update cafe_review set review_cont =?";
		sql +=" ,star_score =? where review_no = ?";
		
		System.out.println(review + "reviewUpdate");
		System.out.println(review.getReview_no());
		int res =0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, review.getReview_cont());
			ps.setInt(2,review.getStar_score());
			ps.setInt(3,review.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	@Override
	public int updateFile(Connection conn, CafeReviewFile cafeFile) {
		String sql="";
		sql+="update review_attachments set rvw_org_file_name = ?";
		sql+=" ,rvw_cpy_file_name= ?";
		sql+=" where review_no=?";
		int res=0;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cafeFile.getRvw_org_file_name());
			ps.setString(2, cafeFile.getRvw_cpy_file_name());
			ps.setInt(3, cafeFile.getReview_no());
			
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	

	@Override
	public UserInfo getuserInfo(UserInfo user,Connection conn) {
		String sql="";
		sql+="select user_nick,user_no,user_rank from user_info where user_id = ?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,user.getUser_id());
			rs=ps.executeQuery();
			while(rs.next()) {
		
				user.setUser_nick(rs.getString("user_nick"));
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_rank(rs.getInt("user_rank"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return user;
	}
	@Override
	public int getRcm(UserInfo user, CafeInfo cafeinfo,Connection conn) {
		String sql = "";
		sql+="select count(*) cnt from user_rcm";
		sql+=" where user_no = ? and";
		sql+=" cafe_no=?";
		int res=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,user.getUser_no());
			ps.setInt(2, cafeinfo.getCafe_no());
			
			rs=ps.executeQuery();
			
			while( rs.next() ) {
				res=rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
		}
		
		
		return res;
	}
	
	@Override
	public int deleteRcm(UserInfo user, CafeInfo cafeinfo, Connection conn) {
		String sql="";
		sql+="delete from user_rcm where user_no=? and cafe_no=?";
		int res=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_no());
			ps.setInt(2, cafeinfo.getCafe_no());
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	@Override
	public int insertRcm(UserInfo user, CafeInfo cafeinfo, Connection conn) {
		String sql ="";
		sql+="insert into user_rcm(user_no,cafe_no)";
		sql+=" values(?,?)";
		int res =0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,user.getUser_no());
			ps.setInt(2, cafeinfo.getCafe_no());
			
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	@Override
	public int updateRcm(CafeInfo cafeinfo,Connection conn) {
		String sql="";
		sql+="update  cafe_info";
		sql+=" set cafe_rcm = (select count(*) cnt from user_rcm where cafe_no=?)";
		sql+=" where cafe_no=?";
		int res=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,cafeinfo.getCafe_no());
			ps.setInt(2,cafeinfo.getCafe_no());
			
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	@Override
	public int getuserRcm(int userno,CafeInfo cafeno,Connection conn) {
		String sql="";
		sql+="select count(*) cnt from user_rcm";
		sql+=" where user_no=? and cafe_no=?";
		int res=0;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, userno);
			ps.setInt(2, cafeno.getCafe_no());
			rs=ps.executeQuery();
			rs.next();
				res=rs.getInt("cnt");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return res;
	}
	
	@Override
	public float getStarAvg(Connection conn, CafeInfo cafeno) {
		String sql="";
		sql+="select round(avg(star_score),1) rnd from cafe_review";
		sql+=" where cafe_no = ?";
		float avg=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafeno.getCafe_no());
			rs=ps.executeQuery();
			rs.next();
			avg=rs.getFloat("rnd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return avg;
	}
	
}


