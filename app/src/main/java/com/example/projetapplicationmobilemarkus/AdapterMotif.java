package com.example.projetapplicationmobilemarkus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.http.Field;

public class AdapterMotif extends RecyclerView.Adapter<AdapterMotif.MonViewHolder>  {

    //DÉCLARATIONS
    //LIST
    private List<Motif> listeMotif;
    //INTERFACEGESTIONCLIC
    interfaceGestionClic gestionClic;
    //ADAPTERMOTIF
    public AdapterMotif(List<Motif> liste)
    {
        listeMotif = liste;
    }


    //--------------------------------------------------------------------------------------
    // ONCREATEVIEWHOLDER() ---------------------------------------------
    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_catalogue, parent, false);
        return new MonViewHolder(view);
    }



    //--------------------------------------------------------------------------------------
    // ONBINDVIEWHOLDER() ---------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        holder.TvNomMotif.setText(listeMotif.get(position).getNomMotif());

        //Changer le listeMotif pour l'array
        Picasso.get().load(listeMotif.get(position).getImgCreation()).into(holder.IMGMotif);
    }



    //--------------------------------------------------------------------------------------
    // GETITEMCOUNT() ---------------------------------------------
    @Override
    public int getItemCount()
    {
        return listeMotif.size();
    }



    //--------------------------------------------------------------------------------------
    // MONVIEWHOLDER() ---------------------------------------------
    public class MonViewHolder extends RecyclerView.ViewHolder {
        TextView TvNomMotif;
        ImageView IMGMotif;


        public MonViewHolder(@NonNull View itemView) {
            super(itemView);

            TvNomMotif = itemView.findViewById(R.id.TVNomMotif);
            IMGMotif = itemView.findViewById(R.id.IMGMotif);


            // ------------------------ Cliquer sur un item pour afficher une boîte de dialog
            // contenant ses informations ------------------------
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int index = getLayoutPosition();
                    gestionClic.gestionLongClic(listeMotif.get(index), index);

                    return false;
                }
            });
        }
    }


    //--------------------------------------------------------------------------------------
    // AJOUTERMOTIF() ---------------------------------------------
    public void ajouterMotif(Motif m)
    {
        listeMotif.add(m);

        notifyItemInserted(listeMotif.size() - 1);

        System.out.println(listeMotif.size());
    }


    //--------------------------------------------------------------------------------------
    // MODIFIERMOTIF() ---------------------------------------------
    public void modifierMotif(int index, Motif modele)
    {
        Motif motif = listeMotif.get(index);
        motif.setNomMotif(modele.getNomMotif());
        motif.setIdUser(modele.getIdUser());
        motif.setImgCreation(modele.getImgCreation());
        motif.setIdType(modele.getIdType());
        motif.setSource((modele.getSource()));
        motif.setDateCreation(modele.getDateCreation());

        notifyItemChanged(index);
    }

    //--------------------------------------------------------------------------------------
    // SUPPRIMERMOTIF() ---------------------------------------------
    public void suprimerMotif(int index) {
        listeMotif.remove(index);
        notifyItemRemoved(index);
    }

    //--------------------------------------------------------------------------------------
    // CLEAR() ---------------------------------------------
    public void clear()
    {
        int size = listeMotif.size();
        for(int i = 0; i < size; i++)
        {
            listeMotif.remove(i);
            notifyItemRemoved(i);
        }
    }
}
