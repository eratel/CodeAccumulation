package com.etoak.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.etoak.bean.Archives;
import com.etoak.bean.Assess;
import com.etoak.bean.AssessAdmin;
import com.etoak.bean.AssessBpwork;
import com.etoak.bean.AssessDay;
import com.etoak.bean.AssessStandard;
import com.etoak.bean.Auditing;
import com.etoak.bean.Employee;
import com.etoak.bean.Role;

public class OptionXml {

	public static void judgeElementForMonth(File xmlDoc , Archives ar , String date)throws Exception{
		//System.out.println("开始判断当月是否存在考核节点");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		List<Element> months = root.elements("绩效考核-月");
		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		boolean ismonth = false;
		for(Element month:months){
			String thismonth = month.attributeValue("month");
			if(thismonth.equals(m)) {
				ismonth = true;
				break;
			}
		}
		if(!ismonth){ //暂无当月对应的考核节点
			//System.out.println("不存在当月对应的考核节点");
			Element month = root.addElement("绩效考核-月");
			month.addAttribute("month", m); 
			Element zhicheng = month.addElement("职称补贴");
			zhicheng.addAttribute("score", Globals.selectScoreByKey(ar.getZhicheng())+"");
			Element gongling = month.addElement("工资工龄");
			gongling.addAttribute("score", Globals.selectScoreByKey(ar.getGlgz())+"");
			repositoryXml(xmlDoc , doc);
		}
	}

	public static void judgeElementForDay(File xmlDoc , String date)throws Exception{
		//System.out.println("开始判断当天是否存在考核节点");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		List<Element> months = root.elements("绩效考核-月");
		for(Element month : months){
			if(m.equals(month.attributeValue("month"))){
				List<Element> days = month.elements("绩效考核-天");
				boolean isday = false;
				for(Element day:days){
					if(date.equals(day.attributeValue("day"))) {
						isday = true;
						break;
					}
				}
				if(!isday){ //暂无当天对应的考核节点
					//System.out.println("不存在当天对应的考核节点");
					Element day = month.addElement("绩效考核-天");
					day.addAttribute("day", date);
					//在当天节点下添加一个‘汇总’节点
					Element summary = day.addElement("summary");
					summary.addElement("爆破作业");
					summary.addElement("绩效考核");
					repositoryXml(xmlDoc , doc);
				}
				break;
			}
		}
	}

	public static void judgeElementForAdmin(File xmlDoc , String date , Employee emp)throws Exception{
		//System.out.println("开始判断当天是否存在管理员节点");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		List<Element> months = root.elements("绩效考核-月");
		for(Element month : months){
			if(m.equals(month.attributeValue("month"))){
				List<Element> days = month.elements("绩效考核-天");
				
				for(Element day:days){
					if(date.equals(day.attributeValue("day"))) {
						List<Element> admins = day.elements("admin");
						if(admins.size()==0){ //不存在管理员节点
							Element ad = day.addElement("admin");
							ad.addAttribute("id", emp.getId()+"");
							ad.addAttribute("name", emp.getName());
							repositoryXml(xmlDoc , doc);
						}else{
							boolean isAdmin = false; //判断是否存在当前该管理员节点
							for(Element admin:admins){
								if(emp.getId().toString().equals(admin.attributeValue("id"))) {
									isAdmin = true;
									break;
								}
							}
							if(!isAdmin){ //不存在当前管理员节点
								Element ad = day.addElement("admin");
								ad.addAttribute("id", emp.getId()+"");
								ad.addAttribute("name", emp.getName());
								repositoryXml(xmlDoc , doc);
							}
						}
						break;
					}
				}
				break;
			}
		}
	}

	public static void judgeElementForBpwork(File xmlDoc , String date , Employee emp)throws Exception{
		//System.out.println("开始判断当天该管理员节点下是否存在爆破作业");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		List<Element> months = root.elements("绩效考核-月");
		for(Element month : months){
			if(m.equals(month.attributeValue("month"))){
				List<Element> days = month.elements("绩效考核-天");
				
				for(Element day:days){
					if(date.equals(day.attributeValue("day"))) {
						List<Element> admins = day.elements("admin");
						for(Element admin:admins){
							if(emp.getId().toString().equals(admin.attributeValue("id"))){
								List<Element> bpworks = admin.elements("爆破作业");
								if(bpworks.size()==0){ //无爆破作业节点
									//System.out.println("当天该管理员节点下不存在的爆破作业节点");
									Element bpwork = admin.addElement("爆破作业");
									repositoryXml(xmlDoc , doc);
								}
								break;
							}
						}
						break;	
					}
				}
				break;
			}
		}
	}

	public static void judgeElementForStandard(File xmlDoc , String date , Integer count , Employee emp)throws Exception{
		//System.out.println("开始判断当天该管理员节点下是否存在绩效考核节点");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		List<Element> months = root.elements("绩效考核-月");
		for(Element month : months){
			if(m.equals(month.attributeValue("month"))){
				List<Element> days = month.elements("绩效考核-天");
				for(Element day:days){
					if(date.equals(day.attributeValue("day"))) {
						List<Element> admins = day.elements("admin");
						for(Element admin:admins){
							if(emp.getId().toString().equals(admin.attributeValue("id"))){
								List<Element> jxkhs = admin.elements("绩效考核");
								if(jxkhs.size()==0){ //无绩效考核节点
									//System.out.println("当天该管理员节点下不存在的绩效考核节点");
									Element jxkh = admin.addElement("绩效考核");
									jxkh.addAttribute("count", count+""); //需要考核的项数
									/*
									 * 向’汇总‘-’绩效考核‘节点中录入需要考核的项数
									 */
									Element summary = day.element("summary");
									Element sj = summary.element("绩效考核");
									sj.addAttribute("count", count+"");
									repositoryXml(xmlDoc , doc);
								}
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
	
	
	public static void judgeElementForKeyOK(File xmlDoc , Archives ar , String date , Integer count , Employee emp)throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlDoc);
		Element root = doc.getRootElement();

		String m = date.substring(0,date.lastIndexOf("-")); //当月 2013-01-01
		List<Element> months = root.elements("绩效考核-月");
		boolean ismonth = false;
		for(Element month:months){
			if(month.attributeValue("month").equals(m)) {
				ismonth = true;
				break;
			}
		}
		if(!ismonth){ 
			/* 不存在该月 */
			Element month = root.addElement("绩效考核-月");
			month.addAttribute("month", m); 
			Element zhicheng = month.addElement("职称补贴");
			zhicheng.addAttribute("score", Globals.selectScoreByKey(ar.getZhicheng())+"");
			Element gongling = month.addElement("工资工龄");
			gongling.addAttribute("score", Globals.selectScoreByKey(ar.getGlgz())+"");
			Element day = month.addElement("绩效考核-天");
			day.addAttribute("day", date);
			//在当天节点下添加一个‘汇总’节点
			Element summary = day.addElement("summary");
			summary.addElement("爆破作业");
			summary.addElement("绩效考核");
			Element sj = summary.element("绩效考核");
			sj.addAttribute("count", count+"");
			Element ad = day.addElement("admin");
			ad.addAttribute("id", emp.getId()+"");
			ad.addAttribute("name", emp.getName());
			Element bpwork = ad.addElement("爆破作业");
			Element jxkh = ad.addElement("绩效考核");
			jxkh.addAttribute("count", count+""); 
		}else{
			/* 存在该月 */
			for(Element month : months){
				if(m.equals(month.attributeValue("month"))){
					List<Element> days = month.elements("绩效考核-天");
					boolean isday = false;
					for(Element day:days){
						if(date.equals(day.attributeValue("day"))) {
							isday = true;
							break;
						}
					}
					if(!isday){ 
						/* 不存在该天 */
						Element day = month.addElement("绩效考核-天");
						day.addAttribute("day", date);
						//在当天节点下添加一个‘汇总’节点
						Element summary = day.addElement("summary");
						summary.addElement("爆破作业");
						summary.addElement("绩效考核");
						Element sj = summary.element("绩效考核");
						sj.addAttribute("count", count+"");
						Element ad = day.addElement("admin");
						ad.addAttribute("id", emp.getId()+"");
						ad.addAttribute("name", emp.getName());
						Element bpwork = ad.addElement("爆破作业");
						Element jxkh = ad.addElement("绩效考核");
						jxkh.addAttribute("count", count+""); 
					}else{
						/* 存在该天 */
						for(Element day:days){
							if(date.equals(day.attributeValue("day"))) {
								List<Element> admins = day.elements("admin");
								if(admins.size()==0){ 
									/* 不存在管理员 */
									Element ad = day.addElement("admin");
									ad.addAttribute("id", emp.getId()+"");
									ad.addAttribute("name", emp.getName());
									Element bpwork = ad.addElement("爆破作业");
									Element jxkh = ad.addElement("绩效考核");
									jxkh.addAttribute("count", count+""); 
									// 向’汇总‘-’绩效考核‘节点中录入需要考核的项数
									Element summary = day.element("summary");
									Element sj = summary.element("绩效考核");
									sj.addAttribute("count", count+"");
								}else{
									boolean isAdmin = false; //判断是否存在当前该管理员节点
									for(Element admin:admins){
										if(emp.getId().toString().equals(admin.attributeValue("id"))) {
											isAdmin = true;
											break;
										}
									}
									if(!isAdmin){ 
										/* 不存在管理员 */
										Element ad = day.addElement("admin");
										ad.addAttribute("id", emp.getId()+"");
										ad.addAttribute("name", emp.getName());
										Element bpwork = ad.addElement("爆破作业");
										Element jxkh = ad.addElement("绩效考核");
										jxkh.addAttribute("count", count+""); 
										// 向’汇总‘-’绩效考核‘节点中录入需要考核的项数
										Element summary = day.element("summary");
										Element sj = summary.element("绩效考核");
										sj.addAttribute("count", count+"");
									}else{
										/* 存在管理员 */
										for(Element admin:admins){
											if(emp.getId().toString().equals(admin.attributeValue("id"))){
												List<Element> bpworks = admin.elements("爆破作业");
												List<Element> jxkhs = admin.elements("绩效考核");
												if(bpworks.size()==0){
													/* 无爆破作业节点 */
													Element bpwork = admin.addElement("爆破作业");
												}
												if(jxkhs.size()==0){ 
													/* 无绩效考核节点 */
													Element jxkh = admin.addElement("绩效考核");
													jxkh.addAttribute("count", count+""); 
													// 向’汇总‘-’绩效考核‘节点中录入需要考核的项数
													Element summary = day.element("summary");
													Element sj = summary.element("绩效考核");
													sj.addAttribute("count", count+"");
												}
												break;
											}
										}
									}
								}
								break;
							}
						}
					}
					break;
				}
			}
		}
		repositoryXml(xmlDoc , doc);
	}
	

	
	
	
	
	public static void repositoryXml(File xmlDoc , Document doc)throws Exception{
		XMLWriter writer = new XMLWriter (new FileOutputStream(xmlDoc),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	public static Double readHour(HttpServletRequest request)throws Exception{

	  	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xmlDoc");
	  	File file = new File(path+"/date.txt");
	  	DataInputStream is = new DataInputStream(new FileInputStream(file));
	  	String hour = is.readLine();
		return Double.parseDouble(hour);
	}
	
}
