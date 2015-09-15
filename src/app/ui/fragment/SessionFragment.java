package app.ui.fragment;

import java.io.IOException;

import com.musicplayer.Player;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import app.ui.BaseFragment;
import app.util.DialogUtils;
import mobi.kuaidian.qunakao.R;

public class SessionFragment extends BaseFragment implements OnClickListener{
	private Player player; 
	 View btnNext, btnPlay,connect,disconnect;  
     EditText UrlText;
     SeekBar skbProgress;
    
    boolean nocon=true;
    boolean isplaying=false;
    
    public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNext: 
    		//todo
			break;
		case R.id.btnPlayUrl:
			if(nocon==true){
				DialogUtils.showToast(this.getActivity(), "未连接网络媒体");
//        	new Thread(new Runnable() {
//    			@Override
//    			public void run() {
//    				String url = UrlText.getText().toString();
////    	            String url="http://www.kfybsf.com/mp3/liangzhu.mp3";
////            		String url="http://conteadiwagner.com/audio/sf.mp3"; //长
//    				player.playUrl(url);
//    			}
//    		}).start();
//            isplaying=true;
//            refreshBottomBar();
//            nocon=false;
        	}
        	else{
        		if(isplaying==true){
        			player.pause();
        			isplaying=false;
        			refreshBottomBar();
        		}
        		else{
        		player.play();
        		isplaying=true;
        		refreshBottomBar();
        		}
        		}
			break;
			
		case R.id.connect: 
			if(nocon==true)
        	{
        	new Thread(new Runnable() {
    			@Override
    			public void run() {
    				String url = UrlText.getText().toString();
//    	            String url="http://www.kfybsf.com/mp3/liangzhu.mp3";
//            		String url="http://conteadiwagner.com/audio/sf.mp3"; //长
    				player.playUrl(url);
    			}
    		}).start();
            isplaying=true;
            refreshBottomBar();
            nocon=false;
        	}
			break;
		case R.id.disconnect: 
			if(nocon==false){
			player.stop();
			isplaying=false;
            refreshBottomBar();
            nocon=true;
			}
			break;
		default:
			break;
		}
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	View view = inflater.inflate(R.layout.fragment_session, container, false);
    	btnPlay = (View)view.findViewById(R.id.btnPlayUrl);
        btnNext = (View)view.findViewById(R.id.btnNext);  
        connect = (View)view.findViewById(R.id.connect); 
        disconnect = (View)view.findViewById(R.id.disconnect); 
        UrlText = (EditText)view.findViewById(R.id.edit_text);
        
      //设置监听器
  		view.findViewById(R.id.btnPlayUrl).setOnClickListener(this);
  		view.findViewById(R.id.btnNext).setOnClickListener(this);
  		view.findViewById(R.id.connect).setOnClickListener(this);
  		view.findViewById(R.id.disconnect).setOnClickListener(this);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        skbProgress=new SeekBar(getActivity());
        skbProgress = (SeekBar)this.getActivity().findViewById(R.id.skbProgress);
        player = new Player(skbProgress);  
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        
//        btnPlay.setOnClickListener(new ClickEvent());
//        btnNext.setOnClickListener(new ClickEvent());  
    }
    
    private void refreshBottomBar() {
//      Player player = Player.getPlayer();
     if(isplaying==false){
//          mImg.setImageResource(R.drawable.img_noplaying);
  	   btnPlay.setBackgroundResource(R.drawable.selector_btn_play);
          }
//          mTvTitle.setText(player.getMusic().getTitle());
//          mTvArtist.setText(player.getMusic().getArtist());
     if(isplaying==true){
//          mImg.setImageResource(R.drawable.img_playing);
  	   btnPlay.setBackgroundResource(R.drawable.selector_btn_pause);
//          mTvTitle.setText(player.getMusic().getTitle());
//          mTvArtist.setText(player.getMusic().getArtist());
          }
     }
    
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {  
        int progress;  
        @Override  
        public void onProgressChanged(SeekBar seekBar, int progress,  
                boolean fromUser) {  
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()  
            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();  
        }  
        @Override  
        public void onStartTrackingTouch(SeekBar seekBar) { }  
  
        @Override  
        public void onStopTrackingTouch(SeekBar seekBar) {  
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字  
            player.mediaPlayer.seekTo(progress);  
        }  
    }

}
