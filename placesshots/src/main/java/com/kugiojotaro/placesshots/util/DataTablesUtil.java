//package com.kugiojotaro.placesshots.util;
//
//import java.util.Iterator;
//import java.util.Map.Entry;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
//import org.springframework.data.jpa.datatables.parameter.CustomSearchParameter;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//public class DataTablesUtil {
//
//	@SuppressWarnings("unchecked")
//	public static DataTablesInput mapCustomSearchParameter(DataTablesInput input, String param) {
//		JsonParser jsonParser = new JsonParser();
//		JsonArray jsonArray = (JsonArray) jsonParser.parse(param);
//		for (int i = 0; i < jsonArray.size(); i++) {
//			Iterator<?> iterator = jsonArray.get(i).getAsJsonObject().entrySet().iterator();
//			while (iterator.hasNext()) {
//				Entry<String, JsonElement> entry = ((Entry<String, JsonElement>) iterator.next());
//				log.info(" key: " + entry.getKey() + ", " + entry.getValue().getAsString());
//				if (StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue().getAsString())) {
//					CustomSearchParameter customSearchParameter = new CustomSearchParameter();
//					customSearchParameter.setName(entry.getKey());
//					customSearchParameter.setValue(entry.getValue().getAsString());
//					input.getCustomSearchs().add(customSearchParameter);
//				}
//			}
//		}
//		
//		return input;
//	}
//	
//}