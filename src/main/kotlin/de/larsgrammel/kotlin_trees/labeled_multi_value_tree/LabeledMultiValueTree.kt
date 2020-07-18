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

class LabeledMultiValueTree<T> {

    val root: LabeledMultiValueTreeNode<T> = LabeledMultiValueTreeNode("")

    fun add(
        name: String,
        value: T? = null
    ): LabeledMultiValueTreeNode<T> = root.add(name, value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other::class != this::class) return false

        val otherNode = other as LabeledMultiValueTree<*>

        return otherNode.root == this.root
    }

    fun walk(treeWalker: TreeWalker<LabeledMultiValueTreeNode<T>>) {
        root.walk(treeWalker)
    }

}

fun <T> tree(initialize: (LabeledMultiValueTreeNode<T>.() -> Unit)? = null): LabeledMultiValueTree<T> {
    val tree = LabeledMultiValueTree<T>()
    if (initialize != null) {
        tree.root.initialize()
    }
    return tree
}