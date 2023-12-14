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
import org.instancio.vavr.generator.specs.SetGeneratorSpec;

import java.util.HashSet;
import java.util.Set;

public class VavrSetGenerator<T> extends AbstractGenerator<Set<T>> implements SetGeneratorSpec<T> {

    protected int minSize;
    protected int maxSize;

    public VavrSetGenerator(final GeneratorContext context) {
        super(context);
        this.minSize = context.getSettings().get(Keys.COLLECTION_MIN_SIZE);
        this.maxSize = context.getSettings().get(Keys.COLLECTION_MAX_SIZE);
        super.nullable(context.getSettings().get(Keys.COLLECTION_NULLABLE));
    }

    @Override
    public String apiMethod() {
        return null;
    }

    @Override
    public SetGeneratorSpec<T> size(final int size) {
        this.minSize = ApiValidator.validateSize(size);
        this.maxSize = size;
        return this;
    }

    @Override
    public SetGeneratorSpec<T> minSize(final int size) {
        this.minSize = ApiValidator.validateSize(size);
        this.maxSize = NumberUtils.calculateNewMaxSize(maxSize, minSize);
        return this;
    }

    @Override
    public SetGeneratorSpec<T> maxSize(final int size) {
        this.maxSize = ApiValidator.validateSize(size);
        this.minSize = NumberUtils.calculateNewMinSize(minSize, maxSize);
        return this;
    }

    @Override
    public Set<T> tryGenerateNonNull(final Random random) {
        return new HashSet<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Hints hints() {
        return Hints.builder()
                .with(InternalContainerHint.builder()
                        .generateEntries(getContext().random().intRange(minSize, maxSize))
                        .addFunction((Set<T> set, Object... args) -> set.add((T) args[0]))
                        .build())
                .with(InternalGeneratorHint.builder()
                        .nullableResult(isNullable())
                        .build())
                .afterGenerate(AfterGenerate.POPULATE_ALL)
                .build();
    }
}
