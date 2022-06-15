package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.tabletostore.*
import java.io.File
import java.io.FileInputStream
import java.util.*


class HomeActivity : AppCompatActivity() , Player.Listener{

    private var mPlayer: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView

    private val videoURL =
        "https://credocoreappimages.blob.core.windows.net/helpvideos/Nutrition_guide_Trim.mp4"

    var mediacontroller : MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabletostore)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("passwordPreference", Context.MODE_PRIVATE)

       /* getBytedatafromvideo();*/
        playerView = findViewById(R.id.playerView)

       /* try {

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
            val sharedNameValue = sharedPreferences.getString("password","null")

            val encodedString = Base64.encodeToString(("$sharedNameValue $currentDate").toByteArray(), Base64.DEFAULT)
            val decodedBytes = Base64.decode(encodedString,0)
            val decodedString = String(decodedBytes)
            Log.e("KeyHash:",""+ decodedString + " encodedString :"+ encodedString)
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }*/

    }

    private fun initPlayer() {

        // Create a player instance.
        mPlayer = SimpleExoPlayer.Builder(this).build()

        // Bind the player to the view.
        playerView.player = mPlayer

        //setting exoplayer when it is ready.
        mPlayer!!.playWhenReady = true

        // Set the media source to be played.
        mPlayer!!.setMediaSource(buildMediaSource())

        // Prepare the player.
        mPlayer!!.prepare()

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || mPlayer == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }


    private fun releasePlayer() {
        if (mPlayer == null) {
            return
        }
        //release player when done
        mPlayer!!.release()
        mPlayer = null
    }

    //creating mediaSource
    private fun buildMediaSource(): MediaSource {
        // Create a data source factory.
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

        // Create a progressive media source pointing to a stream uri.
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoURL))

        return mediaSource
    }



    private fun getBytedatafromvideo(): ByteArray {
        val file = File("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
        //init array with file length
        //init array with file length
        val bytesArray = ByteArray(file.length() as Int)


        val fis = FileInputStream(file)
        fis.read(bytesArray) //read file into bytes[]

        fis.close()
        /*val encodedString = encodeToString(bytesArray, DEFAULT)*/
        return bytesArray
    }

    /* private fun loadVideofromURL(uri: Uri?) {
         videoView.setMediaController(mediacontroller);
         videoView.setVideoURI(uri);
         videoView.requestFocus();
         videoView.start();
     }

     override fun onTouchEvent(event: MotionEvent?): Boolean {
         if (event != null) {
             if (event.getAction() == MotionEvent.ACTION_DOWN) {

                 imageButtonclose.setVisibility(View.VISIBLE);
             }
         }
         if (event != null) {
             if (event.getAction() == MotionEvent.ACTION_UP) {

                 imageButtonclose.setVisibility(View.INVISIBLE);
             }
         }
         return super.onTouchEvent(event);
     }*/
}
