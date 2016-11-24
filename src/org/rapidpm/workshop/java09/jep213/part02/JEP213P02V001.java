package org.rapidpm.workshop.java09.jep213.part02;

import java.io.ByteArrayInputStream;

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
public class JEP213P02V001 {

  public void doSomethingWithOld(ByteArrayInputStream bais) throws Exception {
    try(final ByteArrayInputStream stream = bais) {
      final byte[] readAllBytes = stream.readAllBytes();
    }
  }

  public void doSomethingWith(ByteArrayInputStream bais) throws Exception {
    try(bais) {
      final byte[] readAllBytes = bais.readAllBytes();
    }
  }


  public static void main(String[] args) {





  }
}
