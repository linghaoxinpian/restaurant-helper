package linghaoxinpian.restaurant.fragment;

import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import linghaoxinpian.restaurant.MainActivity;
import linghaoxinpian.restaurant.R;
import linghaoxinpian.restaurant.base.BasePager;
import linghaoxinpian.restaurant.base.impl.MessagePager;
import linghaoxinpian.restaurant.base.impl.HomePager;
import linghaoxinpian.restaurant.base.impl.SettingPager;
import linghaoxinpian.restaurant.base.impl.NewsPager;
import linghaoxinpian.restaurant.view.NoScrollViewPager;

/**
 * Created by linghaoxinpian on 2018/3/5.
 */

public class ContentFragment extends BaseFragment {

    private NoScrollViewPager mViewPager;
    private RadioGroup rgGroup;
    private ArrayList<BasePager> mPages;    //底部标签页的集合

    @Override
    public View initView(){
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        mPages=new ArrayList<>();

        //添加底部的标签
        mPages.add(new HomePager(mActivity));
        mPages.add(new NewsPager(mActivity));
        mPages.add(new MessagePager(mActivity));
        mPages.add(new SettingPager(mActivity));
        //添加适配器
        mViewPager.setAdapter(new ContentAdapter());
        //监听事件
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        //首页
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_news:
                        // 美食日报
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        //消息
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        // 设置
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        // 设置
                        mViewPager.setCurrentItem(4, false);
                        break;

                    default:
                        break;
                }
            }
        });
        rgGroup.callOnClick();

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager basePager=mPages.get(position);
                basePager.initData();
                if (position == 0 || position == mPages.size() - 1) {
                    // 首页和设置页要禁用侧边栏
                    setSlidingMenuEnable(false);
                } else {
                    // 其他页面开启侧边栏
                    setSlidingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void setSlidingMenuEnable(boolean enable){
        MainActivity mainUI=(MainActivity)mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        if(enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
    class ContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPages.get(position);
            View view = pager.mRootView;// 获取当前页面对象的布局

            // pager.initData();// 初始化数据, viewpager会默认加载下一个页面,
            // 为了节省流量和性能,不要在此处调用初始化数据的方法

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    /**
     *获取美食中心页面
     */
    public NewsPager getNewsPager() {
        NewsPager pager = (NewsPager) mPages.get(1);
        return pager;
    }
}
