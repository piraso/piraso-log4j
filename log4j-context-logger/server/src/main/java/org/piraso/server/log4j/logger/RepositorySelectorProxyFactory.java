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

package org.piraso.server.log4j.logger;

import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.piraso.proxy.RegexMethodInterceptorAdapter;
import org.piraso.proxy.RegexMethodInterceptorEvent;
import org.piraso.proxy.RegexProxyFactory;

/**
 * ProxyFactory for {@link RepositorySelector} class.
 */
public class RepositorySelectorProxyFactory extends AbstractLog4jProxyFactory<RepositorySelector> {
    public RepositorySelectorProxyFactory() {
        super(new RegexProxyFactory<RepositorySelector>(RepositorySelector.class));

        factory.addMethodListener("getLoggerRepository", new RegexMethodInterceptorAdapter<RepositorySelector>() {
            @Override
            public void afterCall(RegexMethodInterceptorEvent<RepositorySelector> evt) {
                LoggerRepository returnedValue = (LoggerRepository) evt.getReturnedValue();
                evt.setReturnedValue(new LoggerRepositoryProxyFactory().getProxy(returnedValue));
            }
        });
    }
}
