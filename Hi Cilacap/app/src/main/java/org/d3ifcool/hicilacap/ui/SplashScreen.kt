package org.d3ifcool.hicilacap.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.d3ifcool.hicilacap.R

class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        nextScreen()
    }

    private fun nextScreen() = main {
        delay(3000L)
        Intent(this@SplashScreen, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }
}