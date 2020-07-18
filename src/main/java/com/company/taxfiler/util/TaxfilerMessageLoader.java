package com.company.taxfiler.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

public class TaxfilerMessageLoader {

	private Logger LOGGER = LoggerFactory.getLogger(TaxfilerMessageLoader.class);

	private Map<String, String> taxFilerMessageInfo = new HashMap<>();

	private static TaxfilerMessageLoader taxFilerMessageLoader = null;

	private TaxfilerMessageLoader() throws IOException {
		init();
	}

	public static TaxfilerMessageLoader getTaxFilerMessageLoaderInstance() throws IOException {
		if (null == taxFilerMessageLoader) {
			taxFilerMessageLoader = new TaxfilerMessageLoader();
		}
		return taxFilerMessageLoader;
	}

	private void init() throws IOException {
		LOGGER.info("Loading taxfiler service validation messages");
		Resource resource = new ClassPathResource("taxfiler_messages.properties");
		InputStream inputStream = resource.getInputStream();
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			String data = new String(bdata, StandardCharsets.UTF_8);
			setTaxFilerMessageInfo(getTokenMap(data));
			LOGGER.info(data);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
	}

	private static Map<String, String> getTokenMap(String tokens) {
		Map<String, String> map = new HashMap<>();
		String[] lines = tokens.split("\n");
		for (String data : lines) {
			if (data.trim().length() == 0 || data.charAt(0) == '#') {
				continue;
			}
			int idx = data.indexOf("=");
			if (idx == -1) {
				continue;
			}
			String name = data.substring(0, idx);
			String value = data.substring(idx + 1);
			map.put(name.trim(), value.trim());
		}
		return map;
	}

	public Map<String, String> getTaxFilerMessageInfo() {
		return taxFilerMessageInfo;
	}

	public void setTaxFilerMessageInfo(Map<String, String> taxFilerMessageInfo) {
		this.taxFilerMessageInfo = taxFilerMessageInfo;
	}

}
