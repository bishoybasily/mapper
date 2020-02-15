package com.ibm.clm.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
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

    @RequestMapping("/**/{[path:[^\\.]*}")
    public Content redirect(HttpServletRequest request,
                            @RequestHeader(required = false) MultiValueMap<String, String> headers,
                            @RequestParam(required = false) MultiValueMap<String, String> params,
                            @RequestBody(required = false) Map<String, Object> body) {
        return new Content()
                .setPath(request.getServletPath())
                .setMethod(HttpMethod.valueOf(request.getMethod()))
                .setParameters(params)
                .setHeaders(headers)
                .setBody(body);
    }

}
