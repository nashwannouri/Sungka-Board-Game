package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by nashwan on 11/13/2015.
 */
public class PlayMenuOptionsTest extends ActivityInstrumentationTestCase2<PlayMenuActivity> {
    final int TIMEOUT_IN_MS = 2000;

    public PlayMenuOptionsTest() {
        super(PlayMenuActivity.class);
    }

    /**
     * Tests the player vs player button invokes NameEntryTwoPlayerActivity
     */
    public void testPVPLocalButton() {
        ImageButton plVplButton = (ImageButton) getActivity().findViewById(R.id.plVsplButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor pvpLocalMonitor =
                getInstrumentation().addMonitor(NameEntryMultiplayerActivity.class.getName(),
                        null, false);

        // Validate that PlayMenuOptions is started
        TouchUtils.clickView(this, plVplButton);
        NameEntryMultiplayerActivity nameEntryTwoPlayerActivity = (NameEntryMultiplayerActivity)
                pvpLocalMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("NameEntryTwoPlayerActivity is null", nameEntryTwoPlayerActivity);
        assertEquals("Monitor for NameEntryTwoPlayerActivity has not been called",
                1, pvpLocalMonitor.getHits());
        assertEquals("Activity is of wrong type",
                NameEntryMultiplayerActivity.class, nameEntryTwoPlayerActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(pvpLocalMonitor);
    }
}