package com.swift.android.ktlint

import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.CompositeElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.psiUtil.children
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class Sorter {

    private val importsOrder = listOf(
        "import kotlin.",
        "import kotlinx.",
        "import java.",
        "import javax."
    )

    fun getImportIndex(text: String): Int {
        return importsOrder.indexOfFirst { text.startsWith(it) }
    }

    private val methodsOrder = listOf(
        /* Presenter */
        "providePresenter",
        "onSetArguments",
        "onFirstViewAttach",
        "onBindView",
        "attachView",
        "startView",
        "stopView",
        "detachView",
        "onUnbindView",
        "createScope",
        /* Fragment */
        "onActivityCreated",
        "onCreate",
        "onBind",
        "onAttachFragment",
        "onStartCommand",
        "onCreateView",
        "onViewCreated",
        "onCreateOptionsMenu",
        "onOptionsItemSelected",
        "onStubInflated",
        "onStart",
        "onResume",
        "onPause",
        "onStop",
        "onDetach",
        "onDestroyView",
        "onDestroy",
        "onRealDestroy",
        "onWindowFocusChanged",
        "onRequestPermissionsResult",
        "onActivityResult",
        /* View */
        "onAttachedToWindow",
        "onSizeChanged",
        "onMeasure",
        "onLayout",
        "onDraw",
        "dispatchTouchEvent",
        "onInterceptTouchEvent",
        "onTouchEvent",
        "dispatchSaveInstanceState",
        "dispatchRestoreInstanceState",
        "onSaveInstanceState",
        "onRestoreInstanceState",
        "callOnClick",
        "setEnabled",
        "onStartJob",
        "onStopJob"
    )

    val sortList = listOf(
        KtStubElementTypes.OBJECT_DECLARATION,
        KtStubElementTypes.PROPERTY,
        KtStubElementTypes.PRIMARY_CONSTRUCTOR,
        KtStubElementTypes.SECONDARY_CONSTRUCTOR,
        KtStubElementTypes.CLASS_INITIALIZER,
        KtStubElementTypes.CLASS,
        KtStubElementTypes.FUNCTION
    )

    private val modifiersOrder = listOf(
        "abstract",
        "override",
        "",
        "suspend",
        "protected",
        "private"
    )

    private val modifiersPropertiesOrder = listOf(
        "abstract",
        "override",
        "",
        "protected",
        "private"
    )

    val indentation = "    "

    fun indentation(level: Int): String {
        return indentation.repeat(level)
    }

    fun removeSpaces(node: ASTNode) {
        node.getChildren(null).filter {
            it.elementType == KtTokens.WHITE_SPACE && it.treeNext.elementType == KtTokens.WHITE_SPACE
        }.forEach { node.removeChild(it) }
    }

    private fun String.containsOneOf(items: List<String>): Boolean {
        items.forEach { if (this == it) return true }
        return false
    }

    fun getModifierIndex(node: ASTNode): Int {
        val modifiers = node.children()
            .filter { it.elementType == KtStubElementTypes.MODIFIER_LIST }
            .flatMap { it.text.split(Regex("[\n ]")).asSequence() }
            .filter { it.isNotEmpty() }
            .filter { it.containsOneOf(modifiersPropertiesOrder) }

        val modifier = modifiers.firstOrNull().orEmpty()
        return when (node.elementType) {
            KtStubElementTypes.PROPERTY -> modifiersPropertiesOrder.indexOf(modifier)
            else -> modifiersOrder.indexOf(modifier) + 100
        }
    }

    fun getMethodIndex(node: ASTNode): Int {

        var index = -1
        if (node.elementType == KtStubElementTypes.FUNCTION) {
            node.children().filter { it.elementType == KtTokens.IDENTIFIER }.firstOrNull()?.let {
                index = methodsOrder.indexOf(it.text)
            }
        }
        if (index == -1) index = 1000
        return index
    }

    fun removeWhitespacesBefore(
        parent: ASTNode,
        node: ASTNode
    ) {
        val level = nestingLevel(node) - 1
        var prev = node.treePrev
        while (prev != null && prev.elementType == KtTokens.WHITE_SPACE) {
            val temp = prev.treePrev
            parent.removeChild(prev)
            prev = temp
        }
        parent.addChild(PsiWhiteSpaceImpl("\n" + indentation(level)), node)
    }

    private fun printSimple(node: ASTNode) {
        println("type = ${node.elementType}, text = \'${node.text}\'")
    }

    @Suppress("unused")
    private fun printNode(node: ASTNode) {
        if (node is CompositeElement) {
            node.children().forEach {
                printNode(it)
            }
        } else {
            printSimple(node)
        }
    }

    fun nestingLevel(astNode: ASTNode): Int {
        var nesting = 0
        var parent = astNode
        while (parent.elementType != KtStubElementTypes.FILE) {
            parent = parent.treeParent
            nesting++
        }

        return (nesting - 1) / 2
    }
}