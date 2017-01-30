package cloud.swiftnode.ksecurity.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by EntryPoint on 2017-01-05.
 */
public enum Lang {
    PREFIX("&c[ K-SPAM ] &f"),
    ERROR("체커 " + Key.CHECKER_NAME + " 에서 에러가 발생했습니다."),
    DEBUG("&f프로세서 &e" + Key.PROCESSOR_NAME + " &f실행자 &e" + Key.EXECUTOR_NAME + " &f체커 &e" + Key.CHECKER_NAME + " &f정보 &e" + Key.INFO + " &f결과 &e" + Key.CHECKER_RESULT + " &f소요 시간 &e" + Key.TIME),
    DENY("&c[ K-SPAM ]\n" +
            "&f당신은 K-SPAM 에 의해 차단되었습니다."),
    CACHE_COUNT("&f캐쉬된 데이터: &e" + Key.CACHE_COUNT + " &f개"),
    INTRO("\n" +
            "\n" +
            " `7MMF' `YMM'      .M\"\"\"bgd `7MM\"\"\"Mq.   db      `7MMM.     ,MMF'\n" +
            "   MM   .M'       ,MI    \"Y   MM   `MM. ;MM:       MMMb    dPMM  \n" +
            "   MM .d\"         `MMb.       MM   ,M9 ,V^MM.      M YM   ,M MM  \n" +
            "   MMMMM.           `YMMNq.   MMmmdM9 ,M  `MM      M  Mb  M' MM  \n" +
            "   MM  VMA        .     `MM   MM      AbmmmqMA     M  YM.P'  MM  \n" +
            "   MM   `MM.      Mb     dM   MM     A'     VML    M  `YM'   MM  \n" +
            " .JMML.   MMb.    P\"Ybmmd\"  .JMML. .AMA.   .AMMA..JML. `'  .JMML." +
            "                                                                  \n" +
            " &c[K-SPAM Community Edition] &fv" + Key.KSPAM_VERSION + "\n\n" +
            " &fPlugin Contributors &eEntryPoint, horyu1234\n" +
            " &fK-SPAM DB Powered By &eSwiftnode\n" +
            " &f버그 제보/건의 &ehttps://github.com/EntryPointKR/K-SPAM/issues\n"),
    UPDATE_INFO_NEW("새 버전이 있습니다."),
    NEW_VERSION("&e최신버전: &f" + Key.NEW_VERSION),
    CURRENT_VERSION("&e현재버전: &f" + Key.KSPAM_VERSION),
    DOWNLOAD_URL("&e다운로드: &bhttps://github.com/EntryPointKR/K-SPAM/releases/latest"),
    EXCEPTION("예외가 발생했습니다. 개발자에게 문의해주세요. &bhttps://github.com/EntryPointKR/K-SPAM/issues &f본 메세지를 끄려면 /ksecurity alert 를 입력해주세요. &e정보: &f" + Key.EXCEPTION_MESSAGE),
    SET("&e" + Key.VALUE + " &f로 설정했습니다."),
    COMMAND_CHECK("&e" + Key.VALUE + " &f의 결과"),
    FIRST_LOGIN_KICK("&c[ K-SPAM ]\n" +
            "&f30 초 안에 다시 접속해주세요."),
    INVALID_IP("형식에 맞지 않는 IP 입니다."),
    DISABLED("경고! K-SPAM 플러그인이 종료되었습니다. config.yml 정책에 의해 서버와 함께 종료됩니다. 해당 옵션은 config.yml 에서 끌 수 있습니다."),
    FORCEMODE_ON("강제 모드가 활성화되어 당신은 차단되었습니다. 서버 관리자에게 문의해주세요."),
    /**
     * 본 프로젝트에 기여했을 경우 밑 메세지에 자신의 닉네임을 추가할 수 있습니다.
     */
    LAW_INFO("&c[ K-SPAM ] &f본 서버는 봇 테러 방지 플러그인 &eK-SPAM &f을 사용 중입니다.\n" +
             "&c[ K-SPAM ] &f기여자: §eEntryPoint, horyu1234\n" +
             "&c[ K-SPAM ] &fhttps://github.com/EntryPointKR/K-SPAM\n"),
    SMALL_CACHE("캐시 데이터의 수가 적습니다. 데이터의 수가 적으면 성능이 떨어져 의도한 것이 아니라면 &eplugins/K-Spam_Community_Edition/K-Spam.cache &f파일을 지운 후 리부팅해주세요."),
    SELF_DEFENCE("K-SPAM 플러그인의 비활성화 시도를 차단했습니다."),
    SOCKET_DETECTED("&e" + Key.PLUGIN_NAME + " &f에서 &c소켓 사용&f이 감지되었습니다."),
    SCAN_RESULT("총 &e" + Key.PLUGIN_COUNT + " &f개의 플러그인 검사 완료 &e(" + Key.TIME + "ms)&f, 의심 플러그인 수: " + Key.FIND_COUNT),
    SCAN_WARNING("소켓 사용이 감지된 플러그인들을 정상적인 경로(bukkit.org, spigotmc.org) 에서 다운로드 받았는지 확인하십시오."),
    SCAN_SAFE("위협 요소가 감지되지 않았습니다."),
    SCAN_START("플러그인 간편 검사가 시작됩니다."),
    TRY_NETWORKING("플러그인 &e" + Key.PLUGIN_NAME + " &f이 &e" + Key.VALUE + " &f로 네트워크 연결을 시도했습니다."),
    ;
    private final String msg;

    Lang(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return colorize(msg);
    }

    public MessageBuilder builder() {
        return new MessageBuilder(msg);
    }

    public String colorize(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public enum Key {
        CHECKER_NAME("checker-name"),
        PROCESSOR_NAME("processor-name"),
        CHECKER_RESULT("checker-result"),
        CACHE_COUNT("cache-count"),
        KSPAM_VERSION("ksecurity-version"),
        NEW_VERSION("new-version"),
        TIME("time"),
        EXCEPTION_MESSAGE("exception-message"),
        VALUE("value"),
        EXECUTOR_NAME("executor-name"),
        INFO("info"),
        PLUGIN_NAME("plugin-name"),
        FIND_COUNT("find-count"),
        PLUGIN_COUNT("plugin-count"),
        ;
        private final String key;

        Key(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "%" + key + "%";
        }
    }

    public class MessageBuilder {
        private List<Key> keyList = new ArrayList<>();
        private List<Object> valList = new ArrayList<>();
        private String target;

        MessageBuilder(String target) {
            this.target = target;
        }

        public MessageBuilder addKey(Lang.Key... keys) {
            keyList.addAll(Arrays.asList(keys));
            return this;
        }

        public MessageBuilder addVal(Object... vals) {
            valList.addAll(Arrays.asList(vals));
            return this;
        }

        public MessageBuilder single(Lang.Key key, Object val) {
            keyList.add(key);
            valList.add(val);
            return this;
        }

        public MessageBuilder prefix(String prefix) {
            target = prefix + target;
            return this;
        }

        public MessageBuilder prefix() {
            prefix(Lang.PREFIX.toString());
            return this;
        }

        public String build(boolean prefix) {
            if (prefix) {
                prefix();
            }
            String msg = target;
            for (int i = 0; i < keyList.size(); i++) {
                try {
                    String key = String.valueOf(keyList.get(i));
                    String val = String.valueOf(valList.get(i));
                    if (key == null || val == null) {
                        break;
                    }
                    msg = msg.replaceAll(key, Matcher.quoteReplacement(val));
                } catch (Exception ex) {
                    throw new RuntimeException(keyList + ", " + valList);
                }
            }
            return colorize(msg);
        }

        public String build() {
            return build(true);
        }
    }
}