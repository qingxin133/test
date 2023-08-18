package com.ty.test;


import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyPoint implements MethodInterceptor {

    public TrainStation getProxyObject(Class<? extends TrainStation> trainStation) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer =new Enhancer();
        //设置父类的字节码对象
        enhancer.setSuperclass(trainStation);
        //设置回调函数
        enhancer.setCallback(this);
        //创建代理对象并返回
        return (TrainStation) enhancer.create();
    }

    // 测试
    public static void main(String[] args) {
        ProxyPoint proxy = new ProxyPoint();
        TrainStation guangzhouTrainStation = proxy.getProxyObject(new GuangzhouTrainStation().getClass());
        // 代售火车票收取5%手续费
        // 广州火车站卖票啦
        guangzhouTrainStation.sellTickets();
        TrainStation shenzhenTrainStation = proxy.getProxyObject(new ShenzhenTrainStation().getClass());
        // 代售火车票收取5%手续费
        // 深圳火车站卖票啦
        shenzhenTrainStation.sellTickets();
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代售火车票收取5%手续费");
        return methodProxy.invokeSuper(o, objects);
    }
}
