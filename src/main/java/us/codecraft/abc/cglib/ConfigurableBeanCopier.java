package us.codecraft.abc.cglib;

import com.google.common.base.Function;
import net.sf.cglib.beans.BeanCopier;

/**
 * 使用cglib做BeanCopier，并保留基本的扩展点
 * @author code4crafter@gmail.com
 *         Date: 15-4-17
 *         Time: 上午12:23
 */
public class ConfigurableBeanCopier<F,T> implements Function<F,T> {

	private BeanCopier beanCopier;

	private Class<T> targetClass;

	private Class<F> sourceClass;

	protected Class<T> getTargetClass() {
		return targetClass;
	}

	protected Class<F> getSourceClass() {
		return sourceClass;
	}

	public void setTargetClass(Class<T> targetClass) {
		this.targetClass = targetClass;
	}

	public void setSourceClass(Class<F> sourceClass) {
		this.sourceClass = sourceClass;
	}

	public void setTargetClassName(String targetClass) throws ClassNotFoundException {
		this.targetClass = (Class<T>) Class.forName(targetClass);
	}

	public void setSourceClassName(String sourceClass) throws ClassNotFoundException {
		this.sourceClass = (Class<F>) Class.forName(sourceClass);
	}

	public T postCopy(F source,T target){
		return target;
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
