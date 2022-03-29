package ru.diasoft.lesson10.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.lesson10.messaging.ConsumerChannels;
import ru.diasoft.lesson10.messaging.GreetingMessage;

@Configuration
public class MessageListener {
    private final Logger logger = LogManager.getLogger(MessageListener.class.getName());

    @StreamListener(ConsumerChannels.DIRECT)
    public void directed(GreetingMessage message) {
        logger.info("Directed: {}", message);
    }

    @StreamListener(ConsumerChannels.BROADCAST)
    public void broadcasted(String message) {
        logger.info("Broadcast: {}", message);
    }
}