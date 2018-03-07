package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import pl.fream.evryplace.androidmodule.JokeActivity;
import pl.fream.evryplace.jmodule.JModule;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root.findViewById(R.id.tell_joke_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });
        root.findViewById(R.id.tell_joke_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJokeActivity();
            }
        });
        root.findViewById(R.id.tell_joke_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJokeAsync();
            }
        });
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void tellJoke() {
        JModule module = new JModule();
        Toast.makeText(getActivity(), module.provideJoke(), Toast.LENGTH_LONG).show();
    }

    public void tellJokeActivity() {
        JModule module = new JModule();
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, module.provideJoke());
        getActivity().startActivity(intent);
    }

    public void tellJokeAsync() {
        EndpointAsyncTask task = new EndpointAsyncTask(new EndpointAsyncTask.EndpointCallback() {
            @Override
            public void onStringFetched(String result) {
                if (result != null) {
                    Intent intent = new Intent(getActivity(), JokeActivity.class);
                    intent.putExtra(JokeActivity.EXTRA_JOKE, result);
                    getActivity().startActivity(intent);
                }
            }
        });
        task.execute();
    }
}
