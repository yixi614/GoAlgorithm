/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import Common.LinkedNode;

import java.util.ArrayList;
import java.util.List;

public class JavaBasics {
  public static void main(String[] args) {
    List<Integer> aList = new ArrayList<>();
    aList.add(1);
    List<Integer> bList = new ArrayList<>();
    bList.add(-10);
    changeList(aList, bList);
    System.out.println("Out of the function:");
    for (int i : aList) {
      // this will output "1, 100" instead of "-10, -9"
      // this means,
      // the function passes a copy of a reference of the list, not pointer!!
      // Changes to the copied reference "a" in the function won't change the "a"'s
      // address
      System.out.println(i);
    }

    LinkedNode a = new LinkedNode(1, null);
    LinkedNode b = new LinkedNode(10, null);
    changeNode(a, b);
    // this will output -1 instead of 10 because
    // although the object referenced by "a" is changed.
    // the reference "a" itself is not changed
    System.out.println("Node a's value:");
    System.out.println(a.val);
  }

  public static void changeList(List<Integer> a, List<Integer> b) {
    a.add(100);
    a = b;
    a.add(-9);
    System.out.println("In the function:");
    for (int i : a) {
      System.out.println(i);
    }
  }

  public static void changeNode(LinkedNode a, LinkedNode b) {
    a.val = -1;
    a = b;
  }
}
