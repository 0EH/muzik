package com.simple.basic.recode;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;

@Service
public class RecodeServiceImpl implements RecodeService {

	@Autowired
	RecodeMapper recodeMapper;
	@Value("${project.upload.path}")
	public String uploadPath;
	@Value("${music.upload.path}")
	public String musicPath;
	
	
	// 한번에 업로드 될 수 있게 transactional 사용
	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean recodeInsert(MultipartFile image, MultipartFile file ,RecodeDTO dto) {
		// 레코드 데이터 업로드
		boolean result = recodeMapper.recodeInsert(dto);
		
		// 파일 업로드
		
		String origin = file.getOriginalFilename();
	    String filename = origin.substring(origin.lastIndexOf("\\") + 1);
	    String save = musicPath + "\\"  + filename;
	    
	    System.out.println(save);
	    
	    
	    String imageOrigin = image.getOriginalFilename();
	    String imageName = imageOrigin.substring(origin.lastIndexOf("\\") + 1);
	    String imageSave = uploadPath + "\\"  + imageName;
	    System.out.println(imageSave);
	    try {
	    	File saveFile = new File(save);
	    	File saveImage= new File(imageSave);
	    	
	    	file.transferTo(saveFile);
	    	image.transferTo(saveImage);
	    	// 썸네일 생성
	    	
	    } catch (Exception e) {
	    	
	    	System.out.println("upload error 입니다.");
	    }
	    
	    //selectkey 방식으로 r_num 가져오기 -> mapper

	    //파일 경로 db 업로드
	    recodeMapper.recodeFileInsert(
	    		UploadDTO.builder().r_file(filename).r_image(imageName).r_path(uploadPath).r_name(dto.getR_name()).build()
	    		);
	    
	    
		
		return result;
	}

	@Override
	public List<RecodeDTO> recodeList1() {
		return recodeMapper.recodeList1();
	}

	@Override
	public List<UploadDTO> recodeList2() {
		return recodeMapper.recodeList2();
	}
	
	@Override
	public RecodeDTO recodeDetail1(int r_num) {
		return recodeMapper.recodeDetail1(r_num);
	}

	@Override
	public UploadDTO recodeDetail2(int r_num) {
		return recodeMapper.recodeDetail2(r_num);
	}
	
	@Override
	public boolean recodeDelete(int r_num) {
		return recodeMapper.recodeDelete(r_num);
	}

	@Override
	public boolean recodeUpdate(RecodeDTO dto) {
		return recodeMapper.recodeUpdate(dto);
	}

}
