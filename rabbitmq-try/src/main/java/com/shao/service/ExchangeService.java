package com.shao.service;

import com.shao.domain.dto.DirectMsgDto;

public interface ExchangeService {
    void sendToDirect(DirectMsgDto directMsgDto);

    void sendToFanout(DirectMsgDto directMsgDto);

    void sendToTopic(DirectMsgDto directMsgDto);
}
