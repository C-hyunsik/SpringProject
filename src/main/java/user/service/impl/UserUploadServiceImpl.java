package user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;
import user.dao.UserUploadDAO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;


@Service
public class UserUploadServiceImpl implements UserUploadService{
	@Autowired
	private UserUploadDAO userUploadDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-145";
	
	@Override
	public void imgUpload(List<UserUploadDTO> imgList) {
		userUploadDAO.imgUpload(imgList);
	}
	@Override
	public List<UserUploadDTO> uploadList() {
		return userUploadDAO.uploadList();
	}
	@Override
	public UserUploadDTO uploadView(String seq) {
		return userUploadDAO.uploadView(seq);
	}
	@Override
    public void imgUpdate(UserUploadDTO userUploadDTO, MultipartFile img) {
       //실제 폴더
       String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
       System.out.println("실제 폴더 : " + filePath);
       
       //Object Storage(NCP)는 이미지를 덮어쓰지 않는다.
       //DB에서 seq에 해당하는 imageFileName을 꺼내와서 Object Storage(NCP)의 이미지를 삭제하고,
       //새로운 이미지를 올린다.
       UserUploadDTO dto = userUploadDAO.uploadView(userUploadDTO.getSeq()+"");
       String imageFileName;
       
       if(img.getSize() != 0) {
           //이미지 파일 UUID 이름 가져오기
           imageFileName = dto.getImageFileName();
           //Object NCP 이미지 삭제
           objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
           
           //Object Storage(NCP)는 새로운 이미지 올리기
           imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
           
           String imageOriginalFileName = img.getOriginalFilename();
           File file = new File(filePath, imageOriginalFileName);
           
           try {
        	   img.transferTo(file);
           } catch (IllegalStateException | IOException e) {
               e.printStackTrace();
           }
           
           //새로운 이미지 DB에 업데이트
           userUploadDTO.setImageFileName(imageFileName);
           userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
       } else {
           //이미지 변동 없을 시 기존 이미지 그대로 업데이트
           userUploadDTO.setImageFileName(dto.getImageFileName());
           userUploadDTO.setImageOriginalFileName(dto.getImageOriginalFileName());
       }
       
       userUploadDAO.imgUpdate(userUploadDTO);
    }
	@Override
	public void ncpDelete(int seq) {
		//Object Storage(NCP)의 이미지 삭제
		String imageFileName = userUploadDAO.getImageFileName(seq);
		objectStorageService.deleteFile(bucketName,"storage/", imageFileName);
		
		//DB
		userUploadDAO.ncpDelete(seq);
	}
}
