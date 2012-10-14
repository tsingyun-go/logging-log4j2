/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.log4j;

import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Enumeration;

/**
 *
 */
public final class LogManager {

    /**
     * @deprecated This variable is for internal use only. It will
     * become package protected in future versions.
     * */
    public static final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";

    static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     * */
    public static final String DEFAULT_CONFIGURATION_KEY="log4j.configuration";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     * */
    public static final String CONFIGURATOR_CLASS_KEY="log4j.configuratorClass";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     */
    public static final String DEFAULT_INIT_OVERRIDE_KEY = "log4j.defaultInitOverride";

    private static final LoggerRepository REPOSITORY = new Repository();

    private LogManager() {
    }

    public static Logger getRootLogger() {
        return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), "");
    }

    public static Logger getLogger(final String name) {
        return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), name);
    }

    public static Logger getLogger(final Class clazz) {
        return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), clazz.getName());
    }

    public static Logger getLogger(final String name, final LoggerFactory factory) {
        return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), name);
    }

    public static Logger exists(String name) {
        LoggerContext ctx = (LoggerContext) PrivateManager.getContext();
        if (!ctx.hasLogger(name)) {
            return null;
        }
        return Logger.getLogger(name);
    }

    public static Enumeration getCurrentLoggers() {
        return NullEnumeration.getInstance();
    }

    static void reconfigure() {
        LoggerContext ctx = (LoggerContext) PrivateManager.getContext();
        ctx.reconfigure();
    }

    /**
     * No-op implementation.
     */
    public static void shutdown() {
    }

    /**
     * No-op implementation.
     */
    public static void resetConfiguration() {
    }

    /**
     * No-op implementation.
     * @param selector The RepositorySelector.
     * @param guard prevents calls at the incorrect time.
     * @throws IllegalArgumentException
     */
    public static void setRepositorySelector(RepositorySelector selector, Object guard)
        throws IllegalArgumentException {
    }

    public static LoggerRepository getLoggerRepository() {
        return REPOSITORY;
    }

    private static class Repository implements LoggerRepository {
        public void addHierarchyEventListener(HierarchyEventListener listener) {

        }

        public boolean isDisabled(int level) {
            return false;
        }

        public void setThreshold(Level level) {

        }

        public void setThreshold(String val) {

        }

        public void emitNoAppenderWarning(Category cat) {

        }

        public Level getThreshold() {
            return Level.OFF;
        }

        public Logger getLogger(String name) {
            return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), name);
        }

        public Logger getLogger(String name, LoggerFactory factory) {
            return (Logger) Category.getInstance((LoggerContext) PrivateManager.getContext(), name);
        }

        public Logger getRootLogger() {
            return (Logger) Category.getRoot((LoggerContext) PrivateManager.getContext());
        }

        public Logger exists(String name) {
            return LogManager.exists(name);
        }

        public void shutdown() {
        }

        public Enumeration getCurrentLoggers() {
            return NullEnumeration.getInstance();
        }

        public Enumeration getCurrentCategories() {
            return NullEnumeration.getInstance();
        }

        public void fireAddAppenderEvent(Category logger, Appender appender) {
        }

        public void resetConfiguration() {
        }
    }

    /**
     * Internal LogManager.
     */
    private static class PrivateManager extends org.apache.logging.log4j.LogManager {
        private static final String FQCN = LogManager.class.getName();


        public static org.apache.logging.log4j.spi.LoggerContext getContext() {
            return getContext(FQCN, false);
        }

        public static org.apache.logging.log4j.Logger getLogger(String name) {
            return getLogger(FQCN, name);
        }
    }
}
