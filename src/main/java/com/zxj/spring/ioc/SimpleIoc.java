package com.zxj.spring.ioc;

import com.zxj.spring.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class SimpleIoc {
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleIoc.class);
    //存储bean的容器
    private HashMap<String, Object> iocContainer = new HashMap<>(16);
    private Document document;

    public void init() throws Exception {
        loadXml();
        Element root = document.getDocumentElement();
        NodeList beans = root.getElementsByTagName("bean");

        //遍历bean标签
        for (int i = 0; i < beans.getLength(); i++) {
            Node item = beans.item(i);
            if (item instanceof Element) {
                Element ele = (Element) item;
                String id = ele.getAttribute("id");
                String clazz_str = ele.getAttribute("class");

                Class clazz = null;
                try {
                    clazz = Class.forName(clazz_str);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    LOGGER.error("ClassNotFoundException :"+e.getMessage());
                }

                //创建bean
                Object bean = clazz.newInstance();

                //遍历property标签设置bean属性
                NodeList propertys = ele.getElementsByTagName("property");
                for (int j = 0; j < propertys.getLength(); j++) {
                    Node pro = propertys.item(j);
                    if (pro instanceof Element) {
                        Element proEle = (Element) pro;
                        String name = proEle.getAttribute("name");
                        String value = proEle.getAttribute("value");

                        //通过反射修改访问权限
                        Field field = bean.getClass().getDeclaredField(name);
                        field.setAccessible(true);

                        if (value != null && !value.equals("")) {
                            field.set(bean, value);
                        } else {
                            String ref = proEle.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            field.set(bean, getBean(ref));
                        }
                    }
                }
                //注册bean到容器
                registerBean(id, bean);
            }
        }
    }

    private void registerBean(String id, Object bean) {
        iocContainer.put(id, bean);
    }

    private Object getBean(String name) throws Exception {
        Object bean = iocContainer.get(name);
        if (bean == null) throw new Exception("无效的bean");
        return bean;
    }


    private void loadXml() {
        InputStream in = null;
        BufferedInputStream bin = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream("config/test.xml");
            bin = new BufferedInputStream(in);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            document = docBuilder.parse(bin);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    bin.close();
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        SimpleIoc simpleIoc = new SimpleIoc();
        simpleIoc.init();
        Person person = (Person)simpleIoc.getBean("person");
        System.out.println(person);
    }
}

