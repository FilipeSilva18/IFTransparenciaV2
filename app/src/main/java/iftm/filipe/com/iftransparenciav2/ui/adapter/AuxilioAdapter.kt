package iftm.filipe.com.iftransparenciav2.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.data.model.response.Auxilio
import iftm.filipe.com.iftransparenciav2.data.model.response.AuxilioRequest
import iftm.filipe.com.iftransparenciav2.databinding.ItemListAuxilioBinding

class AuxilioAdapter(
        private val auxilios: ArrayList<Auxilio>,
        private val mInteractor: AuxilioAdapterInteractor,
        private val context: Context
) : RecyclerView.Adapter<AuxilioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AuxilioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_auxilio, parent, false)
        return AuxilioViewHolder(view)
    }

    override fun getItemCount(): Int = auxilios.size

     fun cleanList() = auxilios.clear()

    override fun onBindViewHolder(holder: AuxilioViewHolder, position: Int) {
        holder.mItemViewDataBinding?.tvElemento?.text = "${auxilios[position].elemento}"
        holder.mItemViewDataBinding?.tvData?.text = "${auxilios[position].data}"
        holder.mItemViewDataBinding?.tvValor?.text = "${auxilios[position].valor}"

        holder.mItemViewDataBinding?.rlItemAuxilio?.setOnClickListener {

            mInteractor.clickAuxilio(auxilios[position])
        }
    }
}

class AuxilioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mItemViewDataBinding = DataBindingUtil.bind<ItemListAuxilioBinding>(itemView)
}

interface AuxilioAdapterInteractor {
    fun clickAuxilio(auxilio: Auxilio)
}