package com.mohamed.barki.latex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class GroupActivity extends Activity 
{
	private int nbrItemGroup;
	ListView listView;
    private ArrayList<ModelGroup> ItemModelList;  
	private CustomAdapterGroup customAdapter;
	private ModelGroup md;
	final Context contextSave = GroupActivity.this;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
		if(Function.getValue(contextSave, "screen").equals("true")){getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);}

		baa = "exitt";
		
		listView = findViewById(R.id.listview_group);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); 
		listView.setStackFromBottom(false);
        ItemModelList = new ArrayList<>();
		customAdapter = new CustomAdapterGroup(contextSave, ItemModelList);  
		listView.setAdapter(customAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener((arg0, view, position, id) -> {
            baa = "exitt";
            Intent intent = new Intent(contextSave, ScreenActivity.class);
            String title = Function.getValue(contextSave, "itemGroupT@"+ position);
            intent.putExtra("itemG", title+"@");
            intent.putExtra("itemP", String.valueOf(position+1));
            startActivity(intent);
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener((arg0, arg1, pos, id) -> {
            baa = "exitt";
            dialogTrueFalse(pos);
            return true;
        });
		findViewById(R.id.btn_add_group).setOnClickListener(v -> {
            baa = "exitt";
            dialogAddItemGroup();
        });
        initialisation();
		final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
		pullToRefresh.setOnRefreshListener(() -> {
            baa = "exitt";
            pullToRefresh.setRefreshing(false);
            refreshListView();
        });
    }
    private void initialisation() {
        if(Function.getValue(contextSave, "nbrItemGroup").isEmpty()){Function.saveFromText(contextSave, "nbrItemGroup", "-1"); nbrItemGroup=-1;}
        else{nbrItemGroup = Integer.parseInt(Function.getValue(contextSave, "nbrItemGroup"));}
        int nbr = -1;
        if(nbrItemGroup>-1){
            for(int i=0; i<=nbrItemGroup; i++){
                if(!Function.getValue(contextSave, "itemGroupT@"+ i).isEmpty()){
                    nbr++;
					String title = Function.getValue(contextSave, "itemGroupT@"+ i);
					Function.saveFromText(contextSave, "itemGroupT@"+ nbr, title);
                    md = new ModelGroup(title);  
                    ItemModelList.add(md);
                    customAdapter.notifyDataSetChanged();
                }
            }
            nbrItemGroup = nbr;
            Function.saveFromText(contextSave, "nbrItemGroup", String.valueOf(nbrItemGroup));
        }

    }
    private void refreshListView(){
		if(Function.getValue(contextSave, "nbrItemGroup").isEmpty()){return;}
        else{nbrItemGroup = Integer.parseInt(Function.getValue(contextSave, "nbrItemGroup"));}
        if(nbrItemGroup == -1){return;}
		int nbr = -1;
        for(int i=0; i<=nbrItemGroup; i++){
			if(!Function.getValue(contextSave, "itemGroupT@"+ i).isEmpty()){
				nbr++;
				String title = Function.getValue(contextSave, "itemGroupT@"+ i);
				Function.saveFromText(contextSave, "itemGroupT@"+ nbr, title);
				md = new ModelGroup(title);  
				ItemModelList.set(nbr, md);
				customAdapter.notifyDataSetChanged();
			}
		}
		nbrItemGroup = nbr;
		Function.saveFromText(contextSave, "nbrItemGroup", String.valueOf(nbrItemGroup));
        //scrollMyListViewToBottom();
	}
	private String baa;
	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		superBackPressed();
	}

	private void superBackPressed() {
        if ("exit".equals(baa)) {//Function.removeAllSaveText(this);
            finishAffinity();
        } else {
            Function.showToastMessage(this, getString(R.string.re_exit));
            baa = "exit";
        }
	}
	
    private void dialogAddItemGroup() {
        final Dialog dialog = new Dialog(contextSave, R.style.DialogStyle);
        dialog.setContentView(R.layout.dialog_modif);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setTitle(this.getString(R.string.group_new));
        dialog.findViewById(R.id.dialog_close).setOnClickListener(v -> {
            dialog.dismiss();
            //deleteFun();
        });
        ((TextView) dialog.findViewById(R.id.dialog_info)).setText(this.getString(R.string.group_new));
        final EditText edit = dialog.findViewById(R.id.dialog_edt);
        edit.setText("");
		edit.setHint(this.getString(R.string.set_group_name));
        edit.setOnEditorActionListener((p1, p2, p3) -> {
            if ((p3 != null && (p3.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (p2 == EditorInfo.IME_ACTION_DONE)) {
                if (Function.validate2(contextSave, edit)) {
                    nbrItemGroup = Integer.parseInt(Function.getValue(contextSave, "nbrItemGroup"));
                    nbrItemGroup++;
                    Function.saveFromText(contextSave, "nbrItemGroup", String.valueOf(nbrItemGroup));
                    Function.hideKeyboard2(contextSave, edit);
                    for(int i=nbrItemGroup; i>=1; i--){
                        String title_old = Function.getValue(contextSave, "itemGroupT@"+ (i - 1));
                        Function.saveFromText(contextSave, "itemGroupT@"+ i, title_old);
                    }
                    String title = edit.getText().toString();
                    Function.saveFromText(contextSave, "itemGroupT@0", title);
                    ModelGroup md = new ModelGroup(title);
                    ItemModelList.add(0, md);
                    customAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
            return false;
        });
        dialog.findViewById(R.id.dialog_ok).setOnClickListener(v -> {
            ////click event
            if (Function.validate2(contextSave, edit)) {
                nbrItemGroup = Integer.parseInt(Function.getValue(contextSave, "nbrItemGroup"));
                nbrItemGroup++;
                Function.saveFromText(contextSave, "nbrItemGroup", String.valueOf(nbrItemGroup));
                Function.hideKeyboard2(contextSave, edit);
                for(int i=nbrItemGroup; i>=1; i--){
                    String title_old = Function.getValue(contextSave, "itemGroupT@"+ (i - 1));
                    Function.saveFromText(contextSave, "itemGroupT@"+ i, title_old);
                }
                String title = edit.getText().toString();
                Function.saveFromText(contextSave, "itemGroupT@0", title);
                ModelGroup md = new ModelGroup(title);
                ItemModelList.add(0, md);
                customAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
	}
    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
		switch (id){
            case R.id.group_help:
				baa = "exitt";
				Function.info(this);
                break;
            case R.id.group_exit:
                superBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void dialogTrueFalse(final int position) {
        String v1 = this.getString(R.string.delete_group_name);
        final Dialog dialog = new Dialog(contextSave, R.style.DialogStyle);
        dialog.setContentView(R.layout.dialog_test_remove);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setTitle(v1);
        ((TextView) dialog.findViewById(R.id.dialog_info)).setText(v1);
        Button btnT = dialog.findViewById(R.id.dialog_yes);
        btnT.setOnClickListener(v -> {
            baa = "exitt";
            int nbrItem = Integer.parseInt(Function.getValue(contextSave, "nbrItemGroup"));
            String numGroup = Function.getValue(contextSave, "itemGroupT@"+ position)+"@";
            Function.removeSaveText(contextSave, "itemGroupT@"+ position);
            int nbr = -1;
            for(int i=0; i<=nbrItem; i++){
                if(!Function.getValue(contextSave, "itemGroupT@"+ i).isEmpty()){
                    nbr++;
                    String title = Function.getValue(contextSave, "itemGroupT@"+ i);
                    Function.saveFromText(contextSave, "itemGroupT@"+ nbr, title);
                }
            }
            nbrItem = nbr;
            Function.saveFromText(contextSave, "nbrItemGroup", String.valueOf(nbrItem));
            //Function.showToastMessage(context, String.valueOf(position));
            ItemModelList.remove(position);
            customAdapter.notifyDataSetChanged();
            if(!Function.getValue(contextSave, "nbrItem@"+numGroup).isEmpty()){
                nbrItem = Integer.parseInt(Function.getValue(contextSave, "nbrItem@"+numGroup));
                Function.removeSaveText(contextSave, "nbrItem@"+numGroup);
                for(int i=0; i<=nbrItem; i++){
                    Function.removeSaveText(contextSave, "item@"+numGroup+ i);
                    Function.removeSaveText(contextSave, "itemT@"+numGroup+ i);
                    Function.removeSaveText(contextSave, "itemE@"+numGroup+ i);
                }
            }
            dialog.dismiss();
        });
        Button btnF = dialog.findViewById(R.id.dialog_no);
        btnF.setOnClickListener(v -> {
            baa = "exitt";
            dialog.dismiss();
        });
        dialog.show();
	}
}
