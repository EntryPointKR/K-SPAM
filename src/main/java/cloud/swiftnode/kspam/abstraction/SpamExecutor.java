package cloud.swiftnode.kspam.abstraction;

/**
 * Created by Junhyeong Lim on 2017-01-10.
 */
public abstract class SpamExecutor implements Named {
    protected SpamChecker.Result lastResult;

    public abstract boolean execute(SpamProcessor processor, SpamChecker checker, Deniable deniable);

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    public SpamChecker.Result getLastResult() {
        return lastResult;
    }
}
