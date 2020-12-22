package com.example.lokermu.OnBoarding.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.lokermu.LoginActivity
import com.example.lokermu.OnBoarding.Adapter.OnboardingViewPagerAdapter
import com.example.lokermu.R
import com.example.lokermu.OnBoarding.Utils.Animatoo
import com.example.lokermu.OnBoarding.Utils.Preferences.Companion
import java.util.prefs.Preferences

@Suppress("DEPRECATION")
class OnBoardingSwipeActivity : AppCompatActivity() {

    lateinit var preferences: com.example.lokermu.OnBoarding.Utils.Preferences
    private lateinit var mViewPager: ViewPager
    private lateinit var textSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_swipe)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            window.statusBarColor = Color.TRANSPARENT
        }

        setWindowFlag(
            this,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            false
        )
        window.statusBarColor = Color.TRANSPARENT

        preferences = com.example.lokermu.OnBoarding.Utils.Preferences(this)


        if (preferences.getValues("onboarding").equals("1")) {
            val intent = Intent(this@OnBoardingSwipeActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            finish()
        }

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter(supportFragmentManager, this)

        textSkip = findViewById(R.id.text_skip)

        textSkip.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val btnNextStep: ImageView = findViewById(R.id.btn_next_step)

        btnNextStep.setOnClickListener {
            if (getItem(+1) > mViewPager.childCount-1) {
                preferences.setValues("onboarding", "1")
                finishAffinity()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                mViewPager.setCurrentItem(getItem(+1), true)
            }
        }

    }
    private fun getItem(i: Int): Int {
        return mViewPager.currentItem + i
    }


    companion object {
        //Set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

}