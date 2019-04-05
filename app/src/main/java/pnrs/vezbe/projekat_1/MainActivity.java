package pnrs.vezbe.projekat_1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonMainAdd;
    Button buttonDelete;
    EditText city;
    ListView city_list;
    RowElementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMainAdd = (Button) findViewById(R.id.buttonAdd);
        city = (EditText) findViewById(R.id.city_name);
        buttonMainAdd.setOnClickListener(this);

        adapter = new RowElementAdapter(this);
        adapter.addCity(new RowElement(getString(R.string.Sabac)));
        adapter.addCity(new RowElement(getString(R.string.NoviSad)));
        adapter.addCity(new RowElement(getString(R.string.Beograd)));

        city_list = (ListView) findViewById(R.id.listview);
        city_list.setAdapter(adapter);

        city_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.removeCity(i);
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                if (TextUtils.isEmpty(city.getText().toString()) || city.getText().toString().startsWith(" ")) {
                    city.setText(R.string.empty);
                    city.setError("Greška! Unesite ispravno ime grada.");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            city.setText("");
                            city.setError(null);
                        }
                    }, 2000);

                    return;
                } else {
                    if (!adapter.addCity(new RowElement(city.getText().toString()))) {
                        city.setError("Grad već postoji u listi!");
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                city.setText("");
                                city.setError(null);
                            }
                        }, 2000);

                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                city.setText("");
                            }
                        }, 1000);
                    }
                }
                break;
        }
    }
}











