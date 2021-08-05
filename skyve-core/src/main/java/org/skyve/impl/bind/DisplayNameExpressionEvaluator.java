package org.skyve.impl.bind;

import org.skyve.domain.Bean;
import org.skyve.metadata.model.Attribute;

class DisplayNameExpressionEvaluator extends AttributeExpressionEvaluator {
	static final String PREFIX = "disp";
	
	@Override
	public Object evaluateWithoutPrefix(String expression, Bean bean) {
		Attribute a = obtainAttribute(expression, bean);
		return (a == null) ? null : a.getLocalisedDisplayName();
	}

	@Override
	public String formatWithoutPrefix(String expression, Bean bean) {
		Object result = evaluateWithoutPrefix(expression, bean);
		return (result == null) ? "" : result.toString();
	}
}
