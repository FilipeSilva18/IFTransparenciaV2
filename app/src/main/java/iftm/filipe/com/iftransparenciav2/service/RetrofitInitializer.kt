package iftm.filipe.com.iftransparenciav2.service

import com.google.gson.Gson
import iftm.filipe.com.iftransparenciav2.data.model.response.AuxilioRequest
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class RetrofitInitializer () {

    lateinit var  retrofit: Retrofit

    internal val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    fun init() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.portaltransparencia.gov.br/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
    }

    fun getService(): TransparenciaService {
        return this.retrofit!!.create(TransparenciaService::class.java!!)
    }

}

interface TransparenciaService {

    @GET("despesas/favorecido/resultado?paginacaoSimples=true&tamanhoPagina=50&offset=0&direcaoOrdenacao=desc&colunaOrdenacao=valor&colunasSelecionadas=data,documentoResumido,localizadorGasto,fase,especie,favorecido,ufFavorecido,valor,ug,uo,orgao,orgaoSuperior,grupo,elemento,modalidade&orgaos=UG158099&elemento=18&_=1545073706192&")
    fun buscarAuxilios(@Query("de") data: String, @Query("ate") data2: String, @Query("faseDespesa") fase: String): Call<AuxilioRequest>

    @GET("despesas/documento/pagamento/empenhos-impactados/resultado?paginacaoSimples=true&tamanhoPagina=50&offset=0&direcaoOrdenacao=desc&colunaOrdenacao=empenhoResumido&colunasSelecionadas=empenhoResumido,valorPago,valorRestoInscrito,valorRestoCancelado,valorRestoPago&fase=Pagamento&_=1545512988955&")
    fun getDetalhesDocumento(@Query("codigo") documento: String): Call<AuxilioRequest>

    @GET("despesas/documento/pagamento/favorecido/resultado?paginacaoSimples=true&tamanhoPagina=50&offset=0&direcaoOrdenacao=desc&colunaOrdenacao=valor&colunasSelecionadas=favorecido,codigoFavorecido,valor&fase=Pagamento&_=1545512988956&")
    fun getBeneficiadosInDocumento(@Query("codigo") documento: String): Call<AuxilioRequest>

}