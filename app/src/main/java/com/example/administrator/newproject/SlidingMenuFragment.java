package com.example.administrator.newproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SlidingMenuFragment extends Fragment {

    String name[]={"用户反馈","评价打分","检测新版本","免责声明","关于我们","退出"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding_menu, container, false);
        ListView  slidList= (ListView) view.findViewById(R.id.slid_listview);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, name);
        slidList.setAdapter(arrayAdapter);
        slidList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:  break;
                    case 1:






                        break;
                    case 2:
                        PopupWindow popupWindow = new PopupWindow(getActivity());
                        DisplayMetrics outMetrics=new DisplayMetrics();
                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
                        int width=outMetrics.widthPixels;
                        int height = outMetrics.heightPixels;
                        popupWindow.setWidth(width/2);
                        popupWindow.setHeight(height/14);
                        popupWindow.setFocusable(true);
                        TextView textView = new TextView(getActivity());
                        textView.setMaxWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                        textView.setMaxHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                        textView.setText("版本检测中...");
                        popupWindow.setContentView(textView);
                        popupWindow.showAtLocation(view,Gravity.CENTER,-100,100);
                        try {
                            Thread.sleep(10000);
                            Toast.makeText(getActivity(),"最新版本",Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),MianZeShengMingActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        Intent i = new Intent();
                        i.setClass(getActivity(),GuanYuWoMenActivity.class);
                        startActivity(i);
                        break;
                    case 5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("退出程序")
                                .setPositiveButton("退出",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                    }
                                }).setNegativeButton("取消",null ).show();

                        break;

                }
            }
        });




        return view;
    }


}
