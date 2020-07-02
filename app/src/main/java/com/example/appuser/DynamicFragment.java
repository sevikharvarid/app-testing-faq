package com.example.appuser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DynamicFragment extends Fragment {

    private ListView mainListView;
    private FirebaseAuth auth;
    private String desc,tilt,cat;
    private HomeFragment2 homeFragment2;
    private int page;
    private String title;


    public static DynamicFragment newInstance(int page, String title) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);

        mainListView = view.findViewById(R.id.question_listview);
        //String searchText = getArguments().getString("data");
        //Log.e("String : ",searchText);
        /*if(getArguments()!=null){
            String searchText = getArguments().getString("data");
            initViews(searchText);
        }*/
        Log.e("String : ", title);
         initViews("Teknologi");
        //Log.e("getArgs : ",searchText);
       /* final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Question");
        final DatabaseReference db = dbRef.child(Objects.requireNonNull(auth.getUid()));
        //Query firebaseSearchView = db.orderByChild("category").startAt(searchText).endAt(searchText+ "\uf8ff");
        Query firebaseSearchView = db.orderByChild("category").startAt(searchText).endAt(searchText+ "\uf8ff");
        final FirebaseListAdapter<Question> firebaseListAdapter = new FirebaseListAdapter<Question>(
                this.getActivity(),
                Question.class,
                R.layout.question_layout,
                //dbRef.child(auth.getUid())
                firebaseSearchView
        ) {
            @Override
            protected void populateView(final View v, final Question model, int position) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                TextView questionTitle = v.findViewById(R.id.question_title);
                TextView questionAuthor = v.findViewById(R.id.question_author);

                questionTitle.setText(model.getDescription());
                questionAuthor.setText("by "+ currentUser.getEmail());
                desc = model.getDescription();
                tilt = model.getTitle();
                cat = model.getCategory();
            }
        };

        mainListView.setAdapter(firebaseListAdapter);

*/
      /*  FirebaseListAdapter<Question> firebaseListAdapter = new FirebaseListAdapter<Question>(
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
        */
        return view;

    }
    private void init(){
        auth = FirebaseAuth.getInstance();
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

                Log.e("Category :",model.getCategory());
                Log.e("Title :",model.getTitle());
                Log.e("Description :",model.getDescription());
            }
        };

        mainListView.setAdapter(firebaseListAdapter);

    }

    private void initViews(String searchText) {

        //TextView textView=view.findViewById(R.id.commonTextView);
        //textView.setText(String.valueOf("Category :  "+getArguments().getInt("position")));
        auth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Question");
        final DatabaseReference db = dbRef.child(Objects.requireNonNull(auth.getUid()));
        //Query firebaseSearchView = db.orderByChild("category").startAt(searchText).endAt(searchText+ "\uf8ff");
        Query firebaseSearchView = db.orderByChild("category").startAt(searchText).endAt(searchText+ "\uf8ff");
        final FirebaseListAdapter<Question> firebaseListAdapter = new FirebaseListAdapter<Question>(
                this.getActivity(),
                Question.class,
                R.layout.question_layout,
                //dbRef.child(auth.getUid())
                firebaseSearchView
        ) {
            @Override
            protected void populateView(final View v, final Question model, int position) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                TextView questionTitle = v.findViewById(R.id.question_title);
                TextView questionAuthor = v.findViewById(R.id.question_author);

                questionTitle.setText(model.getDescription());
                questionAuthor.setText("by "+ currentUser.getEmail());
                desc = model.getDescription();
                tilt = model.getTitle();
                cat = model.getCategory();
            }
        };

        mainListView.setAdapter(firebaseListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
