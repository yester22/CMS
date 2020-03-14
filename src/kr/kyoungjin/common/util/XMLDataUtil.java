package kr.kyoungjin.common.util;


import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@Component
public class XMLDataUtil {
	
	@Value("#{config['ACCESS_URL_XML_PATH']}")
	private String ACCESS_URL_XML_PATH;
	
	@Value("#{config['CODE_XML_PATH']}")
	private String CODE_XML_PATH;
	
	@Value("#{config['NATIONAL_XML_PATH']}")
	private String NATIONAL_XML_PATH;
	
	public void saveCodeXml () throws ParserConfigurationException, TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, IOException {
	// Document및 XML트리 생성 
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
		Node root = document.createElement("JCompany"); 
		document.appendChild(root); 
		{ 
			Element people1 = document.createElement("employee"); 
			people1.setAttribute("id", "1"); 
			people1.setAttribute("part", "devlopment"); 
			root.appendChild(people1); 
			
			{ 
				Element name = document.createElement("name"); 
				name.appendChild(document.createTextNode("Gildong Hong")); 
				people1.appendChild(name); 
			} 
			{ 
				Element age = document.createElement("age"); 
				age.appendChild(document.createTextNode("25")); 
				people1.appendChild(age); 
			} 
		} 
		// Document 저장 
		DOMSource xmlDOM = new DOMSource(document); 
		//xml 파일을 읽어서 허용 페이지 목록을 반환한다
    	Resource resource = new ClassPathResource(ACCESS_URL_XML_PATH);
		StreamResult xmlFile = new StreamResult(resource.getFile()); 
		TransformerFactory.newInstance().newTransformer().transform(xmlDOM, xmlFile);
	}
}
