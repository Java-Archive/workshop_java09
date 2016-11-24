package org.rapidpm.workshop.java09.jep269;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class JEP269V001 {

  //pre java9
  final Set<String> setA = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c")));

  final Set<String> setB = Collections.unmodifiableSet(new HashSet<String>() {{
    add("a"); add("b"); add("c");
  }});

  //since Java8
  final Set<String> set = Collections.unmodifiableSet(Stream.of("a", "b", "c").collect(Collectors.toSet()));


  public static void main(String[] args) {

//    final Set<Object> of = Set.of(); //Returns an immutable set containing zero elements
//    of.add(new Object());

//    final boolean addB = Set.of("A").add("B");
//    final boolean addC = Set.of("A", "B").add("C");


    final Map<String, String> keyA = Map.of("keyA", "valueA");

    // will creae an immutable Entry
    final Map.Entry<String, String> entry = Map.entry("keyA", "valueA");

    final Map<String, String> map = Map.ofEntries(entry);


    final Set<String> a = Set.of("A", null, "B");


  }

}
