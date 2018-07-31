package bomnal.example.sc;

import hone.bom.web.context.OnlineContextUtil;

public class ExampleServiceImpl {
	public void getExample()  {
		OnlineContextUtil.getRequestInfo().getClientIp();
	}
}
