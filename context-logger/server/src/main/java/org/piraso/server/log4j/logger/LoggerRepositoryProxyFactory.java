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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;
import org.piraso.proxy.RegexMethodInterceptorAdapter;
import org.piraso.proxy.RegexMethodInterceptorEvent;
import org.piraso.proxy.RegexProxyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Proxy factory for {@link LoggerRepository}.
 */
public class LoggerRepositoryProxyFactory extends AbstractLog4jProxyFactory<LoggerRepository> {

    private static List<String> EXCLUDES = Collections.synchronizedList(new ArrayList<String>());

    static  {
        addExclude("org.springframework.aop");
        addExclude("org.piraso");
        addExclude("org.owasp");
        addExclude("Encoder");
    }

    public static void addExclude(String exclude) {
        EXCLUDES.add(exclude);
    }

    public static boolean isExclude(String category) {
        for(String exclude : EXCLUDES) {
            if(StringUtils.startsWith(category, exclude)) {
                return true;
            }
        }

        return false;
    }

    public LoggerRepositoryProxyFactory() {
        super(new RegexProxyFactory<LoggerRepository>(LoggerRepository.class));

        factory.addMethodListener("getLogger|getRootLogger|exists", new RegexMethodInterceptorAdapter<LoggerRepository>() {
            @Override
            public void afterCall(RegexMethodInterceptorEvent<LoggerRepository> evt) {
                String category = null;
                if(evt.getInvocation().getMethod().getParameterTypes().length > 0) {
                    category = String.valueOf(evt.getInvocation().getArguments()[0]);
                }

                // ensure that there is no conflict on springframework aop classes
                if(category != null && !isExclude(category)) {
                    Logger logger = (Logger) evt.getReturnedValue();

                    if(Log4JLogger.class.isInstance(logger)) {
                        return;
                    }

                    Logger returnedValue = Log4JLoggerEnhancer.wrap(logger);
                    evt.setReturnedValue(new LoggerProxyFactory(category).getProxy(returnedValue));
                }
            }
        });
    }

}