package com.example.tp5

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimaux(private val listeAnimaux: MutableList<Animal>) :
    RecyclerView.Adapter<AdapterAnimaux.AnimalViewHolder>() {

    // ViewHolder pour contenir les vues de chaque élément
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAnimal: ImageView = itemView.findViewById(R.id.imageAnimal)
        val textNom: TextView = itemView.findViewById(R.id.textNom)
        val textEspece: TextView = itemView.findViewById(R.id.textEspece)
        val btnDetails: ImageButton = itemView.findViewById(R.id.btnDetails)
        val btnSupprimer: ImageButton =itemView.findViewById(R.id.btnSupprimer)

        val check: CheckBox= itemView.findViewById(R.id.check)
    }

    // Créer une nouvelle vue pour chaque élément
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    // Lier les données à chaque vue
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = listeAnimaux[position]

        // Afficher les informations de l'animal
        holder.textNom.text = animal.nom
        holder.textEspece.text = animal.espece
        holder.imageAnimal.setImageResource(animal.imageResId)

        // Bouton Détails - Affiche un Dialog avec les informations
        holder.btnDetails.setOnClickListener {
            val message = """
                📌 Nom: ${animal.nom}
                🦁 Espèce: ${animal.espece}
            """.trimIndent()

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Détails de l'animal")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setIcon(animal.imageResId)
                .show()
        }

        // Bouton Supprimer
        holder.btnSupprimer.setOnClickListener {
            val positionActuelle = holder.adapterPosition
            if (positionActuelle != RecyclerView.NO_POSITION) {
                // Afficher une confirmation avant de supprimer
                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Confirmer la suppression")
                    .setMessage("Voulez-vous vraiment supprimer ${animal.nom} ?")
                    .setPositiveButton("Oui") { _, _ ->
                        listeAnimaux.removeAt(positionActuelle)
                        notifyItemRemoved(positionActuelle)
                        notifyItemRangeChanged(positionActuelle, listeAnimaux.size)

                        Toast.makeText(
                            holder.itemView.context,
                            "${animal.nom} a été supprimé",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Non", null)
                    .show()
            }
        }
    }

    // Retourner le nombre total d'éléments
    override fun getItemCount(): Int = listeAnimaux.size
}