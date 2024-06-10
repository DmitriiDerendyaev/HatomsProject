package com.example.tinkhatoms.bottomnav.raiting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tinkhatoms.databinding.FragmentProfileBinding;
import com.example.tinkhatoms.databinding.FragmentRaitingBinding;
import com.example.tinkhatoms.users.User;
import com.example.tinkhatoms.users.UsersAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RaitingFragment extends Fragment {
    private FragmentRaitingBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRaitingBinding.inflate(inflater, container, false);
        loadUsers();

        return binding.getRoot();
    }
    private void loadUsers(){
        ArrayList <User> users = new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()){
                    if(userSnapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        continue;
                    }
                    String username = userSnapshot.child("username").getValue().toString();
                    String points = userSnapshot.child("points").getValue().toString();
                    String profileImage = userSnapshot.child("profileImage").getValue().toString();

                    users.add(new User(username, profileImage, points));
                }
                binding.usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.usersRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.usersRv.setAdapter(new UsersAdapter(users));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
