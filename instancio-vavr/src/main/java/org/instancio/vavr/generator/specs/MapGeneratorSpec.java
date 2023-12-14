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
package org.instancio.vavr.generator.specs;

import org.instancio.documentation.ExperimentalApi;
import org.instancio.generator.specs.SizeGeneratorSpec;

import java.util.Map;

/**
 * Generator spec for {@link io.vavr.collection.Map Maps}.
 *
 * @param <K> the type of the keys
 * @param <V> the type of the mapped values
 * @since 3.7.1
 */
@ExperimentalApi
public interface MapGeneratorSpec<K, V> extends SizeGeneratorSpec<Map<K, V>> {

    @Override
    MapGeneratorSpec<K, V> size(int size);

    @Override
    MapGeneratorSpec<K, V> minSize(int size);

    @Override
    MapGeneratorSpec<K, V> maxSize(int size);
}
