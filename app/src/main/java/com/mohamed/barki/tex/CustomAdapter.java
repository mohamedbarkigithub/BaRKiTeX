package com.mohamed.barki.tex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mohamed.barki.tex.latex.activities.EditorActivity;
import com.mohamed.barki.tex.mathview.KatexView;
import com.mohamed.barki.tex.mathview.MathJaxView;

import java.util.ArrayList;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class CustomAdapter extends BaseAdapter {  
	Context context;
	ArrayList<Model> itemModelList;
	TextView tvNumFormula;
	String posGroup;
	public CustomAdapter(Context context, ArrayList<Model> modelList, TextView tvNumFormula, String posGroup) {  
		this.context = context;  
		this.itemModelList = modelList;
		this.tvNumFormula = tvNumFormula;
		this.posGroup = posGroup;
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
		convertView = mInflater.inflate(R.layout.dialog, null);
		final LinearLayout lny_katex = convertView.findViewById(R.id.layout_formula_view);
		final LinearLayout lny_mathjax = convertView.findViewById(R.id.layout_formula_view_mathjax);
		final LinearLayout lny_tex = convertView.findViewById(R.id.layout_formula_view_tex);
		final LayoutParams paramMW = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final LayoutParams paramM0 = new LayoutParams(LayoutParams.MATCH_PARENT, 0);

		Model m = itemModelList.get(position);
		final String title = m.getTitle().toString();
		final String formula = m.getFormula().toString();
		final String engine = m.getEngine().toString();
		final String numGroup = m.getGroup().toString();
		final int nbrItem = Integer.parseInt(Function.getValue(context, "nbrItem@"+numGroup));
		((TextView) convertView.findViewById(R.id.tvNameItem)).setText(title);
		((Button) convertView.findViewById(R.id.tvImageItem)).setText(String.valueOf(position+1));
		tvNumFormula.setText(nbrItem + 1 +" formula");
		String newFormula = formula, cmnt;
		int i=0, j;
		while(i<formula.length()){
			if(formula.charAt(i) == '%'){
				j=i;
				while(j<formula.length()){
					if(formula.charAt(j) == '\n' || j==formula.length()-1){
						cmnt = formula.substring(i,j+1);
						newFormula = newFormula.replace(cmnt, "");
						i=j+1;
						break;
					}else{j++;}
				}
			}else{i++;}
		}

		((KatexView) convertView.findViewById(R.id.formula_view)).setDisplayText(newFormula);
		((MathJaxView) convertView.findViewById(R.id.formula_view_mathjax)).setText(newFormula);
		((TextView) convertView.findViewById(R.id.formula_view_tex)).setText(newFormula);

		final Button btn = convertView.findViewById(R.id.view_change);
		btn.setText(engine);
		switch(engine){
			case "MathV2":
				lny_katex.setLayoutParams(paramM0);
				lny_mathjax.setLayoutParams(paramMW);
				lny_tex.setLayoutParams(paramM0);
				break;
			case "Text":
				lny_katex.setLayoutParams(paramM0);
				lny_mathjax.setLayoutParams(paramM0);
				lny_tex.setLayoutParams(paramMW);
				break;
			default :
				lny_katex.setLayoutParams(paramMW);
				lny_mathjax.setLayoutParams(paramM0);
				lny_tex.setLayoutParams(paramM0);
		}
		convertView.findViewById(R.id.view_change).setOnClickListener(v -> {
							switch(btn.getText().toString()){
								case "MathV2": btn.setText("Text");
									Function.saveFromText(context, "itemE@"+numGroup+ position, "Text");
									lny_katex.setLayoutParams(paramM0);
									lny_mathjax.setLayoutParams(paramM0);
									lny_tex.setLayoutParams(paramMW);
									break;
								case "Text": btn.setText("MathV1");
									Function.saveFromText(context, "itemE@"+numGroup+ position, "MathV1");
									lny_katex.setLayoutParams(paramMW);
									lny_mathjax.setLayoutParams(paramM0);
									lny_tex.setLayoutParams(paramM0);
									break;
								default : btn.setText("MathV2");
									Function.saveFromText(context, "itemE@"+numGroup+ position, "MathV2");
									lny_katex.setLayoutParams(paramM0);
									lny_mathjax.setLayoutParams(paramMW);
									lny_tex.setLayoutParams(paramM0);
							}
		});
		convertView.findViewById(R.id.imgRemove).setOnClickListener(v -> dialogTrueFalse(position, numGroup));
		convertView.findViewById(R.id.copy).setOnClickListener(v -> Function.doCopy(context, formula));
		convertView.findViewById(R.id.modif).setOnClickListener(v -> {
			Intent intent = new Intent(context, EditorActivity.class);
			intent.putExtra("itemA",String.valueOf(position));
			String formula12 = Function.getValue(context, "item@"+numGroup+ position);
			String title12 = Function.getValue(context, "itemT@"+numGroup+ position);
			String engine12 = Function.getValue(context, "itemE@"+numGroup+ position);
			intent.putExtra("itemT", title12);
			intent.putExtra("itemF", formula12);
			intent.putExtra("itemE", engine12);
			intent.putExtra("itemG",numGroup);
			intent.putExtra("itemP",posGroup);
			context.startActivity(intent);
		});
		convertView.findViewById(R.id.tvNameItem).setOnClickListener(v -> {
		final Dialog dialog = new Dialog(context, R.style.DialogStyle);
							dialog.setContentView(R.layout.dialog_modif);
							dialog.setCanceledOnTouchOutside(false);
							dialog.setCancelable(false);
							dialog.setTitle("Set the title");
							dialog.findViewById(R.id.dialog_close).setOnClickListener(v1 -> dialog.dismiss());
							((TextView) dialog.findViewById(R.id.dialog_info)).setText("Edit Formula Name");
							final EditText edit = dialog.findViewById(R.id.dialog_edt);
							edit.setText(title);
							edit.setHint("set new formula name");
							edit.setOnEditorActionListener((p1, p2, p3) -> {
								if ((p3 != null && (p3.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (p2 == EditorInfo.IME_ACTION_DONE)) {
									if (Function.validateL2(context, edit, numGroup)) {
										String formula1 = Function.getValue(context, "item@"+numGroup+ position);
										String title1 = edit.getText().toString();
										String engine1 = Function.getValue(context, "itemE@"+numGroup+ position);
										Function.saveFromText(context, "itemT@"+numGroup+ position, title1);
										Model m1 = new Model(title1, formula1, engine1, numGroup);
										itemModelList.set(position, m1);
										notifyDataSetChanged();
										dialog.dismiss();
									}
								}
								return false;
							});
							dialog.findViewById(R.id.dialog_ok).setOnClickListener(v12 -> {
								////click event
								if (Function.validateL2(context, edit, numGroup)) {
									String formula13 = Function.getValue(context, "item@"+numGroup+ position);
									String title13 = edit.getText().toString();
									String engine13 = Function.getValue(context, "itemE@"+numGroup+ position);
									Function.saveFromText(context, "itemT@"+numGroup+ position, title13);
									Model m12 = new Model(title13, formula13, engine13, numGroup);
									itemModelList.set(position, m12);
									notifyDataSetChanged();
									dialog.dismiss();
								}
							});
							dialog.show();
		});

		return convertView;
	}
	private void dialogTrueFalse(final int position, final String numGroup) {
        String v1 = "Do you really want to delete this formula?";
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setContentView(R.layout.dialog_test_remove);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setTitle(v1);
        ((TextView) dialog.findViewById(R.id.dialog_info)).setText(v1);
        Button btnT = dialog.findViewById(R.id.dialog_yes);
        btnT.setOnClickListener(v -> {
			int nbrItem = Integer.parseInt(Function.getValue(context, "nbrItem@"+numGroup));
			Function.removeSaveText(context, "item@"+numGroup+ position);
			Function.removeSaveText(context, "itemT@"+numGroup+ position);
			Function.removeSaveText(context, "itemE@"+numGroup+ position);
			int nbr = -1;
			for(int i=0; i<=nbrItem; i++){
				if(!Function.getValue(context, "item@"+numGroup+ i).isEmpty()){
					nbr++;
					String formula = Function.getValue(context, "item@"+numGroup+ i);
					String title = Function.getValue(context, "itemT@"+numGroup+ i);
					String engine = Function.getValue(context, "itemE@"+numGroup+ i);
					Function.saveFromText(context, "item@"+numGroup+ nbr, formula);
					Function.saveFromText(context, "itemT@"+numGroup+ nbr, title);
					Function.saveFromText(context, "itemE@"+numGroup+ nbr, engine);
				}
			}
			nbrItem = nbr;
			Function.saveFromText(context, "nbrItem@"+numGroup, String.valueOf(nbrItem));
			itemModelList.remove(position);
			notifyDataSetChanged();
			dialog.dismiss();
		});
        Button btnF = dialog.findViewById(R.id.dialog_no);
        btnF.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
	}
}  
