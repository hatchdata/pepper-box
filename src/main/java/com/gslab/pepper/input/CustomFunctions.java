package com.gslab.pepper.input;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The CustomFunctions allows users to write custom functions and then it can be used in template.
 *
 * @Author Satish Bhor<satish.bhor@gslab.com>, Nachiket Kate <nachiket.kate@gslab.com>
 * @Version 1.0
 * @since 01/03/2017
 */
public class CustomFunctions {

    //Random value generator
    private static SplittableRandom random = new SplittableRandom();

    public static String UUID_ARRAY(int length, boolean fixed) {

        int elementCount = fixed ? length : random.nextInt(length) + 1;
        List<String> l = new ArrayList<String>();
        for(int i = 0; i < elementCount; i++){
            l.add("\"" + UUID.randomUUID().toString()+ "\"");
        }
        return l.stream().collect(Collectors.joining(","));

    }

    /**
     * Generates an array of timeseries values, from five minutes ago, in json form.
     * @param numValues            the count of desired array items
     * @param timeSeriesSetName    the desired name of the array
     * @param timeSeriesIdPrefix   the desired id prefix for each item in the array
     */
    public static String TIME_SERIES_JSON(int numValues, String timeSeriesSetName, String timeSeriesIdPrefix) {
        Instant fiveMinutesAgo = Instant.now().minus(5, ChronoUnit.MINUTES);
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Timestamp timestamp = Timestamp.from(fiveMinutesAgo);
        String isoFormatted = isoDateFormat.format(timestamp);

        return TIME_SERIES_JSON(numValues, timeSeriesSetName, timeSeriesIdPrefix, isoFormatted);
    }

    /**
     * Generates an array of timeseries values, from some timestamp provided, in json form.
     * @param numValues            the count of desired array items
     * @param timeSeriesSetName    the desired name of the array
     * @param timeSeriesIdPrefix   the desired id prefix for each item in the array
     * @param timestamp            the desired timestamp for each item in the array
     */
    public static String TIME_SERIES_JSON(int numValues, String timeSeriesSetName, String timeSeriesIdPrefix, String timestamp) {
        StringBuilder result = new StringBuilder();
        String newline = System.getProperty("line.separator");
        result.append("{");
        result.append("\"").append(timeSeriesSetName).append("\" : [ ");
        result.append(newline);
        for (int i = 0; i < numValues; i++) {
            String reading = "{\"id\": \"" + timeSeriesIdPrefix + "." + i + "\", \"data\": [{\"timestamp\": \"" + timestamp + "\",\"value\": " + (-100 + Math.random() * (10000 - (-100))) + "}]}";
            result.append(reading).append(",");
            result.append(newline);
        }
        result.append("]");
        result.append(newline);
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        String r = CustomFunctions.UUID_ARRAY(5, false);
        System.out.println(r);
    }

}
