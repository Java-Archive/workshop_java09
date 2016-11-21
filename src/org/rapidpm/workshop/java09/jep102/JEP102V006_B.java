package org.rapidpm.workshop.java09.jep102;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
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
 * Created by RapidPM - Team on 19.11.16.
 */
public class JEP102V006_B {

  private static final BiFunction<CompletableFuture<Process>,
                                  CompletableFuture<Process>,
                                  BiFunction<Process,
                                             Process,
                                             Supplier<ProcessBuilder>>> processResults
      = (procCFA, procCFB) -> {
    final Process procA = procCFA.join();
    final Process procB = procCFB.join();
    System.out.println("jconsole.getPid() = " + procA.getPid());
    System.out.println("jmc.getPid() = " + procB.getPid());
    // work on result....
    return (process, process2) -> JEP102V006.COMMAND_SUPPLIER_FUNCTION.apply("jvisualvm");
  };


  public static void main(String[] args) {
//    final JoinProcesses join = () -> processResults;
    ((JEP102V006.JoinProcesses) () -> processResults)
        .apply(JEP102V006.CREATE_AND_START_PROCESS.apply("jconsole"),
            JEP102V006.CREATE_AND_START_PROCESS.apply("jmc"))
        .join()
        .ifPresent(p -> p
            .onExit()
            .thenAccept(process -> System.out.println("process.getPid() = " + process.getPid()))
            .join()
        );
  }


}
