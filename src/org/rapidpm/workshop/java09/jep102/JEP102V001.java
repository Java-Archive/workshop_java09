package org.rapidpm.workshop.java09.jep102;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

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
public class JEP102V001 {

  public static void main(String[] args) {

    final ProcessBuilder processBuilder = new ProcessBuilder();
    final Map<String, String> environment = processBuilder.environment();

    environment.forEach((key, value) -> System.out.println("key / value = " + key + " - " + value));

    final ProcessBuilder command = processBuilder.command("java" , " --version");
    try {
      final Process start = command.start();
      final long pid = start.getPid();
      System.out.println("pid = " + pid);
      final ProcessHandle.Info info = start.info();



      //wait until exit is done
      final CompletableFuture<Process> onExit = start.onExit();
      onExit.whenComplete((process, throwable) -> {
        final int exitValue = process.exitValue();
        System.out.println("exitValue = " + exitValue);
      });

    } catch (IOException e) {
      e.printStackTrace();
    }


//    ProcessBuilder.startPipeline()
  }

}
