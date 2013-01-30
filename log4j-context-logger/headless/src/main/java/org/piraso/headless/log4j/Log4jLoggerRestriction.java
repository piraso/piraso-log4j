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

package org.piraso.headless.log4j;

import org.apache.commons.collections.CollectionUtils;
import org.piraso.api.entry.Entry;
import org.piraso.api.log4j.Log4jEntry;
import org.piraso.headless.restriction.Restriction;

/**
 * Determines a log4j logger restriction
 */
public class Log4jLoggerRestriction implements Restriction {

    private String logger;

    public Log4jLoggerRestriction(String logger) {
        this.logger = logger;
    }

    public String getLogger(Entry entry) {
        if(entry.getGroup() == null) {
            return "";
        }

        if(CollectionUtils.isNotEmpty(entry.getGroup().getGroups())) {
            return entry.getGroup().getGroups().iterator().next();
        }

        return "";
    }

    public boolean matches(Entry entry) {
        return Log4jEntry.class.isInstance(entry) && getLogger(entry).matches(logger);
    }
}
