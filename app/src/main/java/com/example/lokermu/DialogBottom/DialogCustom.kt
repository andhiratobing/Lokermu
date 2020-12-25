package com.example.lokermu

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.row_awesome_dialog.*


class AwesomeDialog {
    lateinit var from_top: Animation
    lateinit var from_bottom: Animation
    lateinit var titleSuccess: AlertDialog

    /***
     * Positions For Alert Dialog
     * */
    enum class POSITIONS {
        CENTER, BOTTOM
    }

    companion object {

        private lateinit var layoutInflater: LayoutInflater

        /***
         * core method For Alert Dialog
         * */

        fun build(context: Activity): AlertDialog {
            layoutInflater = LayoutInflater.from(context)
            val alertDialog = AlertDialog.Builder(context, R.style.full_screen_dialog)
                .setView(R.layout.row_awesome_dialog)
            val alert: AlertDialog = alertDialog.create()
            // Animation for Icon
            // Let's start with animation work. We just need to create a style and use it here as follows.
            //Pop In and Pop Out Animation yet pending
            alert.window?.attributes?.windowAnimations = R.style.Animation_Design_BottomSheetDialog
            alert.show()
            return alert
        }
    }
}

/***
 * Title Properties For Alert Dialog
 * */


fun AlertDialog.titleSuccess(title: String, fontStyle: Typeface? = null, titleColor: Int = 0, animateIcon: Boolean=true): AlertDialog {
    this.title_success.text = title.trim()
    if (fontStyle != null) {
        this.title_success.typeface = fontStyle
    }
    if (titleColor != 0) {
        this.title_success.setTextColor(titleColor)
    }
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        title_success.startAnimation(Animation)
    }
    this.title_success.show()
    return this
}

fun AlertDialog.titleFailed(title: String, fontStyle: Typeface? = null, titleColor: Int = 0,animateIcon: Boolean=true): AlertDialog {
    this.title_failed.text = title.trim()
    if (fontStyle != null) {
        this.title_failed.typeface = fontStyle
    }
    if (titleColor != 0) {
        this.title_failed.setTextColor(titleColor)
    }
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        title_failed.startAnimation(Animation)
    }
    this.title_failed.show()
    return this
}

/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(dialogBackgroundColor: Int? = null): AlertDialog {
    if (dialogBackgroundColor != null) {
        this.mainLayout.setBackgroundResource(dialogBackgroundColor)
    }
    return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(position: AwesomeDialog.POSITIONS = AwesomeDialog.POSITIONS.BOTTOM): AlertDialog {
    val layoutParams = mainLayout.layoutParams as RelativeLayout.LayoutParams
    if (position == AwesomeDialog.POSITIONS.CENTER) {
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    } else if (position == AwesomeDialog.POSITIONS.BOTTOM) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    }
    mainLayout!!.layoutParams = layoutParams
    return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.bodySuccess(body: String, fontStyle: Typeface? = null, color: Int = 0,animateIcon: Boolean=true): AlertDialog {
    this.subHeadingSuccess.text = body.trim()
    this.subHeadingSuccess.show()
    if (fontStyle != null) {
        this.subHeadingSuccess.typeface = fontStyle
    }
    if (color != 0) {
        this.subHeadingSuccess.setTextColor(color)
    }
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        subHeadingSuccess.startAnimation(Animation)
    }
    return this
}

fun AlertDialog.bodyFailed(body: String, fontStyle: Typeface? = null, color: Int = 0, animateIcon: Boolean=true): AlertDialog {
    this.subHeadingFailed.text = body.trim()
    this.subHeadingFailed.show()
    if (fontStyle != null) {
        this.subHeadingFailed.typeface = fontStyle
    }
    if (color != 0) {
        this.subHeadingFailed.setTextColor(color)
    }
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        subHeadingFailed.startAnimation(Animation)
    }
    return this
}

/***
 * Icon of  Alert Dialog
 * */

fun AlertDialog.iconSuccess(icon: Int, animateIcon: Boolean = true): AlertDialog {
    //this.image_success.setImageResource(icon)
    image_success.show()
    // Animation for Icon
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        image_success.setAnimation(icon)
        image_success.startAnimation(Animation)
    }
    return this
}


fun AlertDialog.iconFailed(icon: Int, animateIcon: Boolean = true): AlertDialog {
    //this.image_failed.setImageResource(icon)
    this.image_failed.show()
    // Animation for Icon
    if (animateIcon) {
        val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        image_failed.setAnimation(icon)
        image_failed.startAnimation(Animation)
    }
    return this
}

/***
 * onPositive Button Properties For Alert Dialog
 * */
fun AlertDialog.onPositive(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
    Btn_positive.startAnimation(Animation)
    this.Btn_positive.show()
    if (buttonBackgroundColor != null) {
        this.Btn_positive.setBackgroundResource(buttonBackgroundColor)
    }
    if (textColor != null) {
        this.Btn_positive.setTextColor(textColor)
    }
    this.Btn_positive.text = text.trim()
    this.Btn_positive.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/***
 * onNegative Button Properties For Alert Dialog
 * */
fun AlertDialog.onNegative(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    this.Btn_Negative.show()
    this.Btn_Negative.text = text.trim()
    val Animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
    Btn_Negative.startAnimation(Animation)

    if (textColor != null) {
        this.Btn_Negative.setTextColor(textColor)
    }
    if (buttonBackgroundColor != null) {
        this.Btn_Negative.setBackgroundResource(buttonBackgroundColor)
    }
    this.Btn_Negative.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

private fun View.show(){
    this.visibility = View.VISIBLE
}

private fun View.dismis(){
    this.visibility = View.INVISIBLE
}
