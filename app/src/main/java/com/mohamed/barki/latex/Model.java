package com.mohamed.barki.latex;

import android.annotation.SuppressLint;

public class Model {
    String title;
	String formula;
    String engine;
    String group;
	@SuppressLint("SuspiciousIndentation")
    public Model(String title, String formula, String engine, String group) {
        this.title = title;
		this.formula = formula;
		this.engine = engine;
        this.group = group;
    }

	public CharSequence getTitle() {
		return title;
	}  
    public CharSequence getFormula() {
        return formula;
	}
	public CharSequence getEngine() {
        return engine;
	}  
    public CharSequence getGroup() {
        return group;
	}
}  
