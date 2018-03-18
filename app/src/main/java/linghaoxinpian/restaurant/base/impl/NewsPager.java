package linghaoxinpian.restaurant.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import linghaoxinpian.restaurant.MainActivity;
import linghaoxinpian.restaurant.base.BaseMenuDetailPager;
import linghaoxinpian.restaurant.base.BasePager;
import linghaoxinpian.restaurant.base.impl.menu.InteractMenuDetailPager;
import linghaoxinpian.restaurant.base.impl.menu.NewsMenuDetailPager;
import linghaoxinpian.restaurant.base.impl.menu.PhotosMenuDetailPager;
import linghaoxinpian.restaurant.base.impl.menu.TopicMenuDetailPager;
import linghaoxinpian.restaurant.domain.NewsMenu;
import linghaoxinpian.restaurant.fragment.LeftMenuFragment;

public class NewsPager extends BasePager {

	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// 菜单详情页集合
	private NewsMenu mNewsData;// 分类信息网络数据

	public NewsPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {

		// 要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("美食日报");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("美食");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);

		//请求服务器数据（Xutils）
		getDataFromServer();
	}

	/**
	 从服务器获取数据(json)
	 */
	private void getDataFromServer(){
		//.....
		processData("");
	}

	/**
	 * 解析服务器传来的json数据
	 */
	protected void processData(String json){
		json="{\"retcode\":200,\"data\":[{\"id\":10000,\"title\":\"新闻\",\"type\":1,\"children\":[{\"id\":10007,\"title\":\"北京\",\"type\":1,\"url\":\"/10007/list_1.json\"},{\"id\":10006,\"title\":\"中国\",\"type\":1,\"url\":\"/10006/list_1.json\"},{\"id\":10008,\"title\":\"国际\",\"type\":1,\"url\":\"/10008/list_1.json\"},{\"id\":10010,\"title\":\"体育\",\"type\":1,\"url\":\"/10010/list_1.json\"},{\"id\":10091,\"title\":\"生活\",\"type\":1,\"url\":\"/10091/list_1.json\"},{\"id\":10012,\"title\":\"旅游\",\"type\":1,\"url\":\"/10012/list_1.json\"},{\"id\":10095,\"title\":\"科技\",\"type\":1,\"url\":\"/10095/list_1.json\"},{\"id\":10009,\"title\":\"军事\",\"type\":1,\"url\":\"/10009/list_1.json\"},{\"id\":10093,\"title\":\"时尚\",\"type\":1,\"url\":\"/10093/list_1.json\"},{\"id\":10011,\"title\":\"财经\",\"type\":1,\"url\":\"/10011/list_1.json\"},{\"id\":10094,\"title\":\"育儿\",\"type\":1,\"url\":\"/10094/list_1.json\"},{\"id\":10105,\"title\":\"汽车\",\"type\":1,\"url\":\"/10105/list_1.json\"}]},{\"id\":10002,\"title\":\"专题\",\"type\":10,\"url\":\"/10006/list_1.json\",\"url1\":\"/10007/list1_1.json\"},{\"id\":10003,\"title\":\"组图\",\"type\":2,\"url\":\"/10008/list_1.json\"},{\"id\":10004,\"title\":\"互动\",\"type\":3,\"excurl\":\"\",\"dayurl\":\"\",\"weekurl\":\"\"}],\"extend\":[10007,10006,10008,10014,10012,10091,10009,10010,10095]}";
		// Gson: Google Json
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);

		//获取侧边栏的对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();

		// 给侧边栏设置数据
		fragment.setMenuData(mNewsData.data);

		// 初始化侧边栏的4个菜单详情页
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

		// 将新闻菜单详情页设置为默认页面
		setCurrentDetailPager(0);
	}

	// 设置菜单详情页
	public void setCurrentDetailPager(int position) {
		// 重新给frameLayout添加内容
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);// 获取当前应该显示的页面
		View view = pager.mRootView;// 当前页面的布局

		// 清除之前旧的布局
		flContent.removeAllViews();

		flContent.addView(view);// 给帧布局添加布局

		// 初始化页面数据
		pager.initData();

		// 更新标题
		tvTitle.setText(mNewsData.data.get(position).title);
	}
}
