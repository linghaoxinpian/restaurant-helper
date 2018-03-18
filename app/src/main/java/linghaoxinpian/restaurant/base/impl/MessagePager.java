package linghaoxinpian.restaurant.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import linghaoxinpian.restaurant.base.BasePager;

public class MessagePager extends BasePager {

	public MessagePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {

		// 要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("消息");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("消息");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
	}

}
