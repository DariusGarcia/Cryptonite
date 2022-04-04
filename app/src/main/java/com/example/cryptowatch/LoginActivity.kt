package com.example.cryptowatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if there's a user logged in
//        //If there is, take them to MainActivity
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        fun View.blink1(
            times: Int = Animation.INFINITE,
            duration: Long = 2000L,
            offset: Long = 500L,
            minAlpha: Float = 0.0f,
            maxAlpha: Float = 1.0f,
            repeatMode: Int = Animation.REVERSE
        ) {
            startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
                it.duration = duration
                it.startOffset = offset
                it.repeatMode = repeatMode
                it.repeatCount = times
            })
        }

        fun View.blink2(
            times: Int = Animation.INFINITE,
            duration: Long = 2000L,
            offset: Long = 1000L,
            minAlpha: Float = 0.0f,
            maxAlpha: Float = 1.0f,
            repeatMode: Int = Animation.REVERSE
        ) {
            startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
                it.duration = duration
                it.startOffset = offset
                it.repeatMode = repeatMode
                it.repeatCount = times
            })
        }

        fun View.blink3(
            times: Int = Animation.INFINITE,
            duration: Long = 1750L,
            offset: Long = 1500L,
            minAlpha: Float = 0.0f,
            maxAlpha: Float = 1.0f,
            repeatMode: Int = Animation.REVERSE
        ) {
            startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
                it.duration = duration
                it.startOffset = offset
                it.repeatMode = repeatMode
                it.repeatCount = times
            })
        }

        fun View.blink4(
            times: Int = Animation.INFINITE,
            duration: Long = 1500L,
            offset: Long = 2000L,
            minAlpha: Float = 0.0f,
            maxAlpha: Float = 1.0f,
            repeatMode: Int = Animation.REVERSE
        ) {
            startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
                it.duration = duration
                it.startOffset = offset
                it.repeatMode = repeatMode
                it.repeatCount = times
            })
        }

        findViewById<TextView>(R.id.welcome).blink1(2)
        findViewById<TextView>(R.id.Crypto).blink2(2)
        findViewById<TextView>(R.id.myText).blink3()
        findViewById<TextView>(R.id.et_username).blink4(0)
        findViewById<TextView>(R.id.et_password).blink4(0)
        findViewById<TextView>(R.id.login_bt).blink4(0)
        findViewById<TextView>(R.id.signup_bt).blink4(0)





        findViewById<Button>(R.id.login_bt).setOnClickListener {
                val username = findViewById<EditText>(R.id.et_username).text.toString()
                val password = findViewById<EditText>(R.id.et_password).text.toString()
                loginUser(username, password)
            }

        findViewById<Button>(R.id.signup_bt).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has successfully created a new account.
                Log.i(TAG, "Successfully logged in!")
                goToMainActivity()
                Toast.makeText(this, "Succesfully signed up!", Toast.LENGTH_LONG).show()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                Toast.makeText(this, "Error: Username taken...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

        private fun loginUser(username: String, password: String) {
            ParseUser.logInInBackground(username, password, ({ user, e ->
                if (user != null) {
                    Log.i(TAG, "Successfully logged in!")
                    goToMainActivity()
                } else {
                    e.printStackTrace()
                    Toast.makeText(this, "Error logging in...", Toast.LENGTH_SHORT).show()
                }})
            )

        }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
    companion object {
        const val TAG = "LoginActivity"
    }

}