package cn.xmrk.options.application;

import cn.xmrk.rkandroid.application.RKApplication;
import cn.xmrk.rkandroid.config.IRKConfig;

/**
 * Created by Au61 on 2016/8/4.
 */
public class OptionsApplication extends RKApplication{
    @Override
    public IRKConfig getRKConfig() {
        return new IRKConfig() {
            @Override
            public boolean isDebug() {
                return false;
            }

            @Override
            public String getBaseUrl() {
                return null;
            }

            @Override
            public boolean isLeakWatch() {
                return false;
            }

            @Override
            public int getNetTimeout() {
                return 0;
            }

            @Override
            public int getNetRetryCount() {
                return 0;
            }
        };
    }
}
