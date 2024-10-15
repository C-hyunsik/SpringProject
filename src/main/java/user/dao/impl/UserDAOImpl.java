//package user.dao.impl;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import user.bean.UserDTO;
//import user.dao.UserDAO;
//
//@Repository
//@Transactional
//public class UserDAOImpl implements UserDAO{
//	@Autowired
//	private SqlSession sqlSession;
//	
//	@Override
//	public String searchUser(String id) {
//		return sqlSession.selectOne("userSQL.searchUser", id);
//	}
//	@Override
//	public void userWrite(UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public List<UserDTO> getUserList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public void userDelete(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void userUpdate(UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		
//	}
//}
