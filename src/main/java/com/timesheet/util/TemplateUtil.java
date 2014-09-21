package com.timesheet.util;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.timesheet.constants.ExceptionCodeConstant;
import com.timesheet.exceptions.ValidationException;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class TemplateUtil {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	private static FreeMarkerConfigurer configurer;
	
	@PostConstruct
	public void setup() {
		configurer = freeMarkerConfigurer;
	}
	
	public static String getTemplateAsString(String templateName, Map<String,Object> model) {	
		try {
			Template template = configurer.getConfiguration().getTemplate(templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException | TemplateException e) {
			throw new ValidationException(ExceptionHandler.getExceptionbean(ExceptionCodeConstant.TEMPLATE_PARSE_ERROR, "Unable to parse Template. "+ExceptionUtils.getStackTrace(e)));
		}
	}
}
