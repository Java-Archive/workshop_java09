package org.rapidpm.workshop.java09.jep213.part05;

import java.util.function.Function;

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
 * Created by RapidPM - Team on 27.11.16.
 */

@FunctionalInterface
public interface Service {

  Function<String, String> funcStrStr = (str)-> str;

  default void workOnDefault() {
    workA();
    workB();
    workC();
    //somethingHidden();
  }

  void workA();

  private void workB() {
    System.out.println("workB");
  }

  private static void workC() {
    System.out.println("workC");
  }

//  private void somethingHidden(); // declaration only not possible
}
