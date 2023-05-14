package com.example.cstomspinner

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.Math.atan2
import kotlin.math.atan2
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var greenwheel1: ImageView
    private lateinit var greenwheel:ImageView
    private lateinit var tvContaner:TextView
    private lateinit var backgroundboders: ImageView
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView
    private lateinit var tv7: TextView
    private lateinit var tv8: TextView
    private var startX by Delegates.notNull<Float>()
    private var startY by Delegates.notNull<Float>()
    private var startAngle by Delegates.notNull<Float>()
    private val tvList: List<TextView> by lazy { listOf(tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8,tv1) }
    private val angles: List<Float> by lazy { listOf(0f, 45f, 90f, 135f, 180f, 225f, 270f, 315f,360f) }
    private var currentAngle = 0f

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor", "MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greenwheel1 = findViewById(R.id.greenwheel1)
        tvContaner = findViewById(R.id.tv_contaner)
//        greenwheel1.setOnClickListener{
//            val intent = Intent(this@MainActivity,SpinnerActivity::class.java)
//            startActivity(intent)
//        }
        backgroundboders = findViewById(R.id.outerboder)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)
        tv7 = findViewById(R.id.tv7)
        tv8 = findViewById(R.id.tv8)

        tvList.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                val targetAngle = angles[index]
                val animation = RotateAnimation(currentAngle, targetAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                animation.duration = 100
                animation.fillAfter = true
              //  greenwheel1.startAnimation(animation)
                tvContaner.startAnimation(animation)
                currentAngle = targetAngle
                if (currentAngle == 360f) {
                    currentAngle = 0f
                }
                if(tvList[index] == tv1)
                {
                    //  backgroundboders.setImageResource(R.drawable.bodercrop)
                    colorChange(tv1)

                }
                else if (tvList[index] == tv2)
                {
                    colorChange(tv2)

                }
                else if (tvList[index] == tv3)
                {
                    colorChange(tv3)

                }
                else if (tvList[index] == tv4)
                {
                    colorChange(tv4)

                }
                else if (tvList[index] == tv5)
                {
                    colorChange(tv5)

                }
                else if (tvList[index] == tv6)
                {
                    colorChange(tv6)

                }
                else if (tvList[index] == tv7)
                {
                    colorChange(tv7)

                }
                else if (tvList[index] == tv8)
                {
                    colorChange(tv8)

                }
                else
                {
                    // backgroundboders.setImageResource(R.drawable.bodercrop)
                    tv1.setTextColor(ContextCompat.getColor(this, R.color.green))
                }
            }
        }
        greenwheel1.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // When the user touches the green-wheel, remember the starting angle of the wheel.
                    startX = event.x
                     startY = event.y
                     startAngle = currentAngle
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // When the user moves their finger on the greenwheel, calculate the angle of the wheel based on the
                    // distance the user has moved their finger.
                    val endX = event.x
                    val endY = event.y
                    val dx = endX - startX
                    val dy = endY - startY
                    val radians = atan2(dy, dx).toDouble()
                    val degrees = (radians / Math.PI * 180.0).toFloat() - 90
                    currentAngle = startAngle + degrees
                    rotateWheel(currentAngle)
                    Log.d("TAG","CurrentAngle1 :: ${currentAngle.toInt()}")

                    true
                }
                else -> false
            }
        }

    }
    private fun colorChange(textView: TextView) {
        tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv7.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv8.setTextColor(ContextCompat.getColor(this, R.color.black))

        when(textView)
        {
            tv1 ->{ tv1.setTextColor(ContextCompat.getColor(this, R.color.green))}
            tv2 -> tv2.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv3 -> tv3.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv4 -> tv4.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv5 -> tv5.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv6 -> tv6.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv7 -> tv7.setTextColor(ContextCompat.getColor(this, R.color.green))
            tv8 -> tv8.setTextColor(ContextCompat.getColor(this, R.color.green))


        }
    }
    private fun rotateWheel(angle: Float) {
        var targetAngle = angle
        // Ensure that targetAngle is always between 0 and 360
        targetAngle %= 360
        if (targetAngle < 0) {
            targetAngle += 360
        }

        val animation = RotateAnimation(currentAngle, targetAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.duration = 100
        animation.fillAfter = true
        tvContaner.startAnimation(animation)
        currentAngle = targetAngle

        if (currentAngle == 360f) {
            currentAngle = 0f
        }
        updateSelectedTextView()
    }
    private fun updateSelectedTextView() {
        if(currentAngle.toInt() > 360) {
            currentAngle = 0.0F
        }
        val angleRanges = mapOf(
            tv1 to 0..22,
            tv2 to 23..67,
            tv3 to 68..112,
            tv4 to 113..157,
            tv5 to 158..202,
            tv6 to 203..247,
            tv7 to 248..292,
            tv8 to 293..360,

        )
        tvList.forEach { textView ->
            val angleRange = angleRanges[textView]

            if (angleRange != null && currentAngle.toInt() in angleRange) {
                Log.d("TAG","CurrentAngle :: ${currentAngle.toInt()}")
                colorChange(textView)
            }
            else {
                textView.setTextColor(ContextCompat.getColor(this, R.color.black))
            }
        }
    }



}
