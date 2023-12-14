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

import java.util.List;

/**
 * Generator spec for {@link io.vavr.collection.Seq Sequences}.
 *
 * @param <V> the type of the values
 * @since 3.7.1
 */
@ExperimentalApi
public interface SeqGeneratorSpec<T> extends SizeGeneratorSpec<List<T>> {

    @Override
    SeqGeneratorSpec<T> size(int size);

    @Override
    SeqGeneratorSpec<T> minSize(int size);

    @Override
    SeqGeneratorSpec<T> maxSize(int size);
}
