package top.xuxing;

/**
 * @description: TODO 
 * @author xu.h.b
 * @date 2020/9/2 20:59
 */
public class WeChatPayService implements PayInterface{
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
