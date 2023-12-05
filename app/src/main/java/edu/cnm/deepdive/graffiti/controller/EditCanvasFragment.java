package edu.cnm.deepdive.graffiti.controller;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.graffiti.databinding.FragmentEditCanvasBinding;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;
import org.jetbrains.annotations.NotNull;

@AndroidEntryPoint
public class EditCanvasFragment extends DialogFragment {

  private FragmentEditCanvasBinding binding;
  private AlertDialog dialog;
  private CanvasViewModel canvasViewModel;
  private Canvas canvas;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = FragmentEditCanvasBinding.inflate(getLayoutInflater(), null, false);
    dialog = new Builder(requireContext())
        .setView(binding.getRoot())
        .create();
    canvasViewModel = new ViewModelProvider(requireActivity()).get(CanvasViewModel.class);
    binding.savebutton.setOnClickListener((v) -> {
      canvas = new Canvas();
      canvas.setName(String.valueOf(binding.canvasname.getText()));
      canvasViewModel.add(canvas);
      canvasViewModel.setColor(Color.BLACK);
      canvasViewModel.setStyle(1);

      dialog.dismiss();

    });
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();

  }
}

