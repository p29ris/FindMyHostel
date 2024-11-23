// AuthHelper.kt
package com.example.apps

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

fun signUpWithEmail(
    name: String,
    email: String,
    password: String,
    onResult: (Boolean, FirebaseUser?) -> Unit
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                    if (emailTask.isSuccessful) {
                        onResult(true, user)
                        println("Verification email sent to ${user.email}")
                    } else {
                        onResult(false, null)
                        println("Failed to send verification email: ${emailTask.exception?.message}")
                    }
                }
            } else {
                onResult(false, null)
                println("Signup failed: ${task.exception?.message}")
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
                val user = auth.currentUser
                if (user != null && user.isEmailVerified) {
                    onResult(true, user)
                } else {
                    println("Email not verified.")
                    onResult(false, null)
                }
            } else {
                onResult(false, null)
                println("Login failed: ${task.exception?.message}")
            }
        }
}

fun sendPasswordResetEmail(
    email: String,
    onResult: (Boolean) -> Unit
) {
    auth.sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true)
                println("Password reset email sent to $email")
            } else {
                onResult(false)
                println("Failed to send reset email: ${task.exception?.message}")
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
