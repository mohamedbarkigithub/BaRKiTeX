package com.mohamed.barki.latex.latex.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.mohamed.barki.latex.R;

import java.util.ArrayList;

@SuppressWarnings({"deprecation", "RedundantSuppression", "rawtypes"})
public class AutoSuggestAdapter extends ArrayAdapter
{
	Context      context;
	int          resource;
	ArrayList<String> items;
	ArrayList<String> tempItems;
	ArrayList<String> suggestions;
	@SuppressWarnings("unchecked")
	public AutoSuggestAdapter(Context context, int resource, ArrayList<String> items)
	{
		super(context, resource, 0, items);

		this.context = context;
		this.resource = resource;
		this.items = items;
		tempItems = new ArrayList<>(items);
		suggestions = new ArrayList<>();
	}
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resource, parent, false);
		}
		view.setOnTouchListener((v, event) -> {
			event.getAction();
			return false;
		});
		String item = items.get(position);
		if(item != null){
			TextView Title = view.findViewById(R.id.listitemTextView);
			Title.setText(item);
			Title.setTextColor(Color.parseColor("#ff33b5e5"));
			if(!item.contains("\\")){Title.setTextColor(Color.parseColor("#F8F8F2"));}
			Title.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			Title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
			Title.setSingleLine(true);
			Title.setHorizontallyScrolling(true);
			Title.setHorizontalFadingEdgeEnabled(true);
			Title.setMarqueeRepeatLimit(-1);
			Title.setSelected(true);

		}
		return view;
	}

	@Override
	public Filter getFilter()
	{
		return nameFilter;
	}
	Filter nameFilter = new Filter()
	{
		@Override
		public CharSequence convertResultToString(Object resultValue)
		{
			return (String) resultValue;
		}

		@Override
		protected FilterResults performFiltering(CharSequence constraint)
		{
			if (constraint != null)
			{
				suggestions.clear();
				for (String names : tempItems)
				{
					if (names.toLowerCase().contains(constraint.toString().toLowerCase()))
					{
						suggestions.add(names);
					}
				}
				FilterResults filterResults = new FilterResults();
				filterResults.values = suggestions;
				filterResults.count = suggestions.size();
				return filterResults;
			}
			else
			{
				return new FilterResults();
			}
		}
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results)
		{
			ArrayList<String> filterList = (ArrayList<String>) results.values;
			if (results.count > 0)
			{
				clear();
				for (String item : filterList)
				{
					add(item);
					notifyDataSetChanged();
				}
			}
		}
	};
}
