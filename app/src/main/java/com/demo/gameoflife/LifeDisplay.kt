package com.demo.gameoflife

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import com.demo.gameoflife.Constant.Companion.GAME_SIZE_X
import com.demo.gameoflife.Constant.Companion.GAME_SIZE_Y

class LifeDisplay(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val livingCellPaint = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }
    private val deadCellColor = Color.BLACK

    var cellWidth: Float = 0.0f
    var cellHeight: Float = 0.0f

    private var displayGrid: List<List<Boolean>> = List(GAME_SIZE_X) {
        List(GAME_SIZE_Y) {
            false
        }
    }

    init {
        setWillNotDraw(false)
    }

    fun updateDisplay(displayGrid: List<List<Boolean>>) {
        this.displayGrid = displayGrid
        this.invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val xPad = (paddingLeft + paddingRight).toFloat()
        val yPad = (paddingTop + paddingBottom).toFloat()

        val realWidth = w.toFloat() - xPad
        val realHeight = h.toFloat() - yPad

        cellWidth = realWidth / GAME_SIZE_X
        cellHeight = realHeight / GAME_SIZE_Y
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            setBackgroundColor(deadCellColor)

            for (x in 0 until GAME_SIZE_X) {
                for (y in 0 until GAME_SIZE_Y) {
                    if (displayGrid.get(x).get(y)) {
                        val left = x * cellWidth + paddingLeft
                        val right = left + cellWidth
                        val top = y * cellHeight + paddingTop
                        val bottom = top + cellHeight
                        drawRect(left, top, right, bottom, livingCellPaint)
                    }
                }
            }
        }
    }
}