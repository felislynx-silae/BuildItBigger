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


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    public void tellJoke(View view) {
        JModule module = new JModule();
        Toast.makeText(getActivity(), module.provideJoke(), Toast.LENGTH_LONG).show();
    }

    public void tellJokeActivity(View view) {
        JModule module = new JModule();
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, module.provideJoke());
        getActivity().startActivity(intent);
    }

    public void tellJokeAsync(View view) {
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
