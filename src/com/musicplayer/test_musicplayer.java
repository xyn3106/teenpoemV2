package com.musicplayer;  
  


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;  
  
public class test_musicplayer extends Activity {  
    private Button btnNext, btnPlay;  
    private EditText UrlText;
    private SeekBar skbProgress;  
    private Player player;  
    private long exitTime = 0;
    boolean canplay=true;
    boolean isplaying=false;
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
    
        this.setTitle("MediaPlayer+HttpProxy");  
  
        btnPlay = (Button) this.findViewById(R.id.btnPlayUrl);  
        btnPlay.setOnClickListener(new ClickEvent());  
  
        btnNext = (Button) this.findViewById(R.id.btnNext);  
        btnNext.setOnClickListener(new ClickEvent());  
  
        UrlText = (EditText)this.findViewById(R.id.edit_text);
 
        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);  
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());  
        player = new Player(skbProgress);  
    }
  
//    public void onStop(){
//    	super.onStop();
//    	System.exit(0);
//    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出!", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}
    
    class ClickEvent implements OnClickListener {
  
        @Override  
        public void onClick(View arg0) {
        	if (arg0 == btnNext) {
        		//todo
            } 
        	else if (arg0 == btnPlay) {
            	if(canplay==true)
            	{
            	String url = UrlText.getText().toString();
//                String url="http://www.kfybsf.com/mp3/liangzhu.mp3";
//            		String url="http://conteadiwagner.com/audio/sf.mp3"; //长
                player.playUrl(url);
                isplaying=true;
                refreshBottomBar();
                canplay=false;
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
            	}
        	}
    }
        
        private void refreshBottomBar() {
//          Player player = Player.getPlayer();
         if(isplaying==false){
//              mImg.setImageResource(R.drawable.img_noplaying);
      	   btnPlay.setBackgroundResource(R.drawable.selector_btn_play);
              }
//              mTvTitle.setText(player.getMusic().getTitle());
//              mTvArtist.setText(player.getMusic().getArtist());
         if(isplaying==true){
//              mImg.setImageResource(R.drawable.img_playing);
      	   btnPlay.setBackgroundResource(R.drawable.selector_btn_pause);
//              mTvTitle.setText(player.getMusic().getTitle());
//              mTvArtist.setText(player.getMusic().getArtist());
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