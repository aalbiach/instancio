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
import org.instancio.generator.GeneratorContext;
import org.instancio.generator.Hints;
import org.instancio.internal.generator.util.OptionalGenerator;
import org.instancio.settings.Settings;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalGeneratorTest {

    protected final int SAMPLE_SIZE = 500;
    private static final Settings DEFAULT_SETTINGS = Settings.defaults().lock();

    private static final Random random = new DefaultRandom();
    private static final GeneratorContext context = new GeneratorContext(DEFAULT_SETTINGS, random);
    private final OptionalGenerator<Boolean> generator = new OptionalGenerator<>(context);

    @Test
    void hints() {
        final Hints hints = generator.hints();

        HintsAssert.assertHints(hints)
                .afterGenerate(null)
                .containerHintGenerateEntriesIsBetween(1, 1);
    }

    @Test
    void generateNonNull() {
        generator.allowEmpty();
        final Object result = generator.tryGenerateNonNull(random);

        // Returns null to delegate creation of optional to the engine
        assertThat(result).satisfiesAnyOf(
                o -> assertThat(o).isNull(),
                o -> assertThat(o).isInstanceOf(Optional.class));
    }

    @Test
    void generateNull() {
        final Object result = generator.tryGenerateNonNull(random);

        // Returns null to delegate creation of optional to the engine
        assertThat(result).isNull();
    }

    @Test
    void generateNullable() {
        generator.nullable();

        final boolean hasNull = Stream.generate(() -> generator.generate(random))
                .limit(SAMPLE_SIZE)
                .anyMatch(Objects::isNull);

        assertThat(hasNull)
                .as("Expected %s to generate a null", generator.getClass().getSimpleName())
                .isTrue();
    }
}
