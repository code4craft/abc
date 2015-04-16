ABC
----
[![Build Status](https://api.travis-ci.org/code4craft/xsoup.png?branch=master)](https://travis-ci.org/code4craft/xsoup)

**A**nother **B**ean **C**opier.

## What's different

Just cover 80% cases with simpler usages.

1. Fields copying instead of Setter/Getter calling

2. Create new instance of target class instead of setting property of existing object

## Core Feature

### 1. Simple API to use.

```java
B b = BeanCopier.to(B.class).copy(a);
```

### 2. Compatible with Guava Function\<F,T>

```java
List<B> listB = Lists.transform(listA, BeanTransformer.<A,B>to(B.class));
```

### 3. 

## License

MIT License, see file `LICENSE`


