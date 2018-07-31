/**
 */
package bomnal.batch.example.task;

import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.support.tasklet.AbstractExtendedBatchTasklet;
import hone.bom.context.ApplicationContextHolder;

import org.springframework.batch.item.ExecutionContext;

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
public class ExBat040JTask extends AbstractExtendedBatchTasklet {
    
    @Override
    public void doExecute(BatchContext batchContext, ExecutionContext executionContext) throws Exception {

//        Box box = new Box();
//        Box modelBox = new Box();
//        CodeComponent codeComponent = ApplicationContextHolder.getBean(CodeComponent.class);
//        codeComponent.grpCdInqr(box, modelBox);
//        logger.debug("{}", modelBox);
        
    }
}

