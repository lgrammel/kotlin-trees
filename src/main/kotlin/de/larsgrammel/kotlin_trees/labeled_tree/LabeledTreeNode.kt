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

class LabeledTreeNode(val value: Any? = null) {

    private val children = mutableMapOf<String, LabeledTreeNode>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other::class != this::class) return false

        val otherNode = other as LabeledTreeNode

        return otherNode.value == this.value
    }

    fun node(
        name: String,
        value: Any? = null,
        initialize: (LabeledTreeNode.() -> Unit)? = null
    ) {
        val child = add(name, value)
        if (initialize != null) {
            child.initialize()
        }
    }

    fun add(
        name: String,
        value: Any? = null
    ): LabeledTreeNode {
        val child = LabeledTreeNode(value)
        children[name] = child
        return child
    }

}