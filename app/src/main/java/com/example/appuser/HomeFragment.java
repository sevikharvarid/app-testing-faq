package com.example.appuser;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<Question> questionList = new ArrayList<>();

//    private RecyclerView recyclerView;
//    private FirebaseRecyclerAdapter adapter;
//    private LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth;
    private String desc,tilt,cat;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView mainListView = view.findViewById(R.id.question_listview);
        auth = FirebaseAuth.getInstance();

  //      final FirebaseDatabase database = FirebaseDatabase.getInstance();

//        DatabaseReference dbRef = database.getReference("posts");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference dbRef = database.getReference("Question");
        FirebaseListAdapter<Question> firebaseListAdapter = new FirebaseListAdapter<Question>(
                this.getActivity(),
                Question.class,
                R.layout.question_layout,
                dbRef.child(auth.getUid())
        ) {
            @Override
            protected void populateView(View v, Question model, int position) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                TextView questionTitle = v.findViewById(R.id.question_title);
                TextView questionAuthor = v.findViewById(R.id.question_author);
             //   TextView questionViews = v.findViewById(R.id.views);

                questionTitle.setText(model.getDescription());
                questionAuthor.setText("by "+ currentUser.getEmail());
               // questionViews.setText(String.valueOf(model.getViews()));
                desc = model.getDescription();
                tilt = model.getTitle();
                cat = model.getCategory();

            }
        };

        mainListView.setAdapter(firebaseListAdapter);
/*
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("posts").child(String.valueOf(i));

                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int viewCount = Integer.parseInt(dataSnapshot.child("views").getValue().toString());
                        dbRef.child("views").setValue(viewCount+1);
                        Intent viewQuestion = new Intent(getActivity(), ViewQuestion.class);
                        viewQuestion.putExtra("question_id", Integer.parseInt(dataSnapshot.child("id").getValue().toString()));
                        startActivity(viewQuestion);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });*/
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("dataCategory", cat);
                bundle.putString("dataTitle", tilt);
                bundle.putString("dataDescription", desc);
                //bundle.putString("getPrimaryKey", listQuestion.get(position).getKey());
                Intent intent = new Intent(view.getContext(), ViewQuestion.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


}
