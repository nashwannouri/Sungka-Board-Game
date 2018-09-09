package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ImageButton;

/**
 * Created by jana on 16/11/2015.
 */
public class MainActivityTest2 extends ActivityInstrumentationTestCase2<MainActivity> {
    private int TIMEOUT_IN_MS = 10000;//10 seconds

    public MainActivityTest2() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testPlayButton() {
        final ImageButton playButton = (ImageButton) getActivity().findViewById(R.id.playButtonMainMenu);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor playMenuOptionsActivityMonitor =
                getInstrumentation().addMonitor(PlayMenuActivity.class.getName(),
                        null, false);

        assertNotNull("PlayMenuOptions is null", playMenuOptionsActivityMonitor);

        // Validate that PlayMenuOptions is started
        TouchUtils.clickView(this, playButton);
        PlayMenuActivity playMenuOptionsActivity = (PlayMenuActivity)
                playMenuOptionsActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("PlayMenuOptions is null", playMenuOptionsActivity);
        assertEquals("Monitor for PlayMenuOptions has not been called",
                1, playMenuOptionsActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                PlayMenuActivity.class, playMenuOptionsActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(playMenuOptionsActivityMonitor);
    }
}
