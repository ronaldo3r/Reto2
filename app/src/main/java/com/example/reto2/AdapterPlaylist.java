package com.example.reto2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;
import com.example.reto2.Model.PlaylistModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPlaylist extends RecyclerView.Adapter<AdapterPlaylist.CustomViewHolder> {

    ArrayList<PlaylistModel> data;
    //FirebaseStorage storage;

    public void showAllAmigos(ArrayList<PlaylistModel> allAmigos) {
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

    public AdapterPlaylist(ArrayList<PlaylistModel> playList){
        data = new ArrayList<>();
    }

    @Override
    public AdapterPlaylist.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_playlist, parent, false);
        AdapterPlaylist.CustomViewHolder vh = new AdapterPlaylist.CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPlaylist.CustomViewHolder holder, final int position) {
        Picasso.get()
                .load(data.get(position).getImagen())
                .into((ImageView) holder.root.findViewById(R.id.renglon_playlist_img));
        ((TextView) holder.root.findViewById(R.id.renglon_playlist_nomLis)).setText(data.get(position).getNombre_lista());
        ((TextView) holder.root.findViewById(R.id.renglon_playlist_nomUsu)).setText(data.get(position).getNombre_usuario());
        ((TextView) holder.root.findViewById(R.id.renglon_playlist_numItems)).setText(data.get(position).getNum_canciones());

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
        void onItemClick(PlaylistModel playlist);
        void onChat(PlaylistModel playlist);
    }

    private AdapterPlaylist.OnItemClickListener listener;

    public void setListener(AdapterPlaylist.OnItemClickListener listener){
        this.listener = listener;
    }
}
