package com.example.spring;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;

import java.net.URLDecoder;

public class SpringTest {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory;

        FactoryBean factoryBean;

        ApplicationContext applicationContext;


        String a = "https://s.logsss.com/_ubc.gif?glb_t=ic&glb_w=79966&glb_tm=1562917619174&glb_sk=D&glb_sckw=Party%C2%A0&glb_x=search&glb_plf=pc&glb_lang=en&glb_bts=%5B%7B%22plancode%22%3A%22shiyan%22%2C%22versionid%22%3A%222202%22%2C%22bucketid%22%3A%2268%22%2C%22planid%22%3A%22758%22%2C%22policy%22%3A%22B%22%7D%2C%7B%22plancode%22%3A%22keywordpc%22%2C%22versionid%22%3A%221922%22%2C%22bucketid%22%3A%2292%22%2C%22planid%22%3A%22651%22%2C%22policy%22%3A%22B%22%7D%5D&glb_oi=1ebbd04da3094dde1774ab55073e8e0b&glb_d=10013&glb_b=a&glb_p=p-15420&glb_dc=ZFAU&glb_od=100131558314770571m75lruk440837&glb_osr_referrer=originalurl&glb_osr_landing=https%3A%2F%2Fhk.zaful.com%2F&glb_cl=https%3A%2F%2Fau.zaful.com%2F&glb_ubcta=%7B%22at%22%3A%22566%22%7D";

        String ac = " " +  URLDecoder.decode(a, "UTF-8");


        System.out.println(a.trim().length());

    }

}
