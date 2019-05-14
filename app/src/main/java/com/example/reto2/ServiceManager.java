package com.example.reto2;

import com.google.gson.Gson;

import java.io.IOException;

public class ServiceManager {

    public static String PLAYLIST_GET="https://api.deezer.com/user/2529/playlists";
    public static String SEARCH_PLAYLIST_GET="https://api.deezer.com/search/playlist?q=";
    public static String PLAYLIST_ID = "https://api.deezer.com/playlist/";
    public static String CANCIONES_GET="https://api.deezer.com/track/";

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

    public static class SearchPlaylistGET{
        OnResponseListener listener;
        public SearchPlaylistGET(OnResponseListener listener, String busqueda){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(SEARCH_PLAYLIST_GET+busqueda);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }



    }

    public static class PlaylistId{
        OnResponseListener listener;
        public PlaylistId(OnResponseListener listener, int id){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(PLAYLIST_ID+id);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }



    }

    public static class CancionGETId{
        OnResponseListener listener;
        public CancionGETId(OnResponseListener listener, int id){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(CANCIONES_GET+id);
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
