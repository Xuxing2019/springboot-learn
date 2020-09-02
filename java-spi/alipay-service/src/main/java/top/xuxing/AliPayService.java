package top.xuxing;

/**
 * @description: TODO 
 * @author xu.h.b
 * @date 2020/9/2 20:57
 */
public class AliPayService implements PayInterface{
    @Override
    public void pay() {
        System.out.println("阿里支付");
    }
}
