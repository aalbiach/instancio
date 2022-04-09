/*
 *  Copyright 2022 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.instancio.test.client.generator.array;

import org.instancio.Instancio;
import org.instancio.test.support.tags.Feature;
import org.instancio.test.support.tags.FeatureTag;
import org.instancio.test.support.pojo.arrays.ArrayLong;
import org.instancio.test.support.tags.NonDeterministicTag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Bindings.all;

@NonDeterministicTag
@FeatureTag(Feature.ARRAY_GENERATOR_NULLABLE_ELEMENT)
class ArrayGeneratorWithNullableElementsTest {
    private static final int ARRAY_LENGTH = 1000;

    @Test
    void arrayWithElements() {
        final ArrayLong result = Instancio.of(ArrayLong.class)
                .generate(all(Long[].class), gen -> gen.array().length(ARRAY_LENGTH).nullableElements())
                .create();

        assertThat(result.getWrapper()).containsNull();
        assertThat(new HashSet<>(Arrays.asList(result.getWrapper()))).hasSizeGreaterThan(ARRAY_LENGTH / 2);
    }

}
