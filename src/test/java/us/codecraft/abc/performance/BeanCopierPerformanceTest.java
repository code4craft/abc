package us.codecraft.abc.performance;

import jodd.bean.BeanCopy;
import org.apache.commons.beanutils.BeanUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import us.codecraft.abc.reflection.BeanCopiers;
import us.codecraft.abc.reflection.copier.BeanCopier;

import java.util.Date;

/**
 * @author yihua.huang@dianping.com
 *         Date: 15-4-16
 *         Time: 下午11:49
 */
public class BeanCopierPerformanceTest {

	@Test
	public void testABC() throws Exception {
		A a = getA();
		long t1 = System.currentTimeMillis();
		BeanCopier<A, B> copier = BeanCopiers.<A, B>copier(A.class, B.class);
		for (int i=0;i<1000000;i++){
			B copy = copier.copy(a);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes "+(t2-t1));
	}

	@Test
	public void testJodd() throws Exception {
		A a = getA();
		long t1 = System.currentTimeMillis();
		for (int i=0;i<1000000;i++){
			B b = new B();
			BeanCopy beans = BeanCopy.beans(a, b);
			beans.copy();
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes "+(t2-t1));
	}

	@Test
	public void testCglib() throws Exception {
		A a = getA();
		long t1 = System.currentTimeMillis();
		net.sf.cglib.beans.BeanCopier beanCopier = net.sf.cglib.beans.BeanCopier.create(A.class, B.class, false);
		for (int i=0;i<1000000;i++){
			B b = new B();
			beanCopier.copy(a,b,null);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes " + (t2 - t1));
	}

	@Test
	public void testSet() throws Exception {
		A a = getA();
		long t1 = System.currentTimeMillis();
		for (int i=0;i<1000000;i++){
			B b = new B();
			b.setF1(a.getF1());
			b.setF2(a.getF2());
			b.setF3(a.getF3());
			b.setF4(a.getF4());
			b.setF5(a.getF5());
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes " + (t2 - t1));
	}

	@Test
	public void testBeanUtils() throws Exception {
		A a = getA();
		long t1 = System.currentTimeMillis();
		for (int i=0;i<1000000;i++){
			B b = new B();
			BeanUtils.copyProperties(a, b);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes " + (t2 - t1));
	}

	@Test
	public void testDozerMapper() throws Exception {
		A a = getA();
		Mapper mapper = new DozerBeanMapper();
		long t1 = System.currentTimeMillis();
		for (int i=0;i<100000;i++){
			B b = mapper.map(a, B.class);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("abc time takes " + (t2 - t1));
		for (int i=0;i<1000000;i++){
			B b = mapper.map(a, B.class);
		}
		long t3 = System.currentTimeMillis();
		System.out.println("abc time takes " + (t3 - t2));
	}

	private A getA() {
		A a = new A();
		a.setF1(1);
		a.setF2(1);
		a.setF3("aaaa");
		a.setF4(new Date());
		a.setF5("bbbb");
		return a;
	}

}
