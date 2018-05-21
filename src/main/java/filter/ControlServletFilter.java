package filter;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SqlFragmentAlias;

import javax.servlet.annotation.WebFilter;
import java.lang.annotation.Annotation;

@WebFilter(urlPatterns = {"/"}, filterName="ControlServletFilter")
public class ControlServletFilter implements Filter  {

    @Override
    public String name() {
        return null;
    }

    @Override
    public String condition() {
        return null;
    }

    @Override
    public boolean deduceAliasInjectionPoints() {
        return false;
    }

    @Override
    public SqlFragmentAlias[] aliases() {
        return new SqlFragmentAlias[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
