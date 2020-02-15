package com.ibm.clm.proxy;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bishoybasily
 * @since 2/15/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class Content {

    private String url;
    private String path;
    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
    private Map<String, Object> body = new HashMap<>();
    private HttpMethod method;

}
