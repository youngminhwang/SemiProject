package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.CafeDao;
import web.dto.Cafe;
import web.dto.CafeFile;
import web.dto.CafeTag;

public class CafeDaoImpl implements CafeDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Cafe> selectAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	cafe_no";
		sql += "	, cafe_name";
//		sql += "	, cafe_tel";
//		sql += "	, cafe_time";
//		sql += "	, cafe_park";
//		sql += "	, cafe_rcm";
		sql += " FROM cafe_info";
		sql += " ORDER BY cafe_no DESC";
		
		//--조회 결과 저장할 List
		List<Cafe> cafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				Cafe c = new Cafe(); //결과값 저장 객체
				
				//결과값 한 행 처리
				c.setCafe_no(rs.getInt("cafe_no"));
				c.setCafe_name( rs.getString("cafe_name") );
//				c.setCafe_tel( rs.getString("cafe_tel") );
//				c.setCafe_time( rs.getString("cafe_time") );
//				c.setCafe_park( rs.getString("cafe_park") );
//				c.setCafe_rcm( rs.getInt("cafe_rcm") );
				
				//리스트객체에 조회한 행 객체 저장
				cafeList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return cafeList;
	}
	
	@Override
	public List<Cafe> selectAll(Connection conn, Paging paging) {
	
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			cafe_no, cafe_name";
		sql += "		FROM cafe_info";
		sql += "		ORDER BY cafe_no DESC";
		sql += " 	) B";
		sql += " ) cafe_info";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Cafe> cafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				Cafe c = new Cafe(); //결과값 저장 객체
				
				///결과값 한 행 처리
				c.setCafe_no(rs.getInt("cafe_no"));
				c.setCafe_name( rs.getString("cafe_name") );
//				c.setCafe_tel( rs.getString("cafe_tel") );
//				c.setCafe_time( rs.getString("cafe_time") );
//				c.setCafe_park( rs.getString("cafe_park") );
//				c.setCafe_rcm( rs.getInt("cafe_rcm") );
//				
				//리스트객체에 조회한 행 객체 저장
				cafeList.add(c);
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
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
	}
	
	
	@Override
	public Cafe selectCafeByCafe_no(Connection conn, Cafe cafe_no) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	cafe_no";
		sql += "	, cafe_name";
		sql += "	, cafe_menu";
		sql += "	, cafe_tel";
		sql += "	, cafe_time";
		sql += "	, cafe_park";
		sql += "	, cafe_rcm";
		sql += "	, cafe_loc";
		sql += " FROM cafe_info";
		sql += " WHERE cafe_no = ?";
		
		//--조회 결과 저장할 List
		Cafe cafe = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, cafe_no.getCafe_no());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				
				cafe = new Cafe();//결과값 저장 객체
				
				//결과값 한 행 처리
				cafe.setCafe_no(rs.getInt("cafe_no"));
				cafe.setCafe_name( rs.getString("cafe_name") );
				cafe.setCafe_menu( rs.getString("cafe_menu") );
				cafe.setCafe_tel( rs.getString("cafe_tel") );
				cafe.setCafe_time( rs.getString("cafe_time") );
				cafe.setCafe_park( rs.getString("cafe_park") );
				cafe.setCafe_loc( rs.getString("cafe_loc") );
				cafe.setCafe_rcm( rs.getInt("cafe_rcm") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return cafe;
	}
	
	
	@Override
	public int selectCafe_no(Connection conn) {
		
		String sql = "";
		sql += "SELECT cafe_no_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextCafe_no = 0;
		
		
		try {
			
			//SQL 수행객체
			ps = conn.prepareStatement(sql);

			//SQL 수행 및 결과집합 저장
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				//왜 1이 들어갈까? (첫번째 인자라는 소리인가)
				nextCafe_no = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return nextCafe_no;
	}

	@Override
	public int insert(Connection conn, Cafe cafe) {
		
		String sql = "";
		sql += "INSERT INTO cafe_info(cafe_no, cafe_name, cafe_menu, cafe_tel, cafe_time, cafe_park, cafe_loc, cafe_rcm)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cafe.getCafe_no());
			ps.setString(2, cafe.getCafe_name());
			ps.setString(3, cafe.getCafe_menu());
			ps.setString(4, cafe.getCafe_tel());
			ps.setString(5, cafe.getCafe_time());
			ps.setString(6, cafe.getCafe_park());
			ps.setString(7, cafe.getCafe_loc());
			
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertFile(Connection conn, CafeFile cafeFile) {

		String sql = "";
		sql += "INSERT INTO cafe_photo( cafe_file_no, cafe_no, cafe_org_file_name, cafe_cpy_file_name, cafe_cpy_size )";
		sql += " VALUES (cafe_file_no_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cafeFile.getCafe_no());
			ps.setString(2, cafeFile.getCafe_org_file_name());
			ps.setString(3, cafeFile.getCafe_cpy_file_name());
			ps.setInt(4, cafeFile.getCafe_cpy_size());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	
	@Override
	public int deleteCafeFile(Connection conn, Cafe cafe) {
		
		String sql = "";
		sql += "DELETE cafe_photo";
		sql += " WHERE cafe_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cafe.getCafe_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	@Override
	public int deleteCafe(Connection conn, Cafe cafe) {
		
		String sql = "";
		sql += "DELETE cafe_info";
		sql += " WHERE cafe_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cafe.getCafe_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	@Override
	public CafeFile selectCafeFile(Connection conn, Cafe viewCafe) {

		String sql = "";
		sql += "SELECT";
		sql += " 		cafe_file_no";
		sql += " 		, cafe_no";
		sql += " 		, cafe_org_file_name";
		sql += " 		, cafe_cpy_file_name";
		sql += " 		, cafe_cpy_size";
		sql += " FROM cafe_photo";
		sql += " WHERE cafe_no = ?";
		
		//결과 저장할 DTO객체
		CafeFile cafeFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewCafe.getCafe_no());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			while(rs.next()) {
				
				cafeFile = new CafeFile(); //결과값 저장 객체
				
				//결과값 행 처리
				cafeFile.setCafe_file_no(rs.getInt("cafe_file_no"));
				cafeFile.setCafe_no(rs.getInt("cafe_no"));
				cafeFile.setCafe_org_file_name(rs.getString("cafe_org_file_name"));
				cafeFile.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				cafeFile.setCafe_cpy_size(rs.getInt("cafe_cpy_size"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cafeFile;
	}
	
	
	@Override
	public CafeTag selectTag_noByCafe_no(Connection conn, Cafe cafe) {

		//SQL 작성
		String sql = "";
		sql += "SELECT ";
		sql += "	LISTAGG(t.tag_name, ', ') WITHIN GROUP(ORDER BY cafe_no) AS result";
		sql += "	FROM tag_conn c, tag t";
		sql += "	WHERE c.tag_no = t.tag_no";
		sql += "   AND cafe_no = ?";
		sql += "   ORDER BY cafe_no";
		
		
		//조회결과 저장할 List
		CafeTag cafetag = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cafe.getCafe_no());
			
			System.out.println(cafe.getCafe_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				cafetag = new CafeTag();
				
				//결과값 한 행 처리
				cafetag.setTag_name(rs.getString("result"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return cafetag;
	}
	
	@Override
	   public int update(Connection conn, Cafe cafe) {
	      
	      String sql = "";
//	      (cafe_name, cafe_menu, cafe_tel, cafe_time, cafe_park, cafe_loc)
	      sql += "UPDATE cafe_info";
	      sql += " set ";
	      sql +=      " cafe_name = ?";
	      sql +=      ", cafe_menu = ?";
	      sql +=      ", cafe_tel = ?";
	      sql +=      ", cafe_time = ?";
	      sql +=      ", cafe_park = ?";
	      sql +=      ", cafe_loc = ? ";
//	      sql +=      ", cafe_rcm = ? ";
	      sql += " WHERE cafe_no = ?";
	      
	      int res = 0;
	      
	      try {
	         //DB작업
	         ps = conn.prepareStatement(sql);
	         System.out.println("카페정보"+cafe);
	         ps.setString(1, cafe.getCafe_name());
	         ps.setString(2, cafe.getCafe_menu());
	         ps.setString(3, cafe.getCafe_tel());
	         ps.setString(4, cafe.getCafe_time());
	         ps.setString(5, cafe.getCafe_park());
	         ps.setString(6, cafe.getCafe_loc());
	         ps.setInt(7, cafe.getCafe_no());
	         
	         
	         res = ps.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(ps);
	      }
	      
	      return res;
	   }
	
	
	@Override
	public List<Cafe> searchNameList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			cafe_no, cafe_name";
		sql += " 		FROM cafe_info";
		sql += "		WHERE Cafe_name LIKE ? ";
		sql += "		ORDER BY cafe_no DESC";
		sql += "	) B";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ? ";
		
		List<Cafe> cafeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				Cafe cafe= new Cafe();
				
				cafe.setCafe_no(rs.getInt("cafe_no"));
				cafe.setCafe_name(rs.getString("cafe_name"));
				
				cafeSearchList.add(cafe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cafeSearchList;
	}
	
	@Override
	public int selectCntNameSearch(Connection conn, String data) {

		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM cafe_info";
		sql += "	WHERE cafe_name LIKE ?";
		sql += "	ORDER BY cafe_no DESC";
					
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
	   public int updateFile(Connection conn, CafeFile cafeFile) {
	      String sql ="";
	      sql+="update cafe_photo set cafe_org_file_name = ? ";
	      sql+=" ,cafe_cpy_file_name=?";
	      sql+=" where cafe_file_no =?";
	      
	      int res=0;
	      try {
	         ps=conn.prepareStatement(sql);
	         
	         ps.setString(1, cafeFile.getCafe_org_file_name());
	         ps.setString(2,cafeFile.getCafe_cpy_file_name());
	         ps.setInt(3, cafeFile.getCafe_file_no());
	         
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
