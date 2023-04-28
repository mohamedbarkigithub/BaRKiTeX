package com.mohamed.barki.tex.latex.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mohamed.barki.tex.Function;
import com.mohamed.barki.tex.IntActivity;
import com.mohamed.barki.tex.R;
import com.mohamed.barki.tex.ScreenActivity;
import com.mohamed.barki.tex.StringActivity;
import com.mohamed.barki.tex.latex.LatexEditor;
import com.mohamed.barki.tex.mathview.KatexView;
import com.mohamed.barki.tex.mathview.MathJaxView;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("deprecation")
public class EditorActivity extends Activity implements OnClickListener
{

	String baa;

	@Override
	public void onClick(View p1) {
	}
	
	final Context contextSave = EditorActivity.this;
	private RecyclerView mDrawerList;
	DividerItemDecoration dividerItemDecoration;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private LatexEditor editor;
    Menu menu;
    //private MenuItem saveButton;
    private String numItem, numGroup, posGroup, title, formula, engine;
	private ArrayList<String> arrayFunction;
	private MyRecyclerViewAdapter adapter;
	
    @SuppressLint("SuspiciousIndentation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
		if(Function.getValue(contextSave, "screen").equals("true")){getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);}

		baa = "exitt";
		
		Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null){
            numItem =(String) b.get("itemA");
			title =(String) b.get("itemT");
            formula =(String) b.get("itemF");
			engine =(String) b.get("itemE");
            numGroup =(String) b.get("itemG");
			posGroup =(String) b.get("itemP");
        }
		//if(true){return;}
		initEditor();
		initDrawer();
		initSymbols();
        openDocumentInEditor(formula);
    }

    @SuppressLint("SuspiciousIndentation")
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
		baa = "exitt";
		outState.putInt("scrollY", findViewById(R.id.editor_scroll_view).getScrollY());
        outState.putInt("selectionStart", ((LatexEditor) findViewById(R.id.editor)).getSelectionStart());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        baa = "exitt";
		findViewById(R.id.editor_scroll_view).setScrollY(savedInstanceState.getInt("scrollY"));
        ((LatexEditor) findViewById(R.id.editor)).setSelection(savedInstanceState.getInt("selectionStart"));
    }

    @Override
    protected void onResume() {
        super.onResume();
		baa = "exitt";
		((LatexEditor) findViewById(R.id.editor)).refreshFontSize();
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        baa = "exitt";
		mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        baa = "exitt";
		mDrawerToggle.onConfigurationChanged(newConfig);
    }
	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        baa = "exitt";
		highlightEditor();
    }

	@Override
	protected void onStop() {
		super.onStop();
	}
	
    private void initDrawer(){
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
	    arrayFunction = new ArrayList<>();
		int len = StringActivity.strFunction[0].length;
		arrayFunction.addAll(Arrays.asList(StringActivity.strFunction[0]).subList(0, len));
		adapter = new MyRecyclerViewAdapter(contextSave, arrayFunction, engine);
		mDrawerList.setAdapter(adapter);
		mDrawerList.addOnItemTouchListener(
			new RecyclerItemClickListener(contextSave, mDrawerList ,new RecyclerItemClickListener.OnItemClickListener() {
					@Override public void onItemClick(View view, int position) {
						insertFunction(arrayFunction.get(position));
						mDrawerLayout.closeDrawers();
					}
					@Override public void onLongItemClick(View view, int position) {
						// do whatever onLongItemClick
					}
				})
		);
		dividerItemDecoration = new DividerItemDecoration(mDrawerList.getContext(), new LinearLayoutManager(this).getOrientation());
		mDrawerList.addItemDecoration(dividerItemDecoration);
        mDrawerLayout.openDrawer(GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ActionBar actionBar = getActionBar();
                if(actionBar!= null) {actionBar.setTitle("BaRKi.TeX");}
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActionBar actionBar = getActionBar();
                if(actionBar!= null) {actionBar.setTitle("Choose Symbol/Function");}
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
		final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
		pullToRefresh.setOnRefreshListener(() -> {
			baa = "exitt";
			switch(engine){
				case "Text": engine="MathV1"; break;
				case "MathV2": engine="Text"; break;
				case "MathV1": default : engine="MathV2"; break;
			}
			adapter = new MyRecyclerViewAdapter(contextSave, arrayFunction, engine);
			mDrawerList.setAdapter(adapter);
			pullToRefresh.setRefreshing(false);
		});
    }
    private void initEditor(){
		editor = findViewById(R.id.editor);
		findViewById(R.id.search_button).setOnClickListener(v -> {
			baa = "exitt";
			findViewById(R.id.LinearLayout1).setLayoutParams(paramMW);
			editor.setNextFocusDownId(R.id.autoEditText);
			findViewById(R.id.autoEditText).requestFocus();
			int selection = Math.max(0, ((AutoCompleteTextView) findViewById(R.id.autoEditText)).getSelectionStart());
			((AutoCompleteTextView) findViewById(R.id.autoEditText)).getText().insert(selection, "");
			((AutoCompleteTextView) findViewById(R.id.autoEditText)).setSelection(selection);
			dialogSearchCommand();
		});
    }

    private void initSymbols(){
		findViewById(R.id.keyboard_button).setOnClickListener(v -> {
			baa = "exitt";
			Function.hideKeyboard(contextSave, editor);
		});
		findViewById(R.id.redo_button).setOnClickListener(v -> {
			baa = "exitt";
			editor.redo(); // perform redo
		});
		findViewById(R.id.undo_button).setOnClickListener(v -> {
			baa = "exitt";
			editor.undo(); // perform undo
		});
        int len = IntActivity.ButtonId.length;
		for(int i=0; i<len ;i++){
			final int ii = i;
			findViewById(IntActivity.ButtonId[i]).setOnClickListener(v -> {
				baa = "exitt";
				popupClickCommand(ii, v, IntActivity.ButtonString[ii]);
			});
		}
		len = IntActivity.imgButtonId.length;
		for(int i=0; i<len ;i++){
			final int ii = i;
			findViewById(IntActivity.imgButtonId[i]).setOnClickListener(v -> {
				baa = "exitt";
				popupTopClick(ii);
			});
		}
		findViewById(R.id.smile_imgbutton).setOnClickListener(v -> {
			baa = "exitt";
			findViewById(R.id.LinearLayout2).setLayoutParams(paramMW);
			editor.setNextFocusDownId(R.id.emojiEditText);
			findViewById(R.id.emojiEditText).requestFocus();
			int selection = Math.max(0, ((EditText) findViewById(R.id.emojiEditText)).getSelectionStart());
			((EditText) findViewById(R.id.emojiEditText)).getText().insert(selection, "");
			((EditText) findViewById(R.id.emojiEditText)).setSelection(selection);
			showEmoji();
		});
    }

	private void popupTopClick(int pos) {
		mDrawerLayout.openDrawer(GravityCompat.START);
		arrayFunction = new ArrayList<>();
		int len = StringActivity.strFunction[pos].length;
		arrayFunction.addAll(Arrays.asList(StringActivity.strFunction[pos]).subList(0, len));
        adapter = new MyRecyclerViewAdapter(contextSave, arrayFunction, "MathV1");
        mDrawerList.setAdapter(adapter);
		mDrawerList.addOnItemTouchListener(
			new RecyclerItemClickListener(contextSave, mDrawerList ,new RecyclerItemClickListener.OnItemClickListener() {
					@Override public void onItemClick(View view, int position) {
						insertFunction(arrayFunction.get(position));
						mDrawerLayout.closeDrawers();
					}
					@Override public void onLongItemClick(View view, int position) {
						// do whatever onLongItemClick
					}
				})
		);
	}
	private void showEmoji() {
		final EditText edit = findViewById(R.id.emojiEditText);
		findViewById(R.id.dialog_closee).setOnClickListener(v -> {
			baa = "exitt";
			edit.setText("");
			editor.requestFocus();
			insertFunction("");
			findViewById(R.id.LinearLayout2).setLayoutParams(paramM0);
		});
        edit.requestFocus();
        edit.setText("");
		edit.setHint("set an emoji");
        edit.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT ) {
				baa = "exitt";
				String selection = edit.getText().toString();//.trim();
				edit.setText("");
				editor.requestFocus();
				insertEmoji(selection);
				findViewById(R.id.LinearLayout2).setLayoutParams(paramM0);
				return false;
			}
			return false;
		});
		findViewById(R.id.dialog_okk).setOnClickListener(v -> {
			baa = "exitt";
			String selection = edit.getText().toString();//.trim();
			edit.setText("");
			editor.requestFocus();
			insertEmoji(selection);
			findViewById(R.id.LinearLayout2).setLayoutParams(paramM0);
		});
	}
	
    private void popupClickCommand(final int ii, View button, final String[][] strCommand) {
        PopupMenu menu = new PopupMenu(EditorActivity.this, button);
        int len = strCommand[0].length;
        for(int i=0; i<len; i++){menu.getMenu().add(strCommand[0][i]);}
        menu.setOnMenuItemClickListener(menuItem -> {
			insertCommand(ii, strCommand, menuItem.getTitle().toString());
			return false;
		});
        menu.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        this.menu = menu;
		return true;
    }
    @SuppressLint("NonConstantResourceId")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		int textSize = Integer.parseInt(Function.getValue(contextSave, "fontSize@"));
        baa = "exitt";
		switch (item.getItemId()){
            case R.id.action_pdf:
                generatePDF();
                break;
            case R.id.action_plus: if(textSize<=24){textSize = textSize +2;}else{textSize=10;}
				Function.saveFromText(contextSave, "fontSize@", String.valueOf(textSize));
				editor.refreshFontSize();
				break;
			case R.id.action_minus: if(textSize>=12){textSize = textSize -2;}else{textSize=26;}
				Function.saveFromText(contextSave, "fontSize@", String.valueOf(textSize));
				editor.refreshFontSize();
				break;
			case android.R.id.home:
				if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
					mDrawerLayout.closeDrawers();
				}else{mDrawerLayout.openDrawer(GravityCompat.START);}
				break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        if (Function.validate(this, editor, editor.getText().toString())) {return;}
        Function.hideKeyboard(this, editor);
        if(!title.equals("@new")){
			Intent intent = new Intent(contextSave, ScreenActivity.class);
			formula = editor.getText().toString();
			Function.saveFromText(contextSave, "item@"+numGroup+numItem, formula);
			Function.saveFromText(contextSave, "itemE@"+numGroup+numItem, engine);
			intent.putExtra("itemG",numGroup); 
			intent.putExtra("itemP",posGroup); 
			startActivity(intent);
		}else{
			final Dialog dialog = new Dialog(contextSave, R.style.DialogStyle);
			dialog.setContentView(R.layout.dialog_save_post);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(false);
			dialog.setTitle(this.getString(R.string.formula_new));
			dialog.findViewById(R.id.dialog_close).setOnClickListener(v -> {
				baa = "exitt";
				dialog.dismiss();
			});
			((TextView) dialog.findViewById(R.id.dialog_info)).setText(this.getString(R.string.formula_new));
			final EditText edit = dialog.findViewById(R.id.dialog_edt);
			edit.setText("");
			edit.setHint(this.getString(R.string.set_formula_name));
			edit.setOnEditorActionListener((p1, p2, p3) -> {
				baa = "exitt";
				if ((p3 != null && (p3.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (p2 == EditorInfo.IME_ACTION_DONE)) {
					if (Function.validateL2(contextSave, edit, numGroup)) {
						Intent intent = new Intent(contextSave, ScreenActivity.class);
						for(int i=Integer.parseInt(numItem); i>=1; i--){
							String title_old = Function.getValue(contextSave, "itemT@"+numGroup+ (i - 1));
							Function.saveFromText(contextSave, "itemT@"+numGroup+ i, title_old);
							String formula_old = Function.getValue(contextSave, "item@"+numGroup+ (i - 1));
							Function.saveFromText(contextSave, "item@"+numGroup+ i, formula_old);
							String engine_old = Function.getValue(contextSave, "itemE@"+numGroup+ (i - 1));
							Function.saveFromText(contextSave, "itemE@"+numGroup+ i, engine_old);
						}
						Function.saveFromText(contextSave, "itemT@"+numGroup+"0", edit.getText().toString());
						Function.saveFromText(contextSave, "item@"+numGroup+"0", editor.getText().toString());
						Function.saveFromText(contextSave, "itemE@"+numGroup+"0", engine);
						Function.saveFromText(contextSave, "nbrItem@"+numGroup, numItem);
						intent.putExtra("itemG",numGroup);
						intent.putExtra("itemP",posGroup);
						dialog.dismiss();
						startActivity(intent);
					}
				}
				return false;
			});
			dialog.findViewById(R.id.dialog_ok).setOnClickListener(v -> {
				baa = "exitt";
				if (Function.validateL2(contextSave, edit, numGroup)) {
					Intent intent = new Intent(contextSave, ScreenActivity.class);
					for(int i=Integer.parseInt(numItem); i>=1; i--){
						String title_old = Function.getValue(contextSave, "itemT@"+numGroup+ (i - 1));
						Function.saveFromText(contextSave, "itemT@"+numGroup+ i, title_old);
						String formula_old = Function.getValue(contextSave, "item@"+numGroup+ (i - 1));
						Function.saveFromText(contextSave, "item@"+numGroup+ i, formula_old);
						String engine_old = Function.getValue(contextSave, "itemE@"+numGroup+ (i - 1));
						Function.saveFromText(contextSave, "itemE@"+numGroup+ i, engine_old);
					}
					Function.saveFromText(contextSave, "itemT@"+numGroup+"0", edit.getText().toString());
					Function.saveFromText(contextSave, "item@"+numGroup+"0", editor.getText().toString());
					Function.saveFromText(contextSave, "itemE@"+numGroup+"0", engine);
					Function.saveFromText(contextSave, "nbrItem@"+numGroup, numItem);
					intent.putExtra("itemG",numGroup);
					intent.putExtra("itemP",posGroup);
					dialog.dismiss();
					startActivity(intent);
				}
			});
			dialog.findViewById(R.id.dialog_exit).setOnClickListener(v -> {
				baa = "exitt";
				Intent intent = new Intent(contextSave, ScreenActivity.class);
				intent.putExtra("itemG",numGroup);
				intent.putExtra("itemP",posGroup);
				dialog.dismiss();
				startActivity(intent);
			});
			dialog.show();
		}
    }

    private void highlightEditor(){
        int scrollY = findViewById(R.id.editor_scroll_view).getScrollY();
        if (scrollY == -1) scrollY = 0;
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int start = Math.max(0, editor.getOffsetForPosition(0, scrollY));
        int end = Math.max(0, editor.getOffsetForPosition(size.x, scrollY + size.y));
        editor.highlightText(start, end);
    }

    private void openDocumentInEditor(String tex){
        int selection = Math.max(0, editor.getSelectionStart());
        editor.getText().insert(selection, tex);
        editor.setSelection(selection + tex.length());
        refreshTitleAndDrawer();
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
    
    @Override
    public void onBackPressed() {
        saveAndExit();
    }
    private void refreshTitleAndDrawer() {
        String title = "BaRKi.TeX";//document.getName();
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setTitle(title);
        }
    }
    
    @SuppressLint("SetTextI18n")
	private void generatePDF(){
        if (Function.validate(contextSave, editor, editor.getText().toString())) {return;}
        Function.hideKeyboard(contextSave, editor);
        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
		dialog.setContentView(R.layout.dialog_view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setTitle("view pdf");
		final LinearLayout lny_katex = dialog.findViewById(R.id.layout_formula_one);
		final LinearLayout lny_mathjax = dialog.findViewById(R.id.layout_formula_two);
		final LinearLayout.LayoutParams paramMW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		final LinearLayout.LayoutParams paramM0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        String text = editor.getText().toString();
        String newTex = text, cmnt;
        int i=0, j;
        while(i<text.length()){
            if(text.charAt(i) == '%'){
                j=i;
                while(j<text.length()){
                    if(text.charAt(j) == '\n' || j==text.length()-1){
                        cmnt = text.substring(i,j+1);
                        newTex = newTex.replace(cmnt, "");
                        i=j+1;
                        break;
                    }else{j++;}
                }
            }else{i++;}
        }
        ((KatexView) dialog.findViewById(R.id.formula_one)).setDisplayText(newTex);
		((MathJaxView) dialog.findViewById(R.id.formula_two)).setText(newTex);
		final Button btn = dialog.findViewById(R.id.view_change);
		btn.setText(engine);
		if ("MathV2".equals(engine)) {
			lny_katex.setLayoutParams(paramM0);
			lny_mathjax.setLayoutParams(paramMW);
		} else {
			lny_katex.setLayoutParams(paramMW);
			lny_mathjax.setLayoutParams(paramM0);
		}
		dialog.findViewById(R.id.view_change).setOnClickListener(v -> {
			baa = "exitt";
			if ("MathV2".equals(btn.getText().toString())) {
				btn.setText("MathV1");
				engine = "MathV1";
				lny_katex.setLayoutParams(paramMW);
				lny_mathjax.setLayoutParams(paramM0);
			} else {
				btn.setText("MathV2");
				engine = "MathV2";
				lny_katex.setLayoutParams(paramM0);
				lny_mathjax.setLayoutParams(paramMW);
			}
		});

        dialog.show();
    }

	private void insertCommand(int mod, String[][] strCommand, String cmnd){
        int pos = indexOfString(cmnd, strCommand[0]);
        String new_cmnd = strCommand[1][pos];
        int selection = Math.max(0, editor.getSelectionStart());
        editor.getText().insert(selection, new_cmnd);
        int slct = new_cmnd.length();
		if(mod == 0){
			if(slct%2 != 0){slct++;}
			slct = slct/2;
		}
		editor.setSelection(selection + slct);
    }
    public int indexOfString(String searchString, String[] domain)
    {
        for(int i = 0; i < domain.length; i++)
            if(searchString.equals(domain[i]))
                return i;
        return 0;
    }
	private void insertFunction(String function){
        int selection = Math.max(0, editor.getSelectionStart());
        editor.getText().insert(selection, function);
        editor.setSelection(selection + function.length());
    }
	private void insertEmoji(String emoji) {
		//String function = Function.decodeEmoji(emoji);
		String function = Function.utf8ToUnicode(emoji);
		
		int selection = Math.max(0, editor.getSelectionStart());
		editor.getText().insert(selection, function);
		editor.setSelection(selection + function.length());
	}
	







	final LinearLayout.LayoutParams paramMW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	final LinearLayout.LayoutParams paramM0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
	 String[] commandString;
	 ArrayList<String> commandArray;
	 AutoSuggestAdapter adapterArray;
	
	private void dialogSearchCommand() {
		final AutoCompleteTextView edit = findViewById(R.id.autoEditText);
		findViewById(R.id.dialog_close).setOnClickListener(v -> {
			baa = "exitt";
			edit.setText("");
			editor.requestFocus();
			insertFunction("");
			findViewById(R.id.LinearLayout1).setLayoutParams(paramM0);
		});
        edit.requestFocus();
        edit.setText("");
		edit.setHint("search a command");
        edit.setOnEditorActionListener((v, actionId, event) -> {
			baa = "exitt";
			if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT ) {
				String selection = edit.getText().toString();
				edit.setText("");
				editor.requestFocus();
				insertFunction(selection);
				findViewById(R.id.LinearLayout1).setLayoutParams(paramM0);
				return false;
			}
			return false;
		});
		commandString = AutoString.cmnd;
		commandArray = new ArrayList<>(Arrays.asList(commandString));
		adapterArray = new AutoSuggestAdapter(EditorActivity.this, R.layout.list_item, commandArray);
		edit.setAdapter(adapterArray);
		edit.setThreshold(3);
		edit.setAdapter(adapterArray);
		edit.setOnItemClickListener((parent, view, position, id) -> {
			baa = "exitt";
			String selection = (String)parent.getItemAtPosition(position);
			edit.setText("");
			editor.requestFocus();
			insertFunction(selection);
			findViewById(R.id.LinearLayout1).setLayoutParams(paramM0);
		});
		findViewById(R.id.dialog_ok).setOnClickListener(v -> {
			baa = "exitt";
			String selection = edit.getText().toString();//.trim();
			edit.setText("");
			editor.requestFocus();
			insertFunction(selection);
			findViewById(R.id.LinearLayout1).setLayoutParams(paramM0);
		});
	}
	
}
