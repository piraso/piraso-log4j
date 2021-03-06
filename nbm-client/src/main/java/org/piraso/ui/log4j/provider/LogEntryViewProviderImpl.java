/*
 * Copyright (c) 2012 Alvin R. de Leon. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.piraso.ui.log4j.provider;

import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.piraso.api.entry.Entry;
import org.piraso.api.log4j.Log4jEntry;
import org.piraso.ui.api.EntryViewProvider;
import org.piraso.ui.log4j.Log4jEntryViewTopComponent;

/**
 * Provides the view for {@link org.piraso.api.log4j.Log4jEntry} entry types.
 */
@ServiceProvider(service=EntryViewProvider.class)
public class LogEntryViewProviderImpl implements EntryViewProvider {
    @Override
    public Class<? extends TopComponent> getViewClass(Entry entry) {
        if(Log4jEntry.class.isInstance(entry)) {
            return Log4jEntryViewTopComponent.class;
        }

        return null;
    }
}
