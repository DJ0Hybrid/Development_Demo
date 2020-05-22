package com.demo.gameoflife

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.gameoflife.Constant.Companion.MILLISECONDS_BETWEEN_TICKS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // TODO Actually handle rotation by preserving our state.

    var lifeController = LifeController()

    val handler = Handler()
    val runnable = object : Runnable {
        override fun run() {
            lifeController.applyGameTick()
            life_display.updateDisplay(lifeController.lifeGrid)
            handler.postDelayed(this, MILLISECONDS_BETWEEN_TICKS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()

        handler.removeCallbacks(runnable)
    }
}
