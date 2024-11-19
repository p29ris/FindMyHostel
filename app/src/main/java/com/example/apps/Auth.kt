// AuthHelper.kt
package com.example.apps

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

fun signUpWithEmail(
    name : String,
    email: String,
    password: String,
    onResult: (Boolean, FirebaseUser?) -> Unit
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, auth.currentUser)
            } else {
                onResult(false, null)
            }
        }
}

fun loginWithEmail(
    email: String,
    password: String,
    onResult: (Boolean, FirebaseUser?) -> Unit
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, auth.currentUser)
            } else {
                onResult(false, null)
            }
        }
}

// Add the validation logic to this file
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 6 && password.any { it.isDigit() } && password.any { !it.isLetterOrDigit() }
}
