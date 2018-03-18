
package linghaoxinpian.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import linghaoxinpian.restaurant.utils.PrefUtils;

/**
 *闪屏启动页面
 */
public class SplashActivity extends AppCompatActivity {
    private RelativeLayout rlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlRoot=(RelativeLayout)findViewById(R.id.rl_root);
        RotateAnimation animRotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);  //保持旋转之后的状态

        //缩放动画
        ScaleAnimation animScale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);

        //渐变动画
        AlphaAnimation animAlpha=new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet set=new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);

        //启动动画
        rlRoot.startAnimation(set);

        //-------新手引导跳转-------
        //监听动画启动与完成
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束
                boolean isFirstEnter= PrefUtils.getBoolean(SplashActivity.this,"is_first_enter",true);
                Intent intent;
                if(isFirstEnter){
                    //跳新手引导
                     intent=new Intent(getApplicationContext(),GuideActivity.class);
                }else{
                    //跳主页面
                    intent=new Intent(getApplicationContext(),MainActivity.class);
                }
                startActivity(intent);
                //结束当前页面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}















