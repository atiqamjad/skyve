package org.skyve.metadata.view.fluent;

import org.skyve.impl.metadata.view.widget.bound.input.DefaultWidget;

public class FluentDefaultWidget extends FluentWidget {
	private DefaultWidget widget = null;
	
	public FluentDefaultWidget() {
		widget = new DefaultWidget();
	}
	
	public FluentDefaultWidget(DefaultWidget widget) {
		this.widget = widget;
	}
	
	public FluentDefaultWidget from(@SuppressWarnings("hiding") DefaultWidget widget) {
		return this;
	}
	
	@Override
	public DefaultWidget get() {
		return widget;
	}
}
