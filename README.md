ABC
----
[![Build Status](https://api.travis-ci.org/code4craft/xsoup.png?branch=master)](https://travis-ci.org/code4craft/xsoup)

'A'nother 'B'ean 'C'opier.

## Core Feature

### 1. Simple API to use.

```java
B b = BeanCopier.to(B.class).copy(a);
```

### 2. Friendly with Guava Function<F,T>

```java
List<B> listB = Lists.transform(listA, BeanTransformer.<A,B>to(B.class));
```


## License

MIT License, see file `LICENSE`


