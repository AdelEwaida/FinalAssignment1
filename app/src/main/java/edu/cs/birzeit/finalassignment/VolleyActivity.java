package edu.cs.birzeit.finalassignment;

import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.cs.birzeit.finalassignment.model.CD;


public class VolleyActivity extends AppCompatActivity {
    private ListView textView;
    private RequestQueue mQueue;
    private String url="https://my-json-server.typicode.com/AdelEwaida/demo/db";
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        textView=findViewById(R.id.VolleyTextView);
        image = (ImageView) findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.fifa);
        RequistVolley();
    }


    private void RequistVolley() {
        Image image ;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        CD[] cds ;
                        try {
                            JSONArray jsonArray = response.getJSONArray("CD's");
                            cds = new CD[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject CD = jsonArray.getJSONObject(i);
                                String id = CD.getString("id");
                                String title = CD.getString("title");
                                String price = CD.getString("price");
                                String genre = CD.getString("genre");
                                CD cd = new CD(id,title,price,genre);

                                cds[i]=cd;
                            }
                            ArrayAdapter<CD> itemsAdapter =
                                    new ArrayAdapter<CD>(VolleyActivity.this, android.R.layout.simple_list_item_1,
                                            cds);
                            textView.setAdapter(itemsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}