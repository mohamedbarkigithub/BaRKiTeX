package com.mohamed.barki.latex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class CustomAdapterGroup extends BaseAdapter {  
	Context context;  
	ArrayList<ModelGroup> itemModelList;
	public CustomAdapterGroup(Context context, ArrayList<ModelGroup> modelList) {  
		this.context = context;  
		this.itemModelList = modelList;  
	}
	@Override  
	public int getCount() {  
		return itemModelList.size();  
	}  
	@Override  
	public Object getItem(int position) {  
		return itemModelList.get(position);  
	}  
	@Override  
	public long getItemId(int position) {  
		return position;  
	}  
	@SuppressLint({"ViewHolder", "SetTextI18n", "InflateParams"})
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {  
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		convertView = mInflater.inflate(R.layout.dialog_group, null);
		final ModelGroup m = itemModelList.get(position);
		((TextView) convertView.findViewById(R.id.tvNameGroup)).setText(m.getTitle());
		((Button) convertView.findViewById(R.id.tvImageGroup)).setText(String.valueOf(position+1));
		convertView.findViewById(R.id.tvNameGroup).setOnClickListener(v -> {
		final Dialog dialog = new Dialog(context, R.style.DialogStyle);
							dialog.setContentView(R.layout.dialog_modif);
							dialog.setCanceledOnTouchOutside(false);
							dialog.setCancelable(false);
							dialog.setTitle("Set the title");
							dialog.findViewById(R.id.dialog_close).setOnClickListener(v1 -> dialog.dismiss());
							((TextView) dialog.findViewById(R.id.dialog_info)).setText("Edit Section Name");
							final EditText edit = dialog.findViewById(R.id.dialog_edt);
							edit.setText(m.getTitle().toString());
							edit.setHint("set new section name");
							edit.setOnEditorActionListener((p1, p2, p3) -> {
								if ((p3 != null && (p3.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (p2 == EditorInfo.IME_ACTION_DONE)) {
									if (Function.validate2(context, edit)) {
										String numGroup = m.getTitle().toString();
										String new_numGroup = edit.getText().toString();
										if(!Function.getValue(context, "nbrItem@"+numGroup+"@").isEmpty()){
											int nbrItem = Integer.parseInt(Function.getValue(context, "nbrItem@"+numGroup+"@"));
											for(int i=0; i<=nbrItem; i++){
												String formula = Function.getValue(context, "item@"+numGroup+"@"+ i);
												String title = Function.getValue(context, "itemT@"+numGroup+"@"+ i);
												String engine = Function.getValue(context, "itemE@"+numGroup+"@"+ i);
												Function.removeSaveText(context, "item@"+numGroup+"@"+ i);
												Function.removeSaveText(context, "itemT@"+numGroup+"@"+ i);
												Function.removeSaveText(context, "itemE@"+numGroup+"@"+ i);
												Function.saveFromText(context, "item@"+new_numGroup+"@"+ i, formula);
												Function.saveFromText(context, "itemT@"+new_numGroup+"@"+ i, title);
												Function.saveFromText(context, "itemE@"+new_numGroup+"@"+ i, engine);
											}
										}
										Function.saveFromText(context, "itemGroupT@"+ position, new_numGroup);
										if(!Function.getValue(context, "nbrItem@"+numGroup+"@").isEmpty()){
											String nbrItemString = Function.getValue(context, "nbrItem@"+numGroup+"@");
											Function.removeSaveText(context, "nbrItem@"+numGroup+"@");
											Function.saveFromText(context, "nbrItem@"+new_numGroup+"@", nbrItemString);
										}
										ModelGroup m1 = new ModelGroup(new_numGroup);
										itemModelList.set(position, m1);
										notifyDataSetChanged();
										dialog.dismiss();
									}
								}
								return false;
							});
							dialog.findViewById(R.id.dialog_ok).setOnClickListener(v12 -> {
								////click event
								if (Function.validate2(context, edit)) {
									String numGroup = m.getTitle().toString();
									String new_numGroup = edit.getText().toString();
									if(!Function.getValue(context, "nbrItem@"+numGroup+"@").isEmpty()){
										int nbrItem = Integer.parseInt(Function.getValue(context, "nbrItem@"+numGroup+"@"));
										for(int i=0; i<=nbrItem; i++){
											String formula = Function.getValue(context, "item@"+numGroup+"@"+ i);
											String title = Function.getValue(context, "itemT@"+numGroup+"@"+ i);
											String engine = Function.getValue(context, "itemE@"+numGroup+"@"+ i);
											Function.removeSaveText(context, "item@"+numGroup+"@"+ i);
											Function.removeSaveText(context, "itemT@"+numGroup+"@"+ i);
											Function.removeSaveText(context, "itemE@"+numGroup+"@"+ i);
											Function.saveFromText(context, "item@"+new_numGroup+"@"+ i, formula);
											Function.saveFromText(context, "itemT@"+new_numGroup+"@"+ i, title);
											Function.saveFromText(context, "itemE@"+new_numGroup+"@"+ i, engine);
										}
									}
									Function.saveFromText(context, "itemGroupT@"+ position, new_numGroup);
									if(!Function.getValue(context, "nbrItem@"+numGroup+"@").isEmpty()){
										String nbrItemString = Function.getValue(context, "nbrItem@"+numGroup+"@");
										Function.removeSaveText(context, "nbrItem@"+numGroup+"@");
										Function.saveFromText(context, "nbrItem@"+new_numGroup+"@", nbrItemString);
									}
									ModelGroup m12 = new ModelGroup(new_numGroup);
									itemModelList.set(position, m12);
									notifyDataSetChanged();
									dialog.dismiss();
								}
							});
							dialog.show();
		});
		return convertView;
	}  
}  
