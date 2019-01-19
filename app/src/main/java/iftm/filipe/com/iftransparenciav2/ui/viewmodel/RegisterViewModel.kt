package iftm.filipe.com.iftransparenciav2.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.view.View
import android.widget.AdapterView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import iftm.filipe.com.iftransparenciav2.data.repositories.AuxilioRepository
import iftm.filipe.com.iftransparenciav2.ui.model.RegisterModel
import iftm.filipe.com.iftransparenciav2.ui.utils.Util
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val auxilioRepository: AuxilioRepository) : ViewModel() {
    var registerModel = RegisterModel()
    lateinit var callbackFillScreen: RegisterListener

    fun onSelectItem(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.getItemAtPosition(pos)) {
            "IFTM. C. UPT" -> registerModel.institution = "1"
            "IFTM. C. Uberaba" -> registerModel.institution = "2"
            "UFTM" -> registerModel.institution = "3"
        }
    }

    fun fillScreen(account: GoogleSignInAccount) {
        if (account != null) {
            registerModel.name = account.displayName.toString()
            registerModel.email = account.email.toString()
            callbackFillScreen?.onFillScreenListener()
        }
    }

    fun register() {
      registerModel.cpf = Util.replaceChars(registerModel.cpf)
        if (registerModelIsValid()) {
            callbackFillScreen.onRegisterLisneter()
        }
    }

    fun registerModelIsValid(): Boolean = registerModel.name.isNotEmpty() &&
            registerModel.email.isNotEmpty() &&
            registerModel.cpf.isNotEmpty() &&
            registerModel.password.isNotEmpty() &&
            registerModel.institution.isNotEmpty() &&
            Util.myValidateCPF(registerModel.cpf)
}

interface RegisterListener {
    fun onFillScreenListener()
    fun onRegisterLisneter()
}