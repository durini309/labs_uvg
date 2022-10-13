package com.durini.solucionlab12.presentation.util

import android.view.View

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}