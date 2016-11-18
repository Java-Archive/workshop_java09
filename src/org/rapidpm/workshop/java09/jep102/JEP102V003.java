package org.rapidpm.workshop.java09.jep102;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
public class JEP102V003 {

  public static void main(String[] args) {
    final ProcessBuilder processBuilder = new ProcessBuilder();
    final ProcessBuilder command = processBuilder.command("java" , "-version"); // only if installed
    try {
      command
          .directory(new File("workshop_java09/_tmp/jep102/v003"))
          .inheritIO()
          .redirectOutput(ProcessBuilder.Redirect.INHERIT)
          .redirectError(ProcessBuilder.Redirect.INHERIT);

      System.out.println("directory.toPath() = " + command.directory().toPath());


      final Process start = command.start();

      final long pid = start.getPid();
      System.out.println("pid = " + pid);


      final ProcessHandle.Info info = start.info();
      final Optional<String[]> arguments = info.arguments();

      //startInstant
      //totalCpuDuration
      //user

      //wait until exit is done
      final CompletableFuture<Process> onExit = start.onExit();
      onExit.whenComplete((process, throwable) -> {
        final int exitValue = process.exitValue();
        System.out.println("exitValue = " + exitValue);
      });

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
