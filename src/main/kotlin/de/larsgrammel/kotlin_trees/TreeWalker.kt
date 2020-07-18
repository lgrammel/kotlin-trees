package de.larsgrammel.kotlin_trees

interface TreeWalker<T> {

    fun visitBeforeChildren(node: T)

    fun visitAfterChildren(node: T)

}