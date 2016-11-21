package org.rapidpm.workshop.java09.jep102;

import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.System.out;

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
    final ProcessHandle current = ProcessHandle.current();
    final long currentPid = current.getPid();
    out.println("pid = " + currentPid);

    final Optional<ProcessHandle> parent = ProcessHandle.current().parent();
    parent.ifPresent(f -> out.println("f.getPid() = " + f.getPid()));


    final Stream<ProcessHandle> processHandleStream = ProcessHandle.allProcesses();

    processHandleStream
        .limit(4)
        .forEach( p -> {
          final long pid = p.getPid();
          out.println("pid = " + pid);
        }
    );

    final ProcessHandle processOne = ProcessHandle.of(1).get();
    processOne
        .children()
        .limit(4)
        .forEach(p -> out.println("p = " + p.info() ));


    ProcessHandle
        .current()
        .descendants()
        .forEach(p -> out.println("p = " + p.info() ));

  }
}
