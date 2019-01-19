package iftm.filipe.com.iftransparenciav2.ui.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import iftm.filipe.com.iftransparenciav2.BR

class LoginModel constructor() : BaseObservable() {
    constructor(
            email: String,
            password: String
    ) : this() {
        this.email = email
        this.password = password
    }

    @get:Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}