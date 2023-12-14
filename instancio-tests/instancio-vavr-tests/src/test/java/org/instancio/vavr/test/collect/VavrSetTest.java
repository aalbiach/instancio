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
package org.instancio.vavr.test.collect;

import io.vavr.collection.Set;
import org.instancio.Instancio;
import org.instancio.TypeToken;
import org.instancio.internal.util.Constants;
import org.instancio.junit.InstancioExtension;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(InstancioExtension.class)
class VavrSetTest {

    private static final class Holder {
        private Set<String> set;
    }

    @Test
    void createViaTypeToken() {
        final Set<String> result = Instancio.create(new TypeToken<>() {
        });

        assertThat(result)
                .isInstanceOf(Set.class)
                .hasSizeBetween(Constants.MIN_SIZE, Constants.MAX_SIZE);
    }

    @Test
    void generatorSpecSize() {
        final Set<String> result = Instancio.of(new TypeToken<Set<String>>() {
                })
                .withSettings(Settings.create()
                        .set(Keys.COLLECTION_MIN_SIZE, 1)
                        .set(Keys.COLLECTION_MAX_SIZE, 10))
                .create();

        assertThat(result)
                .isInstanceOf(Set.class)
                .hasSizeBetween(1, 10);
    }

    @Test
    void generatorSpecField() {
        final Holder result = Instancio.create(Holder.class);

        assertThat(result.set)
                .isInstanceOf(Set.class)
                .hasSizeBetween(Constants.MIN_SIZE, Constants.MAX_SIZE);
    }

}
