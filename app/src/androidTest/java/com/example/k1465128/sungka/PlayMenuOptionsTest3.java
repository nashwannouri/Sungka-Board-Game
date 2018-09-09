package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

/**
 * Created by jana on 16/11/2015.
 */
public class PlayMenuOptionsTest3 extends ActivityInstrumentationTestCase2<PlayMenuActivity> {
    final int TIMEOUT_IN_MS = 2000;

    public PlayMenuOptionsTest3() {
        super(PlayMenuActivity.class);
    }

    public void testReturnHomeButton() {
        Button homeButton = (Button) getActivity().findViewById(R.id.homeButtonplayMenu);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor mainActivityMonitor =
                getInstrumentation().addMonitor(MainActivity.class.getName(),
                        null, false);

        // Validate that PlayMenuOptions is started
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
