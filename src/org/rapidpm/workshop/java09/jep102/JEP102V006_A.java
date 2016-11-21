package org.rapidpm.workshop.java09.jep102;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
public class JEP102V006_A {

  private static Function<String, Supplier<ProcessBuilder>> commandSupplierFunction
      = command -> () -> new ProcessBuilder()
      .command(command)
      .inheritIO()
      .redirectOutput(ProcessBuilder.Redirect.INHERIT)
      .redirectError(ProcessBuilder.Redirect.INHERIT);


  public static void main(String[] args) {
    final CompletableFuture<Optional<Process>> commandJConsole = CompletableFuture
        .supplyAsync(commandSupplierFunction.apply("jconsole"))
        .handleAsync(JEP102V006.START_PROCESS);

    final CompletableFuture<Optional<Process>> commandJMC = CompletableFuture
        .supplyAsync(commandSupplierFunction.apply("jmc"))
        .handleAsync(JEP102V006.START_PROCESS);

    commandJConsole
        .thenCombineAsync(commandJMC, (jconsoleOpt, jmcOpt) -> {
          System.out.println("both proc. are started? " + jconsoleOpt.isPresent() + " - " + jmcOpt.isPresent());
          final CompletableFuture<Process> jconsoleProcess = jconsoleOpt.get().onExit();
          final CompletableFuture<Process> jmcProcess = jmcOpt.get().onExit();
          return jconsoleProcess
              .thenCombineAsync(jmcProcess, (jconsole, jmc) -> {
                //both are done -> work on result
                System.out.println("jconsole.getPid() = " + jconsole.getPid());
                System.out.println("jmc.getPid() = " + jmc.getPid());
                return CompletableFuture //-> create the next COMMAND_SUPPLIER_FUNCTION
                    .supplyAsync(commandSupplierFunction.apply("jvisualvm"));
              })
              .thenComposeAsync(cf -> cf); // flatMap
        })
        .thenComposeAsync(cf -> cf) // flatMap
        .handleAsync(JEP102V006.START_PROCESS)  // start third proc
        .join()
        .ifPresent(p -> p
            .onExit()
            .thenAccept(process -> System.out.println("process.getPid() = " + process.getPid()))
            .join()
        );


  }
}
