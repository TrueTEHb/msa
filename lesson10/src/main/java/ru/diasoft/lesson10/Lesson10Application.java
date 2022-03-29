package ru.diasoft.lesson10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import ru.diasoft.lesson10.messaging.ConsumerChannels;

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class Lesson10Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson10Application.class, args);
	}
}
