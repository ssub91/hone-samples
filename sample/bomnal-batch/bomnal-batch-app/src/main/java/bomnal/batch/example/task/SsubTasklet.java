package bomnal.batch.example.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;

import bomnal.batch.example.vo.PeopleVo;
import bomnal.batch.example.vo.PersonVo;
import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.runtime.item.db.BaseDBItemReader;
import hone.bom.batch.runtime.item.db.BaseDBItemWriter;
import hone.bom.batch.support.tasklet.AbstractExtendedBatchTasklet;

public class SsubTasklet extends AbstractExtendedBatchTasklet {

	private static final Logger log = LoggerFactory.getLogger(SsubTasklet.class);

	@Override
	public void doExecute(BatchContext batchContext, ExecutionContext executionContext) throws Exception {

		log.info("SsubTasklet.execute(...) Called");

		// DB 조회 파라미터를 Map타입으로 지정함.
		String firstNameStartChar = "J";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put( "firstName", firstNameStartChar + "%" );

		
		// DB Reader 생성
		BaseDBItemReader reader = createDBReader();
		// 쿼리 아이디를 이용해 Statement설정
		reader.setSqlStatement(getSqlStatement( "ssubBatchDAO.ssubBatchDAORead" ) );
		// 조회조건 파라미터 설정
		reader.setSqlParameters(parameters);
		// 데이터를 읽어서 처리할 Vo클래스 지정
		reader.setDtoClass(PersonVo.class);

		
		// DB Writer 생성
		BaseDBItemWriter writer = createDBWriter();
		// 쿼리 아이디를 이용해 Statement설정
		writer.setSqlStatement( getSqlStatement( "ssubBatchDAO.ssubBatchDAOInsert" ) );

		
		// DB Reader, DB Writer 오픈
		reader.open( executionContext );
        writer.open( executionContext );


        // 작업	
		PersonVo personVo = null;
        int commitCount = 5;                                         //commit 단위 (배치의 경우 일정 수량으로 updateBatch로 처리함.
			
		while ( ( personVo = (PersonVo) reader.read() ) != null ) {  // DB Reader에서 반복적으로 데이터 읽어옴.

			log.info("[read]: " + personVo.toString());
				
			PeopleVo peopleVo = new PeopleVo();
			peopleVo.setFullName( personVo.getFirstName() + " " + personVo.getLastName() );
				
			//DB Writer를 이용해 파일에 데이터를 기록함.
			writer.write( peopleVo );

			log.info("[write]: " + peopleVo.toString());
			
            if( writer.getItemCount() % commitCount == 0 ) {         //commit 단위가 되었을때 commit처리함.
                commit();
            }			
		}	
		
		// 최종 commit
        commit();
        
        // 자원 정리
		reader.close();
		writer.close();


		// StepSynchronizationManager.getContext().getStepExecution().setExitStatus( (  Math.random() > .5 ) ? ExitStatus.FAILED : ExitStatus.COMPLETED );
		StepSynchronizationManager.getContext().getStepExecution().setExitStatus(ExitStatus.COMPLETED);
	}
}