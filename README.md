# 快速敏感词过滤




#### 主要的优化目标是速度，从以下方面优化：

1. 敏感词都是2个字以上的，
2. 对于句子中的一个位置，用2个字符的hash在稀疏的hash桶中查找，如果查不到说明一定不是敏感词，则继续下一个位置。
3. 2个字符（2x16位），可以预先组合为1个int（32位）的mix，即使hash命中，如果mix不同则跳过。
4. StringPointer，在不生成新实例的情况下计算任意位置2个字符的hash和mix
5. StringPointer，尽量减少实例生成和char数组的拷贝。

### 敏感词库

默认敏感词库拷贝自 https://github.com/observerss/textfilter ，并删除如`女人`、`然后`这样的几个常用词。
使用默认敏感词库的示例如下

```java
// 使用默认单例（加载默认敏感词库）
SensitiveFilter filter = SensitiveFilter.DEFAULT;
// 向过滤器增加一个词
filter.put("婚礼上唱春天在哪里");
	
// 待过滤的句子
String sentence = "然后，市长在婚礼上唱春天在哪里。";
// 进行过滤
String filted = filter.filter(sentence, '*');
	
// 如果未过滤，则返回输入的String引用
if(sentence != filted){
	// 句子中有敏感词
	System.out.println(filted);
}
```

打印结果

```
然后，**在*********。
```

### 依赖

JDK 1.7版本及以上


