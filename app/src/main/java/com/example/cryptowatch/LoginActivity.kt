package com.example.cryptowatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if there's a user logged in
        // If there is, take them to MainActivity
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        findViewById<Button>(R.id.login_bt).setOnClickListener {
                val username = findViewById<EditText>(R.id.et_username).text.toString()
                val password = findViewById<EditText>(R.id.et_password).text.toString()
                loginUser(username, password)
            }
        }

        private fun loginUser(username: String, password: String) {
            ParseUser.logInInBackground(username, password, ({ user, e ->
                if (user != null) {
                    Log.i(TAG, "Successfully logged in!")
                    goToMainActivity()
                } else {
                    e.printStackTrace()
                    Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
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