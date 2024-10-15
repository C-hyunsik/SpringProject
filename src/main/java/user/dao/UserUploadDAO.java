package user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserUploadDTO;

@Mapper
public interface UserUploadDAO {

	public void imgUpload(List<UserUploadDTO> imgList);

	public List<UserUploadDTO> uploadList();

	public UserUploadDTO uploadView(String seq);

	public void imgUpdate(UserUploadDTO userUploadDTO);

	public String getImageFileName(int seq);

	public void ncpDelete(int seq);

	public List<String> getCheckedFileName(int seq);
}
