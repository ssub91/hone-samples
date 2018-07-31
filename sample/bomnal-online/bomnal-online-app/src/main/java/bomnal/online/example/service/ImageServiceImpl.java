package bomnal.online.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bomnal.online.example.dao.ImageDao;
import bomnal.online.example.dto.Image;
import hone.bom.annotation.ServiceId;
import hone.bom.annotation.ServiceName;


@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageDao imageDao;
	

	@Override
	@ServiceId("IM001")
	@ServiceName("이미 생성")
	@Transactional
	public Boolean insertImage(Image image) {
		
		Long no = imageDao.insertImage(image);
		return no != null;
	}

//	@Override
//	@ServiceId("GB004")
//	@ServiceName("방명록 수정")
//	@Transactional
//	public int modifyGuestbook(Guestbook guestbookDto) {
//		return guestbookDao.modifyGuestbook(guestbookDto);
//	}



	
}
