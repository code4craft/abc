package us.codecraft.abc.reflection;

import us.codecraft.abc.reflection.copier.BeanCopier;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public class BeanTransformer<F,T> extends BeanCopier<F,T> {

	public BeanTransformer(Class<F> clazzFrom, Class<T> clazzTo) {
		super(clazzFrom, clazzTo);
	}

	@Override
	public T copy(F f, T t) {
		return doBusiness(f, super.copy(f, t));
	}

	public T doBusiness(F f, T t) {
		return t;
	}
}
