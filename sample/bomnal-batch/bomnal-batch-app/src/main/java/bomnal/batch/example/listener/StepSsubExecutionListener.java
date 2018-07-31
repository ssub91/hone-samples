package bomnal.batch.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepSsubExecutionListener implements StepExecutionListener  {
	
	private static final Logger log = LoggerFactory.getLogger( StepSsubExecutionListener.class );
	
	public ExitStatus afterStep( StepExecution stepExecution ) {
		
		log.info( "StepExecutionListener.afterStep called" );
		
		return ExitStatus.COMPLETED;
		//return ExitStatus.COMPLETED;
    }

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}
}
