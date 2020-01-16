package com.incarcloud.methane.monitor.controller.test;

import com.incarcloud.methane.monitor.controller.HelloController;
import com.incarcloud.methane.monitor.model.VinCount;
import com.incarcloud.methane.monitor.service.ICountService;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HelloControllerTest {
    private static final Logger s_logger = LoggerFactory.getLogger(HelloControllerTest.class);

    private ICountService _mockCountSrv = null;

    @Before
    public void begin(){
        _mockCountSrv = new MockServiceWithFix3();
    }

    @After
    public void end(){
        _mockCountSrv = null;
    }

    @Test
    public void getMessageCountTestWithFix3(){
        HelloController target = new HelloController(_mockCountSrv);

        List<VinCount> listResult = target.getMessageCont(null);

        Assert.assertEquals(3, listResult.size());
        for(VinCount vc : listResult){
            s_logger.info("{} : {}", vc.getVin(), vc.getCount());
        }
    }
}
