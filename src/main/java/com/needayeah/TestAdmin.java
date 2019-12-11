package com.needayeah;

/**
 * @author lixiaole
 * @date 2019-11-29
 */
public class TestAdmin {

    public static void main(String[] args) {
        // 使用默认单例（加载默认词典）
        SensitiveFilter filter = SensitiveFilter.DEFAULT;

        // 待过滤的句子
        String sentence = "然后，市长cao在婚礼上唱春天在哪里。";
        // 进行过滤
        String filted = filter.filter(sentence, '*');

        // 如果未过滤，则返回输入的String引用
        if(sentence != filted){
            // 句子中有敏感词
            System.out.println(filted);
        }
    }
}
