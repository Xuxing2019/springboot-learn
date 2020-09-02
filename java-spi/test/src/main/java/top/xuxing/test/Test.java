package top.xuxing.test;

import top.xuxing.PayInterface;
import java.util.ServiceLoader;

/**
 * @description: TODO 
 * @author xu.h.b
 * @date 2020/9/2 21:01
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<PayInterface> serviceLoader = ServiceLoader.load(PayInterface.class);
        for (PayInterface p:serviceLoader) {
            System.out.println("p = " + p);
        }
    }
}
