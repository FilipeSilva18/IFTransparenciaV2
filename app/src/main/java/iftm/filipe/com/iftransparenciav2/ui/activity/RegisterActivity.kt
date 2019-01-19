package iftm.filipe.com.iftransparenciav2.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.data.model.request.User
import iftm.filipe.com.iftransparenciav2.databinding.ActivityRegisterBinding
import iftm.filipe.com.iftransparenciav2.ui.dialog.ConfirmDialog
import iftm.filipe.com.iftransparenciav2.ui.utils.Util
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.RegisterListener
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.RegisterViewModel
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    @Inject
    lateinit var mViewModel: RegisterViewModel

    var mAuth = FirebaseAuth.getInstance()
    var myRef: DatabaseReference = FirebaseDatabase.getInstance().reference


    var callbackFillScreen = object : RegisterListener {

        override fun onFillScreenListener() {
            mViewDataBinding.etEmail.isEnabled = false
        }

        override fun onRegisterLisneter() {
            register()
        }

    }

    val onConfirmClickListener = object : ConfirmDialog.OnClickListener {
        override fun onButtonClick(alert: AlertDialog) {
            alert.dismiss()
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

    }


    override fun onStart() {
        super.onStart()
        var account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null)
            mViewModel.fillScreen(account!!)
    }

    override fun getContentLayout(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.viewModel = mViewModel
        mViewModel.callbackFillScreen = callbackFillScreen
        mViewDataBinding.etCpf.addTextChangedListener(Util.mask("###.###.###-##", mViewDataBinding.etCpf))

    }

    fun register() {
        mAuth.createUserWithEmailAndPassword(mViewModel.registerModel.email, mViewModel.registerModel.password)
                .addOnCompleteListener(this) { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //Registration OK
                        val firebaseUser = this.mAuth.currentUser
                        myRef.child("user").push().setValue(User(mViewModel.registerModel.name, mViewModel.registerModel.email, mViewModel.registerModel.email, firebaseUser!!.uid))
                        showDialog("Cadastro Realizado com sucesso!!!")
                    } else {
                        showDialog("Error: ${task.exception?.message}")
                    }
                }
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    private fun showDialog(message: String) {
        ConfirmDialog(
                message,
                R.drawable.ic_exclamation_gray,
                onConfirmClickListener,
                layoutInflater,
                this,
                getString(R.string.OK)
        ).show()
    }


}