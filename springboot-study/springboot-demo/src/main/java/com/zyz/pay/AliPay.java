package com.zyz.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.zyz.utils.AliPayConfig;
import com.zyz.utils.AlipayRefund;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zyz
 * @date 2018/7/27
 */
public class AliPay {

/**    ------------------支付---------------------------------------------------------------------------------------------*/
    public String aliPay(String number, double money, HttpServletRequest request, HttpServletResponse response) {
        String payUrl = null;//返回给前台请求支付的url
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AliPayConfig.APP_ID, AliPayConfig.APP_PRIVATE_KEY, "JSON", AliPayConfig.CHARSET, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();
        AlipayTradePayRequest request1 = new AlipayTradePayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("HONGLINCHEN");
        model.setSubject("HONGLINCHEN支付宝APP支付测试Java");
        model.setOutTradeNo(number);//商户网站唯一订单号
        model.setTimeoutExpress("30m");//设置超时时间
        model.setTotalAmount(String.valueOf(money));//设置支付金额
        model.setProductCode("QUICK_MSECURITY_PAY");
        aliPayRequest.setBizModel(model);
        aliPayRequest.setNotifyUrl("http://www.aidongsports.com/ali");//回调地址
        try {
            AlipayTradeAppPayResponse aliPayResponse = alipayClient.sdkExecute(aliPayRequest);
            payUrl = aliPayResponse.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return payUrl;
    }

    /**
     * @Author: zyz
     * @Description: 支付宝退款 退款的金额 不能大于交易金额
     * store_id 可选 代表 商户的门店编号
     * terminal_id 可选 代表 商户的终端编号
     * operator_id 可选 代表 商户的操作员编号
     * refund_reason 可选 代表 退款的原因说明
     * out_request_no 可选 标识一次退款请求，同一笔交易多次退款需要保证唯一（就是out_request_no在2次退款一笔交易时，要不一样），如需部分退款，则此参数必传
     * trade_no 必须 支付宝交易号
     * out_trade_no 必须 商户订单号
     * refund_amount 必须 退款金额
     * @Date: 2017-9-12 15:37
     */
    @RequestMapping(value = "aliPayRefund", method = {RequestMethod.GET, RequestMethod.POST})
    public String alipayRefundRequest() throws AlipayApiException, IllegalAccessException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                AliPayConfig.APP_ID, AliPayConfig.APP_PRIVATE_KEY, "json", "UTF-8",
                AliPayConfig.ALIPAY_PUBLIC_KEY, "RSA");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        如果不想自己封装类，可以用支付宝提供的AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        AlipayRefund alipayRefund= new AlipayRefund();
        alipayRefund.setOut_trade_no("316594250940");//这个是商户的订单号，Y
        alipayRefund.setTrade_no("2017091221001004040242043928");//这个是支付宝的订单号，Y
        alipayRefund.setRefund_amount("0.50");//退款金额，Y
        alipayRefund.setRefund_reason("正常退款");//退款说明，N
        alipayRefund.setOut_request_no("HZ01RF001");
        alipayRefund.setOperator_id("OP001");
        alipayRefund.setStore_id("NJ_S_001");
        alipayRefund.setTerminal_id("NJ_T_001");
        request.setBizContent("把alipayRefund弄成json");//2个都可以，这个参数的顺序 不影响退款
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return "退款成功";
    }

}
