package com.shadows.mydindinnchallenge.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.airbnb.mvrx.BaseMvRxActivity
import com.shadows.mydindinnchallenge.R
import com.shadows.mydindinnchallenge.databinding.MainActivityBinding
import com.shadows.mydindinnchallenge.ui.dishes.DishesFragment
import com.shadows.mydindinnchallenge.utils.binding.viewBinding
import com.shadows.mydindinnchallenge.utils.ui.showView

class MainActivity : BaseMvRxActivity() {

    private val binding by viewBinding (MainActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, DishesFragment.newInstance())
        transaction.commit()
    }

    fun isLoading(flag: Boolean){
        binding.animationLoading.showView(flag)
        if(flag){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

    }
}