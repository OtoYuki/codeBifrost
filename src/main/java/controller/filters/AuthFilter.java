package controller.filters;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public class AuthFilter implements Filter<Object> {
	public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

	@Override
	public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
}
