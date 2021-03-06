/*
 * Copyright 2016 Facebook, Inc.
 * <p>
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations under the License.
 */

package io.reactivesocket.tck

import java.io.PrintWriter

// Counter is use as a unique ID. Counter is incremented by 1 on every call 
object ClientIDGen {
  var count = 0

  def getNewID(): Int = {
    count += 1
    count
  }
}

class DSLTestClient(writer : PrintWriter) {

  private var id: Int = ClientIDGen.getNewID()

  def getID: Int = return this.id

  // resumption

  def resume() : Unit = writer.write("c" + this.id + "%%" + "resume" + "\n")

  def disconnect() : Unit = writer.write("c" + this.id + "%%" + "disconnect" + "\n")

  def assertNoClientErrors() : Unit = writer.write("c" + this.id + "%%" + "assert%%no_client_error" + "\n")

  def assertClientError() : Unit = writer.write("c" + this.id + "%%" + "assert%%client_error" + "\n")

}
