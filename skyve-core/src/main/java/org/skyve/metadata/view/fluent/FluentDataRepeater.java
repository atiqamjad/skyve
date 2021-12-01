package org.skyve.metadata.view.fluent;

import org.skyve.impl.metadata.view.widget.bound.tabular.DataRepeater;

public class FluentDataRepeater extends FluentWidget {
	private DataRepeater data = null;
	
	public FluentDataRepeater() {
		data = new DataRepeater();
	}

	public FluentDataRepeater(DataRepeater data) {
		this.data = data;
	}

	public FluentDataRepeater from(@SuppressWarnings("hiding") DataRepeater data) {
		return this;
	}

	@Override
	public DataRepeater get() {
		return data;
	}
}
