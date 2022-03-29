package ru.diasoft.lesson10.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {
    String DIRECT = "directed";
    String BROADCAST = "broadcasts";

    @Input(DIRECT)
    SubscribableChannel directed();

    @Input(BROADCAST)
    SubscribableChannel  broadcasts();
}
