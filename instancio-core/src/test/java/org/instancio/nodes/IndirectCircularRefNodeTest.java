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
package org.instancio.nodes;

import org.instancio.internal.nodes.Node;
import org.instancio.test.support.pojo.cyclic.IndirectCircularRef;
import org.instancio.test.support.tags.CyclicTag;
import org.instancio.testsupport.templates.NodeTestTemplate;
import org.instancio.testsupport.utils.CollectionUtils;

import static org.instancio.testsupport.asserts.NodeAssert.assertNode;

@CyclicTag
class IndirectCircularRefNodeTest extends NodeTestTemplate<IndirectCircularRef> {

    @Override
    protected void verify(Node rootNode) {
        assertNode(rootNode)
                .hasTargetClass(IndirectCircularRef.class)
                .hasChildrenOfSize(1);

        final Node startA = assertNode(CollectionUtils.getOnlyElement(rootNode.getChildren()))
                .hasTargetClass(IndirectCircularRef.A.class)
                .hasFieldName("startA")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        final Node b = assertNode(CollectionUtils.getOnlyElement(startA.getChildren()))
                .hasTargetClass(IndirectCircularRef.B.class)
                .hasFieldName("b")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        final Node c = assertNode(CollectionUtils.getOnlyElement(b.getChildren()))
                .hasTargetClass(IndirectCircularRef.C.class)
                .hasFieldName("c")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        final Node endA = assertNode(CollectionUtils.getOnlyElement(c.getChildren()))
                .hasTargetClass(IndirectCircularRef.A.class)
                .hasFieldName("endA")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        final Node bAgain = assertNode(CollectionUtils.getOnlyElement(endA.getChildren()))
                .hasTargetClass(IndirectCircularRef.B.class)
                .hasFieldName("b")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        final Node cAgain = assertNode(CollectionUtils.getOnlyElement(bAgain.getChildren()))
                .hasTargetClass(IndirectCircularRef.C.class)
                .hasFieldName("c")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        assertNode(CollectionUtils.getOnlyElement(cAgain.getChildren()))
                .hasTargetClass(IndirectCircularRef.A.class)
                .hasFieldName("endA")
                .hasChildrenOfSize(1)
                .getAs(Node.class);

        // TBC...
    }
}