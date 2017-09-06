package com.quanshi.ums.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateUtil
{

    private boolean proxyEnable;

    private String proxyHost;

    private int proxyPort;

    private RestTemplate restTemplate;
    
    @Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;
    
    @Autowired
    private FormHttpMessageConverter formHttpMessageConverter;
    
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;

    public RestTemplate getRestTemplate()
    {
        if ( proxyEnable )
        {
            SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            Proxy proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( proxyHost, proxyPort ) );
            clientHttpRequestFactory.setProxy( proxy );
            restTemplate = new RestTemplate( clientHttpRequestFactory );

            ObjectMapper objectMapper = new ObjectMapper();
            // 输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
            // 只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串
            objectMapper.setSerializationInclusion( Include.NON_EMPTY );
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
            mappingJacksonHttpMessageConverter.setObjectMapper( objectMapper );
            messageConverters.add( new ByteArrayHttpMessageConverter() );
            messageConverters.add( new ResourceHttpMessageConverter() );
            messageConverters.add( new SourceHttpMessageConverter() );
            messageConverters.add( new AllEncompassingFormHttpMessageConverter() );
            messageConverters.add( new Jaxb2RootElementHttpMessageConverter() );
            messageConverters.add( fastJsonHttpMessageConverter );
            messageConverters.add( formHttpMessageConverter );
            messageConverters.add( stringHttpMessageConverter );
            StringHttpMessageConverter m = new StringHttpMessageConverter( Charset.forName( "UTF-8" ) );
            messageConverters.add( m );

            messageConverters.add( mappingJacksonHttpMessageConverter );
            restTemplate.setMessageConverters( messageConverters );
        }

        return restTemplate;
    }

    public boolean isProxyEnable()
    {
        return proxyEnable;
    }

    public void setProxyEnable( boolean proxyEnable )
    {
        this.proxyEnable = proxyEnable;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public void setProxyHost( String proxyHost )
    {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort( int proxyPort )
    {
        this.proxyPort = proxyPort;
    }

    public void setRestTemplate( RestTemplate restTemplate )
    {
        this.restTemplate = restTemplate;
    }

    public FastJsonHttpMessageConverter getFastJsonHttpMessageConverter()
    {
        return fastJsonHttpMessageConverter;
    }

    public void setFastJsonHttpMessageConverter( FastJsonHttpMessageConverter fastJsonHttpMessageConverter )
    {
        this.fastJsonHttpMessageConverter = fastJsonHttpMessageConverter;
    }

    public FormHttpMessageConverter getFormHttpMessageConverter()
    {
        return formHttpMessageConverter;
    }

    public void setFormHttpMessageConverter( FormHttpMessageConverter formHttpMessageConverter )
    {
        this.formHttpMessageConverter = formHttpMessageConverter;
    }

    public StringHttpMessageConverter getStringHttpMessageConverter()
    {
        return stringHttpMessageConverter;
    }

    public void setStringHttpMessageConverter( StringHttpMessageConverter stringHttpMessageConverter )
    {
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }
    
    

}
