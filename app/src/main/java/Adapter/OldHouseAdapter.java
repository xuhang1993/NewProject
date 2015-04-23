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

import Bean.OldHouseData;
import Utils.ImageUtils;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class OldHouseAdapter extends BaseAdapter {

    private ArrayList<OldHouseData>datas;
    private Context context;
    private LayoutInflater inflater;

    public OldHouseAdapter(ArrayList<OldHouseData> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
    public OldHouseData getItem(int position) {
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
            convertView=inflater.inflate(R.layout.oldhouse_list_item,null);

            holer.buildType= (TextView) convertView.findViewById(R.id.buildType);
            holer.areaName= (TextView) convertView.findViewById(R.id.areaName);
            holer.buildName= (TextView) convertView.findViewById(R.id.buildName);
            holer.price3= (TextView) convertView.findViewById(R.id.price3);
            holer.title= (TextView) convertView.findViewById(R.id.title);

            holer.imageView= (ImageView) convertView.findViewById(R.id.emptyImage);

            convertView.setTag(holer);

        }else {

                holer= (ViewHoler) convertView.getTag();

        }

        holer.buildType.setText(datas.get(position).getName());
        holer.areaName.setText(datas.get(position).getAreaName());
        holer.buildName.setText(datas.get(position).getBuildName());
        holer.price3.setText(datas.get(position).getPrice());
        holer.title.setText(datas.get(position).getTitle());


        BitmapUtils utils=ImageUtils.getBitmapUtils(context);

        utils.display(holer.imageView,datas.get(position).getPath());

        return convertView;
    }


    class ViewHoler {

        ImageView imageView;
        TextView buildType;
        TextView areaName;
        TextView buildName;
        TextView price3;

        TextView title;


    }
}
