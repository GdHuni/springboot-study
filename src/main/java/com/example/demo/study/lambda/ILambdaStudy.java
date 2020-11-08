package com.example.demo.study.lambda;

public interface ILambdaStudy {
    void method();
}


/**一个参数无返回*/
@FunctionalInterface
interface NoReturnOneParam {
    void method(ILambdaStudy a);
}

/**多个参数有返回值*/
@FunctionalInterface
interface ReturnMultiParam {
    int method(int a, int b);
}

/*** 无参有返回*/
@FunctionalInterface
interface ReturnNoParam {
    int method();
}

/**一个参数有返回值*/
@FunctionalInterface
interface ReturnOneParam {
    int method(int a);
}
class Test{

    public Integer ParamTest(int a){
        return a;
    }

    public Integer ParamTest1(int a){
        return a;
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.ParamTest(1);

        NoReturnOneParam noReturnOneParam1 = a -> System.out.println("");


        //一个参数有返回值
        ReturnOneParam returnOneParam =  a -> {
            System.out.println("ReturnOneParam param:" + a);
            return 1;
        };

        int res2 = returnOneParam.method(6);
        System.out.println("return:" + res2);

        // 参数
        NoReturnOneParam noReturnOneParam = (a) -> System.out.println("");

        noReturnOneParam.method(() -> System.out.println());
    }

}