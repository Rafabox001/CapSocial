package com.example.rafaeldomingo.testapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaeldomingo.testapp.data.ItemData;
import com.github.clans.fab.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import de.svenjacobs.loremipsum.LoremIpsum;

public class DetallePromocion extends Activity implements Serializable, View.OnClickListener{
    private LoremIpsum loremImpsum;
    private  StringBuilder stringBuilder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destalle_promcion);
        loremImpsum = new LoremIpsum();

        ItemData itemData = (ItemData) getIntent().getSerializableExtra("data");
        TextView tvDes = (TextView) findViewById(R.id.description);
        TextView tvTitle = (TextView) findViewById(R.id.title);
        TextView tvPromo = (TextView) findViewById(R.id.promo);
        ImageView imageView = (ImageView) findViewById(R.id.header);

        FloatingActionButton email = (FloatingActionButton) findViewById(R.id.floating_email);
        email.setOnClickListener(this);
        FloatingActionButton facebook = (FloatingActionButton) findViewById(R.id.floating_facebook);
        facebook.setOnClickListener(this);
        FloatingActionButton whatsapp = (FloatingActionButton) findViewById(R.id.floating_whatsapp);
        whatsapp.setOnClickListener(this);
        FloatingActionButton twitter = (FloatingActionButton) findViewById(R.id.floating_twitter);
        twitter.setOnClickListener(this);
        Bitmap img = BitmapFactory.decodeByteArray(itemData.getImage(),0,itemData.getImage().length);
        int width = img.getWidth();
        int height = img.getHeight();
        imageView.setMaxWidth(width);
        imageView.setMaxHeight(height);
        imageView.setImageBitmap(img);

        tvPromo.setText(itemData.getDescription());
        tvTitle.setText(itemData.getTitle());
        tvDes.setText(loremImpsum.getWords());
        stringBuilder = new StringBuilder();
        stringBuilder.append(tvTitle.getText());
        stringBuilder.append(tvPromo.getText());
        stringBuilder.append(tvDes.getText());





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_email:

                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                email.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                startActivity(Intent.createChooser(email, "Send Email"));
                break;

            case R.id.floating_facebook:
                Intent shareFacebook = new Intent(Intent.ACTION_SEND);
                shareFacebook.setType("text/plain");
                shareFacebook.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                PackageManager pm = v.getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareFacebook, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareFacebook.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareFacebook.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareFacebook.setComponent(name);
                        v.getContext().startActivity(shareFacebook);
                        break;
                    }
                }
                break;
            case R.id.floating_whatsapp:
                Intent shareWhatsapp = new Intent(Intent.ACTION_SEND);
                shareWhatsapp.setType("text/plain");
                shareWhatsapp.setPackage("com.whatsapp");
                shareWhatsapp.putExtra(Intent.EXTRA_TEXT,stringBuilder.toString() );
                try {
                    this.startActivity(shareWhatsapp);
                } catch (android.content.ActivityNotFoundException ex) {
                    System.out.println("Whatsapp have not been installed.");
                }
                break;

            case R.id.floating_twitter:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, stringBuilder.toString());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                startActivity(Intent.createChooser(sharingIntent,"Share"));
                break;


        }

    }
}
