package com.example.asfaltaaqui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RequerimentsAsfaltaAquiAdapter(private val reqList: ArrayList<AsfaltaAquiModelo>) :
    RecyclerView.Adapter<RequerimentsAsfaltaAquiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.requeriments_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentReq = reqList[position]
        holder.TvPhoneUser.text = "telefone usuario: ${currentReq.phoneUser}"
        holder.TvEmailUser.text = "E-mail Usuario: ${currentReq.emailUser}"
        holder.TvNomeUser.text = "Nome Usuario: ${currentReq.nomeUser}"
        holder.TvRuaUser.text = "RuaUsuario: ${currentReq.ruaUser}"
        holder.TvBairroUser.text = "BairroUsuario: ${currentReq.bairroUser}"
    }

    override fun getItemCount(): Int {
        return reqList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TvPhoneUser : TextView = itemView.findViewById(R.id.TvPhoneUser)
        val TvEmailUser : TextView = itemView.findViewById(R.id.TvEmailUser)
        val TvNomeUser : TextView = itemView.findViewById(R.id.TvNomeUser)
        val TvRuaUser : TextView = itemView.findViewById(R.id.TvRuaUser)
        val TvBairroUser : TextView = itemView.findViewById(R.id.TvBairroUser)
    }
}