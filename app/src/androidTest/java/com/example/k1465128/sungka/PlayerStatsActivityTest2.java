package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

/**
 * Created by jana on 16/11/2015.
 */
public class PlayerStatsActivityTest2 extends ActivityInstrumentationTestCase2<PlayerStatsActivity> {
    private int TIMEOUT_IN_MS = 1000;//1 second

    public PlayerStatsActivityTest2() {
        super(PlayerStatsActivity.class);
    }

    public void testReturnHome() {
        final Button homeButton = (Button) getActivity().findViewById(R.id.homeButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor mainActivityMonitor =
                getInstrumentation().addMonitor(MainActivity.class.getName(),
                        null, false);

        // Validate that MainActivity is started
        TouchUtils.clickView(this, homeButton);
        MainActivity mainActivity = (MainActivity)
                mainActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("MainActivity is null", mainActivity);
        assertEquals("Monitor for MainActivity has not been called",
                1, mainActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                MainActivity.class, mainActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(mainActivityMonitor);
    }
}
