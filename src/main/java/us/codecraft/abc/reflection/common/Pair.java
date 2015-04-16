package us.codecraft.abc.reflection.common;

/**
 * @author yihua.huang@dianping.com
 *         Date: 15-4-16
 *         Time: 下午11:31
 */
public class Pair<A,B> {

	private A a;

	private B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A getA() {
		return a;
	}

	public B getB() {
		return b;
	}
}
