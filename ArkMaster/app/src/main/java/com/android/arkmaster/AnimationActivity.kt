package com.android.arkmaster

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AnimationActivity(
    private val transitionMode: TransitionMode = TransitionMode.NONE
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTransitionAnimation()
    }

    override fun finish() {
        super.finish()
        exitTransitionAnimation()
    }

    private fun createTransitionAnimation() {
        val enterAnimResId = when (transitionMode) {
            TransitionMode.HORIZON -> R.anim.horizon_enter
            TransitionMode.VERTICAL -> R.anim.vertical_enter
            else -> return
        }

        applyAnimation(enterAnimResId, R.anim.none)
    }

    private fun exitTransitionAnimation() {
        val exitAnimResId = when (transitionMode) {
            TransitionMode.HORIZON -> R.anim.horizon_exit
            TransitionMode.VERTICAL -> R.anim.vertical_exit
            else -> return
        }

        applyAnimation(R.anim.none, exitAnimResId)
    }

    private fun applyAnimation(enterResId: Int, exitResId: Int) {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN, enterResId, exitResId
            )
        } else {
            @Suppress("DEPRECATION")
            overridePendingTransition(enterResId, exitResId)
        }
    }

    enum class TransitionMode {
        NONE,
        HORIZON,
        VERTICAL
    }
}
