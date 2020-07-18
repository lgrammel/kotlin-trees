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

import de.larsgrammel.kotlin_trees.TreeWalker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LabeledMultiValueTreeTest {

    private fun value(n: Int) = "value-$n"

    @Test
    fun `structure - empty tree`() {
        val tree = LabeledTree<String>()

        assertEquals(
            root<String>(),
            tree.root
        )
    }

    @Test
    fun `structure - single child`() {
        val tree = LabeledTree<String>()
        tree.add("child1")

        assertEquals(
            root<String> {
                node("child1")
            },
            tree.root
        )
    }

    @Test
    fun `structure - single child with value`() {
        val tree = LabeledTree<String>()
        tree.add("child1", value(0))

        assertEquals(
            root<String> {
                node("child1", value(0))
            },
            tree.root
        )
    }

    @Test
    fun `structure - child with child`() {
        val tree = LabeledTree<String>()
        val child = tree.add("child1")
        child.add("child2")

        assertEquals(
            root<String> {
                node("child1") {
                    node("child2")
                }
            },
            tree.root
        )
    }

    @Test
    fun `isLeaf - empty tree`() {
        val tree = LabeledTree<String>()

        assertEquals(
            true,
            tree.root.isLeaf
        )
    }

    @Test
    fun `isLeaf - single child`() {
        val tree = LabeledTree<String>()
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
    fun `isLeaf - child with child`() {
        val tree = LabeledTree<String>()
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
    fun `directLeafValues - root with 2 children`() {
        val tree = LabeledTree<String>()
        tree.add("child1", value(0))
        tree.add("child2", value(1))

        assertEquals(
            listOf(value(0), value(1)),
            tree.root.directLeafValues
        )
    }

    @Test
    fun `walk tree`() {
        val tree = LabeledTree<String>()
        val child = tree.add("child1")
        val child2 = child.add("child2")
        val child3 = child.add("child3")

        val visitedNodes = mutableListOf<Pair<String, LabeledMultiValueTreeNode<String>>>()

        tree.walk(object : TreeWalker<LabeledMultiValueTreeNode<String>> {
            override fun visitBeforeChildren(node: LabeledMultiValueTreeNode<String>) {
                visitedNodes.add("before" to node)
            }

            override fun visitAfterChildren(node: LabeledMultiValueTreeNode<String>) {
                visitedNodes.add("after" to node)
            }
        })

        assertEquals(
            listOf(
                "before" to tree.root,
                "before" to child,
                "before" to child2,
                "after" to child2,
                "before" to child3,
                "after" to child3,
                "after" to child,
                "after" to tree.root
            ),
            visitedNodes
        )

    }

}