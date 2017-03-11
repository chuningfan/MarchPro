package org.march.utils.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> ifNullReturnEmpty(Collection<T> coll){
		return (Collection<T>) (coll != null ? coll : Collections.emptyList());
	}
	
	public static <T> List<T> array2List(T[] array){
		return Arrays.asList(array);
	}
	
	
}
