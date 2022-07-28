package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ut {
    public static class json {

        // Java 객체 -> JSON 문자열 Serialization
        public static String toJsonStr(Object obj, String defaultValue) {
            // JSON <-> Java 객체 변환을 위한 Jackson 라이브러리 클래스
            ObjectMapper om = new ObjectMapper();

            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

    }
}
