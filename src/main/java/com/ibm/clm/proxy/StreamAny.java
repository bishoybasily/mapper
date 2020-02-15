package com.ibm.clm.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author bishoybasily
 * @since 2/15/20
 */
@RequiredArgsConstructor
@Service
public class StreamAny {

    private final RestTemplate restTemplate;

    @RabbitListener()
    public void consume(Content content) {
        restTemplate.exchange(
                content.getUrl(),
                content.getMethod(),
                new HttpEntity<>(content.getBody(), content.getHeaders()),
                String.class,
                content.getParameters()
        );
    }

}
