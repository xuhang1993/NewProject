package progresdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newproject.R;

/**
 * Created by Administrator on 2015/4/23 0023.
 */
public class CustormProgressDialog extends ProgressDialog{

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int count=0;
    private String oldLodingTip;
    private  int mResid;

    public CustormProgressDialog(Context context,String content,int id) {
        super(context);

        this.mContext=context;
        this.mLoadingTip=content;
        this.mResid=id;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private  void initData(){

        mImageView.setBackgroundResource(mResid);

        mAnimation= (AnimationDrawable) mImageView.getBackground();

        mImageView.post(new Runnable() {
            @Override
            public void run() {

                mAnimation.start();

            }
        });

        mLoadingTv.setText(mLoadingTip);

    }

    public void setContent(String str){

        mLoadingTv.setText(str);

    }


    private void  initView(){

        setContentView(R.layout.progress_dialog);
        mLoadingTv= (TextView) findViewById(R.id.loadingTv);
        mImageView= (ImageView) findViewById(R.id.loadingIv);

    }

}
