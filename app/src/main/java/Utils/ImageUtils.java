package Utils;

import android.content.Context;
import android.os.Environment;

import com.example.administrator.newproject.R;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;

/**
 * Created by Administrator on 2015/4/21 0021.
 */
public class ImageUtils {

    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils(Context context){


        if (bitmapUtils!=null){


            return bitmapUtils;


        }

        File file= Environment.getExternalStorageDirectory();

        File f=new File(file,"zhaofangzi");
        f.mkdir();

        BitmapUtils utils=new BitmapUtils(context,f.getPath(),0.4f,20*1024*1024);

        utils.configDefaultLoadFailedImage(R.drawable.no_msg_iclunch_new);



        //缓存
        utils.configMemoryCacheEnabled(true);
        //磁盘缓存
        utils.configDiskCacheEnabled(true);
        //线程池
        utils.configThreadPoolSize(4);

        return utils;

    }

}
