package com.mohamed.barki.latex.latex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.mohamed.barki.latex.Function;
import com.mohamed.barki.latex.R;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("AppCompatCustomView")
@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class LatexEditor extends EditText {
	//final int finalTabSize = 2;
    public final Pattern[] patterns = {
		Pattern.compile("\\$([^$]*)\\$", Pattern.MULTILINE),
		Pattern.compile("\\\\begin\\{equation\\}([^$]*)\\\\end\\{equation\\}", Pattern.MULTILINE),
		Pattern.compile("\\\\begin\\{equation\\*\\}([^$]*)\\\\end\\{equation\\*\\}", Pattern.MULTILINE),
		Pattern.compile("([\\\\])(\\w+|['`\\\\]\\w*)(\\*)*", Pattern.MULTILINE),
		Pattern.compile("\\\\(begin)\\{", Pattern.MULTILINE),
		Pattern.compile("\\\\(end)\\{", Pattern.MULTILINE),
		Pattern.compile("\\\\(begin)\\[", Pattern.MULTILINE),
		Pattern.compile("([{]).+([}])", Pattern.MULTILINE),
		Pattern.compile("([\\[]).+([]])", Pattern.MULTILINE),
		Pattern.compile("\\$", Pattern.MULTILINE),
		Pattern.compile("([1234567890])", Pattern.MULTILINE),
		Pattern.compile("(%).*$", Pattern.MULTILINE)
	};
    public final int[] patternColors = {
		getResources().getColor(R.color.green),
		getResources().getColor(R.color.green),
		getResources().getColor(R.color.green),
		getResources().getColor(R.color.latex_class),
		getResources().getColor(R.color.Blue),
		getResources().getColor(R.color.Blue),
		getResources().getColor(R.color.Blue),
		getResources().getColor(R.color.latex_keyword),
		getResources().getColor(R.color.latex_third),
		getResources().getColor(R.color.light_green),
		getResources().getColor(R.color.Red),
		getResources().getColor(R.color.text_grey)
	};
    private static final TextPaint numberPainter = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    private static final Paint backgroundPainter = new Paint();
    private float lineHeight;
    private float lineCountPaddingTop;
    private float lineCounterColumnWidth;
    private float lineCounterColumnMargin;


    public LatexEditor(Context context) {
        super(context);
        init();
    }

    public LatexEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LatexEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

	TextWatcher textWatcher;
    private void init(){
		refreshFontSize();
		mEditHistory = new EditHistory();
		textWatcher = new TextWatcher(){
			private CharSequence mBeforeChange;
			CharSequence mAfterChange;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mIsUndoOrRedo) {
					return;
				}
				mAfterChange = s.subSequence(start, start + count);
				mEditHistory.add(new EditItem(start, mBeforeChange, mAfterChange));
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				if (mIsUndoOrRedo) {
					return;
				}
				mBeforeChange = s.subSequence(start, start + count);
			}
			@Override
			public void afterTextChanged(Editable e) {
				//if(){}
				highlightText(0, e.toString().length());
			}
		};
		addTextChangedListener(textWatcher);
    }
	public void highlightText(int start, int end){
        Editable editable = getText();
        clearSpans(editable);
        CharSequence s = getText().subSequence(start, end);
        for(int i = 0; i < patterns.length; i++){
            Matcher matcher = patterns[i].matcher(s);
            while(matcher.find()){
                if(matcher.group().length() > 0){
                    editable.setSpan(new ForegroundColorSpan(patternColors[i%patternColors.length]),
									 start + matcher.start(), start + matcher.end(),
									 Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

	public void refreshFontSize(){
        String fontSizeString;
		if(Function.getValue(getContext(), "fontSize@").isEmpty()){
			Function.saveFromText(getContext(), "fontSize@", "12"); fontSizeString = "12";}
        else{
			fontSizeString = Function.getValue(getContext(), "fontSize@");
		}
        float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        float fontSize = Float.parseFloat(fontSizeString);
        numberPainter.setTextSize((fontSize - 3) * scaledDensity);
        setTextSize(fontSize);
        lineHeight = getLineHeight();
        // Number's color
        numberPainter.setColor(getResources().getColor(R.color.text_grey));
        // Given a point, the numbers are drawn starting from the right
        numberPainter.setTextAlign(Paint.Align.RIGHT);
        lineCountPaddingTop = - (lineHeight * 0.10f);
        float paddingLeft = (lineHeight * 3f);
        float marginBeforeText = paddingLeft * 0.2f;
        // The column width is given by the total padding left less the margin before text
        lineCounterColumnWidth =  paddingLeft - marginBeforeText;
        lineCounterColumnMargin = lineCounterColumnWidth/6;
        setPadding((int) paddingLeft, 0, 0, 0);
    }

	@Override
    protected void onDraw(@NonNull Canvas canvas) {
        backgroundPainter.setColor(getResources().getColor(R.color.editor_column));
        // Draws a colored background to the line counter column
        canvas.drawRect(0, 0, lineCounterColumnWidth, this.getBottom(), backgroundPainter);

        backgroundPainter.setColor(getResources().getColor(R.color.text_darkgrey));
        // Draws a right-border to the line counter column
        canvas.drawLine(lineCounterColumnWidth, 0, lineCounterColumnWidth, getBottom(), backgroundPainter);

        // The number that will be drawn
        int lineToDraw = 1;
        // Set to true if a line hasn't got a newline character
        boolean previousLineNoNewline = false;
        // Will draw a number aside each line in the EditText
        // The number won't be drawn if the previous line hasn't got a newline character
        for (int i = 0; i < getLineCount(); i++) {
            int currentLineStart = getLayout().getLineStart(i);
            int currentLineEnd = getLayout().getLineEnd(i);
            String currentLine = getText().subSequence(currentLineStart, currentLineEnd).toString();
            boolean containsNewLine = currentLine.contains("\n");
            // If the previous line contains a newline character the number it will draw the number
            if (!previousLineNoNewline) {
                canvas.drawText(String.valueOf(lineToDraw),
								lineCounterColumnWidth - lineCounterColumnMargin,
								((i+1) * lineHeight) + lineCountPaddingTop,
								numberPainter);
                if (!containsNewLine) {
                    previousLineNoNewline = true;
                }
                lineToDraw++;
                // When it finds a line containing a newline character, the next line will be drawn
            } else {
                if (containsNewLine) {
                    previousLineNoNewline = false;
                }
            }
        }
        super.onDraw(canvas);
    }

    private void clearSpans( Editable e ){
        // remove foreground color spans
        ForegroundColorSpan[] spans = e.getSpans(
			0,
			e.length(),
			ForegroundColorSpan.class);

        for( int n = spans.length; n-- > 0; )
            e.removeSpan( spans[n] );
    }
	private boolean mIsUndoOrRedo = false;
	private EditHistory mEditHistory;

	public void undo() {
		EditItem edit = mEditHistory.getPrevious();
		if (edit == null) {return;}
		Editable text = getText();
		int start = edit.mmStart;
		int end = start + (edit.mmAfter != null ? edit.mmAfter.length() : 0);
		mIsUndoOrRedo = true;
		text.replace(start, end, edit.mmBefore);
		mIsUndoOrRedo = false;
		for (Object o : text.getSpans(0, text.length(), UnderlineSpan.class)) {
			text.removeSpan(o);
		}
		Selection.setSelection(text, edit.mmBefore == null ? start : (start + edit.mmBefore.length()));
	}
	public void redo() {
		EditItem edit = mEditHistory.getNext();
		if (edit == null) {return;}
		Editable text = getText();
		int start = edit.mmStart;
		int end = start + (edit.mmBefore != null ? edit.mmBefore.length() : 0);
		mIsUndoOrRedo = true;
		text.replace(start, end, edit.mmAfter);
		mIsUndoOrRedo = false;
		for (Object o : text.getSpans(0, text.length(), UnderlineSpan.class)) {
			text.removeSpan(o);
		}
		Selection.setSelection(text, edit.mmAfter == null ? start : (start + edit.mmAfter.length()));
	}
	private static final class EditHistory {
		private int mmPosition = 0;
		int mmMaxHistorySize = -1;
		private final LinkedList<EditItem> mmHistory = new LinkedList<>();
		private void add(EditItem item) {
			while (mmHistory.size() > mmPosition) {
				mmHistory.removeLast();
			}
			mmHistory.add(item);
			mmPosition++;
			if (mmMaxHistorySize >= 0) {
				trimHistory();
			}
		}
		private void trimHistory() {
			while (mmHistory.size() > mmMaxHistorySize) {
				mmHistory.removeFirst();
				mmPosition--;
			}
			if (mmPosition < 0) {
				mmPosition = 0;
			}
		}
		private EditItem getPrevious() {
			if (mmPosition == 0) {
				return null;
			}
			mmPosition--;
			return mmHistory.get(mmPosition);
		}
		private EditItem getNext() {
			if (mmPosition >= mmHistory.size()) {
				return null;
			}
			EditItem item = mmHistory.get(mmPosition);
			mmPosition++;
			return item;
		}
	}
	private static final class EditItem {
		private final int mmStart;
		private final CharSequence mmBefore;
		private final CharSequence mmAfter;
		public EditItem(int start, CharSequence before, CharSequence after) {
			mmStart = start;
			mmBefore = before;
			mmAfter = after;
		}
	}
	
}
