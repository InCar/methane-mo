package com.incarcloud.methane.monitor.serviceImpl;

import com.incarcloud.methane.monitor.service.ICountService;
import com.incarcloud.std.CountServiceV1Grpc;
import com.incarcloud.std.HelloM;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CountService implements ICountService {

    private final static Logger s_logger = LoggerFactory.getLogger(CountService.class);

    private final String _host;
    private final int _port;

    private ManagedChannel _channel = null;
    private CountServiceV1Grpc.CountServiceV1BlockingStub _blockingStub = null;

    public CountService(
            @Value("${com.incarcloud.std.CountServiceV1.host}")String host,
            @Value("${com.incarcloud.std.CountServiceV1.port}")int port)
    {
        _host = host;
        _port = port;
    }

    @PostConstruct
    public void Initialize(){
        // 初始化创建stub
        createStub();
    }

    // 准备连接到google rpc服务
    private void createStub(){
        _channel = ManagedChannelBuilder.forAddress(_host, _port)
                .usePlaintext()
                .build();
        _blockingStub = CountServiceV1Grpc.newBlockingStub(_channel);
    }

    // 查询所有计数值,如果失败抛出异常
    @Override
    public List<HelloM.VinCount> FindAll(){
        // 调用远程服务
        HelloM.HelloCountArgV1 argV1 = HelloM.HelloCountArgV1.newBuilder().build();
        HelloM.HelloCountV1 result = _blockingStub.count(argV1);
        return result.getListList();
    }
}
