package cloud.swiftnode.kspam.abstraction.processor;

import cloud.swiftnode.kspam.KSpam;
import cloud.swiftnode.kspam.abstraction.Processor;
import cloud.swiftnode.kspam.util.Lang;
import cloud.swiftnode.kspam.util.Static;
import cloud.swiftnode.kspam.util.StaticStorage;
import cloud.swiftnode.kspam.util.URLs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Junhyeong Lim on 2017-01-11.
 */
public class CacheInitProcessor implements Processor {
    @Override
    @SuppressWarnings("unchecked")
    public boolean process() {
        File dataFolder = KSpam.INSTANCE.getDataFolder();
        if (!dataFolder.isDirectory()) {
            dataFolder.mkdirs();
        }
        File file = new File(dataFolder, "K-Spam.cache");
        if (!file.isFile()) {
            try (FileOutputStream outStream = new FileOutputStream(file)) {
                URL url = URLs.CACHE.toUrl();
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                outStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (Exception ex) {
                Static.consoleMsg(ex);
            }
        }
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = inStream.readObject();
            if (obj instanceof HashSet) {
                Set<String> strSet = (Set<String>) obj;
                LinkedHashSet<String> treeSet = new LinkedHashSet<>();
                for (String str : strSet) {
                    treeSet.add(str);
                }
                StaticStorage.cachedSet = treeSet;
            } else {
                StaticStorage.cachedSet = (LinkedHashSet<String>) inStream.readObject();
            }
            Static.consoleMsg(Lang.CACHE_COUNT.builder()
                    .prefix().single(Lang.Key.CACHE_COUNT, StaticStorage.cachedSet.size()));
        } catch (Exception ex) {
            Static.consoleMsg(ex);
            return false;
        }
        return true;
    }
}
