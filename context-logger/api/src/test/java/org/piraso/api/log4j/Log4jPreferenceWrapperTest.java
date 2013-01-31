package org.piraso.api.log4j;

import org.junit.Test;
import org.piraso.api.Preferences;

import static org.junit.Assert.assertTrue;

public class Log4jPreferenceWrapperTest {

    @Test
    public void testSample() throws Exception {
        Preferences pref = new Preferences();
        Log4jPreferenceWrapper wrapper = new Log4jPreferenceWrapper(pref);

        wrapper.debug("debug")
                .error("error")
                .info("info");

        assertTrue(pref.isEnabled("log4j.debug.DEBUG"));
        assertTrue(pref.isEnabled("log4j.error.ERROR"));
        assertTrue(pref.isEnabled("log4j.info.INFO"));
    }
}
