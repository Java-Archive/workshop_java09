package org.rapidpm.workshop.java09.jep213.part03;

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
 * Created by RapidPM - Team on 25.11.16.
 */
public class JEP213P03V001 {

  //possible in Java8
  public <T> MySimpleInterface<T> createInterface(T value) {
    return () -> value;
  }

  //NOT possible in Java8
  public <T> MySimpleClass<T> create(T value) {
    //return new MySimpleClass<T>() {
    return new MySimpleClass<>() {
      @Override
      public T create() {
        return value;
      }
    };
  }


  private static abstract class MySimpleClass<T> {
    public abstract T create();
  }

  private interface MySimpleInterface<T> {
    T create();
  }


}


