package com.ibm.clm.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author bishoybasily
 * @since 2/16/20
 */
@RequiredArgsConstructor
@Component
public class ContentExtractor {

    private final ObjectMapper objectMapper;

    public Extractor from(HttpServletRequest request) {
        return new Extractor(objectMapper, request);
    }

    @RequiredArgsConstructor
    public class Extractor {

        private final ObjectMapper objectMapper;
        private final HttpServletRequest request;

        private String getPath() {
            return request.getServletPath();
        }

        private HttpMethod getMethod() {
            return HttpMethod.valueOf(request.getMethod().toUpperCase());
        }

        private LinkedMultiValueMap<String, String> getParams() {
            return new LinkedMultiValueMap<>(
                    enumerationAsStream(request.getParameterNames()).map(k -> {
                        return new AbstractMap.SimpleEntry<>(k, Stream.of(request.getParameterValues(k)).collect(Collectors.toList()));
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }

        private LinkedMultiValueMap<String, String> getHeaders() {
            return new LinkedMultiValueMap<>(
                    enumerationAsStream(request.getHeaderNames()).map(k -> {
                        return new AbstractMap.SimpleEntry<>(k, enumerationAsStream(request.getHeaders(k)).collect(Collectors.toList()));
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }

        private Object getBody() {
            try {
                return objectMapper.readValue(request.getInputStream(), Object.class);
            } catch (IOException e) {
                return null;
            }
        }

        private <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(
                            new Iterator<T>() {
                                public T next() {
                                    return e.nextElement();
                                }

                                public boolean hasNext() {
                                    return e.hasMoreElements();
                                }
                            },
                            Spliterator.ORDERED), false);
        }

        public Content getContent() {
            return new Content()
                    .setPath(getPath())
                    .setHeaders(getHeaders())
                    .setParameters(getParams())
                    .setMethod(getMethod())
                    .setBody(getBody());
        }

    }

}
