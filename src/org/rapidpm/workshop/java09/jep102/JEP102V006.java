package org.rapidpm.workshop.java09.jep102;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
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
 * Created by RapidPM - Team on 21.11.16.
 */
public class JEP102V006 {

  public static final BiFunction<ProcessBuilder, Throwable, Optional<Process>> START_PROCESS = (processBuilder, throwable) -> {
    try {
      System.out.println("startProcess = " + processBuilder.command());
      return Optional.of(processBuilder.start());
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  };


  //private -> new in JDK9
  public static final Function<String, Supplier<ProcessBuilder>> COMMAND_SUPPLIER_FUNCTION
      = command -> () -> new ProcessBuilder()
      .command(command)
      .inheritIO()
      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
      .redirectError(ProcessBuilder.Redirect.INHERIT);


  public static final Function<String, CompletableFuture<Optional<Process>>> CREATE_AND_START_PROCESS = command
      -> CompletableFuture
          .supplyAsync(COMMAND_SUPPLIER_FUNCTION.apply(command))
          .handleAsync(START_PROCESS);

  //@formatter:off
  @FunctionalInterface
  public interface JoinProcesses extends BiFunction<CompletableFuture<Optional<Process>>,
                                                    CompletableFuture<Optional<Process>>,
                                                    CompletableFuture<Optional<Process>>> {

    BiFunction<CompletableFuture<Process>,
               CompletableFuture<Process>,
               BiFunction<Process,
                          Process,
                          Supplier<ProcessBuilder>>> processResultBiFunction();
    //@formatter:on
    @Override
    default CompletableFuture<Optional<Process>> apply(CompletableFuture<Optional<Process>> procOptA,
                                                       CompletableFuture<Optional<Process>> procOptB) {
      // here more specific exception handlimg or default behavior
      // System.out.println("both proc. are started? " + procOptA.isPresent() + " - " + procOptB.isPresent());
      final CompletableFuture<Process> procA = procOptA.thenComposeAsync(p -> p.get().onExit());
      final CompletableFuture<Process> procB = procOptB.thenComposeAsync(p -> p.get().onExit());
      return procA
          .thenCombineAsync(procB, processResultBiFunction().apply(procA, procB))
          .handleAsync((processBuilderSupplier, throwable)
              -> JEP102V006.START_PROCESS.apply(processBuilderSupplier.get(), throwable));
    }
  }

}
