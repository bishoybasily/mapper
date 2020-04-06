package com.gmail.bishoybasily.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bishoybasily
 * @since 2/15/20
 */
@RequiredArgsConstructor
@RestController
public class ControllerAny {

    private final RabbitTemplate rabbitTemplate;
    private final ContentExtractor contentExtractor;

    @RequestMapping("/**/{[path:[^\\.]*}")
    public void publish(HttpServletRequest request) {

        rabbitTemplate.convertAndSend(
                Constants.EXCHANGE,
                Constants.PRODUCE_ROUTING_KEY,
                contentExtractor.from(request)
                        .getContent()
        );

    }


}
