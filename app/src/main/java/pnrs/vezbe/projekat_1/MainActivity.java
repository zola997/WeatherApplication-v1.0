package pnrs.vezbe.projekat_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonMainAdd;
    Button buttonMainRemove;
    EditText city;
    ListView city_list;
    RowElementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMainAdd = (Button) findViewById(R.id.buttonAdd);
        buttonMainRemove = (Button) findViewById(R.id.buttonRemove);
        city = (EditText) findViewById(R.id.city_name);
        buttonMainAdd.setOnClickListener(this);
        buttonMainRemove.setOnClickListener(this);

        adapter = new RowElementAdapter(this);
        adapter.addCity(new RowElement(getString(R.string.Sabac)));
        adapter.addCity(new RowElement(getString(R.string.NoviSad)));
        adapter.addCity(new RowElement(getString(R.string.Beograd)));

        city_list = (ListView) findViewById(R.id.listview);
        city_list.setAdapter(adapter);
        city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                if (TextUtils.isEmpty(city.getText().toString()) || city.getText().toString().startsWith(" ")) {
                    city.setText(R.string.empty);
                    city.setError("Unesite ime grada.");
                    return;
                } else {
                    adapter.addCity(new RowElement(city.getText().toString()));
                    city.setText("");
                }
                break;
            case R.id.buttonRemove:
                if (TextUtils.isEmpty(city.getText().toString()) || city.getText().toString().startsWith(" ")) {
                    city.setText(R.string.empty);
                    city.setError("Unesite ime grada.");
                    return;
                } else {
                     String pv=adapter.removeCity(city.getText().toString());
                     city.setError(pv);
                     city.setText("");
                }
                break;
        }
    }
}











