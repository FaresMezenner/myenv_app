package com.faresmezenner.hachathon;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main.
     */
    // TODO: Rename and change types and number of parameters
    public static Main newInstance(String param1, String param2) {
        Main fragment = new Main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView feed = view.findViewById(R.id.feed);
        ArrayList<Post> Posts = new ArrayList<>();


        String user[] = new String[]{"Malek Mezenner", "Mohammed Haddad", "Kamel Akab", "Yacine Hadri"};
        String rank[] =new String[]{"5000", "3000", "200", "1000", "1500"};
        String rate[] = new String[]{"70% completed","30% completed","10% completed", "00% completed"   };
        String title[] = new String[]{"Trash near mosque", "Broken stop sign", "Leaking tube", "Broken road"};
        String description[] = new String[]{"There's a trash dumpster that has been flipped over near the mosque, now there's trash all over the ground, anyone can help?",
                                            "The stop sign at the exit is broken and this can lead to accidents, please reach it out to authorities ASAP",
                                            "A water tube is leaking and ruining the garden, if any one can fix it, please do it",
                                            "The road at the entry is broken and can lead to accidents, can you fix it or help us notify the authorities? thank you"};

        String[] userPfpPath = new String[]{"@drawable/user0", "@drawable/user1", "@drawable/user2", "@drawable/user3"};
        String[] postPicPath = new String[]{"@drawable/trash", "@drawable/damaged_stop_sign", "@drawable/water_leak", "@drawable/broken_road"};
        for(int i =0; i<4;i++){
            int userPfpId = getResources().getIdentifier(userPfpPath[i], null, getClass().getPackage().getName());
            Drawable userPfp = getResources().getDrawable(userPfpId, null);
            int postPicId = getResources().getIdentifier(postPicPath[i], null, getClass().getPackage().getName());
            Drawable postPic = getResources().getDrawable(postPicId, null);

            Posts.add(new Post(user[i], rank[i], rate[i], title[i], description[i], postPic, userPfp));


        }
        PostAdapter adapter = new PostAdapter(getContext(), Posts);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        feed.setAdapter(adapter);
        feed.setLayoutManager(manager);


    }
}