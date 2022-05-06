package com.example.lovecalculator.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovecalculator.R;
import com.example.lovecalculator.base.BaseFragment;
import com.example.lovecalculator.data.LoveModel;
import com.example.lovecalculator.databinding.FragmentCalculatorBinding;
import com.example.lovecalculator.databinding.FragmentResultBinding;

public class ResultFragment extends BaseFragment<FragmentResultBinding> {

    NavController navController;

    @Override
    public FragmentResultBinding getBinding() {
        return FragmentResultBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        LoveModel loveModel = (LoveModel)getArguments().getSerializable("key");
        binding.txtResultFName.setText(loveModel.getFirstName().toString());
        binding.txtResultSName.setText(loveModel.getSecondName().toString());
        binding.txtPer.setText(loveModel.getPercentage().toString());
        binding.txtResult.setText(loveModel.getResult().toString());

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
    }
}