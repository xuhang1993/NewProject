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

import Bean.Jingjiren;
import Bean.OldHouseData;
import Utils.ImageUtils;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class JingjirenAdapter extends BaseAdapter{

    private ArrayList<Jingjiren> datas;
    private Context context;
    private LayoutInflater inflater;

    public JingjirenAdapter(ArrayList<Jingjiren> datas, Context context) {
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
    public Jingjiren getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.activity_house_detil,null);

        TextView name= (TextView) convertView.findViewById(R.id.jingjirenName);
        name.setText(datas.get(position).getAgentName());
        TextView num= (TextView) convertView.findViewById(R.id.jingjirenNum);
        num.setText(datas.get(position).getMobile());
        TextView jingjirenGosi= (TextView) convertView.findViewById(R.id.jingjirenGosi);
        jingjirenGosi.setText(datas.get(position).getAgencyName());
        ImageView imageView= (ImageView) convertView.findViewById(R.id.jingjiren);


        BitmapUtils utils=ImageUtils.getBitmapUtils(context);
        utils.display(imageView,datas.get(position).getAvatar());

        return convertView;
    }
}
