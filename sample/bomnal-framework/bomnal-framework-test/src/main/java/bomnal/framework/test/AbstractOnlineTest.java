package bomnal.framework.test;

import hone.bom.context.request.RequestContext;
import hone.bom.context.request.RequestContextHolder;
import hone.bom.web.request.BomRequestHolder;
import hone.bom.web.request.BomRequestInfo;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bomnal.framework.test.config.OnlineTestConfig;
import bomnal.framework.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, OnlineTestConfig.class})
public class AbstractOnlineTest {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="printObjectMapper")
    private ObjectMapper objectMapper;
    
	protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    @Before
	public void before() {
		RequestContext context  = RequestContextHolder.getRequestContext();
		BomRequestInfo requestInfo = new BomRequestInfo(context, UUID.randomUUID().toString());
		requestInfo.setClientIp("127.0.0.1");
		requestInfo.setServletRequest(new MockHttpServletRequest());
		BomRequestHolder.putOnRequestContext(context, requestInfo);
	}
	
    protected void printJson(Object object) {
        try {
            logger.debug("{}", objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
