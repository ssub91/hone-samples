package bomnal.online.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bomnal.online.example.dto.Memo;
import hone.bom.dao.hqml.support.HqmlDefaultDaoSupport;
import hone.bom.dao.search.SearchParameters;

@Repository
public class MemoDaoImpl extends HqmlDefaultDaoSupport implements MemoDao {
	private static final Logger logger = LoggerFactory.getLogger(MemoDaoImpl.class);
	
	@Override
	public Memo selectMemo(final Memo memo) {
		return queryForObject(SELECT_MEMO_QUERY, memo, Memo.class);
	}

	@Override
	public Integer selectMemoCount(final SearchParameters searchParameters) {
		return queryForValue(SELECT_MEMO_COUNT_QUERY, null, searchParameters, Integer.class);
	}

	@Override
	public List<Memo> selectMemoList(final SearchParameters searchParameters) {
		return query(SELECT_MEMO_LIST_QUERY, null, searchParameters, Memo.class);
	}

	@Override
	public void insertMemo(final Memo memo) {
		update(INSERT_MEMO_QUERY, memo);
	}

	@Override
	public void updateMemo(final Memo memo) {
		update(UPDATE_MEMO_QUERY, memo);
	}

	@Override
	public void deleteMemo(Memo memo) {
		update(DELETE_MEMO_QUERY, memo);

	}

}
