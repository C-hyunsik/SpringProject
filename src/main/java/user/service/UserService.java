package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import user.bean.UserDTO;
import user.bean.UserUploadDTO;

@Component
public interface UserService {

	public String searchUser(String id);

	public void userWrite(UserDTO userDTO);

	public Map<String, Object> userList(String pg);

	public List<UserDTO> userUpdateForm(String id);

	public void userUpdate(UserDTO userDTO);

	public void userDelete(String id);

}
