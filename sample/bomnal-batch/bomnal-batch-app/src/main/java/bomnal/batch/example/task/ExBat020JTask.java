/**
 */
package bomnal.batch.example.task;

import hone.bom.batch.runtime.config.BatchConfig;
import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.runtime.item.db.BaseDBItemWriter;
import hone.bom.batch.runtime.item.file.BaseFileItemReader;
import hone.bom.batch.support.tasklet.AbstractExtendedBatchTasklet;

import java.nio.charset.Charset;

import org.springframework.batch.item.ExecutionContext;

import bomnal.batch.example.vo.ExCustomerVo;

/**
 * {논리명}
 * 
 * <p>
 * {설명}
 * 
 * <p>
 * <b>변경이력:</b>
 * 
 * <pre>
 * - Gildong Hong(gildong) 2017. 9. 4. 초기작성
 * </pre>
 * @author Gildong Hong(gildong)
 *
 */
public class ExBat020JTask extends AbstractExtendedBatchTasklet {
	
    @SuppressWarnings("rawtypes")
    @Override
    public void doExecute(BatchContext batchContext, ExecutionContext executionContext) throws Exception {

        //실행 모듈(커맨드라인/스케쥴러/온라인)로 부터 전달 받은 쉘 파라메터 데이터 가져오기
        String custType = batchContext.getShellParameter(1);
        
        //File Reader 설정
        BaseFileItemReader<ExCustomerVo> reader = createFixedLengthFileReader(); //고정길이 방식의 File Reader생성
        String inFileName = BatchConfig.BATCH_DATA_HOME + "/ex/exbat010j/"+"exbat010j_output_" + custType + ".dat"; 
        reader.setFilePath(inFileName); //읽어서 처리할 파일 경로 설정
        reader.getBatchIoOptions().setCharset(Charset.forName("EUC-KR")); //문자셋 설정
        reader.setDtoClass(ExCustomerVo.class); //파일을 읽어서 담아 처리할 Vo 클래스 설정

        //DB Writer 설정
        BaseDBItemWriter writer = createDBWriter(); //DB Writer 생성
        writer.setSqlStatement(getSqlStatement("exBat020JDAO.exBat020JDAOWrite")); //쿼리 아이디를 이용해 Statement설정

        //File Reader, DB Writer 오픈
        reader.open(executionContext);
        writer.open(executionContext);

        
        int commitCnt = 100; //commit 단위 (배치의 경우 일정 수량으로 updateBatch로 처리함.
        try {
            ExCustomerVo exCustomerVo; //반복적으로 처리할 데이터 클래스 선언
            while ((exCustomerVo = (ExCustomerVo) reader.read()) != null) { //File Reader에서 반복적으로 데이터 읽어옴.

                writer.write(exCustomerVo); //DB Writer를 이용해 파일에 데이터를 기록함.

                if(writer.getItemCount()%commitCnt == 0) { //commit 단위가 되었을때 commit처리함.
                    commit();
                }

            }

            commit(); //최종 commit

        } finally {
            try { reader.close(); } catch(Exception ex) { /*nothing*/ }
            try { writer.close(); } catch(Exception ex) { /*nothing*/ }
        }

        //처리 건수를 로그로 기록함.
        if (logger.isDebugEnabled()) {
            logger.debug("대상건수={},처리건수={}", reader.getItemCount(),writer.getItemCount());
        }
    }
}

