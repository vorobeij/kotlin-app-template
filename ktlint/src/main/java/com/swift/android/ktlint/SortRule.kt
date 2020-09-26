package com.swift.android.ktlint

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class SortRule : Rule("kotlin-sort") {

    private val sorter = Sorter()

    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {

        when {
            /*node.elementType == KtStubElementTypes.IMPORT_LIST -> {
                val children = node.getChildren(null)
                val sortedInnerElements = children
                    .filter { it.text.isNotBlank() }
                    .sortedBy { it.text }
                    .sortedBy { sorter.getImportIndex(it.text) }

                if (children != sortedInnerElements) {

                    children.forEach {
                        node.removeChild(it)
                    }
                    sortedInnerElements.forEachIndexed { index, astNode ->
                        if (index != 0) node.addChild(PsiWhiteSpaceImpl("\n"), null)
                        node.addChild(astNode, null)
                    }
                }
            }*/
            node.elementType == KtStubElementTypes.CLASS_BODY -> {
                val children = node.getChildren(null)
                val innerElements = children.filter { sorter.sortList.contains(it.elementType) }
                val sortedInnerElements = innerElements
                    .sortedBy { sorter.getMethodIndex(it) }
                    .sortedBy { sorter.getModifierIndex(it) }
                    .sortedBy { sorter.sortList.indexOf(it.elementType) }

                if (innerElements != sortedInnerElements) {

                    emit(node.startOffset, "Incorrect order of inners", true)

                    if (autoCorrect) {
                        innerElements.forEach {
                            node.removeChild(it)
                        }

//                        sorter.removeSpaces(node)

                        val start: ASTNode = node.findChildByType(KtTokens.LBRACE)!!.treeNext

                        sortedInnerElements.forEach { astNode ->
                            node.addChild(astNode, start)
                            val nesting = sorter.nestingLevel(astNode)
                            node.removeChild(astNode)
                            node.addChild(PsiWhiteSpaceImpl("\n\n" + sorter.indentation(nesting)), start)
                            node.addChild(astNode, start)
                        }
                        sorter.removeWhitespacesBefore(
                            node,
                            node.findChildByType(KtTokens.RBRACE)!!
                        )
                    }
                }
            }
        }
    }
}

/*
* todo
* no spaces between similar modifiers
* some order and spacing in functions
* inherit order overrides
* interfaces - define method order
* */