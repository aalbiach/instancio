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
package org.instancio.creation.cyclic.onetomany;

import org.instancio.test.support.pojo.cyclic.onetomany.DetailRecord;
import org.instancio.test.support.pojo.cyclic.onetomany.MainRecord;
import org.instancio.test.support.tags.CyclicTag;
import org.instancio.testsupport.templates.AutoVerificationDisabled;
import org.instancio.testsupport.templates.CreationTestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@CyclicTag
@AutoVerificationDisabled
public class DetailRecordCreationTest extends CreationTestTemplate<DetailRecord> {

    @Override
    protected void verify(DetailRecord result) {
        final MainRecord mainRecord1 = result.getMainRecord();
        assertThat(mainRecord1).isNotNull();

        assertThat(mainRecord1.getDetailRecords()).isNotEmpty()
                .allSatisfy(detail -> {
                    final MainRecord innerMainRecord = detail.getMainRecord();
                    assertThat(innerMainRecord.getDetailRecords())
                            .as("Should have an empty collection to break the cycle")
                            .isEmpty();
                });
    }

}
