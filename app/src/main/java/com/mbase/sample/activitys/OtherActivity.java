package com.mbase.sample.activitys;

import android.graphics.Bitmap;

import com.mbase.monch.BaseApp;
import com.mbase.monch.utils.BitmapUtils;
import com.mbase.monch.utils.DateUtils;
import com.mbase.monch.utils.ListUtils;
import com.mbase.monch.utils.MapUtils;
import com.mbase.monch.utils.ResourceUtils;
import com.mbase.monch.utils.StringUtils;
import com.mbase.monch.utils.encrypt.MD5;
import com.mbase.monch.utils.encrypt.URLDecoderUtil;
import com.mbase.monch.utils.encrypt.URLEncoderUtil;
import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;
import com.mbase.monch.utils.size.Scale;
import com.mbase.monch.utils.toast.T;
import com.mbase.sample.BaseActivity;
import com.mbase.sample.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monch on 15/11/17.
 */
public class OtherActivity extends BaseActivity {

    private Loggers logger = LogManager.getLogger(OtherActivity.class);

    private String[] buttons = new String[]{"Toast弹出测试",
            "MD5加密测试",
            "Encoder/Decoder测试",
            "日志输出测试",
            "尺寸输出测试",
            "图片保存测试",
            "日期输出测试",
            "数组输出测试",
            "Map输出测试",
            "资源测试",
            "字符串测试"
    };

    @Override
    protected String[] getButtonTexts() {
        return buttons;
    }

    @Override
    protected void click(int id) {
        String name = buttons[id] + "：";
        switch (id) {
            case 0:
                logger.info(name + "Toast弹出测试");
                T.ss(name + "Toast弹出测试");
                break;
            case 1:
                String md5Source = "Test MD5";
                String md5Result = MD5.convert(md5Source);
                logger.info(name + "加密前：" + md5Source + "，加密后：" + md5Result);
                break;
            case 2:
                String paramSource = "我是加密前";
                String paramResult = URLEncoderUtil.encoder(paramSource, BaseApp.getCharset().displayName());
                logger.info(name + "加密前：" + paramSource + "，加密后：" + paramResult);
                String paramLast = URLDecoderUtil.decoder(paramResult);
                logger.info(name + "解密前：" + paramResult + "解密后：" + paramLast);
                break;
            case 3:
                logger.debug(name + "我是日志输出测试：debug");
                logger.debug(name + "我是日志输出测试：debug", new NullPointerException("我是异常"));
                logger.info(name + "我是日志输出测试：info");
                logger.info(name + "我是日志输出测试：info", new NullPointerException("我是异常"));
                logger.warn(name + "我是日志输出测试：warn");
                logger.warn(name + "我是日志输出测试：warn", new NullPointerException("我是异常"));
                logger.error(name + "我是日志输出测试：error");
                logger.error(name + "我是日志输出测试：error", new NullPointerException("我是异常"));
                break;
            case 4:
                int width = Scale.getDisplayWidth();
                int height = Scale.getDisplayHeight();
                ScaleClass scale = new ScaleClass();
                scale.displayWidth = width;
                scale.displayHeight = height;
                logger.info(name + scale);
                int dp2px = Scale.dip2px(10f);
                int px2dp = Scale.px2dip(10);
                int sp2px = Scale.sp2px(10f);
                int px2sp = Scale.px2sp(10);
                logger.info(name + "dip2px=" + dp2px + ",px2dip=" + px2dp
                        + ",sp2px=" + sp2px + ",px2sp=" + px2sp);
                break;
            case 5:
                Bitmap bitmap = BitmapUtils.drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher));
                File file = new File(BaseApp.getCacheDir(), "testBitmap.jpg");
                boolean saveBitmapResult = BitmapUtils.saveBitmap(bitmap, file);
                if (saveBitmapResult)
                    logger.info(name + "保存图片成功，路径：" + file.getAbsolutePath());
                else
                    logger.warn(name + "保存图片失败");
                break;
            case 6:
                logger.info(name + "当前日期时间：" + DateUtils.currentTime());
                logger.info(name + "当前日期：" + DateUtils.currentTime("yyyy-MM-dd"));
                logger.info(name + "当前时间：" + DateUtils.currentTime("HH:mm:ss.SSS"));
                logger.info(String.format(name + "当前日期时间2：%d年%d月%d日", DateUtils.currentYear(),
                        DateUtils.currentMonth(), DateUtils.currentDay()));
                break;
            case 7:
                List<Bean> listSource = new ArrayList<>();
                logger.info(name + "源：" + listSource);
                ListUtils.addElement(listSource, new Bean(0));
                logger.info(name + "添加0：" + listSource);
                ListUtils.removeElement(listSource, 0);
                logger.info(name + "移除0：" + listSource);
                for (int i = 0; i < 5; i++) {
                    ListUtils.addElement(listSource, new Bean(i));
                }
                logger.info(name + "添加...：" + listSource);
                break;
            case 8:
                Map<Integer, Bean> mapSource = new HashMap<>();
                logger.info(name + "源：" + mapSource);
                MapUtils.addElement(mapSource, 0, new Bean(0));
                logger.info(name + "添加0：" + mapSource);
                MapUtils.removeElement(mapSource, 0);
                logger.info(name + "移除0：" + mapSource);
                for (int i = 0; i < 5; i++) {
                    MapUtils.addElement(mapSource, i, new Bean(i));
                }
                logger.info(name + "添加...：" + mapSource);
                break;
            case 9:
                String resourceName = "ic_launcher";
                int resId = ResourceUtils.getMipmapResourceId(resourceName);
                logger.info(name + "资源" + resourceName + "ID为：" + resId);
                break;
            case 10:
                String text = "  我 是 测 试 字 符 串  ";
                String text2 = "  我 是 测 试 字 符 串  2";
                logger.info(name + "测试字符串为空" + StringUtils.isEmpty(text));
                logger.info(name + "测试字符串不为空？" + StringUtils.isNotEmpty(text));
                logger.info(name + "测试字符串去边空格？" + StringUtils.trim(text));
                logger.info(name + "测试字符串是否相等？" + StringUtils.equals(text, text2));
                logger.info(name + "测试字符串位置？" + StringUtils.indexOf(text, "测"));
                logger.info(name + "测试字符串最后位置？" + StringUtils.lastIndexOf(text, "测"));
                logger.info(name + "测试字符串是否包涵？" + StringUtils.contains(text, "测"));
                logger.info(name + "测试字符串截取？" + StringUtils.substring(text, 2, 7));
                logger.info(name + "测试字符串替换？" + StringUtils.replace(text, "测", "侧"));
                String text3 = "abC我dE是Fg1测23试4H字iJK符串lmN";
                logger.info(name + "测试字符串大写？" + StringUtils.upperCase(text3));
                logger.info(name + "测试字符串小写？" + StringUtils.lowerCase(text3));
                String text4 = "103982";
                logger.info(name + "测试字符串是否为数字？" + StringUtils.isNumber(text3));
                logger.info(name + "测试字符串是否为数字？" + StringUtils.isNumber(text4));
                logger.info(name + "测试字符串是否为数字？" + StringUtils.getNumber(text3));
                logger.info(name + "测试字符串是否为数字？" + StringUtils.getNumber(text4));
                break;
            default:
                logger.error("异常");
                break;
        }
    }

    static class ScaleClass {
        int displayWidth;
        int displayHeight;

        @Override
        public String toString() {
            return "ScaleClass{" +
                    "displayWidth=" + displayWidth +
                    ", displayHeight=" + displayHeight +
                    '}';
        }
    }

    static class Bean {
        int index;

        public Bean(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "index=" + index +
                    '}';
        }
    }

}
