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

class LabeledMultiValueTreeNode<T>(val name: String, val value: T? = null) {

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

        return (otherNode.value == this.value) && (otherNode.children == this.children)
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
        path: List<String>,
        value: T? = null
    ): LabeledMultiValueTreeNode<T> {
        val directChildName = path.first()
        return if (path.size == 1) {
            add(directChildName, value)
        } else {
            val child = if (!has(directChildName)) {
                add(directChildName, null)
            } else {
                get(directChildName)
            }
            child!!.add(path.drop(1), value)
        }
    }

    fun has(
        name: String
    ): Boolean = (children != null) && (children!!.containsKey(name))

    fun get(
        name: String
    ): LabeledMultiValueTreeNode<T>? = this.children?.get(name)

    fun add(
        name: String,
        value: T? = null
    ): LabeledMultiValueTreeNode<T> {
        val child = LabeledMultiValueTreeNode(name, value)

        if (children == null) {
            children = mutableMapOf()
        }

        children!![name] = child

        return child
    }

    fun walk(treeWalker: TreeWalker<LabeledMultiValueTreeNode<T>>) {
        treeWalker.visitBeforeChildren(this)
        if (children != null) {
            children!!.forEach { it.value.walk(treeWalker) }
        }
        treeWalker.visitAfterChildren(this)
    }

    override fun toString(): String = "\"$name\" ${children.toString()}"

}