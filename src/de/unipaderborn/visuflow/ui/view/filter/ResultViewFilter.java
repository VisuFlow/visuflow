package de.unipaderborn.visuflow.ui.view.filter;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.google.common.base.Optional;

import de.unipaderborn.visuflow.model.VFUnit;

/**
 * This class extends the {@link org.eclipse.jface.viewers.ViewerFilter} to provide filtering functionality for filtering the results of the analysis.
 * @author Shashank B S
 *
 */
public class ResultViewFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		this.searchString = s;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		VFUnit unit = (VFUnit) element;
		if (contains(unit.getUnit().toString(), searchString)) {
			return true;
		}
		if (contains(unit.getUnit().getClass().getName(), searchString)) {
			return true;
		}

		String inset = Optional.fromNullable(unit.getInSet()).or("n/a").toString();
		if(contains(inset, searchString)) {
			return true;
		}

		String outset = Optional.fromNullable(unit.getOutSet()).or("n/a").toString();
		if(contains(outset, searchString)) {
			return true;
		}

		for (Entry<String, String> customAttribute : unit.getHmCustAttr().entrySet()) {
			if (contains(customAttribute.getKey(), searchString) || contains(customAttribute.getValue(), searchString)) {
				return true;
			}
		}

		return false;
	}

	private boolean contains(String haystack, String needle) {
		return haystack.toLowerCase().contains(needle.toLowerCase());
	}
}