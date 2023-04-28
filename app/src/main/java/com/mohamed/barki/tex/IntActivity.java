package com.mohamed.barki.tex;

import android.app.*;
import android.os.*;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class IntActivity extends Activity
{
    public static int[] imgButtonId = new int[]{
		R.id.other_imgbutton,
		R.id.text_imgbutton, 
		R.id.ensemble_imgbutton,
		R.id.font_imgbutton, 
		R.id.space_imgbutton, 
		R.id.matrix_imgbutton, 
		R.id.equation_imgbutton, 
		R.id.brackets_imgbutton,
		R.id.braces_imgbutton,
		R.id.parentheses_imgbutton,
		R.id.integral_imgbutton,
		R.id.sum_imgbutton,
		R.id.prod_imgbutton,
		R.id.frac_imgbutton,
		R.id.lim_imgbutton,
		R.id.under_imgbutton,
		R.id.over_imgbutton,
	    R.id.function_imgbutton,
		R.id.cup_imgbutton,
		R.id.arrow_imgbutton,
		R.id.dasharrow_imgbutton,
		R.id.equal_imgbutton,
		R.id.operation_imgbutton,
		R.id.relation_imgbutton,
		R.id.relation_geometrique_imgbutton,
	    R.id.dots_imgbutton,
		R.id.phisique_imgbutton,
		R.id.circle_imgbutton,
		R.id.form_imgbutton,
		R.id.form_black_imgbutton,
		R.id.form_empty_imgbutton,
		
	};
	public static int[] ButtonId = new int[]{
		R.id.curly_brackets_button,
		R.id.alpha_button,
		R.id.alpha_pro_button,
		R.id.symbole_geometrique_button,
		R.id.operator_button,
		R.id.apostrof_button,
		R.id.maths_button
		
	};
	public static String[][][] ButtonString = new String[][][]{
		StringActivity.strBrackets,
		StringActivity.strAlpha,
		StringActivity.strAlphaPro,
		StringActivity.strSymboleGeometry,
		StringActivity.strOperator,
		StringActivity.strApostrof,
		StringActivity.strMaths
		
	};
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	}
}
