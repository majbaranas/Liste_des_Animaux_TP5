package com.example.tp5

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterAnimaux
    private lateinit var listeAnimaux: MutableList<Animal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser le RecyclerView
        recyclerView = findViewById(R.id.recyclerViewAnimaux)

        // Créer la liste d'animaux
        listeAnimaux = creerListeAnimaux()

        // Initialiser l'adapter
        adapter = AdapterAnimaux(listeAnimaux)

        // Configurer le RecyclerView avec LayoutManager linéaire par défaut
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Configurer les RadioButtons pour changer le type d'affichage
        val radioLineaire: RadioButton = findViewById(R.id.radioLineaire)
        val radioGrille: RadioButton = findViewById(R.id.radioGrille)

        radioLineaire.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        radioGrille.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }

    // Fonction pour créer une liste d'animaux de test
    private fun creerListeAnimaux(): MutableList<Animal> {
        return mutableListOf(
            Animal("Chat", "Mammifère", R.drawable.chat),
            Animal("Chien", "Mammifère", R.drawable.dog),
            Animal("Lion", "Mammifère", R.drawable.lion),
            Animal("Aigle", "Oiseau", R.drawable.eagle),
            Animal("Perroquet", "Oiseau", R.drawable.parrot),
            Animal("Serpent", "Reptile", R.drawable.snake),
            Animal("Crocodile", "Reptile", R.drawable.crocodile),
            Animal("Éléphant", "Mammifère", R.drawable.elephant),
            Animal("Tigre", "Mammifère", R.drawable.tiger),
            Animal("Dauphin", "Mammifère", R.drawable.dolphin)
        )
    }
}