package us.codecraft.abc.cglib;

/**
 * 使用cglib做BeanCopier，并保留基本的扩展点
 * @author code4crafter@gmail.com
 *         Date: 15-4-17
 *         Time: 上午12:23
 */
public class ConfigurableBeanCopier<F,T> extends BeanCopier<F,T> {


	public void setTargetClassName(String targetClass) throws ClassNotFoundException {
		setTargetClass((Class<T>) Class.forName(targetClass));
	}

	public void setSourceClassName(String sourceClass) throws ClassNotFoundException {
		setSourceClass((Class<F>) Class.forName(sourceClass));
	}

}
