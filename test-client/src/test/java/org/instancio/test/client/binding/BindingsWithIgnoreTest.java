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
package org.instancio.test.client.binding;

import org.instancio.Instancio;
import org.instancio.test.support.tags.Feature;
import org.instancio.test.support.tags.FeatureTag;
import org.instancio.test.support.pojo.person.Person;
import org.instancio.test.support.pojo.person.Phone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Bindings.all;
import static org.instancio.Bindings.allStrings;
import static org.instancio.Bindings.field;
import static org.instancio.Bindings.of;

@FeatureTag(Feature.BINDING)
class BindingsWithIgnoreTest {

    @Test
    @DisplayName("Composite binding with different types")
    void compositeBindingWithDifferentTypes() {
        final Person result = Instancio.of(Person.class)
                .ignore(of(
                        allStrings(),
                        all(LocalDateTime.class),
                        all(Date.class),
                        field("gender"),
                        field("age"),
                        field("pets"),
                        all(Phone.class)))
                .create();

        assertThat(result.getAge()).isZero();
        assertThat(result.getName()).isNull();
        assertThat(result.getGender()).isNull();
        assertThat(result.getLastModified()).isNull();
        assertThat(result.getDate()).isNull();
        assertThat(result.getPets()).isNull();
        assertThat(result.getAddress().getCity()).isNull();
        assertThat(result.getAddress().getPhoneNumbers()).isEmpty();
    }
}
