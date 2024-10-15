package user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.bean.UserUploadDTO;
import user.dao.UserDAO;
import user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging;
	
	@Override
	public String searchUser(String id) {
		String exist = userDAO.searchUser(id);
		if(exist == null) {
			return "non_exist";
		}
		else {
			return "exist";
		}
	}
	
	@Override
	public void userWrite(UserDTO userDTO) {
		userDAO.userWrite(userDTO);
	}
	
	@Override
	public Map<String, Object> userList(String pg) {
		//1페이지당 5개씩
		int endnum = 5;
		int startnum = (Integer.parseInt(pg) * endnum) - endnum;
		Map<String, Integer> map = new HashMap<>();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		List<UserDTO> list = userDAO.userList(map);
		
		//페이징 처리
		int totalA = userDAO.getTotalA();
		userPaging.setTotalA(totalA);
		userPaging.setCurrentPage((Integer.parseInt(pg)));
		userPaging.setPageBlock(3);
		userPaging.setPageSize(endnum);
		userPaging.makePagingHTML();
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("userPaging", userPaging);
		map2.put("pg",pg);
		return map2;
	}
	
	@Override
	public List<UserDTO> userUpdateForm(String id) {
		List<UserDTO> list = userDAO.getUserInfo(id);
		return list;	
	}
	
	@Override
	public void userUpdate(UserDTO userDTO) {
		userDAO.userUpdate(userDTO);
	}
	
	@Override
	public void userDelete(String id) {
		userDAO.userDelete(id);
	}
}