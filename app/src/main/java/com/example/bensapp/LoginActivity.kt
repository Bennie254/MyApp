package com.example.bensapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText




import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var gotoregisterpage : TextView ?= null
    var buttonLogIn: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        gotoregisterpage = findViewById(R.id.gotoRegister)
        buttonLogIn = findViewById(R.id.btnLogin)

        gotoregisterpage!!.setOnClickListener {
            val goToUserIntent= Intent(applicationContext,RegisterActivity::class.java)
            startActivity(goToUserIntent)



        }
        buttonLogIn!!.setOnClickListener {
            performlogin()

        }
    }

    private fun performlogin() {
//         Get data from the user
        val email =findViewById<EditText>(R.id.inputEmail)
        val password =findViewById<EditText>(R.id.inputPassword)

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){

            Toast.makeText(this,"Kindly Fill All Fields!", Toast.LENGTH_SHORT).show()

            return

        }
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, Navigate to the MainActivity
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Login Successful",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed. Try Again !",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }
}