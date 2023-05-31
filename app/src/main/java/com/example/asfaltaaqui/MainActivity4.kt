package com.example.asfaltaaqui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.asfaltaaqui.databinding.ActivityMain4Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding : ActivityMain4Binding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var edPhoneUser  = binding.PhoneUser
        var edEmailUser = binding.EmailUser
        var edNomeUser = binding.NomeUser
        var edRuaUser = binding.RuaUser
        var edBairroUser = binding.BairroUser
        var BtCadastro = binding.BtCadastro

        dbRef = FirebaseDatabase.getInstance().getReference("RequerimentosAsfaltaAqui")

        fun onClickCadastrar(){
            val phoneUser = edPhoneUser.text.toString()
            val emailUser= edEmailUser.text.toString()
            val nomeUser = edNomeUser.text.toString()
            val ruaUser = edRuaUser.text.toString()
            val bairroUser = edBairroUser.text.toString()


            if(phoneUser.isEmpty()){
                edPhoneUser.error = "Por favor insira um Telefone"
                return
            }

            if(emailUser.isEmpty()){
                edEmailUser.error = "Por favor insira um E-mail"
                return
            }

            if(nomeUser.isEmpty()){
                edNomeUser.error = "Por favor insira um Nome"
                return
            }

            if(ruaUser.isEmpty()){
                edRuaUser.error = "Por favor insira uma Rua"
                return
            }

            if(bairroUser.isEmpty()){
                edBairroUser.error = "Por favor insira um Bairro"
                return
            }

            val id = dbRef.push().key!!

            val asfaltaAqui = AsfaltaAquiModelo(id, phoneUser, emailUser, nomeUser, ruaUser, bairroUser)

            dbRef.child(id).setValue(asfaltaAqui)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    edPhoneUser.text!!.clear()
                    edEmailUser.text!!.clear()
                    edNomeUser.text!!.clear()
                    edRuaUser.text!!.clear()
                    edBairroUser.text!!.clear()

                    val intent = Intent(this, RequerimentsListAsfaltaAqui::class.java)
                    startActivity(intent)

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }

        BtCadastro.setOnClickListener{
            onClickCadastrar()
        }
    }
}
