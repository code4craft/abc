package us.codecraft.abc.reflection;

import us.codecraft.abc.reflection.copier.BeanCopier;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public abstract class BeanCopiers {

	public static <A,B> BeanCopier<A,B> copier(Class<A> clazzA,Class<B> clazzB){
		return new BeanCopier(clazzA,clazzB);
	}
}
