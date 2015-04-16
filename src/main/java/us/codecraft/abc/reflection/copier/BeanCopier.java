package us.codecraft.abc.reflection.copier;

import com.google.common.base.Function;
import us.codecraft.abc.reflection.common.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 *         Date: 15-4-16
 *         Time: 下午4:59
 */
public class BeanCopier<F, T> implements Function<F, T> {

	private Class<F> clazzFrom;

	private Class<T> clazzTo;

	private List<Pair<Field,Field>> fieldsToCopy;

	private boolean ignoreCase;

	public BeanCopier(Class<F> clazzFrom, Class<T> clazzTo) {
		this(clazzFrom, clazzTo, false);
	}

	public BeanCopier(Class<F> clazzFrom, Class<T> clazzTo,boolean ignoreCase) {
		this.clazzFrom = clazzFrom;
		this.clazzTo = clazzTo;
		this.ignoreCase = ignoreCase;
		compile();
	}

	private void compile(){
		Field[] declaredFieldsFrom = clazzFrom.getDeclaredFields();
		Field[] declaredFieldsTo = clazzTo.getDeclaredFields();
		fieldsToCopy = new ArrayList<Pair<Field, Field>>(Math.min(declaredFieldsFrom.length, declaredFieldsTo.length));
		Map<String,Field> declaredFieldsMapTo = new HashMap<String, Field>(declaredFieldsTo.length);
		for (Field field : declaredFieldsTo) {
			declaredFieldsMapTo.put(ignoreCase ? field.getName().toLowerCase() : field.getName(), field);
		}
		for (Field field : declaredFieldsFrom) {
			String fieldName = ignoreCase ? field.getName().toLowerCase() : field.getName();
			if (declaredFieldsMapTo.containsKey(fieldName)){
				Field fieldTo = declaredFieldsMapTo.get(fieldName);
				if (typeCompatible(field, fieldTo)){
					field.setAccessible(true);
					fieldTo.setAccessible(true);
					fieldsToCopy.add(new Pair<Field, Field>(field, fieldTo));
				}
			}
		}

	}

	private boolean typeCompatible(Field field, Field fieldTo) {
		return field.getType().equals(fieldTo.getType());
	}

	public T copy(F f,T t){
		for (Pair<Field, Field> fieldFieldPair : fieldsToCopy) {
			try {
				fieldFieldPair.getB().set(t, fieldFieldPair.getA().get(f));
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return t;
	}

	public T copy(F f){
		try {
			return copy(f, (T) clazzTo.newInstance());
		} catch (Exception e) {
			throw new RuntimeException("init class " + f.getClass() + "error", e);
		}
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}
}
