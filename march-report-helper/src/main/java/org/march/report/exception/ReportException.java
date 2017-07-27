package org.march.report.exception;

/**
 * 
 * @author Vic Chu
 *
 */
public class ReportException extends Throwable {
	
	private static final long serialVersionUID = -7041479916514586228L;

	public ReportException(String msg){
		super("When Querying data for report, occurred an error! " + msg);
	}
	
}
