package com.example.cstomspinner

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SpinnerActivity : AppCompatActivity() {
    var counter = true
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var button : Button
    @SuppressLint("ResourceType", "MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        button = findViewById(R.id.button)
        passwordInputLayout = findViewById(R.id.password_input_layout)
        passwordEditText = findViewById(R.id.password_edit_text)


        passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        passwordInputLayout.setEndIconOnClickListener {
            togglePasswordVisibility()
        }

        val spinner: Spinner = findViewById(R.id.my_spinner)
        val imageArrow:ImageView = findViewById(R.id.imagearrow)

        spinner.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Change the image in the ImageView to indicate that the drop-down is open
                imageArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                // Change the image in the ImageView to indicate that the drop-down is closed
              // imageArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            }
            false



        }

        val items = listOf("Item 1", "Item 2", "Item 3")
        val adapter = object : ArrayAdapter<String>(this, R.layout.spinner_item_layout, items) {

        }

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               imageArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)

                val selectedItem = parent?.getItemAtPosition(position).toString()

                // Do something with the selected item
//                imagearrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing

            }
        }


        // Set a listener for the "done" action on the password field
       // passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            button.setOnClickListener {
                val password = passwordEditText.text?.toString()
                if (password.isNullOrBlank()) {
                    // Show the red error symbol and hide the eye icon
                    passwordInputLayout.error = "Password is required"
                    passwordInputLayout.isErrorEnabled = true
                    passwordInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
                    passwordInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_error)
                    passwordInputLayout.endIconContentDescription = "Error"
                    passwordInputLayout.setEndIconOnClickListener(null)
                    true
                } else {
                    // Hide the error symbol and show the eye icon
                    passwordInputLayout.isErrorEnabled = false
                    passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    true
                }
            }
//            else {
//                false
//            }

    }

    private fun togglePasswordVisibility() {
        if (passwordEditText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Hide the password
            passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye)
        } else {
            // Show the password
            passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye_off)
        }
        // Move the cursor to the end of the password field
        passwordEditText.setSelection(passwordEditText.text?.length ?: 0)
    }


}

