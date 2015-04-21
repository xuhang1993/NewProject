package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newproject.R;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by aaa on 15-4-21.
 */
public class WoFragListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String,Object>>datas;

    public WoFragListAdapter(ArrayList<HashMap<String, Object>> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WoFragListHolder woFragListHolder=null;
        if(convertView==null){
            woFragListHolder=new WoFragListHolder();
            convertView=inflater.inflate(R.layout.wolistadapter,null);
            woFragListHolder.image1= (ImageView) convertView.findViewById(R.id.wofrag_tubiao);
            woFragListHolder.text= (TextView) convertView.findViewById(R.id.wofrag_text);
            convertView.setTag(woFragListHolder);

        }else{
            woFragListHolder= (WoFragListHolder) convertView.getTag();
        }
        HashMap<String, Object> map = datas.get(position);
        String name = (String) map.get("name");
        int  image= (int) map.get("image");
        woFragListHolder.image1.setImageResource(image);
        woFragListHolder.text.setText(name);

        return convertView;
    }

    class WoFragListHolder{
        ImageView image1;
        TextView  text;
    }

}
