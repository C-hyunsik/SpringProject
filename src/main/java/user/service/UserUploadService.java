package user.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;

public interface UserUploadService {
	
	public void imgUpload(List<UserUploadDTO> imgList);

	public List<UserUploadDTO> uploadList();

	public UserUploadDTO uploadView(String seq);

	public void imgUpdate(UserUploadDTO userUploadDTO, MultipartFile img);

	public void ncpDelete(int seq);

}
