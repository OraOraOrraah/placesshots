package com.kugiojotaro.placesshots.util;

import com.mysema.query.types.expr.BooleanExpression;

public class QueryDslUtil {

	public static BooleanExpression appendAnd(BooleanExpression left, BooleanExpression right) {
		return left == null ? right : left.and(right);
	}
	
}