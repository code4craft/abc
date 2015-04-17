package us.codecraft.abc.cglib;

/**
 * 使用cglib做BeanCopier，并保留基本的扩展点
 * @author code4crafter@gmail.com
 *         Date: 15-4-17
 *         Time: 上午12:23
 */
public class SimpleBeanCopier<F,T> extends BeanCopier<F,T> {


	public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass){
		setSourceClass(sourceClass);
		setTargetClass(targetClass);
		init();
	}

}
