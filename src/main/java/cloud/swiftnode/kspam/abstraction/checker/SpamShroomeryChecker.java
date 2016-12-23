package cloud.swiftnode.kspam.abstraction.checker;

import cloud.swiftnode.kspam.abstraction.SpamChecker;
import cloud.swiftnode.kspam.abstraction.convertor.ShroomeryResultConvertor;
import cloud.swiftnode.kspam.storage.SpamStorage;
import cloud.swiftnode.kspam.util.Result;
import cloud.swiftnode.kspam.util.Static;
import cloud.swiftnode.kspam.util.Type;
import cloud.swiftnode.kspam.util.URLs;

import java.net.URL;

/**
 * Created by EntryPoint on 2016-12-21.
 */
public class SpamShroomeryChecker extends SpamChecker {
    public SpamShroomeryChecker(SpamStorage storage) {
        super(storage);
    }

    @Override
    public boolean check() {
        if (storage.getResult() == Result.TRUE) {
            return true;
        }
        try {
            URL url = URLs.SHROOMERY_API.toUrl(storage.getIp());
            String text = Static.readAllText(url);
            storage.setResult(new ShroomeryResultConvertor(text).convert());
            storage.setType(Type.SHROOMERY);
        } catch (Exception ex) {
            // Ignore
        }
        return true;
    }
}
