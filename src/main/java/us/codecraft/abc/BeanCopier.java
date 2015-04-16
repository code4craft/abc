package us.codecraft.abc;

import com.google.common.base.Function;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public class BeanCopier<F, T> implements Function<F, T> {

	public T copy(F f,T t){
		return null;
	}

	public T copy(F f){
		try {
			return copy(f, (T) f.getClass().newInstance());
		} catch (Exception e) {
			throw new RuntimeException("init class " + f.getClass() + "error", e);
		}
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}
}
