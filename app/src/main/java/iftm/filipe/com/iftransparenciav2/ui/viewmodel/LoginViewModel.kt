package iftm.filipe.com.iftransparenciav2.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import iftm.filipe.com.iftransparenciav2.data.repositories.AuxilioRepository
import iftm.filipe.com.iftransparenciav2.data.repositories.LoginListener
import iftm.filipe.com.iftransparenciav2.data.repositories.LoginRepository
import iftm.filipe.com.iftransparenciav2.ui.model.LoginModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    var loginModel = LoginModel()
    var signInWithGoogle = false
    lateinit var callbackLoginListener: OnLoginListener
    lateinit var callback: LoginListener

    fun login() {
        if (loginIsValid()) {
            callbackLoginListener.onLoginListener(loginModel.email, loginModel.password)
        }
    }

    fun loginIsValid(): Boolean = loginModel.email.isNotEmpty() && loginModel.password.isNotEmpty()

    fun RegisterOrLogin(){
        loginRepository.login(callback)
    }


}

interface OnLoginListener {
    fun onLoginListener(email: String, password: String)
}