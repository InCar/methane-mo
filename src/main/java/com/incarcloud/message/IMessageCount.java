package com.incarcloud.message;

import com.incarcloud.bean.MessageCount;

import java.util.List;

public interface IMessageCount {

    List<MessageCount> getMessageCont(String vin);
}
