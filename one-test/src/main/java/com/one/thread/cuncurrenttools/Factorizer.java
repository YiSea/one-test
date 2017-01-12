package com.one.thread.cuncurrenttools;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName Factorizer.java
  * @author jun.wu  
  * @date 2017年1月6日 下午3:00:04 
  * @Description: 因式分解servlet中使用Memoizer来缓存结果
 */
public abstract class Factorizer implements Servlet {
	private final Computable<BigInteger, BigInteger[]> c =
			new Computable<BigInteger, BigInteger[]>() {
				public BigInteger[] compute(BigInteger arg){
					return factor(arg);// factor(arg);
				}

				private BigInteger[] factor(BigInteger arg) {
					return null;
				}
	};
	
	private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<BigInteger, BigInteger[]>(c);

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		try {
			encodeIntoResponse(res, cache.compute(i));
		} catch (InterruptedException e) {
			encodeError(res, "factorization interrupted");
			e.printStackTrace();
		}
		
	}

	private void encodeError(ServletResponse res, String string) {
		// TODO Auto-generated method stub
		
	}

	private void encodeIntoResponse(ServletResponse res, BigInteger[] compute) {
		// TODO Auto-generated method stub
		
	}

	private BigInteger extractFromRequest(ServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
