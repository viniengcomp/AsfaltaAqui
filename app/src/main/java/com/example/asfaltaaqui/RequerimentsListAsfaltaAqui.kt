package com.example.asfaltaaqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.google.firebase.database.*

class RequerimentsListAsfaltaAqui : AppCompatActivity() {
    private lateinit var reqAsfaltaAquiRecyclerView: RecyclerView
    private lateinit var reqListAsfaltaAqui: ArrayList<AsfaltaAquiModelo>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requeriments_list_asfaltaaqui)

        reqAsfaltaAquiRecyclerView = findViewById(R.id.reqList)
        reqAsfaltaAquiRecyclerView.layoutManager = LinearLayoutManager(this)
        reqAsfaltaAquiRecyclerView.setHasFixedSize(true)

        reqListAsfaltaAqui = arrayListOf<AsfaltaAquiModelo>()

        getEmployeesData()
    }

        private fun getEmployeesData() {

            reqAsfaltaAquiRecyclerView.visibility = View.GONE

            dbRef = FirebaseDatabase.getInstance().getReference("RequerimentosAsfaltaAqui")

            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    reqListAsfaltaAqui.clear()
                    if (snapshot.exists()){
                        for (empSnap in snapshot.children){
                            val reqData = empSnap.getValue(AsfaltaAquiModelo::class.java)
                            reqListAsfaltaAqui.add(reqData!!)
                        }

                        val mAdapter = RequerimentsAsfaltaAquiAdapter(reqListAsfaltaAqui)
                        reqAsfaltaAquiRecyclerView.adapter = mAdapter

                        reqAsfaltaAquiRecyclerView.visibility = View.VISIBLE

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }