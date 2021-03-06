package com.yalovaedu.yncnrdashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

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


public class Top15Scores extends Fragment {

    private View view;
    private ListView listView;
    private ProgressBar progressBar;
    ArrayList<HashMap<String, String>> data;
    private JSONArray jsonArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top15_scores, container, false);
        listView = view.findViewById(R.id.listView);
        progressBar = view.findViewById(R.id.progressBar);
        FillJSON();
        return view;
    }
    private void FillJSON(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "https://"+ view.getContext().getString(R.string.Server) +"/Services/Top15Players.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        GetTop15Scores();
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
    private void GetTop15Scores(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String URL = "https://"+ view.getContext().getString(R.string.Server) +"/Services/JSONFiles/TOP15Players.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            data = new ArrayList<HashMap<String, String>>();
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("players");
                            Log.i("JSON Parser", String.valueOf(jsonArray));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                HashMap<String,String> datum = new HashMap<String, String>();
                                datum.put("Nick", jo.getString("Nick"));
                                datum.put("Score", jo.getString("Score"));
                                data.add(datum);
                            }
                            progressBar.setVisibility(View.INVISIBLE);
                            SimpleAdapter adapter = new SimpleAdapter(view.getContext(), data, android.R.layout.simple_list_item_2, new String[] {"Nick", "Score"}, new int[] {android.R.id.text1, android.R.id.text2});
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.d("JSON Erro", "onResponse: Hata");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("JSON Error", "That didn't work!");
                GetTop15Scores();
            }
        });
        queue.add(stringRequest);
    }
}