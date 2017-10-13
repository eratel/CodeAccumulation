package com.etoak.util;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class ConfigUtil {

	public static JsonConfig getJsonConfigRoles(){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setExcludes(new String[]{"emps","pres"});
		return config;
	}
	public static JsonConfig getJsonConfigAll(){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setExcludes(new String[]{"roles","emps","pres"});
		return config;
	}
}
