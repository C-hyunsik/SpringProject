package user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;

@Controller
@RequestMapping("/user")
public class UserUploadController {
	@Autowired
	private UserUploadService userUploadService;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-145";
	
	@RequestMapping(value="uploadForm")
	public String uploadForm() {
		return "/upload/uploadForm";
	}
	@RequestMapping(value="uploadAJaxForm")
	public String uploadAJaxForm() {
		return "/upload/uploadAJaxForm";
	}
	
	//1개의 이미지만 넣을 때
//	@RequestMapping(value="upload", method=RequestMethod.POST)
//	@ResponseBody
//	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
//						 @RequestParam MultipartFile img,
//						 HttpSession session) {
//		
//		//실제 폴더
//		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
//		System.out.println("실제 폴더 : " + filePath);
//		String imageOriginalFileName = img.getOriginalFilename();
//		File file = new File(filePath,imageOriginalFileName);
//		try {
//			img.transferTo(file);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String result = "<span>" + 
//		"<img src='/spring/storage/" + imageOriginalFileName + 
//		"' alt='" + imageOriginalFileName + "'width='300' height='300'/>";
//		
//		System.out.println(userUploadDTO.getImageName() + ", " +
//						   userUploadDTO.getImageContent() + ", " +
//						   userUploadDTO.getImageFileName() + ", " +
//						   userUploadDTO.getImageOriginalFileName());
//		
//		userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
//		
//		//DB
//		
//		
//		return result;
//	}
	//2개 이상의 이미지
//	@RequestMapping(value="upload", method=RequestMethod.POST)
//	@ResponseBody
//	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
//						 @RequestParam MultipartFile[] img,
//						 HttpSession session) {
//		
//		//실제 폴더
//		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
//		System.out.println("실제 폴더 : " + filePath);
//		
//		String imageOriginalFileName;
//		File file;
//		String result;
//		
//		//---------------------
//		imageOriginalFileName = img[0].getOriginalFilename();
//		file = new File(filePath,imageOriginalFileName);
//		try {
//			img[0].transferTo(file);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		result = "<span>" + 
//				"<img src='/spring/storage/" + imageOriginalFileName + 
//				"' alt='" + imageOriginalFileName + "'width='300' height='300'/>";
//		//---------------------
//		imageOriginalFileName = img[1].getOriginalFilename();
//		file = new File(filePath,imageOriginalFileName);
//		try {
//			img[1].transferTo(file);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		result += "<span>" + 
//		"<img src='/spring/storage/" + imageOriginalFileName + 
//		"' alt='" + imageOriginalFileName + "'width='300' height='300'/>";
//		
//		return result;
//	}
	
	//한 개 또는 여러개(드래그)
	//파일명에 한글 또는 공백이 있어도 업로드가 된다.
	@RequestMapping(value="upload", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
						 @RequestParam("img") List<MultipartFile> list,
						 HttpSession session) {
		
		//실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 : " + filePath);
		
		String imageOriginalFileName;
		File file;
		String result = "" ;
		String imageFileName;
		
		//파일들을 모아서 DB로 보내기
		List<UserUploadDTO> imgList = new ArrayList<>();
		
		for(MultipartFile img : list) {
			imageFileName = UUID.randomUUID().toString();
			
			imageOriginalFileName = img.getOriginalFilename();			
			file = new File(filePath,imageOriginalFileName);
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			try {
				result += "<span>"
					   + "<img src='/spring/storage/"
//					   + URLEncoder.encode(imageOriginalFileName,"UTF-8") => 파일명에 공백이 있으면 안된다
					   + imageOriginalFileName
					   + "' alt='" + imageOriginalFileName + "'width='300' height='300'/>";
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			
			UserUploadDTO dto = new UserUploadDTO();
			
			dto.setImageName(userUploadDTO.getImageName());
			dto.setImageContent(userUploadDTO.getImageContent());
			dto.setImageFileName(imageFileName);
			dto.setImageOriginalFileName(imageOriginalFileName);
			imgList.add(dto);
		}//for
		
		//DB
		userUploadService.imgUpload(imgList);
		return result;
	}
	@RequestMapping(value="uploadList")
	public ModelAndView uploadList() {
		ModelAndView mav = new ModelAndView();
		List<UserUploadDTO> list = userUploadService.uploadList();
		mav.addObject("list",list);
		mav.setViewName("/upload/uploadList");
		return mav;
	}
	
	@RequestMapping(value="ncpUpload", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String ncpUpload(@ModelAttribute UserUploadDTO userUploadDTO,
						 @RequestParam("img") List<MultipartFile> list,
						 HttpSession session) {
		
		//Naver Cloud PlateForm
		
		//네이버 클라우드 Object Storage
		String imageFileName;
		String imageOriginalFileName;
		File file;
		String result = "" ;
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		//파일들을 모아서 DB로 보내기
		List<UserUploadDTO> imgList = new ArrayList<>();
		
		for(MultipartFile img : list) {
			imageFileName = objectStorageService.uploadFile(bucketName,"storage/", img);
			//imageFileName = UUID.randomUUID().toString();
			
			imageOriginalFileName = img.getOriginalFilename();		
			file = new File(filePath,imageOriginalFileName);
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			try {
				result += "<span>"
					   + "<img src='/spring/storage/"
//					   + URLEncoder.encode(imageOriginalFileName,"UTF-8") => 파일명에 공백이 있으면 안된다
					   + imageOriginalFileName
					   + "' alt='" + imageOriginalFileName + "'width='300' height='300'/>";
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			
			UserUploadDTO dto = new UserUploadDTO();
			
			dto.setImageName(userUploadDTO.getImageName());
			dto.setImageContent(userUploadDTO.getImageContent());
			dto.setImageFileName(imageFileName);
			dto.setImageOriginalFileName(imageOriginalFileName);
			imgList.add(dto);
		}//for
		
		//DB	objectStorageService
		userUploadService.imgUpload(imgList);
		return result;
	}
	@RequestMapping(value="uploadView")
	public String uploadView(@RequestParam String seq, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.uploadView(seq);
		model.addAttribute("userUploadDTO",userUploadDTO);
		return "/upload/uploadView";
	}
	@RequestMapping(value="uploadUpdateForm")
	public String uploadUpdateForm(@RequestParam String seq, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.uploadView(seq);
		model.addAttribute("userUploadDTO",userUploadDTO);
		return "/upload/uploadUpdateForm";
	}
	@RequestMapping(value="ncpUpdate", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String ncpUpdate(@ModelAttribute UserUploadDTO userUploadDTO,
						 @RequestParam("img") MultipartFile img) {
		userUploadService.imgUpdate(userUploadDTO, img);
		
		return "이미지 수정 완료";
	}
	@RequestMapping(value="ncpDelete", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String ncpDelete(@RequestParam int seq) {
		userUploadService.ncpDelete(seq);
		
		return "이미지 삭제 완료";
	}
	@RequestMapping(value="checkDelete")
	@ResponseBody
	public int checkDelete(@RequestParam("selectedSeqs[]") List<Integer> list) {
		int count = 0;
		for(int seq : list) {
			userUploadService.ncpDelete(seq);
			count++;
		}
		return count;
	}
}
