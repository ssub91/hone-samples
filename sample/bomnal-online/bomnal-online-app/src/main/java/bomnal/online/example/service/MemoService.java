package bomnal.online.example.service;

import java.util.List;

import bomnal.online.example.dto.Memo;
import hone.bom.dao.search.SearchParameters;

public interface MemoService {
	List<Memo> getMemoList( SearchParameters searchParameters );

	Memo getMemo( Memo memo );

	void createMemo( Memo memo );

	void modifyMemo( Memo memo );

	void removeMemo( Memo memo );
}
