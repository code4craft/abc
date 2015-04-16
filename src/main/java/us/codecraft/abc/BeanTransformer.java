package us.codecraft.abc;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public class BeanTransformer<F,T> extends BeanCopier<F,T> {

	@Override
	public T copy(F f, T t) {
		return postCopy(f, super.copy(f, t));
	}

	public T postCopy(F f, T t) {
		return t;
	}
}
