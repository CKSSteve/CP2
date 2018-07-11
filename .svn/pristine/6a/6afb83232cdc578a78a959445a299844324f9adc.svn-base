package com.uxb2b.ecp.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class FreemarkerService {
	
	private Logger log = LoggerFactory.getLogger(FreemarkerService.class);
	
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public String generateByEmailTemplate(String ftlName, Object vo) throws Exception {
	
		try {
			
			Template template = genTemplate(readTemplate(ftlName+".ftl"));
			HashMap<String, Object> root = new HashMap<String, Object>();
			root.put("vo", vo);
			String body = genOutput(template, root);
            return body;
		} catch (Exception e) {
	        log.error(e.toString(), e);
            throw e;
		}
		
	}
	
	  private String readTemplate(String fileName) throws Exception {
	        StringBuffer sb = new StringBuffer();
	        try {
	        	
	        	InputStream templateStream = resourceLoader.getResource("classpath:config/ftl/"+ fileName).getInputStream();

	            // 指定編碼為 UTF-8
	            InputStreamReader isr = new InputStreamReader(templateStream,
	                    "UTF-8");

	            int binary;
	            while ((binary = isr.read()) != -1) {
	                sb.append((char) binary);
	            }
	            isr.close();

	        } catch (Exception e) {
	            log.error(e.toString(), e);
	        }
	        return sb.toString();
	    }
	  
	    private Template genTemplate(String freemarkerTemplate) throws Exception {

	        try {
	            StringTemplateLoader stringLoader = new StringTemplateLoader();
	            String mailTemplate = "mailTemplate";
	            stringLoader.putTemplate(mailTemplate, freemarkerTemplate);

	            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	            cfg.setEncoding(Locale.TAIWAN, "UTF-8");
	            cfg.setTemplateLoader(stringLoader);
	            Template template = cfg.getTemplate(mailTemplate);

	            return template;
	        } catch (Exception e) {
	            log.error(e.toString(), e);
	            throw e;
	        }
	    }
	
	    private String genOutput(Template template,
	            HashMap<String, Object> root) throws Exception {
	        try {
	            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	            OutputStreamWriter out = new OutputStreamWriter(bytes);
	            template.process(root, out);
	            out.flush();
	            out.close();
	            bytes.close();
	            return bytes.toString();
	        } catch (Exception e) {
	            log.error(e.toString(), e);
	            throw e;
	        }
	    }
}
