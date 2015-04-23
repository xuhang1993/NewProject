package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newproject.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import Bean.OldHouseDetils;
import Utils.ImageUtils;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class OldHouseDetilsAdapter extends BaseAdapter{

    private ArrayList<OldHouseDetils>data;
    private Context context;
    private LayoutInflater inflater;

    public OldHouseDetilsAdapter(ArrayList<OldHouseDetils> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (data!=null){

            return data.size();
        }
        return 0;
    }

    @Override
    public OldHouseDetils getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.activity_house_detil,null);

        TextView title2= (TextView) convertView.findViewById(R.id.title2);
        title2.setText(data.get(position).getTitle());
        TextView price4= (TextView) convertView.findViewById(R.id.price4);
        price4.setText(data.get(position).getTotalPrice());
        TextView junjia= (TextView) convertView.findViewById(R.id.junjia);
        junjia.setText(data.get(position).getAverPrice());
        TextView type= (TextView) convertView.findViewById(R.id.type);
        type.setText(data.get(position).getHouseType());
        TextView mianji= (TextView) convertView.findViewById(R.id.mianji);
        mianji.setText(data.get(position).getArea());
        TextView fitment= (TextView) convertView.findViewById(R.id.fitment);
        fitment.setText(data.get(position).getFitment());
        TextView orientation= (TextView) convertView.findViewById(R.id.orientation);
        orientation.setText(data.get(position).getOrientation());
        TextView floor= (TextView) convertView.findViewById(R.id.floor);
        floor.setText(data.get(position).getFloor());
        TextView houseInfo= (TextView) convertView.findViewById(R.id.houseInfo);
        houseInfo.setText(data.get(position).getHouseInfo());
        TextView map= (TextView) convertView.findViewById(R.id.map);
        map.setText(data.get(position).getAddress());

        ImageView imageView= (ImageView) convertView.findViewById(R.id.mapImage);

        BitmapUtils utils=ImageUtils.getBitmapUtils(context);

        utils.display(imageView,data.get(position).getLocPath());



        return convertView;
    }
}
