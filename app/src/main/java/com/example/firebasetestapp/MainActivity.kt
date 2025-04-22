package com.example.firebasetestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        val email = "test@example.com"
        val password = "123456"

        signIn(email, password)
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("AUTH", "✅ Login successful: ${auth.currentUser?.email}")
                } else {
                    Log.e("AUTH", "❌ Login failed: ${task.exception}")
                    signUp(email, password)
                }
            }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("AUTH", "✅ Signup successful: ${auth.currentUser?.email}")
                } else {
                    Log.e("AUTH", "❌ Signup failed: ${task.exception}")
                }
            }
    }
}
