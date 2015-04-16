ABC
----
[![Build Status](https://api.travis-ci.org/code4craft/xsoup.png?branch=master)](https://travis-ci.org/code4craft/xsoup)

**A**nother **B**ean **C**opier.

*参考了一些工具，决定直接在cglib的BeanCopier上改了*

## 有何不同？

本着为90%的使用场景，提供更便捷和直观的功能的思想，我又造了一个轮子。它有以下特点：

### 1. 创建新对象的功能

90%情况，我们都会在拷贝前创建对象。把两行代码变为一行代码，并且内聚性更高。

*在cglib中发现了Generator这种东西，看来大家都想到了。*

```java
B b = new B();
BeanCopy.beans(a,b).copy();
```

```java
B b = BeanCopiers.copier(A.class,B.class).copy(a);
```

### 2. 性能更好

BeanCopy是一个比较常用的操作，通过合理的利用反射，ABC能够达到一个比较高的性能。但是对比cglib的字节码织入还是败下阵来！算了不做了。

	1,000,000 round
	jdk set/get takes 17ms
	cglib time takes 117ms
	abc time takes 496ms
	jodd time takes 5309ms
	apche beanutils time takes 6264ms

### 3. 规划化业务

我们一般在BeanCopy有两种场景：

1. 简单的field-copy，如无特殊需要，我们希望两个Bean字段名称相同
2. 穿插业务逻辑，例如新对象的某些属性，需要额外的数据源取得

### 3. 兼容Guava的Function\<F,T>和Optional\<T>

一个集合的转换可以变得非常简单：

```java
List<B> listB = Lists.transform(listA, BeanTransformer.<A,B>copier(A.class,B.class));
```

## License

MIT License, see file `LICENSE`


