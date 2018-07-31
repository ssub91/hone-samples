/**
 */
package bomnal.batch.example.task;

import hone.bom.batch.runtime.config.BatchConfig;
import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.runtime.item.BatchIoOptions;
import hone.bom.batch.runtime.item.db.BaseDBItemReader;
import hone.bom.batch.runtime.item.file.BaseFileItemWriter;
import hone.bom.batch.support.tasklet.AbstractExtendedBatchTasklet;
import hone.bom.io.metadata.attr.Align;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

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
public class ExBat010JTask extends AbstractExtendedBatchTasklet {

    @Override
    public void doExecute(BatchContext batchContext, ExecutionContext executionContext) throws Exception {
        
        //실행 모듈(커맨드라인/스케쥴러/온라인)로 부터 전달 받은 쉘 파라메터 데이터 가져오기
        String custType = batchContext.getShellParameter(1);

        //DB 조회 파라미터를 Map타입으로 지정함. 
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("custNo", custType + "%");
        
        
        
        //DB Reader 설정 
        BaseDBItemReader reader = createDBReader(); //DB Reader 생성
        reader.setSqlStatement(getSqlStatement("exBat010JDAO.exBat010JDAORead")); //쿼리 아이디를 이용해 Statement설정
        reader.setSqlParameters(parameters); //조회조건 파라미터 설정
        reader.setDtoClass(ExCustomerVo.class); //데이터를 읽어서 처리할 Vo클래스 지정
        
        //File Writer 설정
        BaseFileItemWriter writer = createFixedLengthFileWriter(); //File Writer 생성
        String outFileName = BatchConfig.BATCH_DATA_HOME + "/ex/exbat010j/"+"exbat010j_output_" + custType + ".dat";  
        writer.setFilePath(outFileName); //파일 경로 및 파일명 설정
        
        //데이터 저장 옵션 설정
        writer.getBatchIoOptions().setNumberAlign(Align.Right); //숫자데이터의 경우 오른쪽 정렬
        writer.getBatchIoOptions().setNumberPaddingChar(BatchIoOptions.PADDING_ZERO); //숫자의 경우 빈 칸을 0으로 채움.
        writer.getBatchIoOptions().setCharset(Charset.forName("EUC-KR")); //문자셋 설정
        writer.getBatchIoOptions().setLineDelimiter("\n"); //라인 구분자 지정
        
        //DB Reader, File Writer 오픈
        reader.open(executionContext);
        writer.open(executionContext);
        
        try {
            ExCustomerVo exCustomerVo; //반복적으로 처리할 데이터 클래스 선언
            while ((exCustomerVo = (ExCustomerVo) reader.read()) != null) { //DB Reader에서 반복적으로 데이터 읽어옴.
               
               //
               
               writer.write(exCustomerVo); //File Writer를 이용해 파일에 데이터를 기록함.
            }
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
