package de.larsgrammel.kotlin_trees.labeled_tree


class LabeledTreeNode {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        return other::class == this::class
    }

}