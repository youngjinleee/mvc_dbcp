package ex0416.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ex0416.domain.Member;
import ex0416.util.DbUtil;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insert(Member member) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql="insert into member(id, pwd, name, age,phone, addr , join_date)"
				+ " values(?,?,?,?,?,? , sysdate)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getId());	
			ps.setString(2, member.getPwd());	
			ps.setString(3, member.getName());	
			ps.setInt(4, member.getAge());	
			ps.setString(5, member.getPhone());	
			ps.setString(6, member.getAddr());
			
			result= ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public boolean idCheck(String id) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean result=false;
		String sql="select *from member where  upper(id)=upper(?)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, id.trim().toUpperCase());
			ps.setString(1, id);	
			rs = ps.executeQuery();
			if(rs.next())result=true;
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return result;
	}

	@Override
	public List<Member> selectAll() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Member> list =new ArrayList<Member>();
		String sql=
		"SELECT ID, PWD, NAME, AGE, PHONE, ADDR ,JOIN_DATE FROM MEMBER";
		try {
			//로드 연결 실행 닫기
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?의 개수만큼 setXxx() 필요하다.
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = 
			       new Member(rs.getString(1), rs.getString(2),
					  rs.getString(3), rs.getInt(4), rs.getString(5),
					   rs.getString(6), rs.getString(7));
				
				//list에 추가
				list.add(member);
				
			}
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public Member selectById(String id) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Member member = null;
		String sql=
		"SELECT ID, PWD, NAME, AGE, PHONE, ADDR ,JOIN_DATE "
		+ "FROM MEMBER where id=?";
		try {
			//로드 연결 실행 닫기
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?의 개수만큼 setXxx() 필요하다.
			ps.setString(1, id.trim());
			rs = ps.executeQuery();
			if(rs.next()) {
				 member = 
			       new Member(rs.getString(1), rs.getString(2),
					  rs.getString(3), rs.getInt(4), rs.getString(5),
					   rs.getString(6), rs.getString(7));
			}
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return member;
	}

	@Override
	public List<Member> searchByKeyword(String keyField, String keyWord) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Member> list =new ArrayList<Member>();
		String sql=
		"SELECT ID, PWD, NAME, AGE, PHONE, ADDR ,JOIN_DATE FROM MEMBER ";
		try {
			if(keyField.equals("id"))
				sql+="where id like ?";
			else if(keyField.equals("name"))
				sql+="where name like ?";
			else if(keyField.equals("addr"))
				sql+="where addr like ?";
			
			//로드 연결 실행 닫기
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?의 개수만큼 setXxx() 필요하다.
			ps.setString(1, "%"+keyWord.trim()+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = 
			       new Member(rs.getString(1), rs.getString(2),
					  rs.getString(3), rs.getInt(4), rs.getString(5),
					   rs.getString(6), rs.getString(7));
				
				//list에 추가
				list.add(member);
			}
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public int delete(String id) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql="delete from member where id = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);	
			result= ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

}
