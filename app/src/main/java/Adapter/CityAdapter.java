package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Bean.CityName;

/**
 * Created by Administrator on 2015/4/20 0020.
 */
public class CityAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<CityName>datas;

    private LayoutInflater inflater;
    public CityAdapter(Context context, ArrayList<CityName> datas) {
        this.context = context;
        this.datas = datas;

        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (datas!=null){

            return datas.size();

        }
        return 0;
    }

    @Override
    public CityName getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHoler holer=null;
        if (convertView==null){

            holer=new ViewHoler();

            convertView=inflater.inflate(android.R.layout.simple_list_item_1,null);

            holer.textView= (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holer);


        }else{

            holer= (ViewHoler) convertView.getTag();



        }


        holer.textView.setText(datas.get(position).getsName());


        return convertView;
    }

    class ViewHoler{

        TextView textView;


    }
}
