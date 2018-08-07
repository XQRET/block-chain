package cn.inbs.blockchain.common.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @Description:加载配置文件bean
 * @Package:
 * @ClassName:
 * @Date: 2018年04月20-18:21
 * @Author: createBy:zhangmingyang
 * @deprecated
 **/
public class BusinessPropertyConfigurer extends PropertyPlaceholderConfigurer {
    private Properties properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.properties = props;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public Object setProperty(String key, String value) {
        return this.properties.setProperty(key, value);
    }
}
