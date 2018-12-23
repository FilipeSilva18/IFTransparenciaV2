package iftm.filipe.com.iftransparenciav2.ui.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import iftm.filipe.com.iftransparenciav2.BR

class AuxilioModel constructor() : BaseObservable() {
    constructor(
            dataInicial: String,
            dataFinal: String,
            fase: String
    ) : this() {
        this.dataInicial = dataInicial
        this.dataFinal = dataFinal
        this.fase = fase
    }

    @get:Bindable
    var dataInicial: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataInicial)
        }

    @get:Bindable
    var dataFinal: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataFinal)
        }

    @get:Bindable
    var fase: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.fase)
        }

    fun clearFields() {
        dataInicial = ""
        dataFinal = ""
    }
}