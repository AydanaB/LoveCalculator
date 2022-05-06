package com.example.lovecalculator.fragment;



import com.example.lovecalculator.R;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;

import com.example.App;
import com.example.lovecalculator.base.BaseFragment;
import com.example.lovecalculator.data.LoveModel;
import com.example.lovecalculator.databinding.FragmentCalculatorBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculatorFragment extends BaseFragment<FragmentCalculatorBinding> {

    String firstName;
    String secondName;
    String HOST = "love-calculator.p.rapidapi.com";
    String KEY = "4b611d4cb3mshe259d9300b187c6p117197jsnd0480f4bd455";
    NavController navController;

    @Override
    public FragmentCalculatorBinding getBinding() {
        return FragmentCalculatorBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = binding.firstName.getText().toString();
                secondName = binding.secondName.getText().toString();
                App.api.getLove(firstName,
                        secondName, HOST, KEY).enqueue(new Callback<LoveModel>() {
                    @Override
                    public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("key", response.body());
                        navController.navigate(R.id.resultFragment, bundle);
                    }

                    @Override
                    public void onFailure(Call<LoveModel> call, Throwable t) {
                    }
                });
            }
        });
    }
}