ABC
----

**A**nother **B**ean **C**opier.

*本来想自己用反射来做一个，因为cglib使用了动态字节码生成，所以效率比反射要高，参考了一些工具，决定直接在cglib的BeanCopier上改了*

## 特点

本着为90%的使用场景，提供更便捷和直观的功能的思想，我又造了一个轮子。它有以下特点：

### 1. 创建新对象的功能

90%情况，我们都会在拷贝前创建对象。把两行代码变为一行代码，并且内聚性更高。


```java
B b = new B();
BeanCopier.create(classFrom, classTo, false).copy(a,b,null);
```

```java
B b = new SimpleBeanCopier(A.class,B.class).copy(a);
```

### 2. 性能更好

BeanCopy是一个比较常用的操作，我也自己通过反射实现了一个BeanCopy，经过调研，cglib的性能是最好的。

	1,000,000 round
	jdk set/get takes 17ms
	cglib takes 117ms
	abc(reflection version) takes 496ms
	jodd takes 5309ms
	apche beanutils takes 6264ms

### 3. 规划化业务

我们一般在BeanCopy有两种场景：

1. 简单的field-copy，如无特殊需要，我们希望两个Bean字段名称相同
2. 穿插业务逻辑，例如新对象的某些属性，需要额外的数据源取得

下面是用Spring实现一个带业务的BeanCopy的过程：

```java
@Service
public class A2BBeanCopier extends BeanCopier<A,B> {

	@PostConstruct
	public void init(){
		setSourceClass(A.class);
		setTargetClass(B.class);
		super.init();
	}

	@Override
	public B postCopy(A source, B target) {
		target.setF5("aaa");
		return target;
	}
}
```

### 4. 兼容Guava的Function\<F,T>

一个集合的转换可以变得非常简单：

```java
BeanCopier<A, B> beanCopier = new SimpleBeanCopier<A, B>(A.class, B.class);
listB = Lists.transform(listA, beanCopier);
listA = Lists.transform(listB, beanCopier.reverse());
```

## License

MIT License, see file `LICENSE`


