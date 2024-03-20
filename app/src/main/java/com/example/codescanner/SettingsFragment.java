package com.example.codescanner;

import static androidx.core.content.ContextCompat.getColor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        SeekBar sbWidth = view.findViewById(R.id.sbWidth);
        SeekBar sbHeight = view.findViewById(R.id.sbHeight);
        TextView tvWidthPixels = view.findViewById(R.id.tvWidthPixels);
        TextView tvHeightPixels = view.findViewById(R.id.tvHeightPixels);
        Spinner spMode = view.findViewById(R.id.spMode);

        assert getArguments() != null;
        int previousModePosition = getArguments().getInt("MODE_POSITION", 0);
        spMode.setSelection(previousModePosition);
        int codeWidth = getArguments().getInt("CODE_WIDTH");
        int codeHeight = getArguments().getInt("CODE_HEIGHT");
        sbWidth.setProgress(codeWidth);
        sbHeight.setProgress(codeHeight);
        tvWidthPixels.setText(MessageFormat.format("{0} px", codeWidth));
        tvHeightPixels.setText(MessageFormat.format("{0} px", codeHeight));

        spMode.setTag("First");
        spMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    ((TextView) parent.getChildAt(i)).setTextColor(getColor(requireContext(),R.color.mainText));
                }

                if(spMode.getTag().equals("First")){
                    spMode.setTag("Not first");
                    return;
                }

                String mode = spMode.getSelectedItem().toString();
                switch (mode){
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    default:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                }

                Bundle bundle = new Bundle();
                bundle.putInt("MODE_POSITION", position);
                getParentFragmentManager().setFragmentResult("MODE_POSITION_CHANGED", bundle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sbWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String text = progress + " px";
                tvWidthPixels.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Bundle bundle = new Bundle();
                bundle.putInt("CODE_WIDTH", seekBar.getProgress());
                bundle.putInt("CODE_HEIGHT", -1);
                getParentFragmentManager().setFragmentResult("CODE_DIMENSIONS_CHANGED", bundle);
            }
        });

        sbHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String text = progress + " px";
                tvHeightPixels.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Bundle bundle = new Bundle();
                bundle.putInt("CODE_HEIGHT", seekBar.getProgress());
                bundle.putInt("CODE_WIDTH", -1);
                getParentFragmentManager().setFragmentResult("CODE_DIMENSIONS_CHANGED", bundle);
            }
        });
        return view;
    }
}