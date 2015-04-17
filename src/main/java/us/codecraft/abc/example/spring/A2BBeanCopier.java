package us.codecraft.abc.example.spring;

import org.springframework.stereotype.Service;
import us.codecraft.abc.cglib.BeanCopier;
import us.codecraft.abc.example.beans.A;
import us.codecraft.abc.example.beans.B;

import javax.annotation.PostConstruct;

/**
 * @author yihua.huang@dianping.com
 *         Date: 15-4-17
 *         Time: 上午7:39
 */
@Service
public class A2BBeanCopier extends BeanCopier<A,B> {

	@PostConstruct
	public void init(){
		setSourceClass(A.class);
		setTargetClass(B.class);
		super.init();
	}

	@Override
	public B postCopy(A source, B target) {
		target.setF5("aaa");
		return target;
	}
}
