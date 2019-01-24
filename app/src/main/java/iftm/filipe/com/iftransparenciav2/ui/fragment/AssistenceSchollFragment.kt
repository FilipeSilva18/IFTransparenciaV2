package iftm.filipe.com.iftransparenciav2.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.data.model.response.Auxilio
import iftm.filipe.com.iftransparenciav2.databinding.FragmentAssistenceSchollBinding
import iftm.filipe.com.iftransparenciav2.ui.activity.DetalhesAuxilioActivity
import iftm.filipe.com.iftransparenciav2.ui.activity.LoginActivity
import iftm.filipe.com.iftransparenciav2.ui.adapter.AuxilioAdapter
import iftm.filipe.com.iftransparenciav2.ui.adapter.AuxilioAdapterInteractor
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.DatePickerFragment
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.MainViewModel
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.OnClickListener
import kotlinx.android.synthetic.main.fragment_assistence_scholl.view.*
import javax.inject.Inject

class AssistenceSchollFragment : BaseFragment<FragmentAssistenceSchollBinding>(), OnClickListener, DatePickerFragment.OnDateSetListener {

    override fun onDateSet(data: String) {
        if (mViewModel.dataInicial) mViewModel.auxilioModel.dataInicial = data
        else mViewModel.auxilioModel.dataFinal = data
    }

    @SuppressLint("MissingSuperCall")
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(context)
        print(account?.email)
    }

    @Inject
    lateinit var mViewModel: MainViewModel

    lateinit var mAuxilioAdapter: AuxilioAdapter

    private val auxiliosAdapterInteractor = object : AuxilioAdapterInteractor {
        override fun clickAuxilio(auxilioRequest: Auxilio) {
            var it = Intent(activity, DetalhesAuxilioActivity::class.java)
            it.putExtra("DOCUMENTO", auxilioRequest.documento)
            startActivity(Intent(activity, DetalhesAuxilioActivity::class.java))

        }
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_assistence_scholl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.viewModel = mViewModel
        mViewModel.onClickListener = this
        setBueiroRecyclerView()
        setupListeners()
    }

    private fun setupListeners() {
        mViewDataBinding.btLogout.setOnClickListener { logout() }
    }

    override fun onClickCalendar() {
        DatePickerFragment(this).show(activity!!.supportFragmentManager, "datePicker")
    }

    private fun setBueiroRecyclerView() {
        mAuxilioAdapter = AuxilioAdapter(mViewModel.auxilios, auxiliosAdapterInteractor, this)
        mViewDataBinding.rvAuxilios.adapter = mAuxilioAdapter
        mViewDataBinding.rvAuxilios.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        mViewDataBinding.llAuxilio.visibility = if (mViewModel.auxilios.size > 0) View.VISIBLE else View.GONE
        mViewDataBinding.llEmpty.visibility = if (mViewModel.auxilios.size > 0) View.GONE else View.VISIBLE
    }

    override fun onListAuxiliosAdded(auxilios: List<Auxilio>) {
        mViewModel.auxilios.addAll(auxilios)
        mAuxilioAdapter.notifyDataSetChanged()
        mViewDataBinding.llAuxilio.visibility = if (mViewModel.auxilios.size > 0) View.VISIBLE else View.GONE
        mViewDataBinding.llEmpty.visibility = if (mViewModel.auxilios.size > 0) View.GONE else View.VISIBLE
        mViewModel.loadingVisibility = false
    }

    override fun onConsultaFail() {
        Snackbar.make(activity!!.window.decorView.rootView, "Existe algo errado na sua busca!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mViewDataBinding.llAuxilio.visibility = if (mViewModel.auxilios.size > 0) View.VISIBLE else View.GONE
        mViewDataBinding.llEmpty.visibility = if (mViewModel.auxilios.size > 0) View.GONE else View.VISIBLE

    }

    override fun onLimparListAuxilios() {
        mAuxilioAdapter.cleanList()
        mAuxilioAdapter.notifyDataSetChanged()
    }

    private fun logout() {
        var mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
    }

}