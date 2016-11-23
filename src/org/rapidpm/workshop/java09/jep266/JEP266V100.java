package org.rapidpm.workshop.java09.jep266;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

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
public class JEP266V100 {
  public static void main(String[] args) {

    final Supplier<String> helloWorld = () -> "HelloWorld";
    final Supplier<String> finallyDone = () -> "finally done";


    final CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(helloWorld);
    final Executor executor = supplyAsync.defaultExecutor();

//    final String joinAsync = supplyAsync.completeAsync(finallyDone).whenCompleteAsync(new BiConsumer<String, Throwable>() {
//      @Override
//      public void accept(final String s, final Throwable throwable) {
//
//
//      }
//    });
    final String join = supplyAsync.completeAsync(finallyDone,executor).join();
    System.out.println("join = " + join);


  }
}
