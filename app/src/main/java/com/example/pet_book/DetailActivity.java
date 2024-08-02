package com.example.pet_book;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailCategory, detailName, detailAgg, detailBreeding, detailSex, detailColor, detailNote;
    ImageView detailImage;
    Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailCategory = findViewById(R.id.detailCategory);
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailAgg = findViewById(R.id.detailAgg);
        detailBreeding = findViewById(R.id.detailBreeding);
        detailSex = findViewById(R.id.detailSex);
        detailColor = findViewById(R.id.detailColor);
        detailNote = findViewById(R.id.detailNote);
        bookButton = findViewById(R.id.bookButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailCategory.setText(bundle.getString("Category"));
            detailName.setText(bundle.getString("Name"));
            detailAgg.setText(bundle.getString("Age"));
            detailBreeding.setText(bundle.getString("Breed"));
            detailSex.setText(bundle.getString("Sex"));
            detailColor.setText(bundle.getString("Color"));
            detailNote.setText(bundle.getString("Note"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open BookActivity
                Intent intent = new Intent(DetailActivity.this, BookActivity.class);
                // Pass any necessary data to BookActivity using intent.putExtra if needed
                startActivity(intent);
            }
        });
    }
}
