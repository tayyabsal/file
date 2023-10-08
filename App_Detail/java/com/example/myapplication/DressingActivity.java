
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DressingActivity extends AppCompatActivity {



    ImageView imgGallery;
    private Uri imageUri;
    private static final int IMAGE_REQUEST = 2;
    String name,address_,designation_,contact_;
    FirebaseDatabase Db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dressing);

        imgGallery = findViewById(R.id.ImageViewUpload);
        Button upload = findViewById(R.id.Upload);
        TextView Name=findViewById(R.id.edittextCONTACT);
        EditText designation =findViewById(R.id.Desination);
        EditText Contact=findViewById(R.id.Conatct_Number);
        EditText address=findViewById(R.id.qualification);
        Button book =findViewById(R.id.Next);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
name=Name.getText().toString();
address_=address.getText().toString();
contact_=Contact.getText().toString();
designation_=designation.getText().toString();

if(!name.isEmpty() && !address_.isEmpty() && !contact_.isEmpty() && !designation_.isEmpty()){
    Users users= new Users(name,designation_,contact_,address_);
    Db =FirebaseDatabase.getInstance();
    reference=Db.getReference("Users");
    reference.child(name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            Name.setText("");
            address.setText("");
            Contact.setText("");
            designation.setText("");
            Toast.makeText(DressingActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

        }
    });
}
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
         imagePickerLauncher.launch(intent);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if (imageUri != null) {
            StorageReference fileRef = FirebaseStorage.getInstance().getReference()
                    .child("uploads").child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                pd.dismiss();
                                Toast.makeText(DressingActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(DressingActivity.this, "Upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Use registerForActivityResult to handle activity results
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        imageUri = data.getData();
                        uploadImage();
                    }
                }
            }
    );
}
