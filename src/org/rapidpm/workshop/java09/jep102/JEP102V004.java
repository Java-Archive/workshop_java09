package org.rapidpm.workshop.java09.jep102;

import static java.lang.System.out;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.util.Arrays.asList;

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
public class JEP102V004 {

  public static void main(String[] args) {

    final ProcessHandle start = ProcessHandle.current();
    final ProcessHandle.Info info = start.info();

    info.commandLine().ifPresent(a -> out.println("commandLine = " + a));
    info.command().ifPresent(a -> out.println("arguments = " + a));
    info.arguments().ifPresent(a -> out.println("arguments = " + asList(a)));
    info.startInstant().ifPresent(t -> out.println("startInstant = " + ofInstant(t, systemDefault())));
    info.totalCpuDuration().ifPresent(d -> out.println("d [ms] = " + d.toMillis()));
    info.user().ifPresent(u -> out.println("user = " + u));


  }
}
