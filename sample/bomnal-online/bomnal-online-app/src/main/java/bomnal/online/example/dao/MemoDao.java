package bomnal.online.example.dao;

import java.util.List;

import bomnal.online.example.dto.Memo;
import hone.bom.dao.search.SearchParameters;

public interface MemoDao {
	static final String HQML_NAME = "bomnal.online.example.dao.MemoDao";
	static final String SELECT_MEMO_QUERY = HQML_NAME + "." + "selectMemo";
	static final String SELECT_MEMO_COUNT_QUERY = HQML_NAME + "." + "selectMemoCount";
	static final String SELECT_MEMO_LIST_QUERY = HQML_NAME + "." + "selectMemoList";
	static final String INSERT_MEMO_QUERY = HQML_NAME + "." + "insertMemo";
	static final String UPDATE_MEMO_QUERY = HQML_NAME + "." + "updateMemo";
	static final String DELETE_MEMO_QUERY = HQML_NAME + "." + "deleteMemo";
	
	Memo selectMemo(Memo memo);
	Integer selectMemoCount(SearchParameters searchParameters);
	List<Memo> selectMemoList(SearchParameters searchParameters);
	void insertMemo(Memo memo);
	void updateMemo(Memo memo);
	void deleteMemo(Memo memo);
}
