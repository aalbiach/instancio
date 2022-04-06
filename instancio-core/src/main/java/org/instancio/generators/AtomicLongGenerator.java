/*
 * Copyright 2022 the original author or authors.
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
package org.instancio.generators;

import org.instancio.GeneratorContext;
import org.instancio.generators.coretypes.AbstractRandomNumberGeneratorSpec;
import org.instancio.generators.coretypes.NumberGeneratorSpec;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongGenerator extends AbstractRandomNumberGeneratorSpec<AtomicLong>
        implements NumberGeneratorSpec<AtomicLong> {

    private static final int DEFAULT_MIN = 1;
    private static final int DEFAULT_MAX = 10_000;

    public AtomicLongGenerator(final GeneratorContext context) {
        super(context,
                new AtomicLong(DEFAULT_MIN),
                new AtomicLong(DEFAULT_MAX),
                false);
    }

    @Override
    protected AtomicLong generateNonNullValue() {
        return new AtomicLong(random().intBetween(min.intValue(), max.intValue()));
    }
}
