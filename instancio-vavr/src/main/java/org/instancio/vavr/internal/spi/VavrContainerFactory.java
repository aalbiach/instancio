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
package org.instancio.vavr.internal.spi;

import io.vavr.collection.Array;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.Multimap;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
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
import org.instancio.internal.spi.InternalContainerFactoryProvider;
import org.instancio.internal.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.instancio.vavr.internal.util.VavrFunctions.fromIterable;
import static org.instancio.vavr.internal.util.VavrFunctions.fromMap;

@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.ExcessiveImports"})
public class VavrContainerFactory implements InternalContainerFactoryProvider {
    private static final Map<Class<?>, Function<?, ?>> MAPPING_FUNCTIONS = getMappingFunctions();
    private static final Set<Class<?>> CONTAINER_CLASSES = getContainerClasses();

    private static Map<Class<?>, Function<?, ?>> getMappingFunctions() {
        final Map<Class<?>, Function<?, ?>> map = new HashMap<>();

        // Sequences
        map.put(Seq.class, fromIterable(io.vavr.collection.List::ofAll));
        map.put(io.vavr.collection.List.class, fromIterable(io.vavr.collection.List::ofAll));
        map.put(Queue.class, fromIterable(Queue::ofAll));
        map.put(Stream.class, fromIterable(Stream::ofAll));
        map.put(IndexedSeq.class, fromIterable(Array::ofAll));
        map.put(Array.class, fromIterable(Array::ofAll));
        map.put(Vector.class, fromIterable(Vector::ofAll));

        // Sets
        map.put(io.vavr.collection.Set.class, fromIterable(HashSet::ofAll));
        map.put(HashSet.class, fromIterable(HashSet::ofAll));
        map.put(LinkedHashSet.class, fromIterable(LinkedHashSet::ofAll));
        map.put(SortedSet.class, fromIterable(TreeSet::ofAll));
        map.put(TreeSet.class, fromIterable(TreeSet::ofAll));

        // Maps
        map.put(io.vavr.collection.Map.class, fromMap(io.vavr.collection.HashMap::ofAll));
        map.put(io.vavr.collection.HashMap.class, fromMap(io.vavr.collection.HashMap::ofAll));
        map.put(LinkedHashMap.class, fromMap(LinkedHashMap::ofAll));
        map.put(SortedMap.class, fromMap(TreeMap::ofAll));
        map.put(TreeMap.class, fromMap(TreeMap::ofAll));

        // Multimaps
        map.put(Multimap.class, fromMap(m -> HashMultimap.withSeq().ofAll(m)));
        map.put(HashMultimap.class, fromMap(m -> HashMultimap.withSeq().ofAll(m)));
        map.put(LinkedHashMultimap.class, fromMap(m -> LinkedHashMultimap.withSeq().ofAll(m)));
        map.put(SortedMultimap.class, fromMap(m -> TreeMultimap.withSeq().ofAll(m)));
        map.put(TreeMultimap.class, fromMap(m -> TreeMultimap.withSeq().ofAll(m)));

        // Trees
        map.put(Tree.class, fromIterable(Tree::ofAll));

        return Collections.unmodifiableMap(map);
    }

    private static Set<Class<?>> getContainerClasses() {
        return Collections.unmodifiableSet(CollectionUtils.asSet(
                Seq.class,
                io.vavr.collection.List.class,
                Queue.class,
                Stream.class,
                IndexedSeq.class,
                Array.class,
                Vector.class,
                io.vavr.collection.Set.class,
                HashSet.class,
                LinkedHashSet.class,
                SortedSet.class,
                TreeSet.class,
                io.vavr.collection.Map.class,
                io.vavr.collection.HashMap.class,
                LinkedHashMap.class,
                SortedMap.class,
                TreeMap.class,
                Multimap.class,
                HashMultimap.class,
                LinkedHashMultimap.class,
                SortedMultimap.class,
                TreeMultimap.class,
                Tree.class,
                Option.class
        ));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, R> Function<T, R> getMappingFunction(Class<R> type, List<Class<?>> typeArguments) {
        return (Function<T, R>) MAPPING_FUNCTIONS.get(type);
    }

    @Override
    public boolean isContainer(final Class<?> type) {
        return CONTAINER_CLASSES.contains(type);
    }
}
