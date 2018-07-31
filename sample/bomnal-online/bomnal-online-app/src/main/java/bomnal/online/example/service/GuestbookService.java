package bomnal.online.example.service;

import java.io.IOException;
import java.util.List;

import bomnal.online.example.dto.Guestbook;
import hone.bom.dao.search.SearchParameters;

public interface GuestbookService {

	public List<Guestbook> getGuestbookList( SearchParameters searchParameters );

	public Guestbook detailGuestbook( Guestbook guestbook );

	public int insertGuestbook( Guestbook guestbook ) throws IOException;

	public int modifyGuestbook( Guestbook guestbook );

	public int deleteGuestbook( Guestbook guestbook );
	
	//다이나믹 in절 쿼리 
	public Guestbook getGuestbookIn(Guestbook guestbook );

	//다이나믹 case절 쿼리 
	public Guestbook getGuestbookCase(Guestbook guestbook );
	
	//blob 예제1 
	public int blobExam(Guestbook guestbook ) throws IOException;

}
