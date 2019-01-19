package iftm.filipe.com.iftransparenciav2.ui.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import iftm.filipe.com.iftransparenciav2.BR

class RegisterModel constructor() : BaseObservable() {
    constructor(
            nome: String,
            email: String,
            password: String,
            cpf: String,
            institution: String
    ) : this() {
        this.name = nome
        this.email = email
        this.cpf = cpf
        this.institution = institution
        this.password = password
    }

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
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

    @get:Bindable
    var cpf: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cpf)
        }

    @get:Bindable
    var institution: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.institution)
        }
}