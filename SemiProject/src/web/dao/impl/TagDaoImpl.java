package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.TagDao;
import web.dto.Cafe;
import web.dto.Tag;
import web.dto.TagConn;

public class TagDaoImpl implements TagDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	
	@Override
	public List<Tag> selectAll(Connection conn) {

		String sql = "";
		sql += "SELECT";
		sql += " 		tag_no";
		sql += " 		, tag_name";
		sql += " FROM tag";
		sql += " ORDER BY tag_no";
		
		//--조회 결과 저장할 List
		List<Tag> tagList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				Tag t = new Tag(); //결과값 저장 객체
				
				//결과값 한 행 처리
				t.setTag_no(rs.getInt("tag_no"));
				t.setTag_name(rs.getString("tag_name"));
				
				//리스트객체에 조회한 행 객체 저장
				tagList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return tagList;
	}

	
	
	@Override
	public List<Tag> selectAll(Connection conn, Paging paging) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			tag_no, tag_name";
		sql += "		FROM tag";
		sql += "		ORDER BY tag_no DESC";
		sql += " 	) B";
		sql += " ) tag";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Tag> tagList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				Tag t = new Tag(); //결과값 저장 객체
				
				//결과값 한 행 처리
				t.setTag_no(rs.getInt("tag_no"));
				t.setTag_name(rs.getString("tag_name"));
				
				//리스트객체에 조회한 행 객체 저장
				tagList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return tagList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM tag";
		
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
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
	}

	@Override
	public int deleteUser(Connection conn, Tag tag) {

		String sql = "";
		sql += "DELETE FROM tag";
		sql += " WHERE tag_no = ?";
		
		//왜 얘도 -1이지
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			System.out.println(tag.getTag_no());
			ps.setInt(1, tag.getTag_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	@Override
	public Tag selectTagByTag_no(Connection conn, Tag tag_no) {

		String sql = "";
		sql += "SELECT";
		sql += " 		tag_no";
		sql += " 		, tag_name";
		sql += " FROM tag";
		sql += " WHERE tag_no = ?";
		
		//결과 저장할 DTO객체
		Tag tag = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, tag_no.getTag_no());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				//결과값 저장 객체
				//ArrayList 대신 Board로 반환. (게시글 하나만 조회하므로)
				tag = new Tag();
				
				//결과값 행 처리
				tag.setTag_no(rs.getInt("tag_no"));
				tag.setTag_name(rs.getString("tag_name"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return tag;
	}
	
	@Override
	public int insert(Connection conn, Tag tag) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO tag(tag_no, tag_name)";
		sql += " VALUES (?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, tag.getTag_no());
			ps.setString(2, tag.getTag_name());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int selectTag_no(Connection conn) {

		String sql = "";
		sql += "SELECT tag_no_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextTagno = 0;
		
		
		try {
			
			//SQL 수행객체
			ps = conn.prepareStatement(sql);

			//SQL 수행 및 결과집합 저장
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				//왜 1이 들어갈까? (첫번째 인자라는 소리인가)
				nextTagno = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return nextTagno;
	}
	
	@Override
	public int insertTagConn(Connection conn, TagConn tagConn) {
		
		String sql = "";
		sql += "INSERT INTO tag_conn( cafe_no, tag_no )";
		sql += " VALUES (?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt( 1, tagConn.getCafeNo() );
			ps.setInt( 2, tagConn.getTagNo() );
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	

	@Override
	public int updateTagConn(Connection conn, TagConn tagConn) {
		
		String sql = "";
		sql += "UPDATE tag_conn";
		sql += " SET tag_no = ?";
		sql += " WHERE cafe_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt( 1, tagConn.getTagNo() );
			ps.setInt( 2, tagConn.getCafeNo() );
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public List<Tag> searchTagList(Connection conn, Paging paging) {

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			tag_no, tag_name";
		sql += "		FROM tag";
		sql += "		WHERE tag_name LIKE ? ";
		sql += "		ORDER BY tag_no DESC";
		sql += " 	) B";
		sql += " ) tag";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Tag> tagSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				Tag tag= new Tag();
				
				tag.setTag_no(rs.getInt("tag_no"));
				tag.setTag_name(rs.getString("tag_name"));
				
				tagSearchList.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return tagSearchList;
	}
		
		

	
	@Override
	public int selectCntTagSearch(Connection conn, String data) {

		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM tag";
		sql += "	WHERE tag_name LIKE ?";
		sql += "	ORDER BY tag_no DESC";
					
		//총 게시글 수
		int count = 0;
					
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");
			System.out.println("아이디 데이터: " +data);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			System.out.println("아이디카운트 : " + count );
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
			
		return count;

	}
	
	@Override
	public int deleteTag(Cafe cafe,Connection conn) {
		String sql="";
		sql+="delete from tag_conn";
		sql+=" where cafe_no =?";
		int res =0;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cafe.getCafe_no());
			
			res=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
		
	}
	
	
	
}//end




