package spms.dao;
import java.util.*;
import spms.vo.Member;



public interface MemberDao {
	List<Member> selectList() throws Exception;
	int insert(Member member) throws Exception;
	Member selectOne(int no)  throws Exception;
	int update(Member member) throws Exception;
	int delete(int no) throws Exception;
	Member exist(String email) throws Exception;
}
