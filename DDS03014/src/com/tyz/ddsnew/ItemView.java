package com.tyz.ddsnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

import com.example.dds.R;

@SuppressLint("DrawAllocation")
public class ItemView extends View  implements OnLongClickListener, OnTouchListener{
	private Rect mBounds;
	private Paint mPaint;
	
//	private String time = "10:00-12:00";
//	private String yuangong = "员工";
//	private String chepai= "车牌";
	ItemData   itemData;
	int  posX ;
	int  posY;
	private boolean isLongPressed = false ;
	public ItemView(Context context, ItemData   itemData) {
		super(context);
		this.itemData = itemData;
		init(context,null, 0);
	}
	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}
	public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, 0);

	}


	private void init(Context context, AttributeSet attrs, int defStyle){
//		time = "今日 10:00-12:00";
//		yuangong = "员工";
//		chepai= "车牌";
        mPaint = new Paint();  
        mPaint.setColor(Color.GRAY);	// 先画背景
//        mBound = new Rect();  
//        mPaint.getTextBounds(time, 0, time.length(), mBound);  
	}
	

	
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//		  （1）setFrame（l,t,r,b），l,t,r,b即子视图在父视图中的具体位置，该函数用于将这些参数保存起来；
//		     （2）onLayout()，在View中这个函数什么都不会做，提供该函数主要是为viewGroup类型布局子视图用的；
	};
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);	// 先画背景
        canvas.drawRect(0, 0, 450, 300, mPaint);

        mPaint.setColor(Color.LTGRAY);
        canvas.drawRect(2, 2, DDSConst.ITEM_WIDTH-2, DDSConst.ITEM_HEIGHT-2, mPaint);
        if(itemData!=null){
        	setOnLongClickListener(this);
        	setOnTouchListener(this);
        	if(!isLongPressed){
        		mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(45); 
                mBounds = new Rect();
                mPaint.getTextBounds(itemData.time, 0, itemData.time.length(), mBounds);  
                float height1 = mBounds.height();  
                canvas.drawText(itemData.time, 10,  height1+5, mPaint);	// 画出时间
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                int height2 = bitmap.getHeight() ;
                int width2 = bitmap.getWidth() ;
                canvas.drawBitmap(bitmap, 10, height1+5,mPaint);
                bitmap.recycle();

                canvas.drawText(itemData.carNo, width2 + 20, (height1+10)+(height2-height1)/2+ height1, mPaint);	// 画出车牌
                
                int lineY = (int) (height1 + 10 + height2 + 5) ;
                mPaint.setColor(Color.GREEN);
                canvas.drawLine(2, lineY , DDSConst.ITEM_WIDTH-2, lineY , mPaint);
                
                mPaint.setColor(Color.BLACK);
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                int height3 = bitmap.getHeight() ;
                int width3 = bitmap.getWidth() ;
                canvas.drawBitmap(bitmap, 10, lineY, mPaint);
                canvas.drawText(itemData.name, width3 + 20, lineY+5+(height3-height1)/2 , mPaint);	// 画出员工
        	}
        	
        }
	}
	@Override
	public boolean onLongClick(View view) {
		// TODO Auto-generated method stub
		Log.i("onLongClick", "onLongClick = "+posY+","+posX);
		DDS0314Activity  activity = 	(DDS0314Activity)view.getContext();
		activity.addViewToWindowManage(this);
//		activity.DragView(this);
//		setOnDragListener(activity.dragListener);
		isLongPressed  = true ;
		invalidate();
		return false;
	}
	public ItemData getItemData() {
		return itemData;
	}
	public void setItemData(ItemData itemData) {
		this.itemData = itemData;
		invalidate();
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_UP:
			Log.i("onTouch", "onTouch  ACTION_UP = "+posY+","+posX);
			isLongPressed  = false ;
			invalidate();
			break;

		default:
			break;
		}
		return false;
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
//	    int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
//	    int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
//	    int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
//	    int width;  
//	    int height ;  
//	    if (widthMode == MeasureSpec.EXACTLY)  {  
//	        width = widthSize;  
//	    }else{  
//	        mPaint.getTextBounds(yuangong, 0, yuangong.length(), mBounds);  
//	        float textWidth = mBounds.width();  
//	        int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());  
//	        width = desired;  
//	    }  
//	  
//	    if (heightMode == MeasureSpec.EXACTLY) {  
//	        height = heightSize;  
//	    } else {  
//	        mPaint.getTextBounds(yuangong, 0, yuangong.length(), mBounds);  
//	        float textHeight = mBounds.height();  
//	        int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());  
//	        height = desired;  
//	    }  
//	    setMeasuredDimension(width, height);  
	};
	
//	public void setData(String time, String carNo, String yuangong){
//		tv_time.setText(time);
//		tv_carNo.setText(carNo);
//		tv_yuangong.setText(yuangong);
//		invalidate();
//		requestLayout();
//	}
//	
//	public void setBackgroundColor(int color){
//		rl_zi.setBackgroundColor(color);
//	}
//	
//	public void setBackgroundResource(int resid){
//		rl_zi.setBackgroundResource(resid);
//	}
//	public void setLayoutParams(RelativeLayout.LayoutParams params){
//		rl_zi.setLayoutParams(params);
//	}
//	
//	public void showOff(){
//		isShowing = false;
//		rl_zi.setBackgroundResource(R.drawable.sharp_item_off_border);
//		tv_time.setVisibility(View.INVISIBLE);
//		iv_line.setVisibility(View.INVISIBLE);
//		tv_carNo.setVisibility(View.INVISIBLE);
//		tv_yuangong.setVisibility(View.INVISIBLE);
//		iv_car.setVisibility(View.INVISIBLE);
//		iv_fuwu.setVisibility(View.INVISIBLE);
//	}
//	
//	public void showOn(){
//		isShowing = true;
//		rl_zi.setBackgroundResource(R.drawable.sharp_item_on_border);
//		tv_time.setVisibility(View.VISIBLE);
//		tv_carNo.setVisibility(View.VISIBLE);
//		tv_yuangong.setVisibility(View.VISIBLE);
//		iv_car.setVisibility(View.VISIBLE);
//		iv_fuwu.setVisibility(View.VISIBLE);
//		iv_line.setVisibility(View.VISIBLE);
//	}
//	
//	

}
