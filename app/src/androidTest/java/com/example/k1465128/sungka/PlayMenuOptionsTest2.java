package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.ImageButton;

/**
 * Created by jana on 16/11/2015.
 */
public class PlayMenuOptionsTest2 extends ActivityInstrumentationTestCase2<PlayMenuActivity> {
    final int TIMEOUT_IN_MS = 2000;

    public PlayMenuOptionsTest2() {
        super(PlayMenuActivity.class);
    }

    /**
     * Tests if NameEntryOnePlayerActivity is invoked after the player vs AI button is clicked
     */
    public void testPVAIButton() {
        ImageButton plVplButton = (ImageButton) getActivity().findViewById(R.id.plVsaiButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor pvaiLocalMonitor =
                getInstrumentation().addMonitor(NameEntryAiGameActivity.class.getName(),
                        null, false);

        // Validate that PlayMenuOptions is started
        TouchUtils.clickView(this, plVplButton);
        NameEntryAiGameActivity nameEntryOnePlayerActivity = (NameEntryAiGameActivity)
                pvaiLocalMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("NameEntryOnePlayerActivity is null", nameEntryOnePlayerActivity);
        assertEquals("Monitor for NameEntryOnePlayerActivity has not been called",
                1, pvaiLocalMonitor.getHits());
        assertEquals("Activity is of wrong type",
                NameEntryAiGameActivity.class, nameEntryOnePlayerActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(pvaiLocalMonitor);
    }
}
