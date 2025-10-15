package infosecadventures.allsafe.challenges;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\RecorderService.smali */
public class RecorderService extends Service implements MediaRecorder.OnInfoListener {
    private MediaRecorder mediaRecorder;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startRecording();
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startRecording() {
        Toast.makeText(this, "Audio recording started!", 0).show();
        try {
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.mediaRecorder = mediaRecorder;
            mediaRecorder.setAudioSource(1);
            this.mediaRecorder.setMaxDuration(10000);
            this.mediaRecorder.setOutputFormat(2);
            this.mediaRecorder.setAudioEncoder(3);
            this.mediaRecorder.setAudioEncodingBitRate(64000);
            this.mediaRecorder.setAudioSamplingRate(16000);
            File outputFile = getOutputFile();
            this.mediaRecorder.setOutputFile(outputFile.getAbsolutePath());
            this.mediaRecorder.prepare();
            this.mediaRecorder.start();
        } catch (Exception e) {
            Log.d("ALLSAFE", "Exception: " + e.getMessage());
        }
    }

    private void stopRecording() {
        try {
            MediaRecorder mediaRecorder = this.mediaRecorder;
            if (mediaRecorder != null) {
                mediaRecorder.stop();
                this.mediaRecorder.reset();
                this.mediaRecorder.release();
                this.mediaRecorder = null;
            }
            stopSelf();
        } catch (Exception e) {
            Log.d("ALLSAFE", "Exception: " + e.getMessage());
        }
        Toast.makeText(getApplicationContext(), "Audio recording stopped!", 0).show();
    }

    private File getOutputFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US);
        String fullPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/allsafe_rec_" + dateFormat.format(new Date()) + ".mp3";
        Toast.makeText(getApplicationContext(), "File: " + fullPath, 0).show();
        return new File(fullPath);
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mr, int what, int extra) {
        stopRecording();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopRecording();
    }
}
