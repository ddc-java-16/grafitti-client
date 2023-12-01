package edu.cnm.deepdive.graffiti.controller;

import android.app.Dialog;
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
import edu.cnm.deepdive.graffiti.adapter.CanvasAdapter;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;
import java.util.List;

@AndroidEntryPoint
public class LoadCanvasFragment extends DialogFragment {

  private CanvasViewModel canvasViewModel;
  private AlertDialog dialog;
  private List<Canvas> canvases;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialog = new Builder(requireContext())
        .setTitle("Select Canvas")
        .setAdapter(new CanvasAdapter(requireContext(), List.of()), (dlg, pos) -> {
          Canvas canvas = canvases.get(pos);
          canvasViewModel.fetch(canvas);
        })
        .create();
    canvasViewModel = new ViewModelProvider(requireActivity())
        .get(CanvasViewModel.class);
    canvasViewModel
        .getCanvases()
        .observe(requireActivity(), (canvasList) -> {
              canvases = canvasList;
              dialog.getListView().setAdapter(new CanvasAdapter(requireContext(), canvasList));
            }
        );
    canvasViewModel.fetchAll();
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return getView();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }


}



