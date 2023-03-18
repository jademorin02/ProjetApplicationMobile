package com.example.projetapplicationmobilemarkus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;

public class AdapterMotif extends RecyclerView.Adapter<AdapterMotif.MonViewHolder>  {

    //DÉCLARATIONS
    //LIST
    private List<Motif> listeMotif;
    private Context context;

    //INTERFACEGESTIONCLIC
    interfaceGestionClic gestionClic;

    //ADAPTERMOTIF
    public AdapterMotif(List<Motif> motifs) {
        this.listeMotif = motifs;
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
        Motif motif = listeMotif.get(position);
        holder.TvNomMotif.setText(listeMotif.get(position).getNomMotif());

        // Récupérer l'image depuis la base de données et la définir sur l'ImageView
        String imageBytes = motif.getImgCreation();
        if (imageBytes != null) {
            byte[] bytes = Base64.decode(imageBytes, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.IMGMotif.setImageBitmap(bitmap);
        }
        //Changer le listeMotif pour l'array
        //Picasso.get().load(listeMotif.get(position).getImgCreation()).into(holder.IMGMotif);
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
