package com.ryunen344.bottomappbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ryunen344.bottomappbar.databinding.ActivityContainerBinding

class ContainerActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityContainerBinding>(
            this,
            R.layout.activity_container
        ).also {
            it.lifecycleOwner = this@ContainerActivity
        }

        navController =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    override fun onBackPressed() {
        if (onBackPressedDispatcher.hasEnabledCallbacks()) {
        } else {
        }

        super.onBackPressed()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
