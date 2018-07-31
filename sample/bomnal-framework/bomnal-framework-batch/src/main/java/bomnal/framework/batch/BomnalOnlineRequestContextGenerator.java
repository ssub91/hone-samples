package bomnal.framework.batch;

import hone.bom.batch.runtime.context.BatchContext;
import hone.bom.batch.support.launch.OnlineRequestContextGenerator;
import hone.bom.context.request.RequestContext;
import hone.bom.context.request.RequestContextHolder;

public class BomnalOnlineRequestContextGenerator implements
		OnlineRequestContextGenerator {

	@Override
	public void generateRequestContext(BatchContext batchContext) {
		RequestContext requestContext = RequestContextHolder.getRequestContext();
	}

}
