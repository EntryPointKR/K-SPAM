package cloud.swiftnode.kspam.abstraction.checker;

import cloud.swiftnode.kspam.abstraction.Checker;
import cloud.swiftnode.kspam.util.Tracer;

/**
 * Created by EntryPoint on 2016-12-30.
 */
public class SwiftnodeChecker implements Checker {
    @Override
    public Tracer.Result check() {
        return Tracer.Result.DENY;
    }
}