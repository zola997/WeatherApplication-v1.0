package pnrs.vezbe.projekat_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton button;


    Button b1;
    EditText editText;
    TextView textView;
    String key="grad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = (Context) this;
        ArrayList<String> cities;
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.element_row,null);
        ViewHolder viewHolder;




        // editText=findViewById(R.id.editText);
       // textView=findViewById(R.id.textView2);
       // b1 = findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText().toString()) || editText.getText().toString().startsWith(" ")) {
					editText.setText(R.string.empty);
                    editText.setError("Унесите име града.");
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










