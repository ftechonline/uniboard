package com.ftechonline.uniboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by sreehari on 17/3/18.
 */

public class ConditionBuilder extends Activity implements View.OnClickListener{

    private static final String SAVE_CONDITION_BASE_URL = "http://192.168.43.47/uniboard/update_cond.php";

    private Spinner spInput;
    private Spinner spLogic;
    private AutoCompleteTextView spInput2;
    private Button btnApply;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.condition_builder);

        spInput = findViewById(R.id.spInput);
        spLogic = findViewById(R.id.spLogic);
        spInput2 = findViewById(R.id.spInput2);
        btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(this);

        String[] input1Spinner = new String[]{"--select--","LDR","PIR"};
        ArrayAdapter<String> spInput1Adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, input1Spinner);
        spInput1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInput.setAdapter(spInput1Adapter);

        String[] logicSpinner = new String[]{"--select--","ABOVE","BELOW","EQUALS"};
        ArrayAdapter<String> spLogicAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, logicSpinner);
        spLogicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLogic.setAdapter(spLogicAdapter);



    }


        @Override
        public void onClick(View view) {

            switch(view.getId()){

                case R.id.btnApply :

                    Long selectedId = spInput.getSelectedItemId();
                    String condition ="";
                    if(selectedId == 1){
                        condition += "analogValue";
                    }else{
                        condition += "digitalValue";
                    }

                    switch(spLogic.getSelectedItem().toString()){

                        case "ABOVE" : condition += ">"; break;
                        case "BELOW" : condition += "<"; break;
                        case "EQUALS" : condition += "="; break;
                        default: condition += "<>"; break;

                    }
                    condition += spInput2.getText();

                    Toast.makeText(ConditionBuilder.this, condition, Toast.LENGTH_SHORT).show();

                    String requestParams = "?port=0&cond="+condition;

                    StringRequest request = new StringRequest(Request.Method.GET, SAVE_CONDITION_BASE_URL+requestParams, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ConditionBuilder.this, "Woohoo! Criteria Successfully Updated", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ConditionBuilder.this, "Oh No! A Bad Day Again !", Toast.LENGTH_SHORT).show();
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(request);
            }


        }


}
