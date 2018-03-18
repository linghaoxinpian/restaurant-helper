package linghaoxinpian.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import linghaoxinpian.restaurant.utils.PrefUtils;

/**
 * Created by linghaoxinpian on 2018/2/24.
 */

public class GuideActivity extends Activity {
    private ViewPager mViewPager;
    private ArrayList<ImageView> mImageView;
    private Button btnStart;    //开始体验按钮
    //引导页图片id
    private int[] mImageIds=new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去掉标题栏,注意必须在setContentView()之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        //页面控件
        mViewPager=(ViewPager)findViewById(R.id.vp_guide);
        btnStart=(Button)findViewById(R.id.btn_start);

        initData();
        mViewPager.setAdapter(new GuideAdapter());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //某个页面被选中
                if(position==mImageView.size()-1){  //最后一个页面显示“开始体验”按钮
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新sp
                PrefUtils.setBoolean(getApplication(),"is_first_enter",false);
                //跳转到主页面
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }

    //初始化数据
    private void initData(){
        mImageView=new ArrayList<ImageView>();
        for(int i=0;i<mImageIds.length;i++){
            ImageView view=new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageView.add(view);
        }
    }

    class GuideAdapter extends PagerAdapter{
        //item的个数
        @Override
        public int getCount(){
            return mImageView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container,int position){
            ImageView view=mImageView.get(position);
            container.addView(view);
            return view;
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container,int position,Object object){
            container.removeView((View)object);
        }
    }
}












