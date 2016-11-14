package com.example.anambhatia.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by AnamBhatia on 14/11/16.
 */

public class intro extends Activity {

    EditText et;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extract);
        et=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button);

    }

    public void go(View v)
    {
       Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("URL",et.getText().toString());
        this.startActivity(intent);
    }
}
