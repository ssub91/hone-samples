package bomnal.online.example.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bomnal.online.example.dao.GuestbookDao;
import bomnal.online.example.dto.Guestbook;
import hone.bom.annotation.ServiceId;
import hone.bom.annotation.ServiceName;
import hone.bom.dao.search.SearchParameters;


@Service
public class GuestbookServiceImpl implements GuestbookService{
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@Override
	@ServiceId("GB001")
	@ServiceName("방명록목록 조회")
	public List<Guestbook> getGuestbookList(SearchParameters searchParameters) {
	
	    searchParameters.set("offsetNum", (searchParameters.getCurrentPage()-1) * searchParameters.getVolumePerPage());
    	searchParameters.set("maxNum", searchParameters.getCurrentPage() * searchParameters.getVolumePerPage());
		
		
		return guestbookDao.getGuestbookList(searchParameters);
	}

	@Override
	@ServiceId("GB002")
	@ServiceName("방명록 상세조회")
	public Guestbook detailGuestbook(Guestbook guestbookDto) {
		return guestbookDao.detailGuestbook(guestbookDto);
	}

	@Override
	@ServiceId("GB003")
	@ServiceName("방명록 생성")
	@Transactional
	public int insertGuestbook(Guestbook guestbookDto) {
		
		return guestbookDao.insertGuestbook(guestbookDto);
	}

	@Override
	@ServiceId("GB004")
	@ServiceName("방명록 수정")
	@Transactional
	public int modifyGuestbook(Guestbook guestbookDto) {
		return guestbookDao.modifyGuestbook(guestbookDto);
	}

	@Override
	@ServiceId("GB005")
	@ServiceName("방명록삭제")
	@Transactional
	public int deleteGuestbook(Guestbook guestbookDto) {
		return guestbookDao.deleteGuestbook(guestbookDto);
	}
	
	@Override
	@ServiceId("GB006")
	@ServiceName("방명록 IN절조회")
	public Guestbook getGuestbookIn(Guestbook guestbookDto) {
		
		List<String> nameList = new ArrayList<String>();
		
		nameList.add("이수빈");
	    nameList.add("이흥주");
	    nameList.add("나보리");
		
	    guestbookDto.setNameList(nameList);
		return guestbookDao.getGuestbookIn(guestbookDto);
	}
	
	@Override
	@ServiceId("GB007")
	@ServiceName("방명록 CASE절조회")
	public Guestbook getGuestbookCase(Guestbook guestbookDto) {
		return guestbookDao.getGuestbookCase(guestbookDto);
	}
	
	@Override
	@ServiceId("GB008")
	@ServiceName("BLOB_EXAM")
	@Transactional
	public int blobExam(Guestbook guestbookDto) throws IOException {

		File f = new File(guestbookDto.getBlob_name());
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
		  FileInputStream fis = new FileInputStream(f);
		  byte[] buf = new byte[1024];
		  while(true) {
		   int x = fis.read();
		   if(x == -1) break;
		   bos.write(buf,0,x);
	  }
		  fis.close();
		  bos.close(); 
		  ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		  
		  byte[] bytes = bos.toByteArray();
		  guestbookDto.setBlob_file(bytes);
		  return guestbookDao.blobExam(guestbookDto);
	}
	
}
