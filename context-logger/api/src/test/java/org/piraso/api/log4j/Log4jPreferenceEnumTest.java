/*
 * Copyright (c) 2012. Piraso Alvin R. de Leon. All Rights Reserved.
 *
 * See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Piraso licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.piraso.api.log4j;

import org.junit.Test;
import org.piraso.api.Level;
import org.piraso.api.PreferenceEnum;

import static junit.framework.Assert.assertTrue;

/**
 * Test for {@link Log4jPreferenceEnum}
 */
public class Log4jPreferenceEnumTest {
    @Test
    public void testLevels() throws Exception {
        for(PreferenceEnum flag : Log4jPreferenceEnum.values()) {
            if(flag.isLevel()) {
                assertTrue(Level.isLevel(flag.getPropertyName()));
            }
        }
    }
}
