package com.yalovaedu.yncnrdashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerStats extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private TextView hostnameText;
    private TextView activecount;
    private TextView registeredcount;
    private TextView bannedcount;

    ArrayList<HashMap<String, String>> data;
    private JSONArray jsonArray;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_server_stats, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        hostnameText = view.findViewById(R.id.hostnameText);
        activecount = view.findViewById(R.id.activecount);
        registeredcount = view.findViewById(R.id.registeredcount);
        bannedcount = view.findViewById(R.id.bannedcount);
        FillJSON();
        return view;
    }
    private void FillJSON(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "http://"+ view.getContext().getString(R.string.Server) +"/Services/Stats.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        getStats();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("JSON Error", "JSON Create Error");
                error.printStackTrace();
                FillJSON();
            }
        });
        queue.add(stringRequest);
    }
    private void getStats(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "http://"+ view.getContext().getString(R.string.Server) +"/Services/JSONFiles/Stats.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("stats");
                            JSONObject jo = jsonArray.getJSONObject(0);
                            hostnameText.setText(jo.getString("hostname"));
                            activecount.setText(jo.getString("players") + " / " + jo.getString("maxplayers"));
                            registeredcount.setText(jo.getString("playerCount"));
                            bannedcount.setText(jo.getString("bannedCount"));
                            progressBar.setVisibility(View.INVISIBLE);
                        } catch (JSONException e) {
                            Log.d("JSON Erro", "onResponse: Hata");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("JSON Error", "That didn't work!");
                getStats();
            }
        });
        queue.add(stringRequest);
    }
}