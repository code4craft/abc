package us.codecraft.abc;

import us.codecraft.abc.copier.BeanCopier;

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
