package com.kugiojotaro.placesshots.predicate;

//import org.springframework.data.jpa.datatables.parameter.CustomSearchParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import com.kugiojotaro.placesshots.entity.qdsl.QFixture;
import com.kugiojotaro.placesshots.util.QueryDslUtil;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

public class FixturePredicate {

	public static Predicate inquiry(DataTablesInput input) {
		BooleanExpression query = null;
		
//		for (CustomSearchParameter customSearchParameter : input.getCustomSearchs()) {
//			if (customSearchParameter.getName().equals("homeTeam") && StringUtils.isNotBlank(customSearchParameter.getValue())) {
//				query = QueryDslUtil.appendAnd(query, QFixture.fixture.home().title.like("%" + customSearchParameter.getValue() + "%"));
//			}
//			if (customSearchParameter.getName().equals("awayTeam") && StringUtils.isNotBlank(customSearchParameter.getValue())) {
//				query = QueryDslUtil.appendAnd(query, QFixture.fixture.away().title.like("%" + customSearchParameter.getValue() + "%"));
//			}
//		}
		
		return query;
	}
}