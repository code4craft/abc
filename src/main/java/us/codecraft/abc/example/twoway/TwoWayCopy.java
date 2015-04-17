package us.codecraft.abc.example.twoway;

import com.google.common.collect.Lists;
import us.codecraft.abc.cglib.BeanCopier;
import us.codecraft.abc.cglib.SimpleBeanCopier;
import us.codecraft.abc.example.beans.A;
import us.codecraft.abc.example.beans.B;

import java.util.List;

/**
 * @author yihua.huang@dianping.com
 *         Date: 15-4-17
 *         Time: 上午8:18
 */
public class TwoWayCopy {

	public static void main(String[] args) {
		List<A> listA;
		List<B> listB;
		listA = Lists.newArrayList();
		listB = Lists.newArrayList();

		BeanCopier<A, B> beanCopier = new SimpleBeanCopier<A, B>(A.class, B.class);
		listB = Lists.transform(listA, beanCopier);
		listA = Lists.transform(listB, beanCopier.reverse());
	}
}
