package com.studiounknown.ui.welcome

import android.os.Handler
import androidx.lifecycle.Observer
import com.studiounknown.R
import com.studiounknown.ui.base.BaseActivity
import com.studiounknown.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class WelcomeActivity : BaseActivity<WelcomeViewModel>() {

    companion object {
        const val COUNT_DOWN_TIME = 3000L //ms
    }

    private val handler = Handler()

    override val viewModel by viewModel<WelcomeViewModel>()

    override val layoutId: Int = R.layout.activity_welcome

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.isFirstRun.observe(this, Observer {
            if (it) {
                gotoMain()
            } else {
                startCountDown()
            }
        })
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun gotoMain() {
        handler.removeCallbacksAndMessages(null)
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }

    private fun startCountDown() {
        handler.postDelayed({
            viewModel.saveFirstRun()
            gotoMain()
        }, COUNT_DOWN_TIME)
    }
}
