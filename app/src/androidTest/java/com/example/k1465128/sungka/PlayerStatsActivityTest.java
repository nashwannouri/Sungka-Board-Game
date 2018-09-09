package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

/**
 * Created by janaldoustorres on 15/11/2015.
 */
public class PlayerStatsActivityTest extends ActivityInstrumentationTestCase2<PlayerStatsActivity> {
    private int TIMEOUT_IN_MS = 1000;//1 second

    public PlayerStatsActivityTest() {
        super(PlayerStatsActivity.class);
    }

    public void testHighScoresButton() {
        final Button highScoresButton = (Button) getActivity().findViewById(R.id.highScoresButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor hsaActivityMonitor =
                getInstrumentation().addMonitor(HighScoresActivity.class.getName(),
                        null, false);

        // Validate that MainActivity is started
        TouchUtils.clickView(this, highScoresButton);
        HighScoresActivity hsaActivity = (HighScoresActivity)
                hsaActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("MainActivity is null", hsaActivity);
        assertEquals("Monitor for MainActivity has not been called",
                1, hsaActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                HighScoresActivity.class, hsaActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(hsaActivityMonitor);
    }
}

