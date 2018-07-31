package bomnal.online.example.dao;

import java.util.List;

import bomnal.online.example.dto.Guestbook;
import hone.bom.dao.search.SearchParameters;

public interface GuestbookDao {

	static final String HQML_NAME = "bomnal.online.example.dao.GuestbookDao";
	static final String DETAIL_GUESTBOOK_QUERY = HQML_NAME + "." + "detailGuestbook";
	static final String SELECT_GUESTBOOK_COUNT_QUERY = HQML_NAME + "." + "selectGuestbookCount";
	static final String GET_GUESTBOOK_LIST_QUERY = HQML_NAME + "." + "getGuestbookList";
	static final String INSERT_GUESTBOOK_QUERY = HQML_NAME + "." + "insertGuestbook";
	static final String UPDATE_GUESTBOOK_QUERY = HQML_NAME + "." + "updateGuestbook";
	static final String DELETE_GUESTBOOK_QUERY = HQML_NAME + "." + "deleteGuestbook";
	static final String GET_GUESTBOOK_IN_QUERY = HQML_NAME + "." + "getGuestBookIn";
	static final String GET_GUESTBOOK_CASE_QUERY = HQML_NAME + "." + "getGuestBookCase";
	
	//blab test
	static final String GET_BLOB_EXAM1_QUERY = HQML_NAME + "." + "blobExam";
	
	
	
	public Guestbook detailGuestbook(Guestbook guestbookDto);
	public int selectGuestbookCount(SearchParameters searchParameters);
	public List<Guestbook> getGuestbookList(SearchParameters searchParameters);
	public int insertGuestbook(Guestbook guestbookDto);
	public int modifyGuestbook(Guestbook guestbookDto);
	public int deleteGuestbook(Guestbook guestbookDto);
	public Guestbook getGuestbookIn(Guestbook guestbookDto);
	public Guestbook getGuestbookCase(Guestbook guestbookDto);
	
	public int blobExam(Guestbook guestbookDto);
	
}

