package com.example.imagemarker.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.example.imagemarker.core.Util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

public class ImagePickerActivity extends Activity {

	private Bitmap bitmap;
	public static final int PICK_FROM_CAMERA = 100;
	public static final int PICK_FROM_GALLERY = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public File createImageFile(){
		String root = Environment.getExternalStorageDirectory().getPath();
		String imageDirectoryPath = root+"/Pictures/ImagePicker/";

		File imageLocation = new File(imageDirectoryPath);
		if(!imageLocation.exists()){
			imageLocation.mkdirs();
		}

		String fileName = "IMG_" + Util.getSystemDate(null).toString();
		File imageFile = new File(imageLocation.getPath(), fileName);
		return imageFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK && data!=null){
			switch (requestCode) {
			case PICK_FROM_CAMERA:
				InputStream stream = null;

				if(bitmap!= null){
					bitmap.recycle();
				}

				try {
					
					
					
					stream = getContentResolver().openInputStream(data.getData());
					bitmap = BitmapFactory.decodeStream(stream);

				} catch (FileNotFoundException e) {
					e.printStackTrace();

				}finally{

					if(stream!=null)
						try {
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}

				break;

			case PICK_FROM_GALLERY:

				break;

			default:
				break;
			}
		}
	}
}
