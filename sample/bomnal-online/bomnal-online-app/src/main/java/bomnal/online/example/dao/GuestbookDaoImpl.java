package bomnal.online.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bomnal.online.example.dto.Guestbook;
import hone.bom.dao.hqml.support.HqmlDefaultDaoSupport;
import hone.bom.dao.search.SearchParameters;

@Repository
public class GuestbookDaoImpl extends HqmlDefaultDaoSupport implements GuestbookDao {
	private static final Logger logger = LoggerFactory.getLogger(GuestbookDaoImpl.class);
	
	@Override
	public Guestbook detailGuestbook(final Guestbook guestBook) {
		return queryForObject(DETAIL_GUESTBOOK_QUERY, guestBook, Guestbook.class);
	}

	@Override
	public int selectGuestbookCount(final SearchParameters searchParameters) {
		return queryForValue(SELECT_GUESTBOOK_COUNT_QUERY, null, searchParameters, Integer.class);
	}

	@Override
	public List<Guestbook> getGuestbookList(final SearchParameters searchParameters) {
		return query(GET_GUESTBOOK_LIST_QUERY, null, searchParameters, Guestbook.class);
	}

	@Override
	public int insertGuestbook(final Guestbook guestBook) {
		return update(INSERT_GUESTBOOK_QUERY, guestBook);
	}

	@Override
	public int modifyGuestbook(final Guestbook guestBook) {
		return update(UPDATE_GUESTBOOK_QUERY, guestBook);
	}

	@Override
	public int deleteGuestbook(Guestbook guestBook) {
		return update(DELETE_GUESTBOOK_QUERY, guestBook);

	}
	
	@Override
	public Guestbook getGuestbookIn(final Guestbook guestBook) {
		return queryForObject(GET_GUESTBOOK_IN_QUERY, guestBook, Guestbook.class);
	}
	
	@Override
	public Guestbook getGuestbookCase(final Guestbook guestBook) {
		return queryForObject(GET_GUESTBOOK_CASE_QUERY, guestBook, Guestbook.class);
	}


	@Override
	public int blobExam(final Guestbook guestBook) {
		return update(GET_BLOB_EXAM1_QUERY, guestBook);
	}

	
}
