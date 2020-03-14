package kr.kyoungjin.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AccessiblePageUtil {

	/**
	 * XML 파일에서 세션없이 접근 가능한 주소 목록을 가져온다
	 * @param xmlFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<String> getAccessiblePageList(File xmlFile) throws ParserConfigurationException, SAXException, IOException  {
		List<String> rtnList = new ArrayList<String>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		NodeList n_list = root.getElementsByTagName("accessList");
		Element el = null;
		NodeList sub_n_list = null; //sub_n_list
		Element sub_el = null; //sub_el

		Node v_txt = null;
		String value="";

		String[] tagList = {"name", "url"};

		for(int i=0; i<n_list.getLength(); i++) {
			el = (Element) n_list.item(i);
			for(int k=0; k< tagList.length; k++) {
				sub_n_list = el.getElementsByTagName(tagList[k]);
				for(int j=0; j<sub_n_list.getLength(); j++) {
					sub_el = (Element) sub_n_list.item(j);
					v_txt = sub_el.getFirstChild();
					value = v_txt.getNodeValue();

					System.out.println(sub_el.getNodeName() + "::::value="+value);
					if ( sub_el.getNodeName().equals("url") ) rtnList.add(value);
				}
			}

		}
		return rtnList;
	}
}
