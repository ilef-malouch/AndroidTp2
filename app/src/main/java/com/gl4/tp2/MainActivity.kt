package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var classSpinner : Spinner;
    lateinit var recherche : EditText;
    lateinit var recyclerView : RecyclerView;
    lateinit var checkbox: CheckBox

    var matieres = listOf<String>("Cours","TP")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        classSpinner = findViewById(R.id.spinner)
        recherche = findViewById(R.id.recherche)
        checkbox = findViewById(R.id.checkBox2)
        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        classSpinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)
        var etudiants = ArrayList<Etudiant>();
        etudiants.add(Etudiant("Ilef","Malouch","G", arrayListOf(Presence("TP",true),Presence("Cours", false))))
        etudiants.add(Etudiant("Malek","Ghorbrl","M",arrayListOf(Presence("TP",true),Presence("Cours", true))))
        etudiants.add(Etudiant("Zaineb","kefi","G", arrayListOf(Presence("TP",false),Presence("Cours", false))))
        etudiants.add(Etudiant("Amine","Gharbi","M",arrayListOf(Presence("TP",false),Presence("Cours", true))))
        etudiants.add(Etudiant("Mariem","Ghozi","G",arrayListOf(Presence("TP",false),Presence("Cours", true))))
        etudiants.add(Etudiant("Moetaz","Hellal","M",arrayListOf(Presence("TP",false),Presence("Cours", true))))
        etudiants.add(Etudiant("Amani","Ourabi","G",arrayListOf(Presence("TP",false),Presence("Cours", true))))
        var adapter = Adapter(etudiants);
        recyclerView.adapter = adapter;


        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, "Cours choisi est : "+matieres[position], Toast.LENGTH_LONG).show()
                adapter.updateMatiere(matieres[position])
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

        recherche.doOnTextChanged {text,start,before,count -> adapter.filter.filter(text)}

        checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            // write here your code for example ...
            adapter.setShowOnlyPresent(isChecked)
        })
    }
}