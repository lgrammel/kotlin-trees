package de.larsgrammel.kotlin_trees.labeled_tree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LabeledTreeTest {

    @Test
    fun `empty tree`() {
        val actual = LabeledTree()

        assertEquals(
            LabeledTreeNode(),
            actual.root
        )
    }

}