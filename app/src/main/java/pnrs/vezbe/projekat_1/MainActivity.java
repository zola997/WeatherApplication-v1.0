package pnrs.vezbe.projekat_1;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static pnrs.vezbe.projekat_1.R.id.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    EditText editText;
    TextView textView;
    String key="grad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView2);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().matches("") || editText.getText().toString().matches(" ")){
                     textView.setText("Грешка! Унесите локацију");
                     return;

            }else {
                    Intent intent =new Intent(MainActivity.this,DetailsActivity.class);
                    intent.putExtra(key, editText.getText().toString());
                    startActivity(intent);


                }

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}










