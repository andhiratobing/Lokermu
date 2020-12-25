package com.example.lokermu.Activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lokermu.BuildConfig
import com.example.lokermu.Models.Pengguna
import com.example.lokermu.R
import com.example.lokermu.RegisterLogin.LoginActivity
import com.example.lokermu.Utils.CustomProgressDialog
import com.example.lokermu.Utils.NetworkCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.theartofdev.edmodo.cropper.CropImage
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_daftar_lowongan.*
import kotlinx.android.synthetic.main.activity_profile.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.ByteArrayOutputStream
import java.io.File

class ProfileActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private val progressDialog = CustomProgressDialog()
    private val networkCheck = NetworkCheck(this)
    lateinit var userr: MutableList<Pengguna>
    val TAG = "ProfileActivity"
    private var PICK_IMAGE_REQUEST = 1
    private var selectedPhotoUri: Uri? = null
    private val LOCATION_AND_CONTACTS = arrayOf<String>(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA
    )
    private val RC_LOCATION_CONTACTS_PERM = 124
    lateinit var from_top: Animation
    lateinit var from_bottom: Animation
    lateinit var image_view_foto: ImageView
    lateinit var ic_email: ImageView
    lateinit var ic_telp: ImageView
    lateinit var ic_alamat: ImageView
    lateinit var ic_password: ImageView
    lateinit var ic_logout: ImageView
    lateinit var tv_nama_lengkap: TextView
    lateinit var ic_ubah_nama_lengkap: ImageView
    lateinit var tv_email: TextView
    lateinit var tv_no_hp: TextView
    lateinit var tv_detail_alamat: TextView
    lateinit var tv_katasandi: TextView
    lateinit var tv_logout: TextView
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        networkCheck.result = { isAvailable, type ->
            when (isAvailable) {
                true -> {
                    when (type) {
                        NetworkCheck.ConnectionType.Wifi -> {
                            // Toast.makeText(this, "Terhubung Wifi", Toast.LENGTH_SHORT).show()
                        }
                        NetworkCheck.ConnectionType.Cellular -> {
                            //  Toast.makeText(this, "Terhubung data seluler", Toast.LENGTH_SHORT) .show()
                        }
                        else -> {
                        }
                    }
                }
                false -> {
                    Toast.makeText(this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
                }

            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = Color.TRANSPARENT
        }

        setWindowFlag(
            this,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false
        )
        window.statusBarColor = Color.TRANSPARENT
        toolbar_profile.title = null
        setSupportActionBar(toolbar_profile)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userr = mutableListOf()

        from_top = AnimationUtils.loadAnimation(this, R.anim.from_top)
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        image_view_foto = findViewById(R.id.image_view_foto_profile)
        ic_ubah_nama_lengkap = findViewById(R.id.ic_ubah_nama_lengkap)
        tv_nama_lengkap = findViewById(R.id.tv_nama_lengkap)
        ic_email = findViewById(R.id.ic_email)
        ic_telp = findViewById(R.id.ic_telp)
        ic_alamat = findViewById(R.id.ic_alamat)
        ic_password = findViewById(R.id.ic_password)
        ic_logout = findViewById(R.id.ic_logout)
        tv_email = findViewById(R.id.tv_email)
        tv_no_hp = findViewById(R.id.tv_no_hp)
        tv_detail_alamat = findViewById(R.id.tv_detail_alamat)
        tv_katasandi = findViewById(R.id.tv_katasandi)
        tv_logout = findViewById(R.id.tv_logout)


        image_view_foto_profile.startAnimation(from_bottom)
        tv_nama_lengkap.startAnimation(from_bottom)
        ic_ubah_nama_lengkap.startAnimation(from_bottom)
        tv_email.startAnimation(from_top)
        ic_email.startAnimation(from_top)
        ic_telp.startAnimation(from_top)
        ic_alamat.startAnimation(from_top)
        ic_password.startAnimation(from_top)
        ic_logout.startAnimation(from_top)
        tv_email.startAnimation(from_top)
        tv_no_hp.startAnimation(from_top)
        tv_detail_alamat.startAnimation(from_top)
        tv_katasandi.startAnimation(from_top)
        tv_logout.startAnimation(from_top)

        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid").child("profile")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (h in snapshot.children) {
                        val pengguna = h.getValue(Pengguna::class.java)
                        userr.add(pengguna!!)
                        tv_nama_lengkap.text = pengguna.nama_lengkap
                        tv_email.text = pengguna.email
                        tv_no_hp.text = pengguna.no_hp
                        //  val image_profile: Pengguna? = h.child("default").getValue(Pengguna::class.java)

                        Glide.with(applicationContext).load(pengguna.image_profile)
                            .into(image_view_foto)
                        Glide.with(applicationContext).load(pengguna.image_profile)
                            .placeholder(R.drawable.ic_foto_user).into(image_view_foto)
                        if (pengguna.image_profile?.equals("default")!!) {
                            Glide.with(applicationContext).load(pengguna.image_profile).placeholder(
                                R.drawable.ic_foto_user
                            ).into(image_view_foto)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



        image_view_foto.setOnClickListener {
            if (hasLocationAndContactsPermissions()) {
                val galleryIntent = Intent()
                galleryIntent.type = "image/*"
                galleryIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(galleryIntent, "SELECT IMAGE"), PICK_IMAGE_REQUEST
                )
            } else {
                // Ask for both permissions
                EasyPermissions.requestPermissions(
                    this,
                    "This app needs access to your location and contacts to know where and who you are.",
                    RC_LOCATION_CONTACTS_PERM,
                    *LOCATION_AND_CONTACTS
                )
            }
        }


        tv_logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Keluar")
            builder.setMessage("Yakin Keluar Aplikasi?")
            builder.setPositiveButton("Ya") { dialog, which ->
                progressDialog.show(this, "Tolong tunggu...")
                // Dismiss progress bar after 2 seconds
                progressDialog.dialog.dismiss()
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("Tidak") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

    private fun hasLocationAndContactsPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this, *LOCATION_AND_CONTACTS)
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")
            .child("profile")

        val userHasMap = HashMap<String, Any?>()
        userHasMap["image_profile"] = profileImageUrl

        ref.child(uid).updateChildren(userHasMap).addOnSuccessListener {
            Handler(Looper.getMainLooper()).postDelayed({
                // Dismiss progress bar after 2 seconds
                progressDialog.dialog.dismiss()
                Toast.makeText(this, "Foto profil berhasil di ganti ", Toast.LENGTH_SHORT)
                    .show()
            }, 1000)
        }.addOnFailureListener {
            Handler(Looper.getMainLooper()).postDelayed({
                // Dismiss progress bar after 2 seconds
                progressDialog.dialog.dismiss()
                Toast.makeText(this, "Gagal mengganti foto profil", Toast.LENGTH_SHORT).show()
            }, 1000)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            //crop image
            CropImage.activity(selectedPhotoUri)
                .setAspectRatio(1, 1)
                .setMinCropWindowSize(500, 500)
                .start(this)
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                progressDialog.show(this, "Tolong tunggu...")

                val resultUri = result.uri
                //compressor bit map
                val thumb_filePath = File(resultUri.path)

                //Compressor
                val bitmap = Compressor(this)
                    .setMaxWidth(200)
                    .setMaxHeight(200)
                    .setQuality(75)
                    .compressToBitmap(thumb_filePath)

                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                val uid = FirebaseAuth.getInstance().currentUser?.uid
                val refStorage = FirebaseStorage.getInstance().getReference("foto_profile/$uid")

                refStorage.putFile(resultUri).addOnSuccessListener {
                    Handler(Looper.getMainLooper()).postDelayed({
                        // Dismiss progress bar after 1 seconds
                        progressDialog.dialog.dismiss()
                        Log.d(TAG, "Berhasil mengunggah foto profil: ${it.metadata?.path}")
                    }, 800)

                    refStorage.downloadUrl.addOnSuccessListener {
                        Log.d(TAG, "File Lokasi: $it")

                        saveUserToFirebaseDatabase(it.toString())
                    }
                }.addOnFailureListener {
                    Log.d(TAG, "Gagal mengunggah foto profil: ${it.message}")
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                result.error
            }
        }

    }

    override fun onResume() {
        super.onResume()
        networkCheck.register()
    }

    override fun onStop() {
        super.onStop()
        networkCheck.unregister()
    }


    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)

        if (item.itemId == android.R.id.home) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        //Set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }
}