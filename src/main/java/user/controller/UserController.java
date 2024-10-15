package user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "user/writeForm";
	}
	
	@RequestMapping(value="listForm", method = RequestMethod.GET)
	public String listForm(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		Map<String, Object> map2 = userService.userList(pg);
		model.addAttribute("map2",map2);
		model.addAttribute("pg",pg);
		return "/user/listForm";	// => /WEB-INF/user/list.jsp
	}
	
	@RequestMapping(value="searchUser", method = RequestMethod.POST)
	@ResponseBody
	public String searchUser(String id) {
		return userService.searchUser(id);
	}
	
	@RequestMapping(value="userWrite", method = RequestMethod.POST)
	@ResponseBody
	public void userWrite(@ModelAttribute UserDTO userDTO) {
		userService.userWrite(userDTO);
	}
	
	@RequestMapping(value="updateForm", method = RequestMethod.GET)
	public String updateForm(@RequestParam String id, Model model) {
		List<UserDTO> list = userService.userUpdateForm(id);
		model.addAttribute("list",list);
		return "user/updateForm";
	}
	
	@RequestMapping(value="userUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void userUpdate(@ModelAttribute UserDTO userDTO) {
		userService.userUpdate(userDTO);
	}
	
	@RequestMapping(value="deleteForm", method = RequestMethod.GET)
	public String deleteForm(@RequestParam String id, Model model) {
		List<UserDTO> list = userService.userUpdateForm(id);
		model.addAttribute("list",list);
		return "user/deleteForm";
	}
	
	@RequestMapping(value="userDelete", method = RequestMethod.POST)
	@ResponseBody
	public void userDelete(@RequestParam String id) {
		userService.userDelete(id);
	}
	
}
