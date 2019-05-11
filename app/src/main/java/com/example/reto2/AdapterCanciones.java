package com.example.reto2;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCanciones extends RecyclerView.Adapter<AdapterCanciones.CustomViewHolder> {

    ArrayList<CancionModel> data;
    //FirebaseStorage storage;

    public void showAllAmigos(ArrayList<CancionModel> allAmigos) {
        for(int i = 0 ; i<allAmigos.size() ; i++){
            if(!data.contains(allAmigos.get(i))) data.add(allAmigos.get(i));
        }
        notifyDataSetChanged();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public AdapterCanciones(){
        data = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_canciones, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        Picasso.get()
                .load(data.get(position).getImagen())
                .into((ImageView) holder.root.findViewById(R.id.renglon_cancion_img));
        ((TextView) holder.root.findViewById(R.id.renglon_cancion_nombre)).setText(data.get(position).getNombre());
        ((TextView) holder.root.findViewById(R.id.renglon_cancion_artista)).setText(data.get(position).getArtista());
        ((TextView) holder.root.findViewById(R.id.renglon_cancion_anio)).setText(data.get(position).getAnio());

//        storage = FirebaseStorage.getInstance();
//        StorageReference ref = storage.getReference().child("profiles").child(data.get(position).getTelefono());
//        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                ImageView img = holder.root.findViewById(R.id.renglon_amigo_img);
//                Glide.with(holder.root.getContext()).load(uri).into(img);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //OBSERVER
    public interface OnItemClickListener{
        void onItemClick(CancionModel cancion);
        void onChat(CancionModel cancion);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
