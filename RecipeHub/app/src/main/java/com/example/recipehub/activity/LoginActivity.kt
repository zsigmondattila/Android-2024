package com.example.recipehub.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.example.recipehub.databinding.ActivityLoginBinding
import com.example.recipehub.utils.SharedPreferences
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding
    private val RC_SIGN_IN = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("162589133748-qjgufs6rv44fcrt4q8dstre6v1elo4cs.apps.googleusercontent.com")
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleSignInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)
                val tokenManager = SharedPreferences(applicationContext)
                val bearerToken = "yJhbGciOiJSUzI1NiIsImtpZCI6IjJjOGEyMGFmN2ZjOThmOTdmNDRiMTQyYjRkNWQwODg0ZWIwOTM3YzQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTIyNzA1MjI0NjM1MzcwNDk5MzUiLCJoZCI6InN0dWRlbnQubXMuc2FwaWVudGlhLnJvIiwiZW1haWwiOiJ6c2lnbW9uZC5hdHRpbGFAc3R1ZGVudC5tcy5zYXBpZW50aWEucm8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IkZDM1ZLR2dzNXVHRVczVmw1V2xZTlEiLCJuYW1lIjoiWnNpZ21vbmQgQXR0aWxhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0t3Z0RpaUhQSDdGTTYtNTFqMnp4c2NYc2l4SGstTFJsX0hOQU05OGgxQkFnWTlVQT1zOTYtYyIsImdpdmVuX25hbWUiOiJac2lnbW9uZCIsImZhbWlseV9uYW1lIjoiQXR0aWxhIiwiaWF0IjoxNzMzNjQ5MTIwLCJleHAiOjE3MzM2NTI3MjB9.n_o-qjCIS_RHS4GLpaGfBb8cLPA1JTBpyuZCS579ccEIau6wL6c-9aNzhy3ejDMaXSJalizYbrx4CfoqV_0ruxRttU-quvX7XnLkMRdAvHTmgYU3IIWZ7NKEhhGFW9B3UqD1o3nHJ_5ybNiCMQxLwzToV_fgfxZvcA_PRM6T6lpJ52oUFCkgwUVFtO7ueh0mK-fYewXkTiRT83V_yCkTikIImAKvAQ8LbokYbk9Zk3U5drnoGK5dO4eGtK4pS4pGT65Dly4k_D2HGr2jQZbWAbh99xcuQUu0ptgYdXs7m7KXCi9LPY5gRzkF9TE6F5zVgsuLkPTP_82a3HLe2ePBZg"
                tokenManager.saveToken(bearerToken)

                val tokenPayload = bearerToken.split(".")[1]
                val decodedPayload = String(android.util.Base64.decode(tokenPayload, android.util.Base64.URL_SAFE))
                val userInfo = JSONObject(decodedPayload)
                Log.d("User Info", userInfo.toString())

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } catch (e: ApiException) {

                Log.w("Google Sign-In", "signInResult:failed code=" + e.statusCode)

                val tokenManager = SharedPreferences(applicationContext)
                val bearerToken = "yJhbGciOiJSUzI1NiIsImtpZCI6IjJjOGEyMGFmN2ZjOThmOTdmNDRiMTQyYjRkNWQwODg0ZWIwOTM3YzQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTIyNzA1MjI0NjM1MzcwNDk5MzUiLCJoZCI6InN0dWRlbnQubXMuc2FwaWVudGlhLnJvIiwiZW1haWwiOiJ6c2lnbW9uZC5hdHRpbGFAc3R1ZGVudC5tcy5zYXBpZW50aWEucm8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IkZDM1ZLR2dzNXVHRVczVmw1V2xZTlEiLCJuYW1lIjoiWnNpZ21vbmQgQXR0aWxhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0t3Z0RpaUhQSDdGTTYtNTFqMnp4c2NYc2l4SGstTFJsX0hOQU05OGgxQkFnWTlVQT1zOTYtYyIsImdpdmVuX25hbWUiOiJac2lnbW9uZCIsImZhbWlseV9uYW1lIjoiQXR0aWxhIiwiaWF0IjoxNzMzNjQ5MTIwLCJleHAiOjE3MzM2NTI3MjB9.n_o-qjCIS_RHS4GLpaGfBb8cLPA1JTBpyuZCS579ccEIau6wL6c-9aNzhy3ejDMaXSJalizYbrx4CfoqV_0ruxRttU-quvX7XnLkMRdAvHTmgYU3IIWZ7NKEhhGFW9B3UqD1o3nHJ_5ybNiCMQxLwzToV_fgfxZvcA_PRM6T6lpJ52oUFCkgwUVFtO7ueh0mK-fYewXkTiRT83V_yCkTikIImAKvAQ8LbokYbk9Zk3U5drnoGK5dO4eGtK4pS4pGT65Dly4k_D2HGr2jQZbWAbh99xcuQUu0ptgYdXs7m7KXCi9LPY5gRzkF9TE6F5zVgsuLkPTP_82a3HLe2ePBZg"
                tokenManager.saveToken(bearerToken)

                val storedToken = tokenManager.getToken()
                if (storedToken != null) {
                    Log.d("Token", "Saved Token: $storedToken")
                } else {
                    Log.d("Token", "No token found")
                }

                val tokenPayload = bearerToken.split(".")[1]
                val decodedPayload = String(android.util.Base64.decode(tokenPayload, android.util.Base64.URL_SAFE))
                val userInfo = JSONObject(decodedPayload)
                Log.d("User Info", userInfo.toString())
                tokenManager.saveUserData(bearerToken, userInfo)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                Toast.makeText(this, "Sign-in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show()
        }
    }
}
