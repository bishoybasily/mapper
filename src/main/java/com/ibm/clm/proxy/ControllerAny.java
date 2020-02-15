package com.ibm.clm.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author bishoybasily
 * @since 2/15/20
 */
@RequiredArgsConstructor
@RestController
public class ControllerAny {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping("/**/{[path:[^\\.]*}")
    public void publish(HttpServletRequest request,
                        @RequestHeader(required = false) LinkedMultiValueMap<String, String> headers,
                        @RequestParam(required = false) LinkedMultiValueMap<String, String> params,
                        @RequestBody(required = false) Map<String, Object> body) {

        rabbitTemplate.convertAndSend(
                Constants.EXCHANGE,
                Constants.PRODUCE_ROUTING_KEY,
                new Content()
                        .setPath(request.getServletPath())
                        .setMethod(HttpMethod.valueOf(request.getMethod()))
                        .setParameters(params)
                        .setHeaders(headers)
                        .setBody(body)
        );

    }

}
