package com.example.k1465128.sungka;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

/**
 * Created by janaldoustorres on 15/11/2015.
 */
public class HighScoresActivityTest extends ActivityInstrumentationTestCase2<HighScoresActivity> {
    private int TIMEOUT_IN_MS = 1000; // 1 second


    public HighScoresActivityTest() {
        super(HighScoresActivity.class);
    }

//    @MediumTest
//    public void testStatisticsButton() {
//        final Button playerStatsButton = (Button) getActivity().findViewById(R.id.playerStatsButton);
//
//        // Set up an ActivityMonitor
//        Instrumentation.ActivityMonitor psActivityMonitor =
//                getInstrumentation().addMonitor(PlayerStatsActivity.class.getName(),
//                        null, false);
//
//        // Validate that PlayerStatsActivity is started
//        TouchUtils.clickView(this, playerStatsButton);
//        PlayerStatsActivity psActivity = (PlayerStatsActivity)
//                psActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
//        assertNotNull("PlayerStatsActivity is null", psActivity);
//        assertEquals("Monitor for PlayerStatsActivity has not been called",
//                1, psActivityMonitor.getHits());
//        assertEquals("Activity is of wrong type",
//                PlayerStatsActivity.class, psActivity.getClass());
//
//        // Remove the ActivityMonitor
//        getInstrumentation().removeMonitor(psActivityMonitor);
//    }

    @MediumTest
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
