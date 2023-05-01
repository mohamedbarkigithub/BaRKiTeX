package com.mohamed.barki.latex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohamed.barki.latex.latex.activities.EditorActivity;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class ScreenActivity extends Activity implements OnClickListener
{
	private int nbrItem;
	ListView listView;
    private ArrayList<Model> ItemModelList;  
	private CustomAdapter customAdapter;
	private Model md;
    private String numGroup, posGroup;  
	final Context contextSave = ScreenActivity.this;

	private String baa;
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
	}
    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);
		if(Function.getValue(contextSave, "screen").equals("true")){getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);}

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
			findViewById(R.id.editor_action_bar).setVisibility(View.VISIBLE);
		}

		Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null){
            numGroup =(String) b.get("itemG");
			posGroup =(String) b.get("itemP");
        }
		
		baa = "exitt";
		
		listView = findViewById(R.id.listview);
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); 
		listView.setStackFromBottom(false);
        findViewById(R.id.btn_add).setOnClickListener(v -> {
			baa = "exitt";
			dialogAddItem();
		});
        ItemModelList = new ArrayList<>();
		customAdapter = new CustomAdapter(contextSave, ItemModelList, findViewById(R.id.tvNumItemGroup), posGroup);
		listView.setAdapter(customAdapter);
        
        ((TextView) findViewById(R.id.tvNameItemGroup)).setText(numGroup.substring(0, numGroup.length()-1));
		((Button) findViewById(R.id.tvImageItemGroup)).setText(posGroup);
        initialisation();
		final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
		pullToRefresh.setOnRefreshListener(() -> {
			pullToRefresh.setRefreshing(false);
			baa = "exitt";
			refreshListView();
		});
    }
    @SuppressLint("SuspiciousIndentation")
    private void initialisation() {
        if(Function.getValue(contextSave, "nbrItem@"+numGroup).isEmpty()){Function.saveFromText(contextSave, "nbrItem@"+numGroup, "-1"); nbrItem=-1;}
        else{nbrItem = Integer.parseInt(Function.getValue(contextSave, "nbrItem@"+numGroup));}
        int nbr = -1;
		if(nbrItem>-1){
            for(int i=0; i<=nbrItem; i++){
                if(!Function.getValue(contextSave, "itemT@"+numGroup+ i).isEmpty()){
                    nbr++;
					String formula = Function.getValue(contextSave, "item@"+numGroup+ i);
					String title = Function.getValue(contextSave, "itemT@"+numGroup+ i);
					String engine = Function.getValue(contextSave, "itemE@"+numGroup+ i);
					Function.saveFromText(contextSave, "item@"+numGroup+ nbr, formula);
                    Function.saveFromText(contextSave, "itemT@"+numGroup+ nbr, title);
					Function.saveFromText(contextSave, "itemE@"+numGroup+ nbr, engine);
					md = new Model(title, formula, engine, numGroup);  
                    ItemModelList.add(md);
                    customAdapter.notifyDataSetChanged();
                }
            }
            nbrItem = nbr;
            Function.saveFromText(contextSave, "nbrItem@"+numGroup, String.valueOf(nbrItem));
        }
	}
    private void refreshListView(){
		if(Function.getValue(contextSave, "nbrItem@"+numGroup).isEmpty()){return;}
        else{nbrItem = Integer.parseInt(Function.getValue(contextSave, "nbrItem@"+numGroup));}
        if(nbrItem == -1){return;}
		int nbr = -1;
        for(int i=0; i<=nbrItem; i++){
			if(!Function.getValue(contextSave, "itemT@"+numGroup+ i).isEmpty()){
				nbr++;
				String formula = Function.getValue(contextSave, "item@"+numGroup+ i);
				String title = Function.getValue(contextSave, "itemT@"+numGroup+ i);
				String engine = Function.getValue(contextSave, "itemE@"+numGroup+ i);
				Function.saveFromText(contextSave, "item@"+numGroup+ nbr, formula);
				Function.saveFromText(contextSave, "itemT@"+numGroup+ nbr, title);
				Function.saveFromText(contextSave, "itemE@"+numGroup+ nbr, engine);
				md = new Model(title, formula, engine, numGroup);  
				ItemModelList.set(nbr, md);
				customAdapter.notifyDataSetChanged();
			}
		}
		nbrItem = nbr;
		Function.saveFromText(contextSave, "nbrItem@"+numGroup, String.valueOf(nbrItem));
	}
	@SuppressLint("SuspiciousIndentation")
    private void dialogAddItem() {
		String nbrItemString = Function.getValue(contextSave, "nbrItem@"+numGroup);
		nbrItem = Integer.parseInt(nbrItemString);
        nbrItem++;
        String title = "@new";
        String engine = "MathV1";
		String formula = "%%% "+this.getString(R.string.this_is_a_new_formula)+"\n"+
		    "%% "+this.getString(R.string.section)+" = "+numGroup.substring(0, numGroup.length()-1) +"\n"+
			"%% "+this.getString(R.string.section_num)+" = "+posGroup+"\n"+
		    "% "+this.getString(R.string.formula_num)+" = "+ (nbrItem + 1) +"\n";
        Intent intent = new Intent(contextSave, EditorActivity.class);
        intent.putExtra("itemA",String.valueOf(nbrItem)); 
		intent.putExtra("itemT",title); 
		intent.putExtra("itemF",formula);
		intent.putExtra("itemE",engine); 
        intent.putExtra("itemG",numGroup); 
        intent.putExtra("itemP",posGroup); 
		startActivity(intent);
	}
    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_item, menu);
        this.menu = menu;
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_exit) {
            returnToGroup();
        }
        return super.onOptionsItemSelected(item);
    }
    private void returnToGroup() {
		if ("exit".equals(baa)) {
			Intent intent = new Intent(contextSave, GroupActivity.class);
			startActivity(intent);
		} else {
			Function.showToastMessage(this, getString(R.string.re_group));
			baa = "exit";
		}
    }
	@Override
    public void onBackPressed(){
		returnToGroup();
	}
}
