package bomnal.online.example.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bomnal.framework.test.AbstractOnlineTest;
import bomnal.online.example.dto.Memo;

public class MemoServiceTest extends AbstractOnlineTest {
	@Autowired
	private MemoService memoService;
	
	@Test
	public void select() {
		Memo memo = new Memo();
		memo.setMemoId("1");
		
		Memo result = memoService.getMemo(memo);
		
		printJson(result);
	}
}
