package temp.cloud.swiftnode.kspam.abstraction;

import temp.cloud.swiftnode.kspam.KSpam;
import temp.cloud.swiftnode.kspam.abstraction.deniable.DeniableInfoAdapter;
import temp.cloud.swiftnode.kspam.util.Config;
import temp.cloud.swiftnode.kspam.util.Lang;
import temp.cloud.swiftnode.kspam.util.Static;
import temp.cloud.swiftnode.kspam.util.Tracer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by EntryPoint on 2016-12-30.
 */
public abstract class SpamProcessor implements Processor {
    private DeniableInfoAdapter adapter;
    private Set<Checker> checkerList;
    private SpamExecutor executor;

    @SafeVarargs
    public SpamProcessor(DeniableInfoAdapter adapter, SpamExecutor executor, Class<? extends SpamChecker>... checker) {
        this.adapter = adapter;
        this.executor = executor;
        this.checkerList = new LinkedHashSet<>();
        addChecker(checker);
    }

    @SafeVarargs
    protected final void addChecker(Class<? extends SpamChecker>... classes) {
        for (Class<? extends SpamChecker> cls : classes) {
            try {
                SpamChecker checker = cls.getConstructor(DeniableInfoAdapter.class, SpamProcessor.class).newInstance(adapter, this);
                checkerList.add(checker);
            } catch (Exception ex) {
                throw new IllegalArgumentException(cls.getName() + " is not valid spam checker.");
            }
        }
    }

    public boolean process() {
        Tracer tracer = new Tracer();
        for (Checker checker : checkerList) {
            long time = System.currentTimeMillis();
            tracer.setLastChecker(checker);
            tracer.setLastProcessor(this);
            try {
                tracer.setResult(checker.check());
            } catch (Exception ex) {
                tracer.setResult(Tracer.Result.ERROR);
                ex.printStackTrace();
            }
            executor.execute(tracer, adapter, time);
            if (KSpam.INSTANCE.getConfig().getBoolean(Config.DEBUG_MODE, false)) {
                Static.consoleMsg(Lang.DEBUG.builder()
                        .addKey(Lang.Key.PROCESSOR_NAME, Lang.Key.EXECUTOR_NAME, Lang.Key.CHECKER_NAME, Lang.Key.CHECKER_RESULT, Lang.Key.TIME)
                        .addVal(this.name(), executor.name(), checker.name(), tracer.getResult(), System.currentTimeMillis() - time).prefix());
            }
            if (tracer.getResult() == Tracer.Result.FORCE_PASS ||
                    tracer.getResult() == Tracer.Result.DENY) {
                return true;
            } else if (tracer.getResult() == Tracer.Result.ERROR) {
                Static.consoleMsg(Lang.ERROR.builder()
                        .single(Lang.Key.CHECKER_NAME, checker.name())
                        .prefix()
                        .build());
            }
        }
        return false;
    }

    public SpamExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(SpamExecutor executor) {
        this.executor = executor;
    }


    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }
}
