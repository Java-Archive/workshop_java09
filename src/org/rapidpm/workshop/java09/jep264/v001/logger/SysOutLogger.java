package org.rapidpm.workshop.java09.jep264.v001.logger;

import java.util.ResourceBundle;

import static java.text.MessageFormat.format;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 23.11.16.
 */
public class SysOutLogger implements System.Logger {

  @Override
  public String getName() {
    return SysOutLogger.class.getSimpleName();
  }

  @Override // this conf is useless ;-)
  public boolean isLoggable(Level level) {
    switch (level) {
      case OFF:
        return false;
      case TRACE:
        return false;
      case DEBUG:
        return true;
      case INFO:
        return true;
      case WARNING:
        return false;
      case ERROR:
        return false;
      case ALL:
        return false;
      default:
        return true;
    }
  }

  @Override
  public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
    System.out.printf("SysOutLogger: %s: \"%s\" with \"%s\"%n", level, msg, thrown);
  }

  @Override
  public void log(Level level, ResourceBundle bundle, String format, Object... params) {
    System.out.printf("SysOutLogger:  %s: \"%s\"%n", level, format(format, params));
  }
}
