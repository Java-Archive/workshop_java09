package org.rapidpm.workshop.java09.streams;

import java.util.List;
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
 * Created by RapidPM - Team on 18.11.16.
 */
public class StreamsDropWhile001 {
  public static void main(String[] args) {
    workOn(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOn(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOn(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOn(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
    workOnParallel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOnParallel(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOnParallel(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOnParallel(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
  }

  private static void workOn(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .dropWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("dropWhile.isEmpty() = " + collect.isEmpty());
    System.out.println("dropWhile = " + collect);
  }
  private static void workOnParallel(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .parallel()
        .dropWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("dropWhileParallel.isEmpty() = " + collect.isEmpty());
    System.out.println("dropWhileParallel = " + collect);
  }
}
