package web.controller;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class XmlBeanFactory {
    HashMap<String, Controller> beans = new HashMap<>();

    public HashMap<String, Controller> getBeans() {
        return beans;
    }

    public Controller getBean(String key) {
        return beans.get(key);
    }

    public XmlBeanFactory(String path) throws Exception {
        //beans.xml 읽기
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(path, new MyDefaultHandler());

    }


    class MyDefaultHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            System.out.println("문서 읽기 시작");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            String id = attributes.getValue("id");
            String className = attributes.getValue("class");
            if(className!=null) {
                try {
                    Constructor c = Class.forName(className).getConstructor();
                    Object o = c.newInstance();
                    System.out.println(o);

                    beans.put(id, (Controller)o);

                }catch (InvocationTargetException e) {
                    // 실제 예외의 원인 출력
                    System.out.println("Error during object creation: " + e.getCause());
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println("<" + qName + "/>");
        }
    }

    public static void main(String[] args) throws Exception {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("c:\\Temp\\beans.xml");


    }
}
