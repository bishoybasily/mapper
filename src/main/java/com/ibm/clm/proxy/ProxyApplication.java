package com.ibm.clm.proxy;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner(RabbitTemplate rabbitTemplate){
//		return args -> {
//
//			rabbitTemplate.convertAndSend(
//					Constants.EXCHANGE,
//					Constants.CONSUME_ROUTING_KEY,
//					new Content()
//							.setUrl("http://checkip.amazonaws.com/")
//							.setMethod(HttpMethod.GET)
//			);
//
//		};
//	}

}
