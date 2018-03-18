package linghaoxinpian.restaurant.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import linghaoxinpian.restaurant.R;
import linghaoxinpian.restaurant.base.BasePager;

/**
 * 首页
 */
public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {

		// 要给帧布局填充布局对象
//		TextView view = new TextView(mActivity);
//		view.setText("首页");
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);
//		flContent.addView(view);

		//添加图片
		ImageView img=new ImageView(mActivity);
		img.setImageResource(R.drawable.guide_1);
		flContent.addView(img);



		// 修改页面标题
		tvTitle.setText("校园餐饮");

		// 隐藏菜单按钮
		btnMenu.setVisibility(View.GONE);
	}
}
