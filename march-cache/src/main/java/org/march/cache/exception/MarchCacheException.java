package org.march.cache.exception;

public class MarchCacheException extends Throwable {

	private static final String CACHE_EXC_KEY = "March cache error : ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5102161173362338256L;

	public MarchCacheException(String msg){
		super(msg);
	}

	@Override
	public String getMessage() {
		return CACHE_EXC_KEY + super.getMessage();
	}
	
	
}
