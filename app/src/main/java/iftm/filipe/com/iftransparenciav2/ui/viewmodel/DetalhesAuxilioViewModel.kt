package iftm.filipe.com.iftransparenciav2.ui.viewmodel

import android.arch.lifecycle.ViewModel
import iftm.filipe.com.iftransparenciav2.data.repositories.AuxilioRepository
import javax.inject.Inject

class DetalhesAuxilioViewModel @Inject constructor(private val auxilioRepository: AuxilioRepository) : ViewModel() {
}