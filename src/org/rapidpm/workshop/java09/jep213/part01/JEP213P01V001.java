package org.rapidpm.workshop.java09.jep213.part01;

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
 * Created by RapidPM - Team on 24.11.16.
 */
public class JEP213P01V001 {

  @SafeVarargs
  public static <T> void myStaticMethod(T... args) {
    // don something
  }

  @SafeVarargs
  public final <T> void myUnStaticMethod(T... args) {
    // don something
  }

  @SafeVarargs
  private final <T> void myPrivateUnStaticMethod(T... args) {
    // don something
  }

  @SafeVarargs
  private  <T> void myPrivateUnFinalUnStaticMethod(T... args) {
    // don something
  }

  @SafeVarargs
  private static final <T> void myPrivateStaticMethod(T... args) {
    // don something
  }

  @SafeVarargs
  private static <T> void myPrivateUnFinalStaticMethod(T... args) {
    // don something
  }

  public static final class MyFinalClass {

    @SafeVarargs
    public static <T> void myStaticMethod(T... args) {
      // don something
    }

    @SafeVarargs
    public final <T> void myUnStaticMethod(T... args) {
      // don something
    }

    @SafeVarargs
    private final <T> void myPrivateUnStaticMethod(T... args) {
      // don something
    }

    @SafeVarargs
    private <T> void myPrivateUnFinalUnStaticMethod(T... args) {
      // don something
    }

    @SafeVarargs
    private static <T> void myPrivateStaticMethod(T... args) {
      // don something
    }

    @SafeVarargs
    private  <T> void myPrivateUnFinalStaticMethod(T... args) {
      // don something
    }

  }



}
