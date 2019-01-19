package iftm.filipe.com.iftransparenciav2.data.repositories

import android.content.Intent
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat.startActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import iftm.filipe.com.iftransparenciav2.data.model.request.User
import iftm.filipe.com.iftransparenciav2.ui.activity.MainActivity
import iftm.filipe.com.iftransparenciav2.ui.activity.RegisterActivity
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    // private var mAuth: FirebaseAuth? = null

    fun login(callback: LoginListener) {
        var myRef = FirebaseDatabase.getInstance().reference
        var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
        var flagRegister = true
        myRef.child("user").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (noteDataSnapshot in dataSnapshot.children) {
                    val u = User(noteDataSnapshot.child("name")
                    !!.value as String, noteDataSnapshot.child("email")!!.value as String, noteDataSnapshot.child("cpf")!!.value as String, noteDataSnapshot.child("uid")!!.value as String)
                    if (u!!.uid.equals(mAuth?.currentUser?.uid)) {
                        callback.callbackLoginListener()
                        flagRegister = false
                        break
                    }
                }
                if (flagRegister)
                    callback.callbackRegisterListener()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }


}

interface LoginListener {
    fun callbackLoginListener()
    fun callbackRegisterListener()
}