/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.instancio.test.pojo.vavr;

import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedMultimap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.Stream;
import io.vavr.collection.Tree;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import lombok.Value;

import java.util.UUID;

@Value
public class AllSupportedVavrTypes {

    // Sequences
    Seq<UUID> seq;
    List<UUID> list;
    Queue<UUID> queue;
    Stream<UUID> stream;
    IndexedSeq<UUID> indexedSeq;
    Array<UUID> array;
    Vector<UUID> vector;
    // Sets
    Set<UUID> set;
    HashSet<UUID> hashSet;
    LinkedHashSet<UUID> linkedHashSet;
    SortedSet<UUID> sortedSet;
    TreeSet<UUID> treeSet;
    // Maps
    Map<UUID, String> map;
    HashMap<UUID, String> hashMap;
    LinkedHashMap<UUID, String> linkedHashMap;
    SortedMap<UUID, String> sortedMap;
    TreeMap<UUID, String> treeMap;
    // Multimaps
    Multimap<UUID, String> multimap;
    HashMultimap<UUID, String> hashMultimap;
    LinkedHashMultimap<UUID, String> linkedHashMultimap;
    SortedMultimap<UUID, String> sortedMultimap;
    TreeMultimap<UUID, String> treeMultimap;
    // Tree
    Tree<UUID> tree;
    // Option
    Option<UUID> option;
}
