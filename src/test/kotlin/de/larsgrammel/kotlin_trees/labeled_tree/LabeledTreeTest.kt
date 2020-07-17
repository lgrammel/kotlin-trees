/*
 * Copyright 2020 Lars Grammel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.larsgrammel.kotlin_trees.labeled_tree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LabeledTreeTest {

    @Test
    fun `empty tree`() {
        val tree = LabeledTree()

        assertEquals(
            root(),
            tree.root
        )
    }

    @Test
    fun `single child`() {
        val tree = LabeledTree()
        tree.add("child1")

        assertEquals(
            root {
                node("child1")
            },
            tree.root
        )
    }

}