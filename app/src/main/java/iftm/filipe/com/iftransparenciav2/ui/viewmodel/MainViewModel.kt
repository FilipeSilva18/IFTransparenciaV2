package iftm.filipe.com.iftransparenciav2.ui.viewmodel

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.DatePicker
import iftm.filipe.com.iftransparenciav2.data.repositories.AuxilioRepository
import iftm.filipe.com.iftransparenciav2.ui.model.AuxilioModel
import java.util.*
import javax.inject.Inject
import android.widget.AdapterView
import iftm.filipe.com.iftransparenciav2.data.model.response.Auxilio
import iftm.filipe.com.iftransparenciav2.data.model.response.AuxilioRequest
import kotlin.collections.ArrayList


class MainViewModel @Inject constructor(private val auxilioRepository: AuxilioRepository) : ViewModel() {

    var auxilioModel: AuxilioModel = AuxilioModel()
    var auxilioRequest: AuxilioRequest = AuxilioRequest("", "", "", arrayListOf())
    val auxilios = ArrayList<Auxilio>()

    var dataInicial: Boolean = false

    //var loadingVisibility: Boolean = false
    var loadingVisibility: Boolean = false


    var onClickListener: OnClickListener? = null

    fun onClick() {
        dataInicial = true
        onClickListener?.onClickCalendar()
    }

    fun onClickDataFinal() {
        dataInicial = false
        onClickListener?.onClickCalendar()
    }

    fun onSelectItem(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (parent.getItemAtPosition(pos)) {
            "Empenho" -> auxilioModel.fase = "1"
            "Liquidação" -> auxilioModel.fase = "2"
            "Pagamento" -> auxilioModel.fase = "3"
        }
        print(auxilioModel)
    }

    fun buscarAuxilios() {
        auxilioRepository.onClickListener = onClickListener
        if (auxilioModelIsValid()) {
            loadingVisibility = true
            onClickListener?.onLimparListAuxilios()
            auxilios.clear()
            auxilioRepository.findAllAuxilios(auxilioModel)
            auxilioModel.clearFields()

        }
    }


    fun auxilioModelIsValid(): Boolean = auxilioModel.dataInicial.isNotEmpty() &&
            auxilioModel.dataFinal.isNotEmpty() && auxilioModel.fase.isNotEmpty()

}

@SuppressLint("ValidFragment")
class DatePickerFragment constructor(var callbackListenet: OnDateSetListener) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        var dia: String = "$day"
        var mes: String = "${month.toInt() + 1}"
        var ano: String = "$year"
        if (day < 10) dia = "0$dia"
        if (month < 10) mes = "0$month"
        callbackListenet.onDateSet("$dia/$mes/$ano")
    }

    interface OnDateSetListener {
        fun onDateSet(data: String)
    }

}

interface OnClickListener {
    fun onClickCalendar()
    fun onListAuxiliosAdded(auxilio: List<Auxilio>)
    fun onLimparListAuxilios()
    fun onConsultaFail()
}