package com.example.recipeapp;

import android.os.Bundle;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import java.time.Duration;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
@LooperMode(LooperMode.Mode.PAUSED)
public class SplashLifecycleTest {
    private Fragment current(MainActivity activity) {
        activity.getSupportFragmentManager().executePendingTransactions();
        return activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @Test public void foregroundSplashNavigatesAfterDelay() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            assertTrue(current(controller.get()) instanceof SplashFragment);
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofSeconds(3));
            assertTrue(current(controller.get()) instanceof LoginFragment);
        }
    }

    @Test public void backgroundWithSavedStateDoesNotNavigateUntilResumed() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            Fragment splash = current(controller.get());
            controller.pause().saveInstanceState(new Bundle()).stop();
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofSeconds(5));
            assertSame(splash, current(controller.get()));
            controller.restart().start().resume();
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofSeconds(3));
            assertTrue(current(controller.get()) instanceof LoginFragment);
        }
    }

    @Test public void removedSplashCannotReplaceAnotherScreen() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            current(controller.get());
            Fragment next = new Fragment();
            controller.get().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, next).commitNow();
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofSeconds(5));
            assertSame(next, current(controller.get()));
        }
    }
}
