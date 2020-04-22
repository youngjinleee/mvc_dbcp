package ex0416.dao;

import java.sql.SQLException;
import java.util.List;

import ex0416.domain.Member;

/**
 * ȸ�������� �ʿ��� CRUD
 * */
public interface MemberDAO {
	/**
	 * ȸ�� ���
	 * insert into member(id, pwd, name, age,phone, addr , join_date)
	 * values(?,?,?,?,?,? , sysdate)
	 * */
	int insert(Member member)throws SQLException;
	
	/**
	 *  id �ߺ�üũ 
	 *  @return : true�̸� �ߺ�, false�̸� �ߺ��ƴ�
	 * */
	boolean idCheck(String id) throws SQLException;
	
	/**
	 * ȸ�� ��ü�˻�
	 *  : ��� ���ڵ尡 0���̻� ����. - List
	 * */
	List<Member> selectAll()throws SQLException;
	
	/**
	 * id�� �ش��ϴ� ���ڵ� �˻� - id�� Ŭ���ؼ� �󼼺��� 
	 *  : ��� ���ڵ尡 0 or 1�̸� Member 
	 * */
	Member selectById(String id) throws SQLException;
	
	/**
	 * keyField�� �ش��ϴ�(�ʵ��) keyword�� ������ ���ڵ� �˻��ϱ�
	 * ex)
	 *  select * from member where id like '%a%' or
        select * from member where name like '%a%' or
        select * from member where addr like '%a%'
	 * */
	
	List<Member> searchByKeyword(String keyField, String keyWord) throws SQLException;
	
	/**
	 * ȸ�� ����
	 * */
	int delete(String id)throws SQLException;
}













