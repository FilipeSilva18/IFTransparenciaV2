package iftm.filipe.com.iftransparenciav2.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.data.repositories.LoginListener
import iftm.filipe.com.iftransparenciav2.databinding.ActivityLoginBinding
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.LoginViewModel
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.OnLoginListener
import javax.inject.Inject


class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    var mAuth = FirebaseAuth.getInstance()

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth


    @Inject
    lateinit var mViewModel: LoginViewModel

    var callbackLoginListener = object : OnLoginListener {
        override fun onLoginListener(email: String, password: String) {
            loginWithEmailAndPassword(email, password)
        }
    }

    var callbacl = object : LoginListener {
        override fun callbackLoginListener() {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        override fun callbackRegisterListener() {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    override fun getContentLayout(): Int = R.layout.activity_login

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            showAlertDialog()
            mViewModel.RegisterOrLogin()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding.viewModel = mViewModel
        mViewModel.callbackLoginListener = callbackLoginListener
        mViewModel.callback = callbacl

        firebaseAuth = FirebaseAuth.getInstance()


        configureGoogleSignIn()
        setupUI()

        setClickListener()

    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
        mGoogleSignInClient.signOut()
    }

    private fun setupUI() {
        mViewDataBinding.signInButton.setSize(SignInButton.SIZE_STANDARD)
        mViewDataBinding.signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showAlertDialog()
                mViewModel.RegisterOrLogin()
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loginWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                showMessage(window.decorView.rootView, "Error: ${task.exception?.message}")
            }
        }
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    fun showAlertDialog() {
         var dialog = ProgressDialog.show (this@LoginActivity, "Teste",
        "Loading. Please wait...", true);
    }

    fun setClickListener() {
        mViewDataBinding.btRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }


}