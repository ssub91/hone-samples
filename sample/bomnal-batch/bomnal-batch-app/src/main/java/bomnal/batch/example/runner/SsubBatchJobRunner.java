package bomnal.batch.example.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bomnal.batch.config.SsubBatchConfig;

public class SsubBatchJobRunner {

	public static void main(String[] args) {
		try {
			
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( SsubBatchConfig.class );
	
			JobLauncher jobLauncher = context.getBean( JobLauncher.class );
			Job ssubJob = context.getBean( "ssubJob", Job.class );
	
			JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
	
			jobLauncher.run( ssubJob, jobParameters );

			context.close();
		} catch(JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}


	}

}
