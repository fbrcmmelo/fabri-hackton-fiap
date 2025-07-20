package com.fabri.srvmessagebroker.domain.usecases;

public interface SendQueueMessageUseCase {

    void send(String queue, Object message);

}
