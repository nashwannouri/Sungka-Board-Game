package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ImageButton;

/**
 * Created by nashwan on 11/13/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private int TIMEOUT_IN_MS = 10000;//10 seconds

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testHighScoresButton() {
        final ImageButton highScoresButton = (ImageButton) getActivity().findViewById(R.id.highScoresButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor hsActivityMonitor =
                getInstrumentation().addMonitor(HighScoresActivity.class.getName(),
                        null, false);

        // Validate that HighScoresActivity is started
        TouchUtils.clickView(this, highScoresButton);
        HighScoresActivity hsActivity = (HighScoresActivity)
                hsActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("HighScoresActivity is null", hsActivity);
        assertEquals("Monitor for HighScoresActivity has not been called",
                1, hsActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                HighScoresActivity.class, hsActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(hsActivityMonitor);
    }

    public void testExitButton() {
        final ImageButton exitButton = (ImageButton) getActivity().findViewById(R.id.exitButton123);

        TouchUtils.clickView(this, exitButton);

        assertEquals("App has not been destroyed", true, getActivity().isAppQuit());
    }


}
