package org.rapidpm.workshop.java09.java.lang;

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
 * Created by RapidPM - Team on 18.11.16.
 */
public class StackTraceElementV001 {


  public static void main(String[] args) {
    try {
      throw new RuntimeException("and go");
    } catch (Exception e) {
      final StackTraceElement[] stackTrace = e.getStackTrace();
      for (StackTraceElement stackTraceElement : stackTrace) {
        final String moduleName = stackTraceElement.getModuleName();
        final String moduleVersion = stackTraceElement.getModuleVersion();
        System.out.println("moduleVersion = " + moduleVersion);
        System.out.println("moduleName = " + moduleName);
        System.out.println(stackTraceElement.getClassName() + " - " + stackTraceElement.getMethodName());
      }
    }
  }


}
