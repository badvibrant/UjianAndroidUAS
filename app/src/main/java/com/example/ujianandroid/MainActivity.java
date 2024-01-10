package com.example.ujianandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUmur;
    private TextView textViewStatus;
    private Button buttonProses;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen UI
        editTextUmur = findViewById(R.id.editTextUmur);
        textViewStatus = findViewById(R.id.textViewStatus);
        buttonProses = findViewById(R.id.buttonProses);

        // Menambahkan event click listener pada tombol
        buttonProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memeriksa apakah input umur tidak kosong
                String umurString = editTextUmur.getText().toString().trim();
                if (!umurString.isEmpty()) {
                    try {
                        // Mengambil nilai umur dari input
                        int umur = Integer.parseInt(umurString);

                        // Menampilkan status berdasarkan umur
                        if (umur < 10) {
                            textViewStatus.setText("Anak-anak");
                        } else if (umur < 20) {
                            textViewStatus.setText("Remaja");
                        } else if (umur < 40) {
                            textViewStatus.setText("Dewasa");
                        } else {
                            textViewStatus.setText("Tua");
                        }
                    } catch (NumberFormatException e) {
                        // Tangani jika umur tidak dapat diubah menjadi angka
                        textViewStatus.setText("Masukkan umur dengan benar.");
                    }
                } else {
                    // Jika input umur kosong, tampilkan pesan kesalahan
                    textViewStatus.setText("Masukkan umur terlebih dahulu.");
                }
            }
        });

        // Menyimpan dan memulihkan keadaan pada perubahan konfigurasi (rotasi layar)
        if (savedInstanceState != null) {
            String savedStatus = savedInstanceState.getString("status_key", "");
            textViewStatus.setText(savedStatus);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Menyimpan keadaan sebelum konfigurasi berubah
        outState.putString("status_key", textViewStatus.getText().toString());
        super.onSaveInstanceState(outState);
    }
}