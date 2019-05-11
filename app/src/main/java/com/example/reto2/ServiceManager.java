package com.example.reto2;

import com.google.gson.Gson;

import java.io.IOException;

public class ServiceManager {

    public static String PLAYLIST_GET="https://api.deezer.com/user/2529/playlists";
    public static String CANCIONES_GET="https://api.deezer.com/user/2529/playlists";

    public static class PlaylistGET{
        OnResponseListener listener;
        public PlaylistGET(OnResponseListener listener){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(PLAYLIST_GET);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }



    }

    public static class CancionesGET{
        OnResponseListener listener;
        public CancionesGET(OnResponseListener listener){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(CANCIONES_GET);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }
    }


}
