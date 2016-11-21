package org.rapidpm.workshop.java09.jep102;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
public class JEP102V005 {
  public static void main(String[] args) {

    final ProcessBuilder processBuilder = new ProcessBuilder();
    final ProcessBuilder command = processBuilder.command("jconsole"); // or maybe jmc ;-)
    command
        .inheritIO()
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT);

    try {
      final Process processJConsole = command.start();
      System.out.println("isAlive = " + processJConsole.isAlive());
      System.out.println("supportsNormalTermination = " + processJConsole.supportsNormalTermination());
      final boolean waitForStartup = processJConsole.waitFor(5, TimeUnit.SECONDS);
      System.out.println("waitForStartup = " + waitForStartup);
      if (!waitForStartup) {
        //process still running and we are now killing it
        final Process destroyForcibly = processJConsole.destroyForcibly();
        System.out.println("destroyForcibly = " + destroyForcibly);
        final int waitFor = processJConsole.waitFor();
        System.out.println("waitFor = " + waitFor);
        System.out.println("isAlive = " + processJConsole.isAlive());
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
