package org.rapidpm.workshop.java09.java.util.optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
 * Created by RapidPM - Team on 27.11.16.
 */
public class OptionalV001 {


  public static void main(String[] args) {

    final Function<Integer, Optional<Integer>> function
        = (value) -> (value <= 4) ? Optional.of(value) : Optional.empty();

    final List<Integer> collectionJava8 = Stream
        .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map(function) // only to wrap
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());

    final List<Integer> collectionJava9 = Stream
        .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map(function) // only to wrap
        .flatMap(Optional::stream)
        .collect(Collectors.toList());

    System.out.println("collectionJava8 = " + collectionJava8);
    System.out.println("collectionJava9 = " + collectionJava9);


  }


}
