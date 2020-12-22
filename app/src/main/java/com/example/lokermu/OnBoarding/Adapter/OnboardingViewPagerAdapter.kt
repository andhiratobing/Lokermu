package com.example.lokermu.OnBoarding.Adapter

import android.content.Context
import android.opengl.Visibility
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lokermu.OnBoarding.Fragment.OnBoardingFragment
import com.example.lokermu.R

class OnboardingViewPagerAdapter(manager: FragmentManager, private val context : Context) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding1),
                context.resources.getString(R.string.deskripsi_onboarding1),
                R.drawable.onboarding1)
            1 -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding2),
                context.resources.getString(R.string.deskripsi_onboarding2),
                R.drawable.onboarding2)
            2 -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding3),
                context.resources.getString(R.string.deskripsi_onboarding3),
                R.drawable.onboarding3)


            else -> null
        }!!
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private const val NUM_ITEMS = 3
    }


}