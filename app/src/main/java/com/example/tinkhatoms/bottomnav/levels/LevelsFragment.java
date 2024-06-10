package com.example.tinkhatoms.bottomnav.levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tinkhatoms.R;
import com.example.tinkhatoms.category;

public class LevelsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_levels, container, false);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton level1 = view.findViewById(R.id.level1);
        ImageButton level2 = view.findViewById(R.id.level2);
        ImageButton level3 = view.findViewById(R.id.level3);

        level1.setOnClickListener(v -> openCategoryActivity(1));
        level2.setOnClickListener(v -> openCategoryActivity(2));
        level3.setOnClickListener(v -> openCategoryActivity(3));

        return view;
    }

    private void openCategoryActivity(int level) {
        Intent intent = new Intent(getActivity(), category.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}