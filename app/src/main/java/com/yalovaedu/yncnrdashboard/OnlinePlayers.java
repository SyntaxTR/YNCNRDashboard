package com.yalovaedu.yncnrdashboard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yalovaedu.yncnrdashboard.PlayerInfo.PlayerInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OnlinePlayers extends Fragment {

    public View view;
    public ArrayList<PlayerInfo> PlayerList = new ArrayList<PlayerInfo>();
    public TextView textView;
    private Gson gson;
    private JSONArray jsonArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_online_players2, container, false);
        textView = view.findViewById(R.id.teessst);
        getActivePlayers();
        return view;
    }

    private void getActivePlayers(){
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "http://"+ view.getContext().getString(R.string.Server) +"/Services/fillJson.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AssignPlayersToArray();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
    private void AssignPlayersToArray(){

        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "http://"+ view.getContext().getString(R.string.Server) +"/Services/players.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("players");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                PlayerInfo player = new PlayerInfo();
                                player.setUsername(jo.getString("Nick"));
                                player.setScore(jo.getInt("Score"));
                                PlayerList.add(player);
                            }
                            writeScreen();
                        } catch (JSONException e) {
                            Log.d("TAG", "onResponse: Hata");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
    public void writeScreen(){
        for(PlayerInfo playerInfo: PlayerList){
            Log.d("Player", playerInfo.getUsername() + " / " + playerInfo.getScore());
        }
    }
}