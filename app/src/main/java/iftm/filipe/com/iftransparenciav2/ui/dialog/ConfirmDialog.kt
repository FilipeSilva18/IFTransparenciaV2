package iftm.filipe.com.iftransparenciav2.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import iftm.filipe.com.iftransparenciav2.R


class ConfirmDialog constructor(
        val message: String,
        val image: Int,
        val listener: OnClickListener,
        val layoutInflater: LayoutInflater,
        val context: Context,
        val buttonText: String = "Sim",
        val closeOnTouchOutside: Boolean = true
) {


    fun show() {
        val mBuilder = AlertDialog.Builder(context, R.style.FragmentDialogStyle)
        val view = layoutInflater.inflate(R.layout.confirm_fragment_dialog, null)
        val textView = view.findViewById<TextView>(R.id.tvMessage)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val button = view.findViewById<Button>(R.id.bt_confirm)
        button.text = buttonText
        imageView.setImageResource(image)
        textView.text = message
        mBuilder.setView(view)
        val alert = mBuilder.create()
        button.setOnClickListener { listener.onButtonClick(alert) }
        alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alert.setCanceledOnTouchOutside(closeOnTouchOutside)
        alert.show()
    }

    interface OnClickListener {
        fun onButtonClick(alert: AlertDialog)
    }
}