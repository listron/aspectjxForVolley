package com.example.zhangqiang.aspectjx;

import android.util.Log;

import org.apache.http.StatusLine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import com.szzc.minivolley.Request;
import java.nio.charset.Charset;

/**
 * Created by zhangqiang on 2016/11/5.
 */
@Aspect
public class TAspectJForVolley {
    @Pointcut("execution(* *..logSlowRequests(..))")
    public void onRequestPerfLog(){

    }

    @Before("onRequestPerfLog()")
    public void printRequestPerfLog(JoinPoint joinPoint){
        if (joinPoint != null){
            Object[] objects = joinPoint.getArgs();
            String requestUrl = "";
            Long duration = 0l;
            int timeOutLength = 0;
            int retryCount = 0;
            int statusCode = -1;
            int downBytesSize = 0;
            int upBytesSize = 0;
            if (objects[1] != null && objects[1] instanceof Request){
                Request request = (Request)objects[1];
                requestUrl = request.getUrl();
                retryCount = request.getRetryPolicy().getCurrentRetryCount();
                timeOutLength = request.getRetryPolicy().getCurrentTimeout();
                upBytesSize = request.getUrl().length();
            }
            if (objects[0] != null && objects[0] instanceof Long){
                duration = (Long)objects[0];
            }
            if (objects[3] != null && objects[3] instanceof StatusLine){
                StatusLine statusLine = (StatusLine)objects[3];
                statusCode = statusLine.getStatusCode();
            }
            if (objects[2] != null && objects[2] instanceof byte[]){
                byte[] responseContent = (byte[])objects[2];
                String response = new String(responseContent, Charset.forName("UTF-8"));
                downBytesSize = responseContent.length;
            }
            Log.d("bb","aga");
            Log.d("DemoAspect net",
                    "statusCode= " + statusCode + "\n" +
                            " duration= " + duration + "\n" +
                            " timeOutLength= " + timeOutLength + "\n" +
                            " retryCount= " + retryCount + "\n" +
                            " upBytesSize= " + upBytesSize + "\n" +
                            " downBytesSize= " + downBytesSize);
            //PerfCollectingService.runNetRequestAnalysis(PerfStalker.getInstance().getmContext(),requestUrl,duration,statusCode,retryCount);
        }
    }
}
