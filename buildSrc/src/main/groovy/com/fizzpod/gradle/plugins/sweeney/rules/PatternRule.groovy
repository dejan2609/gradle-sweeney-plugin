package com.fizzpod.gradle.plugins.sweeney.rules

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PatternRule implements Rule {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatternRule); 
	
	public static final String PATTERN_TYPE_VALUE = "pattern"

	@Override
	public String getType() {
		return PATTERN_TYPE_VALUE;
	}

	@Override
	public boolean accept(RuleDefinition ruleDefinition) {
		if(ruleDefinition.hasAttribute(TYPE_ATTRIBUTE) && PATTERN_TYPE_VALUE.equals(ruleDefinition.getAttribute(TYPE_ATTRIBUTE).call())) {
			return true;
		} 
		return false;
	}

	@Override
	public void validate(def scope, RuleDefinition ruleDefinition) {
		String value = String.valueOf(ruleDefinition.getAttribute("value").call()); 
		String expect = ruleDefinition.getAttribute("expect").call();
		
		LOGGER.info("Checking value {} with expected {}", value, expect);
		assert value.matches(expect), 'Validation failed for rule definition' + ruleDefinition
	}

	@Override
	public String toString() {
		return getType();
	}
	

}
