package edu.cnm.deepdive.graffiti.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.skydoves.colorpickerview.listeners.ColorListener;
import edu.cnm.deepdive.graffiti.R;
import edu.cnm.deepdive.graffiti.databinding.FragmentColorPickerBinding;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;

public class ColorPickerFragment extends Fragment {

  int styleValue, checked;
  private FragmentColorPickerBinding binding;
  private CanvasViewModel viewModel;


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(CanvasViewModel.class);

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = FragmentColorPickerBinding.inflate(getLayoutInflater());
    binding.round.setChecked(true);
    binding.radioGrup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        checked = checkedId;
        if (R.id.round == checkedId) {
          //viewModel.setStyle(1);
          styleValue = 1;

        } else if (R.id.star == checkedId) {
          //viewModel.setStyle(2);
          styleValue = 2;
        } else {
          //viewModel.setStyle(3);
          styleValue = 3;
        }
      }
    });
    binding.apply.setOnClickListener((v) -> {
      viewModel.setStroke((int) binding.stroke.getValue());
      if(checked == 0){
        viewModel.setStyle(1);
      } else {
        viewModel.setStyle(styleValue);
      }
      getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    });

    binding.colorpicker.setColorListener(new ColorListener() {
      @Override
      public void onColorSelected(int color, boolean fromUser) {
        viewModel.setColor(color);
      }
    });

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return binding.getRoot();
  }
}
