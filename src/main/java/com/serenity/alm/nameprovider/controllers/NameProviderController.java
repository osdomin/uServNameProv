package com.serenity.alm.nameprovider.controllers;

import java.io.InputStream;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class NameProviderController {
	private static String name = "Serenity ALM";
	private static String lastName = "Team";

	@RequestMapping(method = RequestMethod.GET, value = "/name")
	private String getName() {
		return name;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/name")
	private String setName(String newName) {
		return name;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lastName")
	private String geLastName() {
		return lastName;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/date")
	private String getDate() {
		return new Date().toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/version")
	private String getVersion() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = getClass().getResourceAsStream("/META-INF/maven/com.serenity.alm/name-provider-I/pom.xml");
			if (is==null){
				return "pom.xml not found!";
			}else{
				Document doc = builder.parse(is);
				XPathExpression xp = XPathFactory.newInstance().newXPath().compile("/project/version/text()");

				return "Current '" + name + "' Version: " +xp.evaluate(doc);

			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			return t.toString();
		}
	}
}
