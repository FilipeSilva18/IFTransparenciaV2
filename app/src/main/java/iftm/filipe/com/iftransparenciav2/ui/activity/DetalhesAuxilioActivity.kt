package iftm.filipe.com.iftransparenciav2.ui.activity

import android.os.Bundle
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.databinding.ActivityDetalhesAuxilioBinding

class DetalhesAuxilioActivity : BaseActivity<ActivityDetalhesAuxilioBinding>() {

    var documento: String = ""

    override fun getContentLayout(): Int = R.layout.activity_detalhes_auxilio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("oi")

        if (intent.hasExtra("DOCUMENTO")) {
            documento = intent.getStringExtra("DOCUMENTO").toString()
            print(documento)
        }


    }
}