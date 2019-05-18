package com.homeworks.homework20.beanpostprocessor;

import com.homeworks.homework20.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class UserBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER  = LoggerFactory.getLogger(UserBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            LOGGER .info("In postProcessBeforeInitialization " + bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            LOGGER .info("In postProcessAfterInitialization " + bean);
        }
        return bean;
    }
}
