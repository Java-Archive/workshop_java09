package org.rapidpm.workshop.java09.jep102;

import java.io.IOException;

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
public class JEP102V002 {
  public static void main(String[] args) {
    final ProcessBuilder processBuilder = new ProcessBuilder();
    final ProcessBuilder command = processBuilder.command("java", "-version"); // only if installed
    try {
      command
          .inheritIO()
          .redirectOutput(ProcessBuilder.Redirect.INHERIT)
          .redirectError(ProcessBuilder.Redirect.INHERIT);
//          .redirectOutput(ProcessBuilder.Redirect.DISCARD)
//          .redirectError(ProcessBuilder.Redirect.DISCARD);

      final Process start = command.start();
      final int waitFor = start.waitFor();

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

  }
}
