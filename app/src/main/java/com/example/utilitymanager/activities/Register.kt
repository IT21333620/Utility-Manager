package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.utilitymanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {

    private lateinit var enterEmail: EditText
    private lateinit var enterPassword: EditText
    private lateinit var reEnterPassword: EditText
    private lateinit var register: Button
    private lateinit var reSignin: Button
    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@register, MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        enterEmail = findViewById(R.id.loginPassword)
        enterPassword = findViewById(R.id.regPassword)
        reEnterPassword = findViewById(R.id.reEnterPass)
        register = findViewById(R.id.signUp)
        reSignin = findViewById(R.id.regSignin)


        reSignin.setOnClickListener {
            val intent = Intent(this@register, login_page::class.java)
            startActivity(intent)

        }

        register.setOnClickListener {
            var email: String = ""
            var password: String = ""
            var rePassword: String = ""

            email = enterEmail.text.toString()
            password = enterPassword.text.toString()
            rePassword = reEnterPassword.text.toString()

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this@register, "Enter Email", Toast.LENGTH_SHORT).show()
                //return

            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this@register, "Enter a Password", Toast.LENGTH_SHORT).show()

            }
            if(TextUtils.isEmpty(rePassword)){
                Toast.makeText(this@register, "ReEnter Password", Toast.LENGTH_SHORT).show()

            }

            if (password == rePassword) {
                // passwords match
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Register Sucessfully.",
                                Toast.LENGTH_SHORT,
                            ).show()

                            val intent = Intent(this@register, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
            } else {
                // passwords don't match
                Toast.makeText(
                    baseContext,
                    "Password do not match.",
                    Toast.LENGTH_SHORT,
                ).show()
            }


        }

    }
}