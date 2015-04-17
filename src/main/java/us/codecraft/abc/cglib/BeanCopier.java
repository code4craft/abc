package us.codecraft.abc.cglib;

import com.google.common.base.Function;

/**
 * @author yihua.huang@dianping.com
 *         Date: 15-4-17
 *         Time: 上午8:06
 */
public class BeanCopier<F,T> implements Function<F,T> {

	private net.sf.cglib.beans.BeanCopier beanCopier;

	protected net.sf.cglib.beans.BeanCopier getBeanCopier() {
		return beanCopier;
	}

	protected void init(){
		this.beanCopier = net.sf.cglib.beans.BeanCopier.create(sourceClass, targetClass, false);
	}

	private Class<T> targetClass;

	private Class<F> sourceClass;

	private BeanCopier<T,F> reverse;

	public BeanCopier<T, F> reverse() {
		return reverse;
	}

	public Function<T, F> getReverse() {
		if (this.reverse != null) {
			return this.reverse;
		}
		BeanCopier<T, F> reverse = this.reverse;
		synchronized (this) {
			if (reverse == null) {
				reverse = new BeanCopier<T, F>();
				reverse.setSourceClass(this.getTargetClass());
				reverse.setTargetClass(this.getSourceClass());
				reverse.init();
			}
		}
		return reverse;
	}

	public void setReverse(BeanCopier<T, F> reverse) {
		this.reverse = reverse;
	}

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

	public T postCopy(F source,T target){
		return target;
	}

	public T copy(F input) {
		try {
			T o = targetClass.newInstance();
			beanCopier.copy(input, o, null);
			return postCopy(input,o);
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + targetClass.getName() + " ", e);
		}
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}
}
