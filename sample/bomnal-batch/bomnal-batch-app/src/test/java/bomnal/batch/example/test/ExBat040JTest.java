package bomnal.batch.example.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bomnal.batch.example.task.ExBat040JTask;
import bomnal.framework.test.AbstractBatchTest;
import hone.bom.batch.runtime.config.BatchConfig;
import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.support.launch.ExtendedTestJobRunner;

public class ExBat040JTest extends AbstractBatchTest {
	
	@Test
	public void testBatch() {
		/** 1.Shell Parameter 설정 */
		List<String> shellParameters = new ArrayList<String>();
		shellParameters.add("M");
		
		/** 2. BatchContext 설정 */
		BatchContext batchContext = new BatchContext();
		batchContext.setShellParameters(shellParameters);

		/** 3. 배치 프로그램 호출 */
		ExtendedTestJobRunner runner = new ExtendedTestJobRunner ();
		int result = runner. runBatch ("ExBat040J", ExBat040JTask.class, null, 
				shellParameters);
		assertEquals(BatchConfig.EXIT_CODE_SUCCESS, result);

	}
}
