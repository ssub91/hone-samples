package bomnal.batch.example.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bomnal.batch.config.SsubBatchConfig;
import bomnal.framework.test.AbstractBatchTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes={ SsubBatchConfig.class } )
public class SsubBatchTest extends AbstractBatchTest {
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;	
	
	@Autowired
	@Qualifier("ssubJob")
	private Job ssubJob;
    
	@Test
	public void testBatch() throws Exception {
        
		jobLauncherTestUtils.setJob( ssubJob );
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		assertEquals( ExitStatus.COMPLETED, jobExecution.getExitStatus() );
	}
}
