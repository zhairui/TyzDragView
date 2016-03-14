package com.tyz.ddsnew;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dds.R;

public class DDS0314Activity extends Activity {
	
	FrameLayout ll_body ;
	LinearLayout ll_left;
	private int maxLie;
	private ArrayList<ArrayList<ItemData>> allData;
	private android.view.WindowManager.LayoutParams mWindowParams;
	private WindowManager mWindowManager;
	private ImageView mDragView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dds_0314);
		ll_left = (LinearLayout) findViewById(R.id.ll_left);
		ll_body= (FrameLayout) findViewById(R.id.ll_body);
		ScreenUtil.getScreenInfo(this);
		initView();
	}
	
	
	private  void  initView(){
		LinearLayout.LayoutParams leftItemParams =  new LinearLayout.LayoutParams(DDSConst.LEFT_WIDTH,DDSConst.ITEM_HEIGHT);
		ll_left.setLayoutParams(new LinearLayout.LayoutParams(DDSConst.LEFT_WIDTH,DDSConst.ITEM_HEIGHT*DDSConst.HANG_NUM));

		
		try {
			for(int j=1; j<=DDSConst.HANG_NUM;j++){	// 表示每行创建 level 列
				TextView textView = new TextView(this);
				textView.setLayoutParams(leftItemParams);
				textView.setText("工位"+j);
				textView.setGravity(Gravity.CENTER);
				textView.setBackgroundResource(R.drawable.sharp_item_on_border);
				ll_left.addView(textView);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		maxLie =-1;
		Random  random  = new Random();
//		GongWeiBG  temGongwei ;
		ItemView  temGongwei ;
		try {
			allData = new ArrayList<ArrayList<ItemData>>();
			ItemData  temData = null ;
			ArrayList<ItemData> temhangList;
			for(int i=0; i<DDSConst.HANG_NUM;i++){	// 表示要创建  level 行
				int  lie =  random.nextInt(DDSConst.LIE_NUM);
				if(lie>maxLie){
					maxLie = lie ;
				}
				temhangList = new ArrayList<ItemData>();
//				LinearLayout  hanglayout = new LinearLayout(this);
//				hanglayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, DDSConst.ITEM_HEIGHT));
				for(int j=0; j<DDSConst.LIE_NUM;j++){	// 表示每行创建 level 列
//					temGongwei = new GongWeiBG(this);
					if(j>lie){
//						temGongwei.showOff();
						temData = null ;
					}else{
//						temGongwei.setData("10:00-12:00", "沪"+i+j,"YG"+i+j);
						temData  = new ItemData("10:00-12:00", "沪"+(i+1)+(j+1),"YG"+(i+1)+(j+1));
						temhangList.add(temData);
					}
					
					temGongwei = new ItemView(this,temData);
					temGongwei.setPosX(j);
					temGongwei.setPosY(i);
					FrameLayout.LayoutParams  imgParams =  new FrameLayout.LayoutParams(DDSConst.ITEM_WIDTH,DDSConst.ITEM_HEIGHT);
					imgParams.leftMargin = j*DDSConst.ITEM_WIDTH;
					imgParams.topMargin = i*DDSConst.ITEM_HEIGHT;
					temGongwei.setLayoutParams(imgParams);
					ll_body.addView(temGongwei);
//					hanglayout.addView(temGongwei);
				}
				allData.add(temhangList);
				
			}
//			work_bg.setAllList(allData);
			temhangList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Log.i("gongwei","当前最多列数为："+maxLie);

//		 if((DDSConst.HANG_NUM*DDSConst.ITEM_HEIGHT>ScreenUtil.screenHeigh-ScreenUtil.statebarHeight )){// 可以竖向移动||可以横向移动
//         	isCanVMove = true ;
//		 }
//         if((DDSConst.LIE_NUM*DDSConst.ITEM_WIDTH>ScreenUtil.screenWidth-DDSConst.LEFT_WIDTH)){
//         	isCanHMove = true;
//		 }
	}
	
	public void DragView(ItemView view){
		ClipData.Item item = new ClipData.Item((String) view.getTag());
//		view.setVisibility(View.INVISIBLE);
		ClipData data = new ClipData("tag", new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
		view.startDrag(data, new View.DragShadowBuilder(view), null, 0);
	}
	
	public OnDragListener  dragListener = new OnDragListener() {
		
		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (action) {
			case DragEvent.ACTION_DRAG_STARTED: // 拖拽开始事件
				Log.i("DragEvent", "DRAG_STARTED = "+event.getX()+","+event.getY());
				return false;
			case DragEvent.ACTION_DRAG_ENTERED: // 被拖放View进入目标View
				Log.i("DragEvent", "DRAG_ENTERED = "+event.getX()+","+event.getY());
				return true;
			case DragEvent.ACTION_DRAG_LOCATION:
				Log.i("DragEvent", "DRAG_LOCATION = "+event.getX()+","+event.getY());
				return true;
			case DragEvent.ACTION_DRAG_EXITED: // 被拖放View离开目标View
				Log.i("DragEvent", "DRAG_EXITED = "+event.getX()+","+event.getY());
				return true;
			case DragEvent.ACTION_DROP: // 释放拖放阴影，并获取移动数据
				Log.i("DragEvent", "DROP = "+event.getX()+","+event.getY());
				return true;
			case DragEvent.ACTION_DRAG_ENDED: // 拖放事件完成
				Log.i("DragEvent", "DRAG_ENDED = "+event.getX()+","+event.getY());
				  if(event.getResult()){
					  Log.i("DragEvent", "DRAG_ENDED = "+event.getX()+","+event.getY());
                   }else {
                	   Log.i("DragEvent", "DRAG_ENDED = "+event.getX()+","+event.getY());

                   };
				return true;
			default:
				break;
			}
			return true;
		}

	};
	private View mHandler;
	private boolean isDrag;
	private int moveX;
	private int moveY;
	private Bitmap mDragBitmap;
	private int mDownX;
	private int mDownY;
	private Object mDragPosition;
	private int mDownScrollBorder;
	private int mUpScrollBorder;
	
	
	
	public void addViewToWindowManage(final ItemView itemView) { // int x, int y
		mWindowParams = new WindowManager.LayoutParams();
		mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
		// mWindowParams.x = x;
		// mWindowParams.y = y;
		mWindowParams.x = (int) itemView.getPosX() * DDSConst.ITEM_WIDTH + DDSConst.LEFT_WIDTH + 20;
		mWindowParams.y = (int) itemView.getPosY() * DDSConst.ITEM_HEIGHT + ScreenUtil.statebarHeight+20;
		mWindowParams.alpha = 0.85f; // 透明度
		mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		mWindowParams.format = PixelFormat.TRANSLUCENT;
		mWindowParams.windowAnimations = 0;
		mWindowManager = (WindowManager)itemView.getContext().getSystemService("window");
		try {
			itemView.setDrawingCacheEnabled(true);
			Bitmap bitmap = Bitmap.createBitmap(itemView.getDrawingCache());
			mDragView = new ImageView(itemView.getContext());
			mDragView.setBackgroundColor(0x00000000);
			mDragView.setImageBitmap(bitmap);
			mDragView.destroyDrawingCache();
			mDragView.setOnDragListener(new OnDragListener() {
				
				@Override
				public boolean onDrag(View view, DragEvent event) {
					// TODO Auto-generated method stub
						switch(event.getAction()) {
			            case DragEvent.ACTION_DRAG_STARTED:
							LogUtil.i("activity","onDrag = DRAG_STARTED ");
			                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
			                	view.invalidate();
			                    return true;
			                } else {
			                    return false;
			  
			                }
			            case DragEvent.ACTION_DRAG_ENTERED: 
			            	LogUtil.i("activity","onDrag = DRAG_ENTERED ");
	//		                v.setColorFilter(Color.GREEN);
			            	view.invalidate();
		                    return true;
			           case DragEvent.ACTION_DRAG_LOCATION:
			            	LogUtil.i("activity","onDrag = DRAG_LOCATION ");
		                    return true;
			           case DragEvent.ACTION_DRAG_EXITED:
			            	LogUtil.i("activity","onDrag = DRAG_EXITED ");
	//		                    v.setColorFilter(Color.BLUE);
			        	    view.invalidate();
		                    return true;
			           case DragEvent.ACTION_DROP:
			            		LogUtil.i("activity","onDrag = DROP ");
			                    ClipData.Item item = event.getClipData().getItemAt(0);
			                    // Gets the text data from the item.
			                    Toast.makeText(itemView.getContext(), "Dragged data is " + item.getText(), Toast.LENGTH_LONG).show();
	//		                    v.clearColorFilter();
				        	    view.invalidate();
			                    return true;
			           case DragEvent.ACTION_DRAG_ENDED:
			            	LogUtil.i("activity","onDrag = DRAG_ENDED ");
	//		                    v.clearColorFilter();
			        	   		view.invalidate();
			  
			                    // Does a getResult(), and displays what happened.
			                    if (event.getResult()) {
			                    	Toast.makeText(itemView.getContext(),"The drop was handled.", Toast.LENGTH_LONG).show();
			  
			                    } else {
			                    	Toast.makeText(itemView.getContext(),"The drop didn't work.", Toast.LENGTH_LONG).show();
			  
			                    };
			  
			                    // returns true; the value is ignored.
			                    return(true);
		                default:
		                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
		                    return true;
			        }
				}
			});
			mWindowManager.addView(mDragView, mWindowParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateViewPosition(int x, int y) {
		mWindowParams.alpha = 1.0f;
		mWindowParams.x = x;
		mWindowParams.y = y;
		if (mDragView != null) {
			mWindowManager.updateViewLayout(mDragView, mWindowParams);
		}

	}

	public void removeView() {
		if (mDragView != null) {
			mWindowManager.removeView(mDragView);
			mDragView = null;
//			if (mDragBitmap != null) {
//				mDragBitmap.recycle();
//				mDragBitmap = null;
//			}
		}
	}
	
	
	 @Override
	    public boolean dispatchTouchEvent(MotionEvent ev) {
	        switch(ev.getAction()){
	        case MotionEvent.ACTION_DOWN:
	            //使用Handler延迟dragResponseMS执行mLongClickRunnable
//	            mHandler.postDelayed(mLongClickRunnable, dragResponseMS);
	             
	            mDownX = (int) ev.getX();
	            mDownY = (int) ev.getY();
	            Log.i("activity", "dispatchTouchEvent  ACTION_UP = "+mDownX+","+mDownY);
//	            //根据按下的X,Y坐标获取所点击item的position
//	            mDragPosition = pointToPosition(mDownX, mDownY);
//	             
//	            if(mDragPosition == AdapterView.INVALID_POSITION){
//	                return super.dispatchTouchEvent(ev);
//	            }
	             
	            //根据position获取该item所对应的View
//	            mDragView = getChildAt(mDragPosition - getFirstVisiblePosition());
//	             
//	            //下面这几个距离大家可以参考我的博客上面的图来理解下
//	            mPoint2ItemTop = mDownY - mDragView.getTop();
//	            mPoint2ItemLeft = mDownX - mDragView.getLeft();
//	             
//	            mOffset2Top = (int) (ev.getRawY() - mDownY);
//	            mOffset2Left = (int) (ev.getRawX() - mDownX);
//	             
//	            //获取DragGridView自动向上滚动的偏移量，小于这个值，DragGridView向下滚动
//	            mDownScrollBorder = ll_body.getHeight() /5;
//	            //获取DragGridView自动向下滚动的偏移量，大于这个值，DragGridView向上滚动
//	            mUpScrollBorder = ll_body.getHeight() * 4/5;
//	             
//	            //开启mDragItemView绘图缓存
//	            mDragView.setDrawingCacheEnabled(true);
//	            //获取mDragItemView在缓存中的Bitmap对象
//	            mDragBitmap = Bitmap.createBitmap(mDragView.getDrawingCache());
//	            //这一步很关键，释放绘图缓存，避免出现重复的镜像
//	            mDragView.destroyDrawingCache();
	             
	             
	            break;
	        case MotionEvent.ACTION_MOVE:
	            int moveX = (int)ev.getX();
	            int moveY = (int) ev.getY();
	            Log.i("activity", "dispatchTouchEvent  ACTION_MOVE = "+moveX+","+moveY);
	            //如果我们在按下的item上面移动，只要不超过item的边界我们就不移除mRunnable
//	            if(!isTouchInItem(mDragView, moveX, moveY)){
//	                mHandler.removeCallbacks(mLongClickRunnable);
//	            }
	            break;
	        case MotionEvent.ACTION_UP:
	        	Log.i("activity", "dispatchTouchEvent  ACTION_UP ");
//	            mHandler.removeCallbacks(mLongClickRunnable);
//	            mHandler.removeCallbacks(mScrollRunnable);
	            break;
	        }
	        return super.dispatchTouchEvent(ev);
	    }
	 
	     
	    /**
	     * 是否点击在GridView的item上面
	     * @param itemView
	     * @param x
	     * @param y
	     * @return
	     */
	    private boolean isTouchInItem(View dragView, int x, int y){
	        int leftOffset = dragView.getLeft();
	        int topOffset = dragView.getTop();
	        if(x < leftOffset || x > leftOffset + dragView.getWidth()){
	            return false;
	        }
	         
	        if(y < topOffset || y > topOffset + dragView.getHeight()){
	            return false;
	        }
	         
	        return true;
	    }
	     
	     
	 
	    @Override
	    public boolean onTouchEvent(MotionEvent ev) {
//	        if(isDrag && mDragView != null){
//	            
//	        }
	        switch(ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();
                //拖动item
//                onDragItem(moveX, moveY);
                Log.i("activity", "onTouchEvent  onDragItem = "+moveX+","+moveY);
                break;
            case MotionEvent.ACTION_UP:
//                onStopDrag();
                Log.i("activity", "onTouchEvent  onStopDrag = "+moveX+","+moveY);
                isDrag = false;
                break;
            }
//            return true;
	        return super.onTouchEvent(ev);
	    }
	     
	     
	    /**
	     * 创建拖动的镜像
	     * @param bitmap 
	     * @param downX
	     *          按下的点相对父控件的X坐标
	     * @param downY
	     *          按下的点相对父控件的X坐标
	     */
//	    private void createDragImage(Bitmap bitmap, int downX , int downY){
//	    	mWindowParams = new WindowManager.LayoutParams();
//	        mWindowParams.format = PixelFormat.TRANSLUCENT; //图片之外的其他地方透明
//	        mWindowParams.gravity = Gravity.TOP | Gravity.LEFT;
//	        mWindowParams.x = downX - mPoint2ItemLeft + mOffset2Left;
//	        mWindowParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
//	        mWindowParams.alpha = 0.55f; //透明度
//	        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
//	        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  
//	        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  
//	                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE ;
//	           
//	        mDragView = new ImageView(this);  
//	        mDragView.setImageBitmap(bitmap);  
//	        mWindowManager.addView(mDragView, mWindowParams);  
//	    }
	     
	    /**
	     * 从界面上面移动拖动镜像
	     */
	    private void removeDragImage(){
	        if(mDragView != null){
	            mWindowManager.removeView(mDragView);
	            mDragView = null;
	        }
	    }
	     
	    /**
	     * 拖动item，在里面实现了item镜像的位置更新，item的相互交换以及GridView的自行滚动
	     * @param x
	     * @param y
	     */
//	    private void onDragItem(int moveX, int moveY){
//	        mWindowParams.x = moveX - mPoint2ItemLeft + mOffset2Left;
//	        mWindowParams.y = moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
//	        mWindowManager.updateViewLayout(mDragView, mWindowParams); //更新镜像的位置
//	        onSwapItem(moveX, moveY);
//	         
//	        //GridView自动滚动
//	        mHandler.post(mScrollRunnable);
//	    }
	     
	     
	    /**
	     * 当moveY的值大于向上滚动的边界值，触发GridView自动向上滚动
	     * 当moveY的值小于向下滚动的边界值，触犯GridView自动向下滚动
	     * 否则不进行滚动
	     */
//	    private Runnable mScrollRunnable = new Runnable() {
//	         
//	        @Override
//	        public void run() {
//	            int scrollY;
//	            if(moveY > mUpScrollBorder){
//	                 scrollY = -speed;
//	                 mHandler.postDelayed(mScrollRunnable, 25);
//	            }else if(moveY < mDownScrollBorder){
//	                scrollY = speed;
//	                 mHandler.postDelayed(mScrollRunnable, 25);
//	            }else{
//	                scrollY = 0;
//	                mHandler.removeCallbacks(mScrollRunnable);
//	            }
//	             
//	            //当我们的手指到达GridView向上或者向下滚动的偏移量的时候，可能我们手指没有移动，但是DragGridView在自动的滚动
//	            //所以我们在这里调用下onSwapItem()方法来交换item
//	            onSwapItem(moveX, moveY);
//	             
//	            View view = getChildAt(mDragPosition - getFirstVisiblePosition());
//	            //实现GridView的自动滚动
//	            smoothScrollToPositionFromTop(mDragPosition, view.getTop() + scrollY);
//	        }
//	    };
	     
	     
	    /**
	     * 交换item,并且控制item之间的显示与隐藏效果
	     * @param moveX
	     * @param moveY
	     */
//	    private void onSwapItem(int moveX, int moveY){
//	        //获取我们手指移动到的那个item的position
//	        int tempPosition = pointToPosition(moveX, moveY);
//	         
//	        //假如tempPosition 改变了并且tempPosition不等于-1,则进行交换
//	        if(tempPosition != mDragPosition && tempPosition != AdapterView.INVALID_POSITION){
//	            getChildAt(tempPosition - getFirstVisiblePosition()).setVisibility(View.INVISIBLE);//拖动到了新的item,新的item隐藏掉
//	            getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);//之前的item显示出来
//	             
//	            if(onChanageListener != null){
//	                onChanageListener.onChange(mDragPosition, tempPosition);
//	            }
//	             
//	            mDragPosition = tempPosition;
//	        }
//	    }
	     
	     
	    /**
	     * 停止拖拽我们将之前隐藏的item显示出来，并将镜像移除
	     */
//	    private void onStopDrag(){
//	        getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);
//	        removeDragImage();
//	    }
	     
	
}
