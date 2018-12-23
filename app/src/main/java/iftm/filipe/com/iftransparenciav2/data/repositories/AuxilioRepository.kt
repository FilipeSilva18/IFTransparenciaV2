package iftm.filipe.com.iftransparenciav2.data.repositories

import iftm.filipe.com.iftransparenciav2.data.model.response.AuxilioRequest
import iftm.filipe.com.iftransparenciav2.service.APIClient
import iftm.filipe.com.iftransparenciav2.service.TransparenciaService
import iftm.filipe.com.iftransparenciav2.ui.model.AuxilioModel
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.OnClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuxilioRepository @Inject constructor(private val client: APIClient) {
    var auxilio: AuxilioRequest = AuxilioRequest("", "", "", arrayListOf())
    public var onClickListener: OnClickListener? = null
    fun findAllAuxilios(auxilioModel: AuxilioModel) {


        client.retrofit.create(TransparenciaService::class.java).buscarAuxilios(auxilioModel.dataInicial, auxilioModel.dataFinal, auxilioModel.fase)
                .enqueue(object : Callback<AuxilioRequest> {
                    override fun onResponse(call: Call<AuxilioRequest>, response: Response<AuxilioRequest>) {
                       if(response.isSuccessful){
                           onClickListener?.onListAuxiliosAdded(response.body()!!.data)
                       }else{
                           onClickListener?.onConsultaFail()
                       }
                        print("as $response")

                    }

                    override fun onFailure(call: Call<AuxilioRequest>, t: Throwable) {
                        onClickListener?.onListAuxiliosAdded(arrayListOf())

                    }
                })

    }
}