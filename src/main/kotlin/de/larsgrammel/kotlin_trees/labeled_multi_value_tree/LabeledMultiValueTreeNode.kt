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

class LabeledMultiValueTreeNode<T>(val value: T? = null) {

    val directLeafValues: List<T>?
        get() = if (isLeaf) null else
            children!!
                .filter { it.value.isLeaf && it.value.value != null }
                .map { it.value.value!! }

    val isLeaf: Boolean
        get() = children == null

    private var children: MutableMap<String, LabeledMultiValueTreeNode<T>>? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other::class != this::class) return false

        val otherNode = other as LabeledMultiValueTreeNode<*>

        return otherNode.value == this.value
    }

    fun node(
        name: String,
        value: T? = null,
        initialize: (LabeledMultiValueTreeNode<T>.() -> Unit)? = null
    ) {
        val child = add(name, value)
        if (initialize != null) {
            child.initialize()
        }
    }

    fun add(
        name: String,
        value: T? = null
    ): LabeledMultiValueTreeNode<T> {
        val child = LabeledMultiValueTreeNode(value)

        if (children == null) {
            children = mutableMapOf()
        }

        children!![name] = child

        return child
    }

}