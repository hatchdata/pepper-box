package com.gslab.pepper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.log.Logger;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderUtils {

    private static String MESSAGE_KEY = "MESSAGE_KEY";

    private static final Logger log = LoggingManager.getLoggerForClass();


    public Iterable<Header> generateHeaders(String messageHeaders) throws JsonProcessingException {
        List<Header> headers = new ArrayList<>();
        if (!StringUtils.isEmpty(messageHeaders)) {
            JSONObject jsonHeaders = new JSONObject(messageHeaders);
            Iterator<String> keys = jsonHeaders.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!MESSAGE_KEY.equals(key)) {
                    headers.add(new RecordHeader(key, jsonHeaders.getString(key).getBytes(StandardCharsets.UTF_8)));
                }
            }
        }
        return headers;
    }

    public String getMessageKey(String messageHeaders) {
        if (!StringUtils.isEmpty(messageHeaders)) {
            return new JSONObject(messageHeaders).optString(MESSAGE_KEY, null);
        }
        return null;
    }
}
