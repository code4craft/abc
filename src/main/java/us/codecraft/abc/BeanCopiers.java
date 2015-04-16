package us.codecraft.abc;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public abstract class BeanCopiers {

	public static <A,B> BeanCopier copier(Class<A> clazzA,Class<B> clazzB){
		return new BeanCopier();
	}
}
