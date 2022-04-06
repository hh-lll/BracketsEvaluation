package com.android.example.cameraxbasic.ui.photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.example.cameraxbasic.CameraActivity;
import com.android.example.cameraxbasic.databinding.FragmentPhotoBinding;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class PhotoFragment extends Fragment {

private FragmentPhotoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPhotoBinding.inflate(inflater, container, false);

        initview();
        View root = binding.getRoot();
        return root;
    }
    private void initview(){
        View root = binding.getRoot();
        root.getBackground().setAlpha(50);//0~255透明度值 0：全透明；255不透明
        final TextView detail1 = binding.detail1;
        detail1.setText("\u3000\u3000"+"本项目针对传统托槽粘接教学训练中“训练教具种类单一、评估指标依赖主观、评估方式耗时耗力”三大痛点，研发具备“训练病例多元化、评分体系标准化、评估流程自动化”特点的托槽粘接教学训练系统，以期突破传统教学模式限制，推动口腔医学教育改革发展。");
        QMUIRoundButton jump_to_camera = binding.jumpToCamera;
        jump_to_camera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}