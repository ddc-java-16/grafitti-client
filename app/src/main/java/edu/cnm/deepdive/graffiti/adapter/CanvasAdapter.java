package edu.cnm.deepdive.graffiti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.graffiti.model.Canvas;
import java.util.List;

public class CanvasAdapter extends ArrayAdapter<Canvas> {

  private final List<Canvas> canvases;
  private final LayoutInflater inflater;


  public CanvasAdapter(Context context, List<Canvas> canvases) {
    super(context, android.R.layout.simple_list_item_1, canvases);
   this.canvases = canvases;
   this.inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
     TextView view = (TextView) ((convertView != null)
              ? convertView
              : inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
     Canvas canvas = getItem(position);
     view.setText(canvas.getName());
     return view;
  }
}
