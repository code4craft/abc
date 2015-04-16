package us.codecraft.abc.cglib;

import com.google.common.base.Function;
import net.sf.cglib.beans.BeanCopier;

/**
 * 使用cglib做BeanCopier，并保留基本的扩展点
 * @author code4crafter@gmail.com
 *         Date: 15-4-17
 *         Time: 上午12:23
 */
public class SimpleBeanCopier<F,T> implements Function<F,T> {

	private BeanCopier beanCopier;

	private Class<T> targetClass;

	private Class<F> sourceClass;

	protected Class<T> getTargetClass() {
		return targetClass;
	}

	protected Class<F> getSourceClass() {
		return sourceClass;
	}

	public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass){
		this.beanCopier = BeanCopier.create(sourceClass, targetClass, false);
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}

	protected T postCopy(F from,T to){
		return to;
	}

	@Override
	public T apply(F input) {
		try {
			T o = targetClass.newInstance();
			beanCopier.copy(input,o,null);
			postCopy(input,o);
			return o;
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + targetClass.getName() + " ", e);
		}
	}
}
