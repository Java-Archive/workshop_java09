package org.rapidpm.workshop.java09.jep259;

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
public class JEP259V003 {

  public static void main(String[] args) {
    final Class<?> callerClass = StackWalker
        .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE) //Option needed
        .getCallerClass();
    System.out.println("callerClass = " + callerClass);
  }
}
