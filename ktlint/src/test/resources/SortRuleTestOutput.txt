package com.swift.android.ktlint

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Parcelable
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Html
import android.text.InputType
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.util.SparseArray
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.serialization.json.Json
import org.jetbrains.anko.dip
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sp
import org.jetbrains.anko.textAppearance
import org.jetbrains.anko.topPadding
import javax.inject.Inject

class BrowserFragment(
    private val param: Int
) : BaseAppFragment<BrowserPresenter, BrowserView>(),
    BrowserView {

    companion object {

        val publicVal2 = 0

        private val privateVal2 = 0

        fun getInstance(screenState: ScreenState) = BrowserFragment().apply {
            this.state = screenState.savedState
            this.arguments = screenState.arguments
        }
    }

    override val layoutId: Int get() = R.layout.fragment_browser

    private val menuPopupWrapper: MenuPopupWrapper by lazy { getMenuPopup() }

    constructor() : this(0)

    inner class SomeClass() {
        companion object {

            val publicVal3 = 0

            private val privateVal3 = 0

            fun getInstance(screenState: ScreenState) = BrowserFragment().apply {
                this.state = screenState.savedState
                this.arguments = screenState.arguments
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

class TitleBody3Holder(parent: View) : RecyclerView.ViewHolder(TitleBody3(parent.context))
