package com.gslab.pepper.test;

import com.gslab.pepper.input.CustomFunctions;
import com.gslab.pepper.input.FieldDataFunctions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by armand.ballaci on 26/3/21.
 */
public class CustomFunctionsTest {

    @Test
    public void verifyCustomFunctions() {

        assertTrue("Wrong number of elements generated", CustomFunctions.UUID_ARRAY(5, true).split(",").length == 5);
        assertNotNull("Should always return a non null/empty string", CustomFunctions.UUID_ARRAY(5, false));

    }

    @Test
    public void verifyTimeSeriesJsonGenerator() {
        int expectedPoints = 10;
        String actualJson = CustomFunctions.TIME_SERIES_JSON(
                expectedPoints,
                "points",
                "provider.point.id"
        );
        JSONObject obj = new JSONObject(actualJson);
        JSONArray points = obj.getJSONArray("points");
        assertEquals("Wrong number of elements generated", points.length(), expectedPoints);

        for (int i = 0; i < points.length(); i++) {
            String actualIdentifier = points.getJSONObject(i).getString("id");
            String expectedIdentifer = "provider.point.id." + i;
            assertEquals("Wrong name of element generated", actualIdentifier, expectedIdentifer);
        }
    }

    @Test
    public void verifyTimeSeriesJsonGeneratorWithSpecifiedTimestamp() {
        String expectedTimestamp = "2021-09-10T15:15:00Z";
        String actualJson = CustomFunctions.TIME_SERIES_JSON(
                5,
                "points",
                "provider.point.id",
                expectedTimestamp
        );
        JSONObject obj = new JSONObject(actualJson);
        JSONArray points = obj.getJSONArray("points");
        for (int i = 0; i < points.length(); i++) {
            String actualTimestamp = points.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("timestamp");
            String expectedIdentifer = "provider.point.id." + i;
            assertEquals("Wrong value of element generated", actualTimestamp, expectedTimestamp);
        }
    }
}
