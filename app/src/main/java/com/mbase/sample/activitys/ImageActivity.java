package com.mbase.sample.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbase.monch.utils.ListUtils;
import com.mbase.monch.utils.StringUtils;
import com.mbase.monch.view.MImageView;
import com.mbase.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monch on 15/12/1.
 */
public class ImageActivity extends Activity {
    private static final List<String> URL_LIST;

    static {
        URL_LIST = new ArrayList<>();
        URL_LIST.add("http://img4.duitang.com/uploads/item/201510/07/20151007124603_4Tj2K.jpeg");
        URL_LIST.add("http://img.name2012.com/uploads/allimg/2015-06/30-023131_451.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg%21200x200.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201408/06/12/02/53e1a8ba2d164654.jpg%21200x200.jpg");
        URL_LIST.add("http://img5.imgtn.bdimg.com/it/u=4168775445,1420260708&fm=21&gp=0.jpg");
        URL_LIST.add("http://img2.imgtn.bdimg.com/it/u=2442164737,612382051&fm=21&gp=0.jpg");
        URL_LIST.add("http://www.cssxn.com/fzl/tupian/201501/2015011412373426.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg%21200x200.jpg");
        URL_LIST.add("http://img5.duitang.com/uploads/item/201509/26/20150926122734_KaTCA.jpeg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201405/20/21/18/537b5619604f5958.jpg%21200x200.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201303/19/22/06/514870d0a039e484.jpg%21200x200.jpg");
        URL_LIST.add("http://img.woyaogexing.com/2014/08/15/0e0875831a1790e3%21200x200.jpg");
        URL_LIST.add("http://img1.imgtn.bdimg.com/it/u=2960599358,123009059&fm=21&gp=0.jpg");
        URL_LIST.add("http://img5.imgtn.bdimg.com/it/u=159694920,2166605543&fm=21&gp=0.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201403/30/09/33/533774802e7c6272.jpg%21200x200.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201405/01/01/40/53613582688bd260.jpg%21200x200.jpg");
        URL_LIST.add("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=46112a499a2f07085f502204dc1494af/d53f8794a4c27d1e13dc03c21fd5ad6eddc43839.jpg");
        URL_LIST.add("http://v1.qzone.cc/avatar/201306/21/10/59/51c3c1a39a1a8074.jpg%21200x200.jpg");
        URL_LIST.add("http://image.tianjimedia.com/uploadImages/upload/20140902/upload/201409/ydinwe51s3ljpg.jpg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);String titleText = getIntent().getStringExtra("titleText");
        if (StringUtils.isNotEmpty(titleText)) {
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(titleText);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new ImageAdapter(this, URL_LIST));
    }

    static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private LayoutInflater inflater;
        private List<String> data;

        ImageAdapter(Context context, List<String> data) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data;
        }

        @Override
        public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
            String item = ListUtils.getElement(data, position);
            holder.imageView.setUrl(item);
        }

        @Override
        public int getItemCount() {
            return ListUtils.getCount(data);
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            MImageView imageView;
            public ImageViewHolder(View itemView) {
                super(itemView);
                imageView = (MImageView) itemView.findViewById(R.id.image);
            }
        }

    }

}
