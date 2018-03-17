package com.ftechonline.uniboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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

        String[] inputSpinner = new String[]{"--select--","IN01 - LDR ","IN02 - PIR"};
        ArrayAdapter<String> spInputAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, inputSpinner);
        spInputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInput.setAdapter(spInputAdapter);

        String[] logicSpinner = new String[]{"--select--","<","<=","=",">=",">","<>"};
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
                    if(selectedId.equals(1)){
                        condition += "analogValue";
                    }else{
                        condition += "digitalValue";
                    }

                    condition += spLogic.getSelectedItem().toString();
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

            }

        }


}
