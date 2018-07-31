package bomnal.online.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bomnal.online.example.dao.MemoDao;
import bomnal.online.example.dto.Memo;
import hone.bom.annotation.ServiceId;
import hone.bom.annotation.ServiceName;
import hone.bom.dao.search.SearchParameters;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	private MemoDao memoDao;
	
	@Override
	@ServiceId("YM001")
	@ServiceName("메모목록 조회")
	public List<Memo> getMemoList(SearchParameters searchParameters) {
		return memoDao.selectMemoList(searchParameters);
	}

	@Override
	@ServiceId("YM002")
	@ServiceName("메모조회")
	public Memo getMemo(Memo memo) {
		return memoDao.selectMemo(memo);
	}

	@Override
	@ServiceId("YM003")
	@ServiceName("메모생성")
	@Transactional
	public void createMemo(Memo memo) {
		memoDao.insertMemo(memo);
	}

	@Override
	@ServiceId("YM004")
	@ServiceName("메모수정")
	@Transactional
	public void modifyMemo(Memo memo) {
		memoDao.updateMemo(memo);
	}

	@Override
	@ServiceId("YM005")
	@ServiceName("메모삭제")
	@Transactional
	public void removeMemo(Memo memo) {
		memoDao.deleteMemo(memo);
	}
	

	

}
