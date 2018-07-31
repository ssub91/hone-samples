package bomnal.online.example.service;

import org.junit.Test;

import bomnal.framework.test.AbstractOnlineTest;
import hone.bom.batch.support.util.BatchUtil;

public class BatchTest extends AbstractOnlineTest {

	
	@Test
	public void select() throws Exception {
		BatchUtil.startOnDemand("EXBAT010J", new String[]{"M"});
	}
}
