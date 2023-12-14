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

import io.vavr.control.Option;
import org.instancio.Random;
import org.instancio.generator.GeneratorContext;
import org.instancio.generator.Hints;
import org.instancio.internal.generator.AbstractGenerator;
import org.instancio.internal.generator.InternalContainerHint;
import org.instancio.vavr.generator.specs.OptionGeneratorSpec;

public class VavrOptionGenerator<T> extends AbstractGenerator<Option<T>>
        implements OptionGeneratorSpec<T> {

    private boolean allowEmpty;

    public VavrOptionGenerator(final GeneratorContext context) {
        super(context);
    }

    @Override
    public String apiMethod() {
        return null;
    }

    @Override
    public VavrOptionGenerator<T> allowEmpty() {
        allowEmpty = true;
        return this;
    }

    @Override
    public Option<T> tryGenerateNonNull(final Random random) {
        if (random.diceRoll(allowEmpty)) {
            return Option.none();
        }
        return null; // must be null to delegate creation to the engine
    }

    @Override
    public Hints hints() {
        return Hints.builder()
                .with(InternalContainerHint.builder()
                        .generateEntries(1)
                        .createFunction(args -> args[0] == null
                                ? Option.none()
                                : Option.of(args[0]))
                        .build())
                .build();
    }
}
