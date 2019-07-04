package com.SkyBlue.base.controller;

import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.SkyBlue.base.to.OpenApiBean;
import com.SkyBlue.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;



@Controller
public class OpenApiController{
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;

		return nValue.getNodeValue();
	}

	@RequestMapping("/base/OpenApiList.do")
	public void findApiList(@RequestAttribute("inData") PlatformData inData,
 			@RequestAttribute("outData") PlatformData outData) throws Exception {
		List<OpenApiBean> ApiList=new ArrayList<>();
		OpenApiBean openApiBean = null;
		openApiBean = new OpenApiBean();
		String urlstr = "http://apis.data.go.kr/1480523/Airemiss/getAiremissList?numOfRows=1&pageNo=1&serviceKey=myPzPUQNnH793f%2FXjs1oNN2Oq9R6HbaBO%2Bve0nI5e%2BZfK1%2FkErdYOyF7GDgbdX2p9OyTWT9E5a9V4gWXAem9Tw%3D%3D&resultType=xml";;
	
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(urlstr);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root Element:"+doc.getDocumentElement().getNodeName());
		
		
		
		NodeList nList = doc.getElementsByTagName("item");
		
		
		System.out.println("파싱할 리스트수:"+nList.getLength());
		
	   for(int i=0;i<nList.getLength();i++) {
			   	
		Node nNode = nList.item(i);

		if(nNode.getNodeType() == Node.ELEMENT_NODE){		   
		Element eElement = (Element) nList.item(0);
		
		

        openApiBean.setSidonm(getTagValue("sidonm", eElement));
		openApiBean.setCo(getTagValue("co", eElement));
		openApiBean.setNo(getTagValue("no", eElement));
		openApiBean.setSo(getTagValue("so", eElement));
		openApiBean.setTsp(getTagValue("tsp", eElement));
		openApiBean.setPm(getTagValue("pm", eElement));
		openApiBean.setVoc(getTagValue("voc", eElement));
		openApiBean.setPm2(getTagValue("pm2", eElement));
		openApiBean.setNh3(getTagValue("nh3", eElement));

		ApiList.add(openApiBean);
	    }
		
	   }

	
		 datasetBeanMapper.beansToDataset(outData, ApiList, OpenApiBean.class);
		
	}
	
	
	
}
