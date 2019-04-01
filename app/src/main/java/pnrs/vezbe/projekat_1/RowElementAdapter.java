package pnrs.vezbe.projekat_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.ArrayList;

public class RowElementAdapter extends BaseAdapter {



    private Context mContext;
    private ArrayList<RowElement> mCities;
    private RadioButton button = null;

    public RowElementAdapter(Context context){
        mContext = context;
        mCities = new ArrayList<RowElement>();
        }

    public void addCity(RowElement element){
        mCities.add(element);
        notifyDataSetChanged();
    }

    public String removeCity(String grad){

        if(mCities.contains(grad)){
            mCities.remove(grad);
            notifyDataSetChanged();
            return "Uspešno izbrisan grad.";
        }

        else {
            notifyDataSetChanged();
            return "Grad nije pronađen";
        }

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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.element_row,null);
            ViewHolder holder = new ViewHolder();
            holder.button=(RadioButton) view.findViewById(R.id.radioButton);
            holder.city=(TextView) view.findViewById(R.id.textView);
            view.setTag(holder);

        }
        RowElement element = (RowElement) getItem(i);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.city.setText(element.city_name);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, DetailsActivity.class);
                Bundle b = new Bundle();
                b.putString("grad", holder.city.getText().toString());
                myIntent.putExtras(b);
                mContext.startActivity(myIntent);

                button = (RadioButton)v;
                button.setChecked(false);

            }
        });

        return view;
    }


    private class ViewHolder{
        public RadioButton button=null;
        public TextView city=null;

    }
}
