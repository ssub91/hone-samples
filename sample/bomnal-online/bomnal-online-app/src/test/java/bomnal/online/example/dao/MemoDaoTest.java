package bomnal.online.example.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bomnal.framework.test.AbstractOnlineTest;
import bomnal.online.example.dto.Memo;

public class MemoDaoTest extends AbstractOnlineTest {
	@Autowired
	private MemoDao memoDao;
	
	@Test
	public void select() {
		Memo memo = new Memo();
		memo.setMemoId("1");
		
		printJson(memoDao.selectMemo(memo));
	}
}
