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
package org.instancio.vavr.internal.generator;

import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.GeneratorContext;
import org.instancio.generator.Hints;
import org.instancio.internal.ApiValidator;
import org.instancio.internal.generator.AbstractGenerator;
import org.instancio.internal.generator.InternalContainerHint;
import org.instancio.internal.generator.InternalGeneratorHint;
import org.instancio.internal.util.NumberUtils;
import org.instancio.settings.Keys;
import org.instancio.vavr.generator.specs.MapGeneratorSpec;

import java.util.HashMap;
import java.util.Map;

public class VavrMapGenerator<K, V> extends AbstractGenerator<Map<K, V>> implements MapGeneratorSpec<K, V> {

    protected int minSize;
    protected int maxSize;

    public VavrMapGenerator(final GeneratorContext context) {
        super(context);
        this.minSize = context.getSettings().get(Keys.MAP_MIN_SIZE);
        this.maxSize = context.getSettings().get(Keys.MAP_MAX_SIZE);
        super.nullable(context.getSettings().get(Keys.MAP_NULLABLE));
    }

    @Override
    public String apiMethod() {
        return null;
    }

    @Override
    public MapGeneratorSpec<K, V> size(final int size) {
        this.minSize = ApiValidator.validateSize(size);
        this.maxSize = size;
        return this;
    }

    @Override
    public MapGeneratorSpec<K, V> minSize(final int size) {
        this.minSize = ApiValidator.validateSize(size);
        this.maxSize = NumberUtils.calculateNewMaxSize(maxSize, minSize);
        return this;
    }

    @Override
    public MapGeneratorSpec<K, V> maxSize(final int size) {
        this.maxSize = ApiValidator.validateSize(size);
        this.minSize = NumberUtils.calculateNewMinSize(minSize, maxSize);
        return this;
    }

    @Override
    public Map<K, V> tryGenerateNonNull(final Random random) {
        return new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Hints hints() {
        return Hints.builder()
                .with(InternalContainerHint.builder()
                        .generateEntries(getContext().random().intRange(minSize, maxSize))
                        .addFunction((Map<K, V> map, Object... args) -> map.put((K) args[0], (V) args[1]))
                        .build())
                .with(InternalGeneratorHint.builder()
                        .nullableResult(isNullable())
                        .build())
                .afterGenerate(AfterGenerate.POPULATE_ALL)
                .build();
    }
}
