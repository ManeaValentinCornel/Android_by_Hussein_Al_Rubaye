package com.vcmanea.twitterapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.addtweet_ticket.view.*
import kotlinx.android.synthetic.main.tweets_ticket.*
import kotlinx.android.synthetic.main.tweets_ticket.view.*
import kotlinx.android.synthetic.main.tweets_ticket.view.tweet_user_imageUrl
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    //FIREBASE
    private var database = FirebaseDatabase.getInstance()
    private var mDatabaseRef = database.reference
    var mEmail: String? = null
    var mUID: String? = null
    var mTweetsList = ArrayList<Tweet>()
    var mAdapter: MyTweetAdapter? = null
    private var mDownloadUrl: String = "nothing"
    var mBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTweetsList.add(Tweet("0", "Wall", "url", "add"))
        //BUNDLE
        var b: Bundle = intent.extras as Bundle
        mEmail = b.getString("email")
        mUID = b.getString("uid")
        //ADAPTER
        mAdapter = MyTweetAdapter(mTweetsList, this)
        tweets_listView.adapter = mAdapter
        loadPost()
    }

    //LOAD IMAGE ----------------------------------> FROM YOUR ITNERNAL STORAGE USING AN INTENT
    private val PICK_IMAGE_CODE = 160
    fun loadImage() {
        val galleryIntent = Intent()
        galleryIntent.type = ("image/*")
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(galleryIntent, PICK_IMAGE_CODE)
    }

    //ON ACTIVITY RESULT
    //THIS METHOD GETS FIRED AUTOMATICALLY WHEN THE startActivityForResult is called
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            try {
                val imgUri = data.data
                mBitmap =
                    BitmapFactory.decodeStream(imgUri?.let { contentResolver.openInputStream(it) })
            } catch (ex: Exception) {
                Toast.makeText(applicationContext, ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    //CHECK PERMISSION
    private val READ_IMAGE = 155
    private fun checkReadExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READ_IMAGE)
                return
            }
        }
        loadImage()
    }

    //ON REQUEST PERMISSION RESULT
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_IMAGE ->
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
                else {
                    Toast.makeText(this, "Cannot access your images", Toast.LENGTH_LONG).show()
                }
        }
    }

    //UPLOAD IMG TO FIREBASE STORAGE
    private fun uploadImgToStorage(view: View) {
        //PROGRESS BAR
        mTweetsList.add(0, Tweet("0", "Wall", "url", "loading"))
        mAdapter?.notifyDataSetChanged()
        //can also use the reference by URl -> FirebaseStorage.getInstance().getReferenceFromUrl()
        val firebaseStorage = FirebaseStorage.getInstance()
        var firebaseStorageRef = firebaseStorage.reference
        //creating the image path using the first character of the user email address and dateformat
        val dateFormat = SimpleDateFormat("ddMMyyHHmmss", Locale.getDefault())
        val dateObj = Date()
        val imagePath = mEmail?.substring(0, 4) + dateFormat.format(dateObj) + ".jpg"
        //Firebase image reference
        val imageRef = firebaseStorageRef.child("imagesPost/$imagePath")
        //bitmaps and scaling them down before saving them into memory
        val baos = ByteArrayOutputStream()
        mBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        //Upload picture to Storage
        imageRef.putBytes(data).addOnSuccessListener {
            var result = it.metadata?.reference?.downloadUrl
            result?.addOnSuccessListener {
                //IF YOU TRY TO CALL THE METHOD UPDATEDATABSE POUT OIF THIS METHOID THE URL VALUE WILL BE CHANGED>>>
                mDownloadUrl = it.toString()
                Log.d("URL-MAIN", mDownloadUrl)
                updateDataBase(view)
                mTweetsList.removeAt(0)
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    //UPDATE REAL TIME DATABASE AFTER THE IMG IS SUCCESFULLY ADDED TO STORAGE
    private fun updateDataBase(view: View) {
        mDatabaseRef.child("Posts").push().setValue(PostInfo(mUID, view.addTweet_eTxtSend.text.toString(), mDownloadUrl)).addOnSuccessListener {
            Toast.makeText(applicationContext, "PostInfo succesfully saved", Toast.LENGTH_LONG).show()
        }
    }

    //BASE ADAPTER
    inner class MyTweetAdapter : BaseAdapter {
        var mTweetsList = ArrayList<Tweet>()
        var context: Context? = null

        constructor(listTweets: ArrayList<Tweet>, context: Context?) : super() {
            this.mTweetsList = listTweets
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myTweet = mTweetsList[position]
            if (myTweet.tweetPersonUID == "add") {
                var myView = LayoutInflater.from(context).inflate(R.layout.addtweet_ticket, null)
                //lisener
                myView.addTweet_imgAttach.setOnClickListener {
                    checkReadExternalStoragePermission()
                }
                //lisener
                myView.addTweet_imgSend.setOnClickListener {
                    if (mBitmap != null) {
                        uploadImgToStorage(myView)
                    }
                    else {
                        Toast.makeText(applicationContext, "Pleas insert an image you send the post", Toast.LENGTH_LONG).show()
                    }
                }
                return myView
            }
            else if (myTweet.tweetPersonUID.equals("loading")) {
                var myView = LayoutInflater.from(context).inflate(R.layout.loading_ticket, null)
                return myView
            }
            else {
                var myView = layoutInflater.inflate(R.layout.tweets_ticket, null)
                myView.tweet_title.setText(myTweet.txt)
                myView.tweet_userName.setText(myTweet.tweetPersonUID)
                Picasso.get().load(myTweet.imgUrl).into(myView.tweet_image)

                mDatabaseRef.child("Users").child(myTweet.tweetPersonUID.toString()).addValueEventListener(object :
                    ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var tableData = dataSnapshot.value as HashMap<String, *>
                        for (key in tableData.keys) {
                            var userInfo = tableData[key] as String
                            if (key.equals("email")) {
                                myView.tweet_userName.setText(userInfo)
                            }
                            else {
                                Picasso.get().load(userInfo).into(myView.tweet_user_imageUrl)
                            }
                        }
                    }
                }
                )
                return myView
            }
        }

        override fun getItem(position: Int): Any {
            return mTweetsList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mTweetsList.size
        }
    }

    //load Post
    fun loadPost() {
        mDatabaseRef.child("Posts")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        mTweetsList.clear()
                        mTweetsList.add(Tweet("0", "Wall", "url", "add"))

                        var td = dataSnapshot?.value as HashMap<*, *>
                        for (key in td.keys) {

                            var post = td[key] as HashMap<*, *>
                            mTweetsList.add(Tweet(key.toString(), post["text"] as String, post["postImage"] as String, post["id"] as String))
                        }
                        mAdapter?.notifyDataSetChanged()
                    } catch (ex: Exception) {
                        ex.message
                    }
                }
            })
    }
}
