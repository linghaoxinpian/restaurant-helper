package linghaoxinpian.restaurant.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import linghaoxinpian.restaurant.base.BaseMenuDetailPager;

public class NewsMenuDetailPager extends BaseMenuDetailPager {

	public NewsMenuDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("菜单详情页-新闻");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		return view;
	}

}
