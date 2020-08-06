package com.lzy.chatmovie.rabbitmq;



import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "msg")
public class MsgListener {

    @Value("${aliyun.msg.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.msg.accessKeySecret}")
    private String accessKeySecret;
    @RabbitHandler
    public void executeMsg(Map<String,String> map)
    {
        String mobilePhone = map.get("mobile");
        String checkCode = map.get("checkcode");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobilePhone);
        request.putQueryParameter("SignName", "在线放映室");
        request.putQueryParameter("TemplateCode", "SMS_183155189");
        request.putQueryParameter("TemplateParam", "{checkcode:\""+checkCode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
