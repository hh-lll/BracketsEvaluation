package com.android.example.cameraxbasic.ui.historical;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.example.cameraxbasic.HistoricalDetailActivity;
import com.android.example.cameraxbasic.R;
import com.android.example.cameraxbasic.databinding.FragmentHistoricalBinding;

public class HistorialFragment extends Fragment {

private FragmentHistoricalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentHistoricalBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        initView();
        return root;
    }
    private void initView() {
        final HistoricalItem his_item = binding.hisItem;
//        tiao1.getBackground().setAlpha(0);//注意调的这个R.id.tiao1控件中必须要有android:background="任意选择一个颜色"
        his_item.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.his_item:
                    Intent intent= new Intent(getActivity(), HistoricalDetailActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}