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
package de.larsgrammel.kotlin_trees.labeled_multi_value_tree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LabeledMultiValueTreeTest {

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

    @Test
    fun `isLeaf - empty tree`() {
        val tree = LabeledTree()

        assertEquals(
            true,
            tree.root.isLeaf
        )
    }

    @Test
    fun `isLeaf - single child`() {
        val tree = LabeledTree()
        val child = tree.add("child1")

        assertEquals(
            false,
            tree.root.isLeaf
        )
        assertEquals(
            true,
            child.isLeaf
        )
    }

    @Test
    fun `isLeaf child with child`() {
        val tree = LabeledTree()
        val child = tree.add("child1")
        val child2 = child.add("child2")

        assertEquals(
            false,
            child.isLeaf
        )
        assertEquals(
            true,
            child2.isLeaf
        )
    }

    @Test
    fun `single child with value`() {
        val tree = LabeledTree()
        val value = object : Any() {}
        tree.add("child1", value)

        assertEquals(
            root {
                node("child1", value)
            },
            tree.root
        )
    }

    @Test
    fun `child with child`() {
        val tree = LabeledTree()
        val child = tree.add("child1")
        child.add("child2")

        assertEquals(
            root {
                node("child1") {
                    node("child2")
                }
            },
            tree.root
        )
    }

}