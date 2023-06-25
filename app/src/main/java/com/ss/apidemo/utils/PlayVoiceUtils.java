package com.ss.apidemo.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class PlayVoiceUtils {
    private static MediaPlayer player;
    // 打开本地音乐文件 并 播放
    public static MediaPlayer startPlayVoice(Context context,String audio) {

        try {
            AssetFileDescriptor fileDescriptor = context.getResources().getAssets().openFd(audio);
            if (player == null){
                player = new MediaPlayer();
            }
            if (!player.isPlaying()){//是否播放中
                player.reset();
                player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                player.prepare();
                player.start();
            }


            /*
            * 循环播放
            * */
//            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    if (mediaPlayer == null) {
//                        return;
//                    }
//                    mediaPlayer.start();
//                    mediaPlayer.setLooping(true);
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    // 暂停音乐播放
    public static void pausePlayVoice() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
            }
        }

    }

    // 关闭音乐播放
    public static void stopPlayVoice() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

}
