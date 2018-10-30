package com.egco428.ex9_singletouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class SingleTouchView(context: Context):View(context) {
    private val paint = Paint()
    private val paint2 = Paint()
    private val paint3 = Paint()
    private val path = Path()
    private val path2 = Path()
    private val path3 = Path()

    private var eventX: Float = 0F //or 0.toFloat()
    private var eventY: Float = 0F
    private var fingerDown = false
    private var count:Int = 1

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 6f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND

        paint2.isAntiAlias = true
        paint2.strokeWidth = 6f
        paint2.color = Color.RED
        paint2.style = Paint.Style.STROKE
        paint2.strokeJoin = Paint.Join.ROUND

        paint3.isAntiAlias = true
        paint3.strokeWidth = 6f
        paint3.color = Color.BLUE
        paint3.style = Paint.Style.STROKE
        paint3.strokeJoin = Paint.Join.ROUND
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        eventX = event!!.x
        eventY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                if(count==1) {
                    fingerDown = true

                    path.moveTo(eventX,eventY)
                    return true
                } else if(count==2) {
                    fingerDown = true

                    path2.moveTo(eventX,eventY)
                    return true
                } else if(count==3) {
                    fingerDown = true

                    path3.moveTo(eventX,eventY)
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if(count==1) {
                    path.lineTo(eventX,eventY)
                } else if(count==2) {
                    path2.lineTo(eventX,eventY)
                } else if(count==3) {
                    path3.lineTo(eventX,eventY)
                }
            }
            MotionEvent.ACTION_UP -> {
                fingerDown = false
                if(count==1) {
                    count = 2
                } else if(count==2) {
                    count = 3
                } else if(count==3) {
                    count = 1
                }
            }
            else -> return false
        }
        invalidate() //update on User Interface
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path, paint)
        canvas!!.drawPath(path2, paint2)
        canvas!!.drawPath(path3, paint3)

        if(count==1) {

            if(fingerDown){
                canvas!!.drawCircle(eventX,eventY,20F,paint)
            }
        } else if(count==2) {
            if(fingerDown){
                canvas!!.drawCircle(eventX,eventY,20F,paint2)
            }
        } else if(count==3) {
            if(fingerDown){
                canvas!!.drawCircle(eventX,eventY,20F,paint3)
            }
        }
    }
}