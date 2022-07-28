package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;


public class Ut {
    // 인자로 들어온 (key,value)쌍을 Map으로 변환해주는 메서드
    public static Map<String, Object> mapOf(Object... args) {

        // 순서 보장을 위해 LinkedHashMap 사용
        Map<String, Object> res = new LinkedHashMap<>();
        for (int i = 0; i < args.length / 2; i++) {
            String key = (String) args[i * 2];
            Object value = args[i * 2 + 1];
            res.put(key, value);
        }
        return res;
    }

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

        // JSON -> Java 객체 Deserialization
        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {
            try {
                return om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        // 복잡한 Json -> Java List, Map
        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }
    }
}
