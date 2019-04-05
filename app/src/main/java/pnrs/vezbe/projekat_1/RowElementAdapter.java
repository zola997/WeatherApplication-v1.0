package pnrs.vezbe.projekat_1;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class RowElementAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<RowElement> mCities;
    private RadioButton button = null;

    public RowElementAdapter(Context context){
        mContext = context;
        mCities = new ArrayList<RowElement>();
        }

    public boolean addCity(RowElement element){
        Boolean b =isCityInstanced(element.city_name);
        if (!b) {
            mCities.add(element);
            notifyDataSetChanged();
            return true;
        }
        else return false;
    }

    public void removeCity(int element){

            mCities.remove(element);
            notifyDataSetChanged();

    }
    public boolean isCityInstanced(String city){
        for(RowElement element : mCities)
        {
              if(element.city_name.toUpperCase().equals(city.toUpperCase())){
                  return true;
            }
        }
        return false;

    }
    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public Object getItem(int i) {
        Object rv = null;
        try{
            rv=mCities.get(i);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return rv;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.element_row,null);
            ViewHolder holder = new ViewHolder();
            holder.button=(RadioButton) view.findViewById(R.id.radioButton);
            holder.deleteButton=(Button) view.findViewById(R.id.ButtonDelete);
            holder.city=(TextView) view.findViewById(R.id.textView);
            view.setTag(holder);

        }
        RowElement element = (RowElement) getItem(i);

        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.city.setText(element.city_name);

        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                removeCity(i);
                view.clearFocus();

            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key="grad";
                button = (RadioButton) v;
                button.setChecked(false);
                Intent intent = new Intent(mContext,DetailsActivity.class);
                intent.putExtra(key, holder.city.getText().toString());
                mContext.startActivity(intent);
            }
        });

        return view;
    }
    private class ViewHolder{
        public RadioButton button=null;
        public TextView city=null;
        public Button  deleteButton=null;
    }
}
