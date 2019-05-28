package pnrs.vezbe.projekat_1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    Button buttonMainAdd;
    EditText city;
    ListView city_list;
    RowElementAdapter adapter;
    public static String CHANNEL_ID;
    private final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String error;
        if (getIntent().hasExtra("error")) {
            error = getIntent().getStringExtra("error");
            Toast toast = Toast.makeText(this, error, Toast.LENGTH_LONG);
            toast.show();
        }

        super.onCreate(savedInstanceState);
        try {
            createNotificationChannel();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
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
                    }, 1500);

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
                        }, 1500);

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

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Weather", importance);
            channel.setDescription("temperature update");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}











