package Adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newproject.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import Bean.TopData;
import Utils.ImageUtils;

/**
 * Created by Administrator on 2015/4/21 0021.
 */
public class TopDataAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<TopData>list;
    private LayoutInflater inflater;
    public ArrayList<View> view;

    public TopDataAdapter(Context context, ArrayList<TopData> list) {
        this.context = context;
        this.list = list;

        init();
    }

    public void init(){

        view=new ArrayList<View>();
        view.clear();

        for (int i = 0; i < list.size(); i++) {

            View v = LayoutInflater.from(context).inflate(R.layout.top_data_item, null);

            ImageView icon = (ImageView) v.findViewById(R.id.top_imageView);

            String state= Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)){

                BitmapUtils utils=ImageUtils.getBitmapUtils(context);

                utils.display(icon,list.get(i).getsImageUrl());

            }

            view.add(v);

        }

    }

    @Override
    public void notifyDataSetChanged() {

        init();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return view.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View v=view.get(position);
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(view.get(position));


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
