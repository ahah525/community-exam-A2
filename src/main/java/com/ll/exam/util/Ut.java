package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ut {
    public static class json {
        // ObjectMapper는 생성 비용이 비싸므로 bean/static 처리 필요
        private static final ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        // Java 객체 -> JSON Serialization
        public static String toStr(Object obj, String defaultValue) {
            // JSON <-> Java 객체 변환을 위한 Jackson 라이브러리 클래스
            ObjectMapper om = new ObjectMapper();

            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        // JSON 문자열 -> Java 객체 Deserialization
        public static Object toObj(String jsonStr, Class cls, Object defaultValue) {
            try {
                return om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }
    }
}
