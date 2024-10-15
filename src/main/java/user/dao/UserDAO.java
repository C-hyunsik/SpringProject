package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import user.bean.UserDTO;
import user.bean.UserUploadDTO;

@Mapper
public interface UserDAO {

	public String searchUser(String id);

	public void userWrite(UserDTO userDTO);

	public List<UserDTO> userList(Map<String, Integer> map);

	public int getTotalA();
	
	public void userUpdate(UserDTO userDTO);

	public void userDelete(String id);

	public List<UserDTO> getUserInfo(String id);
}
