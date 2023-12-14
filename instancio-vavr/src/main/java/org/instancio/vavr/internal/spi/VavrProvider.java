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
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generator.GeneratorContext;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;
import org.instancio.vavr.internal.generator.VavrMapGenerator;
import org.instancio.vavr.internal.generator.VavrOptionGenerator;
import org.instancio.vavr.internal.generator.VavrSeqGenerator;
import org.instancio.vavr.internal.generator.VavrSetGenerator;

@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.ExcessiveImports"})
public class VavrProvider implements InstancioServiceProvider {

    private GeneratorContext generatorContext;

    @Override
    public void init(final ServiceProviderContext providerContext) {
        this.generatorContext = new GeneratorContext(
                providerContext.getSettings(),
                providerContext.random());
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final java.util.Map<Class<?>, Generator<?>> generators = new java.util.HashMap<>();

        // Sequences
        final Generator<?> seqGenerator = new VavrSeqGenerator<>(generatorContext);
        generators.put(Seq.class, seqGenerator);
        generators.put(List.class, seqGenerator);
        generators.put(Queue.class, seqGenerator);
        generators.put(Stream.class, seqGenerator);
        generators.put(IndexedSeq.class, seqGenerator);
        generators.put(Array.class, seqGenerator);
        generators.put(Vector.class, seqGenerator);

        // Sets
        final Generator<?> setGenerator = new VavrSetGenerator<>(generatorContext);
        generators.put(Set.class, setGenerator);
        generators.put(HashSet.class, setGenerator);
        generators.put(LinkedHashSet.class, setGenerator);
        generators.put(SortedSet.class, setGenerator);
        generators.put(TreeSet.class, setGenerator);

        // Maps
        final Generator<?> mapGenerator = new VavrMapGenerator<>(generatorContext);
        generators.put(Map.class, mapGenerator);
        generators.put(HashMap.class, mapGenerator);
        generators.put(LinkedHashMap.class, mapGenerator);
        generators.put(SortedMap.class, mapGenerator);
        generators.put(TreeMap.class, mapGenerator);

        // Multimaps
        generators.put(Multimap.class, mapGenerator);
        generators.put(HashMultimap.class, mapGenerator);
        generators.put(LinkedHashMultimap.class, mapGenerator);
        generators.put(SortedMultimap.class, mapGenerator);
        generators.put(TreeMultimap.class, mapGenerator);

        // Trees
        generators.put(Tree.class, seqGenerator);

        // Option
        final Generator<?> optionGenerator = new VavrOptionGenerator<>(generatorContext);
        generators.put(Option.class, optionGenerator);

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
