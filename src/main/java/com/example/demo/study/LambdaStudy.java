package com.example.demo.study;

import com.example.demo.study.lambda.ILambdaStudy;

/**
 * lambda表达式学习
 */
public class LambdaStudy {

//https://www.cnblogs.com/haixiang/p/11029639.html

    public static void main(String[] args) {
        //无参无返回
        //语法形式为 () -> {}，其中 () 用来描述参数列表，{} 用来描述方法体，-> 为 lambda运算符 ，读作(goes to)
        ILambdaStudy noReturnNoParam = new ILambdaStudy() {
            @Override
            public void method() {
                System.out.println("");
            }
        };
        ILambdaStudy lambdaStudy = () -> System.out.println("");

        lambdaStudy.method();
        noReturnNoParam.method();



    }
}
